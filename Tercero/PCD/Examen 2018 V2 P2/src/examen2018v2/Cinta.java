package examen2018v2;

import static java.lang.Thread.sleep;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Cinta implements Runnable {

    private final Monton monton;

    public Cinta(Monton monton) {
        this.monton = monton;
    }

    @Override
    public void run() {
        Random r = new Random();
        r.setSeed(System.nanoTime());
        try {
            while (true) {
                sleep(1000 + r.nextInt(4000));
                monton.Rellena(2 + r.nextInt(4));
            }
        } catch (InterruptedException ex) {
            System.out.println("Cinta interrumpida");
        }
    }
}
