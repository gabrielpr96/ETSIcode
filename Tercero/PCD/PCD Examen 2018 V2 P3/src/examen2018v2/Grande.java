package examen2018v2;

import static java.lang.Thread.sleep;
import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Grande implements Runnable {

    private final Semaphore s;
    private final CanvasMina canvas;

    public Grande(Semaphore s, CanvasMina canvas) {
        this.s = s;
        this.canvas = canvas;
    }

    @Override
    public void run() {
        Random r = new Random();
        r.setSeed(System.nanoTime());
        System.out.println("Se crea Cargadora Grande " + Thread.currentThread().getId());
        try {
            for (int i = 0; i < 7; i++) {
                canvas.estado(2, 1);
                s.acquire();
                canvas.cantidad(s.availablePermits());
                canvas.grandeLlenado(1);
                s.acquire();
                canvas.cantidad(s.availablePermits());
                canvas.grandeLlenado(2);
                canvas.estado(2, 0);
                sleep(2000 + r.nextInt(2000));
                canvas.grandeLlenado(0);
            }
            canvas.estado(2, 2);
        } catch (InterruptedException ex) {
            Logger.getLogger(Grande.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
