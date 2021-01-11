package examen2019v1;

import static java.lang.Thread.sleep;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Club implements Runnable{
private final Piscina piscina;

    public Club(Piscina piscina) {
        this.piscina = piscina;
    }
    @Override
    public void run() {
        Random r = new Random();
        r.setSeed(System.nanoTime());
        System.out.println("Se crea Club "+Thread.currentThread().getId());
        try {
            int calle = piscina.entraClub();
            sleep(2000 + r.nextInt(2000));
            piscina.saleClub(calle);
        } catch (InterruptedException ex) {
            Logger.getLogger(Libre.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
