package pcd2019v1;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Libre extends Thread {

    private final Piscina piscina;

    public Libre(Piscina piscina) {
        this.piscina = piscina;
    }

    @Override
    public void run() {
        Random r = new Random();
        r.setSeed(System.nanoTime());
        try {
            piscina.entraLibre();
            Thread.sleep(2000 + r.nextInt(2001));
            piscina.saleLibre();
        } catch (InterruptedException ex) {
            Logger.getLogger(Club.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
