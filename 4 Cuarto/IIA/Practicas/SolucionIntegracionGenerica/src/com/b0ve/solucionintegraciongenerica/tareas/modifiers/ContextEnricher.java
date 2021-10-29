package com.b0ve.solucionintegraciongenerica.tareas.modifiers;

import com.b0ve.solucionintegraciongenerica.utils.flujo.Mensaje;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import org.xml.sax.SAXException;

public class ContextEnricher extends ContextEnricherTemplate {


    @Override
    protected void enrich(Mensaje mensaje, Mensaje condicion){
        try {
            //System.out.println("Enrich "+mensaje.evaluateXPath("/drink/name").item(0).getTextContent());
            mensaje.setBody(Mensaje.mergeXML(mensaje.getBody(), condicion.getBody()));
        } catch (ParserConfigurationException | SAXException | IOException | TransformerException ex) {
            Logger.getLogger(ContextEnricher.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
