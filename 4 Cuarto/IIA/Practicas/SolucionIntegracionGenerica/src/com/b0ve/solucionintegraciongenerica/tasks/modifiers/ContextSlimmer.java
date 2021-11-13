package com.b0ve.solucionintegraciongenerica.tasks.modifiers;

import com.b0ve.solucionintegraciongenerica.flow.Message;
import com.b0ve.solucionintegraciongenerica.utils.exceptions.XPathEvaluationException;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

public class ContextSlimmer extends ContextSlimmerTemplate {

    @Override
    protected void slim(Message m, Message condition) throws XPathEvaluationException {
        Document doc = m.getBody();
        NodeList conditions = condition.evaluateXPath("/list/item");
        for (int j = 0; j < conditions.getLength(); j++) {
            NodeList nodes = Message.evaluateXPath(doc, conditions.item(j).getTextContent());
            for (int i = 0; i < nodes.getLength(); i++) {
                nodes.item(i).getParentNode().removeChild(nodes.item(i));
            }
        }
    }

}
