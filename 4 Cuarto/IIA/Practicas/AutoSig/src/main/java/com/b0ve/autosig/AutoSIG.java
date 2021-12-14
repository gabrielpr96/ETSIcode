package com.b0ve.autosig;

import com.b0ve.autosig.gui.MainWindow;
import com.b0ve.sig.flow.FragmentInfo;
import com.b0ve.sig.flow.Message;
import com.b0ve.sig.utils.XMLUtils;
import java.io.StringReader;
import java.io.StringWriter;
import java.nio.file.Paths;
import java.util.Iterator;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import net.sf.saxon.s9api.Processor;
import net.sf.saxon.s9api.SaxonApiException;
import net.sf.saxon.s9api.Serializer;
import org.w3c.dom.Document;

public class AutoSIG {

    //TODO: Probar cuando MySQL esta apagado, controlar las excepciones.
    public static void main(String[] args) {
        MainWindow mainWindow = new MainWindow();
        mainWindow.setLocationRelativeTo(null);
        mainWindow.setVisible(true);
        
        //mainWindow.load(Paths.get("C:\\PROYECTOS\\UNI\\IIA\\Simulaciones\\WP-MC.xml"));
        //mainWindow.load(Paths.get("C:\\PROYECTOS\\UNI\\IIA\\Simulaciones\\Cafe.xml"));
        //mainWindow.load(Paths.get("C:\\PROYECTOS\\UNI\\IIA\\Simulaciones\\RedesSociales.xml"));
        mainWindow.load(Paths.get("C:\\PROYECTOS\\UNI\\IIA\\Simulaciones\\PS-CSV.xml"));
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
