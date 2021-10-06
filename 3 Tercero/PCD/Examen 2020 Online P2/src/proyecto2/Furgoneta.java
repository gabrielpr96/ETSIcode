package proyecto2;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Furgoneta implements Runnable {

    private final Tunel tunel;

    public Furgoneta(Tunel tunel) {
        this.tunel = tunel;
    }

    @Override
    public void run() {
        try {
            Random r = new Random();
            r.setSeed(System.nanoTime());
            for (int i = 0; i < 3; i++) {
                tunel.entraPrelavadoFurgoneta();
                Thread.sleep(2000);
                tunel.entraLavadoFurgoneta();
                Thread.sleep(2000);
                tunel.entraSecadoFurgoneta();
                Thread.sleep(2000);
                tunel.saleSecadoFurgoneta();
                if(i != 2)
                    Thread.sleep(3000+r.nextInt(2000));
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(Furgoneta.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
