package ejercicio4;

import com.b0ve.solucionintegraciongenerica.adaptadores.Adaptador;
import com.b0ve.solucionintegraciongenerica.utils.excepciones.ExecutionException;
import com.b0ve.solucionintegraciongenerica.utils.flujo.Mensaje;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

public class AdaptadorCCS extends Adaptador {

    private final Thread watcher;

    public AdaptadorCCS() {
        watcher = new Thread() {
            @Override
            public void run() {
                Scanner s = new Scanner(System.in);
                while (!isInterrupted()) {
                    try {
                        enviarPuerto(new Mensaje(s.nextLine()));
                    } catch (ParserConfigurationException | SAXException | IOException ex) {
                        Logger.getLogger(AdaptadorCCS.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        };
    }

    @Override
    public void enviarApp(Mensaje m) {
        throw new ExecutionException("Este puerto es solo de entrada");
    }

    @Override
    public void iniciar() {
        if (watcher != null) {
            watcher.start();
        }
    }

    @Override
    public void detener() {
        if (watcher != null) {
            watcher.interrupt();
        }
    }

}
