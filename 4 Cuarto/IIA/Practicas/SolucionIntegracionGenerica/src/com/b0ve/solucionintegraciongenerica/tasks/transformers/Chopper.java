package com.b0ve.solucionintegraciongenerica.tasks.transformers;

import com.b0ve.solucionintegraciongenerica.flow.Message;
import com.b0ve.solucionintegraciongenerica.utils.exceptions.SIGException;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

public final class Chopper extends ChopperTemplate {

    private final String xpath;

    public Chopper(String xpath) {
        super();
        this.xpath = xpath;
    }

    @Override
    protected Document[] chop(Message mensaje) throws SIGException {
        NodeList lista = mensaje.evaluateXPath(xpath);
        Document[] partes = new Document[lista.getLength()];
        for (int i = 0; i < lista.getLength(); i++) {
            partes[i] = Message.node2document(lista.item(i));
        }
        return partes;
    }

}
