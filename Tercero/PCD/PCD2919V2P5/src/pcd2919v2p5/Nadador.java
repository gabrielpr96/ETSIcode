package pcd2919v2p5;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Nadador implements Runnable{

    private final Semaphore sPiscina, sPalas, sAletas;
    private final CanvasPiscina canvas;
    
    public Nadador(Semaphore sPiscina, Semaphore sPalas, Semaphore sAletas, CanvasPiscina canvas){
        this.sPiscina = sPiscina;
        this.sPalas = sPalas;
        this.sAletas = sAletas;
        this.canvas = canvas;
    }
    
    @Override
    public void run() {
        Random r = new Random();
        r.setSeed(System.nanoTime());
        try {
            canvas.entra();
            sPiscina.acquire();
            canvas.calienta();
            Thread.sleep(1000+r.nextInt(1001));
            canvas.coge();
            sAletas.acquire();
            canvas.obtieneAletas();
            sPalas.acquire();
            canvas.obtienePalas();
            Thread.sleep(2000+r.nextInt(1001));
            sAletas.release();
            sPalas.release();
            sPiscina.release();
            canvas.sale();
        } catch (InterruptedException ex) {
            Logger.getLogger(Nadador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
