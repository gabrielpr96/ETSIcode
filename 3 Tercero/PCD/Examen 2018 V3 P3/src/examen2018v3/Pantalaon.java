package examen2018v3;

import static examen2018v3.CanvasLinea.*;
import static java.lang.Thread.sleep;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Pantalaon implements Runnable {

    private final CanvasLinea canvas;
    private final Semaphore cortar, coser;

    public Pantalaon(Semaphore cortar, Semaphore coser, CanvasLinea canvas) {
        this.cortar = cortar;
        this.coser = coser;
        this.canvas = canvas;
    }

    @Override
    public void run() {
        System.out.println("Se crea Pantalon "+Thread.currentThread().getId());
        try {
            canvas.espera(TIPO_PANTALON);
            cortar.acquire();
            canvas.corte();
            sleep(2000);
            canvas.corteTermina();
            coser.acquire();
            canvas.cosido();
            cortar.release();
            sleep(3000);
            canvas.salida();
            coser.release();
        } catch (InterruptedException ex) {
            Logger.getLogger(Pantalaon.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
