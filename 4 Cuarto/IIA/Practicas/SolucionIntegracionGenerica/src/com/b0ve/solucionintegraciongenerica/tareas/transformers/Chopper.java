package com.b0ve.solucionintegraciongenerica.tareas.transformers;

import com.b0ve.solucionintegraciongenerica.utils.flujo.Mensaje;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import org.w3c.dom.NodeList;

public final class Chopper extends ChopperTemplate {

    private final String xpath;

    public Chopper(String xpath) {
        super();
        this.xpath = xpath;
    }

    @Override
    protected String[] split(Mensaje mensaje) {
        try {
            NodeList lista = mensaje.evaluateXPath(xpath);
            String[] partes = new String[lista.getLength()];
            for (int i = 0; i < lista.getLength(); i++) {
                partes[i] = Mensaje.serialiceXML(Mensaje.node2document(lista.item(i)));
            }
            return partes;
        } catch (TransformerException | ParserConfigurationException ex) {
            Logger.getLogger(Chopper.class.getName()).log(Level.SEVERE, null, ex);
            return new String[0];
        }
    }

}
