package proyecto1;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Coche implements Runnable {

    private final Tunel tunel;

    public Coche(Tunel tunel) {
        this.tunel = tunel;
    }

    @Override
    public void run() {
        try {
            Random r = new Random();
            r.setSeed(System.nanoTime());
            for (int i = 0; i < 3; i++) {
                tunel.entraPrelavadoCoche();
                Thread.sleep(2000);
                tunel.entraLavadoCoche();
                Thread.sleep(2000);
                tunel.entraSecadoCoche();
                Thread.sleep(2000);
                tunel.saleSecadoCoche();
                if(i != 2)
                    Thread.sleep(3000 + r.nextInt(2000));
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(Furgoneta.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
