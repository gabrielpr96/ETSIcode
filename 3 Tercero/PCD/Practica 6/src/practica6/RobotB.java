package practica6;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RobotB implements Runnable {

    private final Semaforo SiloM1, SiloM2;
    private final CanvasFabrica canvas;

    RobotB(Semaforo SiloM1, Semaforo SiloM2, CanvasFabrica canvas) {
        this.SiloM1 = SiloM1;
        this.SiloM2 = SiloM2;
        this.canvas = canvas;
    }

    @Override
    public void run() {
        Random r = new Random();
        r.setSeed(System.nanoTime());
        try {
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 1; j++) {
                    SiloM1.WAIT();
                    canvas.tomarM1B();
                }
                for (int j = 0; j < 3; j++) {
                    SiloM2.WAIT();
                    canvas.tomarM2B();
                }
                canvas.fabricarB();
                Thread.sleep(1000+r.nextInt(2000));
                canvas.fabricadoB();
            }
            canvas.terminaB();
        } catch (InterruptedException ex) {
            System.out.println("Interrumpido RobotB");
        }
    }

}
