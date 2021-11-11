package com.b0ve.solucionintegraciongenerica.utils.flujo;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Iterator;
import java.util.Stack;
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
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public final class Mensaje {

    private static int counter = 0;

    private final long ID;
    private long correlationID;
    private final Stack<FragmentInfo> fragmentInfo;
    private Document body;

    private Mensaje(long ID, long correlationID, Document body) {
        this.ID = ID;
        this.correlationID = correlationID;
        this.body = body;
        this.fragmentInfo = new Stack<>();
    }

    public Mensaje(Document body) {
        this(counter, counter, body);
        counter++;
    }

    public Mensaje(String body) throws ParserConfigurationException, SAXException, IOException {
        this(parseXML(body));
    }

    public Mensaje(Mensaje m) {
        this(counter++, m.correlationID, m.body);
        for (Iterator<FragmentInfo> iterator = m.fragmentInfo.iterator(); iterator.hasNext();) {
            addFragmentInfo(iterator.next());
        }
        addFragmentInfo(m.getFragmentInfoStack());
    }

    public long getID() {
        return ID;
    }

    public long getCorrelationID() {
        return correlationID;
    }

    public Document getBody() {
        return body;
    }

    public String getBodyString() {
        try {
            return serialiceXML(body);
        } catch (TransformerException ex) {
            Logger.getLogger(Mensaje.class.getName()).log(Level.SEVERE, null, ex);
            return "Malformed body";
        }
    }

    public FragmentInfo getFragmentInfo() {
        return fragmentInfo.isEmpty() ? null : fragmentInfo.peek();
    }
    
    public long getFragmentID(){
        return fragmentInfo.isEmpty()?-1:fragmentInfo.peek().getFragmentID();
    }
    
    public long getFragmentSize(){
        return fragmentInfo.isEmpty()?-1:fragmentInfo.peek().getFragmentSize();
    }

    public Iterator<FragmentInfo> getFragmentInfoStack() {
        return fragmentInfo.iterator();
    }

    public void setBody(Document body) {
        this.body = body;
    }

    public void setCorrelationID(long correlationID) {
        this.correlationID = correlationID;
    }

    public void addFragmentInfo(FragmentInfo finfo) {
        fragmentInfo.push(finfo);
    }

    public void addFragmentInfo(Iterator<FragmentInfo> finfo) {
        for (; finfo.hasNext();) {
            fragmentInfo.push(finfo.next());
        }
        
    }

    public FragmentInfo removeFragmentInfo() {
        return fragmentInfo.isEmpty()?null:fragmentInfo.pop();
    }

    public boolean isFragment() {
        return fragmentInfo.isEmpty();
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
            Source text = new StreamSource(new StringReader(serialiceXML(body))); //El XML con los datos
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
            body = parseXML(sb.toString());
        } catch (SaxonApiException | TransformerException | ParserConfigurationException | SAXException | IOException ex) {
            Logger.getLogger(Mensaje.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String toString() {
        try {
            return "Mensaje{" + "ID=" + ID + ", correlationID=" + correlationID + ", fragmentInfo=" + fragmentInfo + ", body=" + serialiceXML(body) + '}';
        } catch (TransformerException ex) {
            return "Mensaje{" + "ID=" + ID + ", correlationID=" + correlationID + ", fragmentInfo=" + fragmentInfo + ", body=" + ex.getMessage() + '}';
        }
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
    
    public static NodeList evaluateXPath(Document documento, String expresion) throws ParserConfigurationException, XPathExpressionException, SAXException, IOException {
        XPath xpath = XPathFactory.newInstance().newXPath();
        return (NodeList) xpath.evaluate(expresion, documento, XPathConstants.NODESET);
    }

    public static Document mergeXML(Document xml1, Document xml2) throws ParserConfigurationException, SAXException, IOException, TransformerException {
        XmlCombiner combiner = new XmlCombiner();
        combiner.combine(xml1);
        combiner.combine(xml2);
        return combiner.buildDocument();
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
    
    public static Mensaje newMensaje(long id, int correlationID, String body){
        try {
            return new Mensaje(id, correlationID, parseXML(body));
        } catch (ParserConfigurationException | SAXException | IOException ex) {
            Logger.getLogger(Mensaje.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
