package ejercicio3;

import com.b0ve.solucionintegraciongenerica.adapters.Adapter;
import com.b0ve.solucionintegraciongenerica.flow.Message;
import com.b0ve.solucionintegraciongenerica.utils.exceptions.ParseException;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class AdaptadorCRM1Entrada extends Adapter {

    private final Thread watcher;

    public AdaptadorCRM1Entrada(String dir) {
        File folder = new File(dir);
        watcher = new Thread() {
            @Override
            public void run() {
                try {
                    while (!isInterrupted()) {
                        for (final File fileEntry : folder.listFiles()) {
                            if (fileEntry.isFile()) {
                                Document doc = Message.parseXML(new String(Files.readAllBytes(fileEntry.toPath()), StandardCharsets.UTF_8));
                                sendPort(doc);
                                fileEntry.delete();
                            }
                        }
                        sleep(1000);
                    }
                } catch (InterruptedException | IOException | ParseException  ex) {
                    Logger.getLogger(AdaptadorCRM1Entrada.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };
    }

    @Override
    public void iniciate() {
        if (watcher != null) {
            watcher.start();
        }
    }

    @Override
    public void halt() {
        if (watcher != null) {
            watcher.interrupt();
        }
    }

    @Override
    public com.b0ve.solucionintegraciongenerica.utils.Process.PORTS getCompatiblePortType() {
        return com.b0ve.solucionintegraciongenerica.utils.Process.PORTS.INPUT;
    }
}
