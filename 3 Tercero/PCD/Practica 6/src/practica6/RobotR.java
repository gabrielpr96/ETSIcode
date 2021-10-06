package practica6;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RobotR extends Thread {

    private final Semaforo SiloM1, SiloM2;
    private final CanvasFabrica canvas;

    RobotR(Semaforo SiloM1, Semaforo SiloM2, CanvasFabrica canvas) {
        this.SiloM1 = SiloM1;
        this.SiloM2 = SiloM2;
        this.canvas = canvas;
    }

    @Override
    public void run() {
        Random r = new Random();
        r.setSeed(System.nanoTime());
        try {
            while (true) {
                sleep(4000);
                int nM1 = 3+r.nextInt(3), nM2 = 3+r.nextInt(3);
                for (int i = 0; i < nM1; i++) {
                    canvas.agregarM1();
                    SiloM1.SIGNAL();
                }
                for (int i = 0; i < nM2; i++) {
                    canvas.agregarM2();
                    SiloM2.SIGNAL();
                }
            }
        } catch (InterruptedException ex) {
            System.out.println("Interrumpido RobotR");
        }
    }
}
