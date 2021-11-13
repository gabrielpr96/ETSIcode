package ejercicio3;

import com.b0ve.solucionintegraciongenerica.adapters.Adapter;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

public class AdaptadorCRM2Entrada extends Adapter {

    private final Thread watcher;

    public AdaptadorCRM2Entrada() {
        watcher = new Thread() {
            @Override
            public void run() {
                Scanner s = new Scanner(System.in);
                while (!isInterrupted()) {
                    try {
                        sendPort(s.nextLine());
                    } catch (ParserConfigurationException | SAXException | IOException ex) {
                        Logger.getLogger(AdaptadorCRM2Entrada.class.getName()).log(Level.SEVERE, null, ex);
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
    public com.b0ve.solucionintegraciongenerica.utils.Process.PORTS getCompatiblePortType() {
        return com.b0ve.solucionintegraciongenerica.utils.Process.PORTS.INPUT;
    }

}
