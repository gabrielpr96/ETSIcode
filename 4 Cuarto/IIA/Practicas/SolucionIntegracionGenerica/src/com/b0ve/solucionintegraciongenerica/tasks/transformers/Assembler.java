package com.b0ve.solucionintegraciongenerica.tasks.transformers;

import com.b0ve.solucionintegraciongenerica.flow.Message;
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

public final class Assembler extends AssemblerTemplate {

    private final String rootName;

    public Assembler(String rootName) {
        super();
        this.rootName = rootName;
    }

    @Override
    protected Document join(Message[] mensajes) {
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = dbf.newDocumentBuilder();
            Document doc = builder.newDocument();
            Element root = doc.createElement(rootName);
            doc.appendChild(root);
            for (Message mensaje : mensajes) {
                Node newChild = Message.document2node(mensaje.getBody());
                Node imported = doc.importNode(newChild, true);
                root.appendChild(imported);
            }
            return doc;
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(Assembler.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

}
