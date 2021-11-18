package com.b0ve.autosig;

import com.b0ve.autosig.gui.MainWindow;
import com.b0ve.solucionintegraciongenerica.flow.FragmentInfo;
import com.b0ve.solucionintegraciongenerica.flow.Message;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Iterator;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import net.sf.saxon.s9api.Processor;
import net.sf.saxon.s9api.SaxonApiException;
import net.sf.saxon.s9api.Serializer;

public class AutoSIG {

    //TODO: Probar cuando MySQL esta apagado, controlar las excepciones.
    public static void main(String[] args) {
        MainWindow mainWindow = new MainWindow();
        mainWindow.setLocationRelativeTo(null);
        mainWindow.setVisible(true);
    }

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

        try {
            Source source = new StreamSource(new StringReader(m.getBodyString()));
            StringWriter outWriter = new StringWriter();
            Processor processor = new Processor(false);
            Serializer serializer = processor.newSerializer(outWriter);
            serializer.setOutputProperty(Serializer.Property.INDENT, "yes");
            serializer.serialize(source);
            sb.append(outWriter.getBuffer().toString());
        } catch (SaxonApiException e) {
            sb.append("Could not serialize document");
        }

        return sb.toString();
    }

}
