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
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public final class Aggregator extends AggregatorTemplate {

    private final String rootName;

    public Aggregator(String rootName) {
        super();
        this.rootName = rootName;
    }

    @Override
    protected String join(Mensaje[] mensajes) {
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = dbf.newDocumentBuilder();
            Document doc = builder.newDocument();
            Element root = doc.createElement(rootName);
            doc.appendChild(root);
            for (Mensaje mensaje : mensajes) {
                Node newChild = Mensaje.document2node(Mensaje.parseXML(mensaje.getBody()));
                Node imported = doc.importNode(newChild, true);
                root.appendChild(imported);
            }
            return Mensaje.serialiceXML(doc);
        } catch (ParserConfigurationException | TransformerException | SAXException | IOException ex) {
            Logger.getLogger(Aggregator.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

}
