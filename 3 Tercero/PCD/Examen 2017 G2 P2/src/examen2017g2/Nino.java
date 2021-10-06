package examen2017g2;

import static java.lang.Thread.sleep;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Nino implements Runnable {

    private final Puente puente;

    public Nino(Puente puente) {
        this.puente = puente;
    }

    @Override
    public void run() {
        Random r = new Random();
        r.setSeed(System.nanoTime());
        System.out.println("Se crea Nino "+Thread.currentThread().getId());
        try {
            puente.entraNino();
            sleep(2000 + r.nextInt(3000));
            puente.saleNino();
        } catch (InterruptedException ex) {
            Logger.getLogger(Nino.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
