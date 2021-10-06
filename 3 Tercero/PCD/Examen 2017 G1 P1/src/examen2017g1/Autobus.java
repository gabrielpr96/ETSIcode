package examen2017g1;

import static java.lang.Thread.sleep;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Autobus extends Thread{
private final Parking parking;

    public Autobus(Parking parking) {
        this.parking = parking;
    }
    @Override
    public void run() {
        Random r = new Random();
        r.setSeed(System.nanoTime());
        System.out.println("Se crea Autobus "+getId());
        try {
            parking.entraBus();
            sleep(3000 + r.nextInt(2000));
            parking.saleBus();
        } catch (InterruptedException ex) {
            Logger.getLogger(Turismo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
