package pcd2019v1;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Club implements Runnable {

    private final Piscina piscina;

    public Club(Piscina piscina) {
        this.piscina = piscina;
    }

    @Override
    public void run() {
        Random r = new Random();
        r.setSeed(System.nanoTime());
        try {
            int cual = piscina.entraClub();
            Thread.sleep(3000+r.nextInt(3001));
            piscina.saleClub(cual);
        } catch (InterruptedException ex) {
            Logger.getLogger(Club.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
