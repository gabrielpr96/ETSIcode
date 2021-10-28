package ejercicio3;

import com.b0ve.solucionintegraciongenerica.adaptadores.Adaptador;
import com.b0ve.solucionintegraciongenerica.utils.excepciones.ExecutionException;
import com.b0ve.solucionintegraciongenerica.utils.flujo.Mensaje;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AdaptadorCRM1Entrada extends Adaptador {

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
                                enviarPuerto(new Mensaje(new String(Files.readAllBytes(fileEntry.toPath()), StandardCharsets.UTF_8)));
                                fileEntry.delete();
                            }
                        }
                        sleep(1000);
                    }
                } catch (InterruptedException | IOException ex) {
                    Logger.getLogger(AdaptadorCRM1Entrada.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };
    }

    @Override
    public void enviarApp(Mensaje m) {
        throw new ExecutionException("Este puerto es solo de entrada");
    }

    @Override
    public void detener() {
        if (watcher != null) {
            watcher.interrupt();
        }
    }

    public void iniciar() {
        if (watcher != null) {
            watcher.start();
        }
    }

}
