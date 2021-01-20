package examen2017g1;

import static java.lang.Thread.sleep;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Turismo implements Runnable {

    private final Parking parking;

    public Turismo(Parking parking) {
        this.parking = parking;
    }

    @Override
    public void run() {
        Random r = new Random();
        r.setSeed(System.nanoTime());
        System.out.println("Se crea Coche "+Thread.currentThread().getId());
        try {
            int pos = parking.entraCoche();
            sleep(2000 + r.nextInt(3000));
            parking.saleCoche(pos);
        } catch (InterruptedException ex) {
            Logger.getLogger(Turismo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
