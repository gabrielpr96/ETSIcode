package examen2018v1;

import static java.lang.Thread.sleep;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Camion extends Thread{
private final Tayer tayer;

    public Camion(Tayer piscina) {
        this.tayer = piscina;
    }
    @Override
    public void run() {
        Random r = new Random();
        r.setSeed(System.nanoTime());
        System.out.println("Se crea Camion "+getId());
        try {
            tayer.entraCamion();
            sleep(3000 + r.nextInt(2000));
            tayer.saleCamion();
        } catch (InterruptedException ex) {
            Logger.getLogger(Turismo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
