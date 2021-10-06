package proyecto2;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Practicas implements Runnable{

    private final Revision revision;
    public Practicas(Revision revision){
        this.revision = revision;
    }
    
    @Override
    public void run() {
        try {
            Random r = new Random();
            r.setSeed(System.nanoTime());
            System.out.println("Entra Alumno Practicas: "+Thread.currentThread().getId());
            revision.entraPracticas();
            Thread.sleep(2000+r.nextInt(3001));
            revision.salePracticas();
            System.out.println("Sale Alumno Practicas: "+Thread.currentThread().getId());
        } catch (InterruptedException ex) {
            Logger.getLogger(Practicas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
