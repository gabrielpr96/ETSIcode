package examen2019v2;

import static java.lang.Thread.sleep;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Nadador implements Runnable{
private final Piscina piscina;

    public Nadador(Piscina piscina) {
        this.piscina = piscina;
    }
    @Override
    public void run() {
        Random r = new Random();
        r.setSeed(System.nanoTime());
        System.out.println("Se crea nadador "+Thread.currentThread().getId());
        try {
            piscina.entraPiscina();
            sleep(1000 + r.nextInt(1000));
            piscina.cogeMaterial();
            sleep(2000 + r.nextInt(1000));
            piscina.sueltaMaterial();
            piscina.salePiscina();
        } catch (InterruptedException ex) {
            Logger.getLogger(Nadador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
