package com.b0ve.solucionintegraciongenerica.tasks.transformers;

import com.b0ve.solucionintegraciongenerica.flow.Message;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

public final class Splitter extends SplitterTemplate {

    private final String xpath;

    public Splitter(String xpath) {
        super();
        this.xpath = xpath;
    }

    @Override
    protected Document[] split(Message mensaje) {
        try {
            NodeList lista = mensaje.evaluateXPath(xpath);
            Document[] partes = new Document[lista.getLength()];
            for (int i = 0; i < lista.getLength(); i++) {
                partes[i] = Message.node2document(lista.item(i));
            }
            return partes;
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(Splitter.class.getName()).log(Level.SEVERE, null, ex);
            return new Document[0];
        }
    }

}
