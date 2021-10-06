package pcd2919v2p1;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Nadador implements Runnable{

    private final Piscina piscina;
    
    public Nadador(Piscina piscina){
        this.piscina = piscina;
    }
    
    @Override
    public void run() {
        Random r = new Random();
        r.setSeed(System.nanoTime());
        try {
            piscina.entraPiscina();
            Thread.sleep(1000+r.nextInt(1001));
            piscina.cogeMaterial();
            Thread.sleep(2000+r.nextInt(1001));
            piscina.sueltaMaterial();
            piscina.salePiscina();
        } catch (InterruptedException ex) {
            Logger.getLogger(Nadador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
