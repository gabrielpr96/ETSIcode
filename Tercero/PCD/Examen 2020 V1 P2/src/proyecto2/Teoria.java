package proyecto2;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Teoria extends Thread{
    private final Revision revision;
    public Teoria(Revision revision){
        this.revision = revision;
    }
    
    @Override
    public void run() {
        try {
            Random r = new Random();
            r.setSeed(System.nanoTime());
            System.out.println("Entra Alumno Teoria: "+Thread.currentThread().getId());
            revision.entraTeoria();
            Thread.sleep(2000+r.nextInt(3001));
            revision.saleTeoria();
            System.out.println("Sale Alumno Teoria: "+Thread.currentThread().getId());
        } catch (InterruptedException ex) {
            Logger.getLogger(Practicas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
