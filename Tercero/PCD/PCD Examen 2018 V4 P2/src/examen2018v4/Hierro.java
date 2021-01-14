package examen2018v4;

import static java.lang.Thread.sleep;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Hierro implements Runnable {

    private final Vibradora vibradora;

    public Hierro(Vibradora vibradora) {
        this.vibradora = vibradora;
    }

    @Override
    public void run() {
        Random r = new Random();
        r.setSeed(System.nanoTime());
        System.out.println("Se crea Hierro "+Thread.currentThread().getId());
        try {
            vibradora.entraHierro();
            sleep(2000 + r.nextInt(3000));
            vibradora.saleHierro();
        } catch (InterruptedException ex) {
            Logger.getLogger(Hierro.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
