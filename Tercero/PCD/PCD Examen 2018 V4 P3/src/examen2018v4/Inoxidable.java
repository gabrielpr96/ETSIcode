package examen2018v4;

import static java.lang.Thread.sleep;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Inoxidable extends Thread{
private final Vibradora vibradora;

    public Inoxidable(Vibradora vibradora) {
        this.vibradora = vibradora;
    }
    @Override
    public void run() {
        Random r = new Random();
        r.setSeed(System.nanoTime());
        System.out.println("Se crea Inox "+getId());
        try {
            vibradora.entraInox();
            sleep(3000 + r.nextInt(2000));
            vibradora.saleInox();
        } catch (InterruptedException ex) {
            Logger.getLogger(Hierro.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
