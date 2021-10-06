package examen2019v3;

import static java.lang.Thread.sleep;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Adulto implements Runnable{
private final Piscina piscina;

    public Adulto(Piscina piscina) {
        this.piscina = piscina;
    }
    @Override
    public void run() {
        Random r = new Random();
        r.setSeed(System.nanoTime());
        System.out.println("Se crea Adulto "+Thread.currentThread().getId());
        try {
            piscina.entraAdulto();
            sleep(3000 + r.nextInt(3000));
            piscina.saleAdulto();
        } catch (InterruptedException ex) {
            Logger.getLogger(Ninyo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
