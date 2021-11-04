package com.b0ve.solucionintegraciongenerica.utils.flujo;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import net.sf.saxon.s9api.Processor;
import net.sf.saxon.s9api.SaxonApiException;
import net.sf.saxon.s9api.Serializer;
import net.sf.saxon.s9api.Xslt30Transformer;
import net.sf.saxon.s9api.XsltCompiler;
import net.sf.saxon.s9api.XsltExecutable;
import org.atteo.xmlcombiner.XmlCombiner;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class Mensaje {

    private final long ID, sequenceID;
    private final int sequenceSize;
    private int correlationID;
    private String body;
    private static int counter = 0;

    public Mensaje(long ID, int correlationID, String body, long sequenceID, int sequenceSize) {
        this.ID = ID;
        this.correlationID = correlationID;
        this.body = body;
        this.sequenceID = sequenceID;
        this.sequenceSize = sequenceSize;
    }

    public Mensaje(int ID, int correlationID, String body) {
        this(ID, correlationID, body, 0, 0);
    }

    public Mensaje(String body, int correlationID) throws TransformerException {
        this(counter++, correlationID, body);
    }

    public Mensaje(Document body, int correlationID) throws TransformerException {
        this(counter++, correlationID, serialiceXML(body));
    }

    public Mensaje(Document body) throws TransformerException {
        this(serialiceXML(body));
    }

    public Mensaje(String body) {
        this(counter, counter, body, 0, 0);
        counter++;
    }

    public Mensaje(String body, int sequenceID, int sequenceSize) {
        this(counter, counter, body, sequenceID, sequenceSize);
        counter++;
    }

    public Mensaje(Mensaje m) {
        this(counter++, m.correlationID, m.body, m.sequenceID, m.sequenceSize);
    }

    public long getID() {
        return ID;
    }

    public int getCorrelationID() {
        return correlationID;
    }

    public String getBody() {
        return body;
    }

    public long getSequenceID() {
        return sequenceID;
    }

    public int getSequenceSize() {
        return sequenceSize;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setCorrelationID(int correlationID) {
        this.correlationID = correlationID;
    }

    public NodeList evaluateXPath(String expresion) {
        try {
            return evaluateXPath(body, expresion);
        } catch (ParserConfigurationException | SAXException | IOException | XPathExpressionException ex) {
            return null;
        }
    }

    public String evaluateXPathString(String expresion) {
        NodeList res = evaluateXPath(expresion);
        if (res == null) {
            return null;
        }
        return res.item(0).getTextContent();
    }

    public void transformBody(String style) {
        try {
            Source xslt = new StreamSource(new StringReader(style)); //El XLT con el formato
            Source text = new StreamSource(new StringReader(body)); //El XML con los datos
            StringWriter outWriter = new StringWriter();

            Processor processor = new Processor(false);
            XsltCompiler compiler = processor.newXsltCompiler();
            XsltExecutable stylesheet = compiler.compile(xslt);
            Serializer out = processor.newSerializer(outWriter);
            out.setOutputProperty(Serializer.Property.METHOD, "xml");
            out.setOutputProperty(Serializer.Property.INDENT, "no");
            Xslt30Transformer transformer = stylesheet.load30();
            transformer.transform(text, out);

            StringBuffer sb = outWriter.getBuffer();
            body = sb.toString();
        } catch (SaxonApiException ex) {
            Logger.getLogger(Mensaje.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String toString() {
        return "Mensaje{" + "ID=" + ID + ", sequenceID=" + sequenceID + ", sequenceSize=" + sequenceSize + ", correlationID=" + correlationID + ", body=" + body + '}';
    }

    public static Document parseXML(String xml) throws ParserConfigurationException, SAXException, IOException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document documento = builder.parse(new InputSource(new StringReader(xml)));
        return documento;
    }

    public static String serialiceXML(Document document) throws TransformerException {
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer t = tf.newTransformer();
        StringWriter sw = new StringWriter();
        t.transform(new DOMSource(document), new StreamResult(sw));
        return sw.toString();
    }

    public static NodeList evaluateXPath(String xml, String expresion) throws ParserConfigurationException, XPathExpressionException, SAXException, IOException {
        return evaluateXPath(parseXML(xml), expresion);
    }

    public static NodeList evaluateXPath(Document documento, String expresion) throws ParserConfigurationException, XPathExpressionException, SAXException, IOException {
        XPath xpath = XPathFactory.newInstance().newXPath();
        return (NodeList) xpath.evaluate(expresion, documento, XPathConstants.NODESET);
    }

    public static String mergeXML(String xml1, String xml2) throws ParserConfigurationException, SAXException, IOException, TransformerException {
        XmlCombiner combiner = new XmlCombiner();
        combiner.combine(parseXML(xml1));
        combiner.combine(parseXML(xml2));
        return serialiceXML(combiner.buildDocument());
    }

    public static Document node2document(Node node) throws ParserConfigurationException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document newDocument = builder.newDocument();
        Node importedNode = newDocument.importNode(node, true);
        newDocument.appendChild(importedNode);
        return newDocument;
    }

    public static Node document2node(Document doc) throws ParserConfigurationException {
        return doc.getFirstChild();
    }
}
