package ejercicio2;

import com.b0ve.solucionintegraciongenerica.adapters.Adapter;
import com.b0ve.solucionintegraciongenerica.utils.exceptions.ExecutionException;
import com.b0ve.solucionintegraciongenerica.flow.Message;
import com.b0ve.solucionintegraciongenerica.utils.Process;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class AdaptadorExchange extends Adapter {

    private final Thread watcher;

    public AdaptadorExchange() {
        watcher = new Thread() {
            @Override
            public void run() {
                Scanner s = new Scanner(System.in);
                while (!isInterrupted()) {
                    try {
                        sendPort(s.nextLine());
                    } catch (ParserConfigurationException | SAXException | IOException ex) {
                        Logger.getLogger(AdaptadorExchange.class.getName()).log(Level.SEVERE, null, ex);
                    }
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
    public Process.PORTS getCompatiblePortType() {
        return Process.PORTS.INPUT;
    }

}
