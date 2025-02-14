/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.b0ve.autosig;

import com.b0ve.sig.flow.FragmentInfo;
import com.b0ve.sig.flow.Message;
import com.b0ve.sig.utils.XMLUtils;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Iterator;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import net.sf.saxon.s9api.Processor;
import net.sf.saxon.s9api.SaxonApiException;
import net.sf.saxon.s9api.Serializer;
import org.w3c.dom.Document;

/**
 *
 * @author borja
 */
public class PrettyPrinting {
    public static String prettyPrintMessage(Message m) {
        StringBuilder sb = new StringBuilder();
        sb.append("ID: ").append(m.getID()).append("\nCorrelation ID: ").append(m.getCorrelationID()).append("\nFragmentationInfo:");
        if (m.isFragment()) {
            for (Iterator<FragmentInfo> iterator = m.getFragmentInfoStack(); iterator.hasNext();) {
                FragmentInfo fi = iterator.next();
                sb.append("\n\tID: ").append(fi.getFragmentID()).append(" Size: ").append(fi.getFragmentSize());
            }
        } else {
            sb.append(" Is not a fragment");
        }
        sb.append("\n\nBody:");
        sb.append(prettyPrintDocument(m.getBody()));

        return sb.toString();
    }
    
    public static String prettyPrintDocument(Document doc) {
        try {
            Source source = new StreamSource(new StringReader(XMLUtils.serialize(doc)));
            StringWriter outWriter = new StringWriter();
            Processor processor = new Processor(false);
            Serializer serializer = processor.newSerializer(outWriter);
            serializer.setOutputProperty(Serializer.Property.INDENT, "yes");
            serializer.serialize(source);
            return outWriter.getBuffer().toString();
        } catch (SaxonApiException e) {
            return "Could not serialize document";
        }
    }
}
