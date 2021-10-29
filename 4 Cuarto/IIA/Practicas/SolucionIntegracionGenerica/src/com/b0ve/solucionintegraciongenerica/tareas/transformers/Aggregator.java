package com.b0ve.solucionintegraciongenerica.tareas.transformers;

import com.b0ve.solucionintegraciongenerica.utils.flujo.Mensaje;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

public final class Aggregator extends AggregatorTemplate {

    private final Object rootName;

    public Aggregator(Object rootName) {
        super();
        this.rootName = rootName;
    }

    @Override
    protected String join(Mensaje[] mensajes) {
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = dbf.newDocumentBuilder();
            Document doc = builder.newDocument();
            Element appendPoint = null;
            if(rootName instanceof String){
                appendPoint = doc.createElement((String) rootName);
                doc.appendChild(appendPoint);
            }else if(rootName instanceof String[]){
                String[] rootNames = (String[]) rootName;
                for (String name : rootNames) {
                    Element newPoint = doc.createElement(name);
                    if(appendPoint == null){
                        doc.appendChild(newPoint);
                    }else{
                        appendPoint.appendChild(newPoint);
                    }
                    appendPoint = newPoint;
                }
            }else{
                appendPoint = doc.createElement("list");
                doc.appendChild(appendPoint);
            }
            for (Mensaje mensaje : mensajes) {
                Node newChild = Mensaje.document2node(Mensaje.parseXML(mensaje.getBody()));
                Node imported = doc.importNode(newChild, true);
                appendPoint.appendChild(imported);
            }
            return Mensaje.serialiceXML(doc);
        } catch (ParserConfigurationException | TransformerException | SAXException | IOException ex) {
            Logger.getLogger(Aggregator.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

}
