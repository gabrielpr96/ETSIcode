package ejercicio1;

import com.b0ve.solucionintegraciongenerica.adaptadores.Adaptador;
import com.b0ve.solucionintegraciongenerica.utils.excepciones.ExecutionException;
import com.b0ve.solucionintegraciongenerica.utils.flujo.Mensaje;
import java.util.Scanner;

public class AdaptadorGMS extends Adaptador {

    private final Thread watcher;

    public AdaptadorGMS() {
        watcher = new Thread() {
            @Override
            public void run() {
                Scanner s = new Scanner(System.in);
                while (!isInterrupted()) {
                    enviarPuerto(new Mensaje(s.nextLine()));
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
