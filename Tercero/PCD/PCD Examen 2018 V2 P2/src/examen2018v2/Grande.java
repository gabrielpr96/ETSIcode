package examen2018v2;

import static java.lang.Thread.sleep;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Grande implements Runnable {

    private final Monton monton;

    public Grande(Monton monton) {
        this.monton = monton;
    }

    @Override
    public void run() {
        Random r = new Random();
        r.setSeed(System.nanoTime());
        System.out.println("Se crea Cargadora Grande " + Thread.currentThread().getId());
        try {
            for (int i = 0; i < 7; i++) {
                monton.cargaMucho();
                sleep(2000 + r.nextInt(2000));
            }
            monton.grandeSeVa();
        } catch (InterruptedException ex) {
            Logger.getLogger(Grande.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
