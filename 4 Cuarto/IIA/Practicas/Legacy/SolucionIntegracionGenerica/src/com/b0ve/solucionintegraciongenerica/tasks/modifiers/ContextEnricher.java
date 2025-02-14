package com.b0ve.solucionintegraciongenerica.tasks.modifiers;

import com.b0ve.solucionintegraciongenerica.flow.Message;
import com.b0ve.solucionintegraciongenerica.utils.exceptions.SIGException;

/**
 * Merges the body of two messages
 * @author borja
 */
public class ContextEnricher extends ContextEnricherTemplate {

    @Override
    protected void enrich(Message m, Message condition) throws SIGException {
        m.setBody(Message.mergeXML(m.getBody(), condition.getBody()));
    }

}
