package com.b0ve.solucionintegraciongenerica.flow;

import com.b0ve.solucionintegraciongenerica.utils.exceptions.ParseException;
import com.b0ve.solucionintegraciongenerica.utils.exceptions.XMLMergeException;
import com.b0ve.solucionintegraciongenerica.utils.exceptions.XPathEvaluationException;
import com.b0ve.solucionintegraciongenerica.utils.exceptions.XSLTransformationException;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Iterator;
import java.util.Stack;
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

public final class Message {

    private static int counter = 0;

    private final long ID;
    private long correlationID;
    private final Stack<FragmentInfo> fragmentInfo;
    private Document body;

    private Message(long ID, long correlationID, Document body) throws ParseException {
        this.ID = ID;
        this.correlationID = correlationID;
        this.body = cloneDocument(body);
        this.fragmentInfo = new Stack<>();
    }

    public Message(Document body) throws ParseException {
        this(counter, counter, body);
        counter++;
    }

    public Message(String body) throws ParseException {
        this(parseXML(body));
    }

    public Message(Message m) throws ParseException {
        this(counter++, m.correlationID, m.body);
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
        return serialiceXML(body);
    }

    public FragmentInfo getFragmentInfo() {
        return fragmentInfo.isEmpty() ? null : fragmentInfo.peek();
    }

    public long getFragmentID() {
        return fragmentInfo.isEmpty() ? -1 : fragmentInfo.peek().getFragmentID();
    }

    public long getFragmentSize() {
        return fragmentInfo.isEmpty() ? -1 : fragmentInfo.peek().getFragmentSize();
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
        return fragmentInfo.isEmpty() ? null : fragmentInfo.pop();
    }

    public boolean isFragment() {
        return fragmentInfo.isEmpty();
    }

    public NodeList evaluateXPath(String expresion) throws XPathEvaluationException {
        return evaluateXPath(body, expresion);
    }

    public String evaluateXPathString(String expresion) throws XPathEvaluationException {
        NodeList res = evaluateXPath(expresion);
        if (res == null || res.getLength() < 1) {
            return null;
        }
        return res.item(0).getTextContent();
    }

    public void transformBody(String style) throws XSLTransformationException {
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
        } catch (SaxonApiException | ParseException ex) {
            throw new XSLTransformationException(ex.getMessage(), style, ex);
        }
    }

    @Override
    public String toString() {
        return "Mensaje{" + "ID=" + ID + ", correlationID=" + correlationID + ", fragmentInfo=" + fragmentInfo + ", body=" + serialiceXML(body) + '}';
    }

    public static Document parseXML(String xml) throws ParseException {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document documento = builder.parse(new InputSource(new StringReader(xml)));
            return documento;
        } catch (SAXException | IOException | ParserConfigurationException ex) {
            throw new ParseException(ex.getMessage(), xml, ex);
        }
    }

    public static String serialiceXML(Document document) {
        try {
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer t = tf.newTransformer();
            StringWriter sw = new StringWriter();
            t.transform(new DOMSource(document), new StreamResult(sw));
            return sw.toString();
        } catch (TransformerException ex) {
            return "Malformed document";
        }
    }

    public static NodeList evaluateXPath(Document documento, String expresion) throws XPathEvaluationException {
        try {
            XPath xpath = XPathFactory.newInstance().newXPath();
            return (NodeList) xpath.evaluate(expresion, documento, XPathConstants.NODESET);
        } catch (XPathExpressionException ex) {
            throw new XPathEvaluationException(ex.getMessage(), expresion, ex);
        }
    }

    public static Document mergeXML(Document xml1, Document xml2) throws XMLMergeException {
        try {
            XmlCombiner combiner = new XmlCombiner();
            combiner.combine(xml1);
            combiner.combine(xml2);
            return combiner.buildDocument();
        } catch (ParserConfigurationException ex) {
            throw new XMLMergeException(ex.getMessage(), new Document[]{xml1, xml2}, ex);
        }
    }

    public static Document node2document(Node node) throws ParseException {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setNamespaceAware(true);
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document newDocument = builder.newDocument();
            Node importedNode = newDocument.importNode(node, true);
            newDocument.appendChild(importedNode);
            return newDocument;
        } catch (ParserConfigurationException ex) {
            throw new ParseException(ex.getMessage(), node, ex);
        }
    }

    public static Node document2node(Document doc) {
        return doc.getFirstChild();
    }

    public static Document cloneDocument(Document doc) throws ParseException {
        return node2document(document2node(doc));
    }

    public static Message newMessage(long id, int correlationID, String body) {
        try {
            return new Message(id, correlationID, parseXML(body));
        } catch (ParseException ex) {
            return null;
        }
    }
}
