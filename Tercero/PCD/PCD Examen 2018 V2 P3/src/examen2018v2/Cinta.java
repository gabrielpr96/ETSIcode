package examen2018v2;

import static java.lang.Thread.sleep;
import java.util.Random;
import java.util.concurrent.Semaphore;

public class Cinta implements Runnable {

    private final Semaphore s;
    private final CanvasMina canvas;

    public Cinta(Semaphore s, CanvasMina canvas) {
        this.s = s;
        this.canvas = canvas;
    }

    @Override
    public void run() {
        Random r = new Random();
        r.setSeed(System.nanoTime());
        try {
            while (true) {
                sleep(1000 + r.nextInt(4000));
                int carga = 2 + r.nextInt(4);
                for (int i = 0; i < carga; i++) {
                    s.release();
                    canvas.cantidad(s.availablePermits());
                }
            }
        } catch (InterruptedException ex) {
            System.out.println("Cinta interrumpida");
        }
    }
}
