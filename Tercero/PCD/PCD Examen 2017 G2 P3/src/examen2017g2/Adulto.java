package examen2017g2;

import static java.lang.Thread.sleep;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Adulto extends Thread{
private final Puente puente;

    public Adulto(Puente puente) {
        this.puente = puente;
    }
    @Override
    public void run() {
        Random r = new Random();
        r.setSeed(System.nanoTime());
        System.out.println("Se crea Adulto "+getId());
        try {
            puente.entraAdulto();
            sleep(3000 + r.nextInt(2000));
            puente.saleAdulto();
        } catch (InterruptedException ex) {
            Logger.getLogger(Nino.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
