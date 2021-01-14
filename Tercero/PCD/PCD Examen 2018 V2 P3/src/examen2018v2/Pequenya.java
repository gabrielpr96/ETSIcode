package examen2018v2;

import static java.lang.Thread.sleep;
import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Pequenya extends Thread {

    private final Semaphore s;
    private final int id;
    private final CanvasMina canvas;

    public Pequenya(Semaphore s, int id, CanvasMina canvas) {
        this.s = s;
        this.id = id;
        this.canvas = canvas;
    }

    @Override
    public void run() {
        Random r = new Random();
        r.setSeed(System.nanoTime());
        System.out.println("Se crea Cargadora Pequenya " + id + " " + getId());
        try {
            for (int i = 0; i < 10; i++) {
                canvas.estado(id, 1);
                s.acquire();
                canvas.cantidad(s.availablePermits());
                canvas.estado(id, 0);
                sleep(1000 + r.nextInt(2000));
            }
            canvas.estado(id, 2);
        } catch (InterruptedException ex) {
            Logger.getLogger(Grande.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
