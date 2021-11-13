package com.b0ve.solucionintegraciongenerica.tasks.modifiers;

import com.b0ve.solucionintegraciongenerica.flow.Message;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import org.xml.sax.SAXException;

public class ContextEnricher extends ContextEnricherTemplate {


    @Override
    protected void enrich(Message mensaje, Message condicion){
        try {
            mensaje.setBody(Message.mergeXML(mensaje.getBody(), condicion.getBody()));
        } catch (ParserConfigurationException | SAXException | IOException | TransformerException ex) {
            Logger.getLogger(ContextEnricher.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
