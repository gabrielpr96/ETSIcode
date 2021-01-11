package examen2018v1;

import static java.lang.Thread.sleep;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Turismo implements Runnable {

    private final Tayer tayer;

    public Turismo(Tayer piscina) {
        this.tayer = piscina;
    }

    @Override
    public void run() {
        Random r = new Random();
        r.setSeed(System.nanoTime());
        System.out.println("Se crea Coche "+Thread.currentThread().getId());
        try {
            tayer.entraCoche();
            sleep(2000 + r.nextInt(2000));
            tayer.saleCoche();
        } catch (InterruptedException ex) {
            Logger.getLogger(Turismo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
