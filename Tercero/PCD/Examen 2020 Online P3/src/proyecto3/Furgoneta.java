package proyecto3;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;
import static proyecto3.CanvasTunel.VEHICULO_FURGONETA;

public class Furgoneta implements Runnable {

    private final Semaphore prelavado, lavado, secado;
    private final CanvasTunel canvas;

    public Furgoneta(Semaphore prelavado, Semaphore lavado, Semaphore secado, CanvasTunel canvas) {
        this.prelavado = prelavado;
        this.lavado = lavado;
        this.secado = secado;
        this.canvas = canvas;
    }

    @Override
    public void run() {
        try {
            Random r = new Random();
            r.setSeed(System.nanoTime());
            for (int i = 0; i < 3; i++) {
                canvas.entraEspera(VEHICULO_FURGONETA);
                prelavado.acquire();
                canvas.entraPrelavado();
                Thread.sleep(2000);
                lavado.acquire();
                prelavado.release();
                canvas.entraLavado();
                Thread.sleep(2000);
                secado.acquire();
                lavado.release();
                canvas.entraSecado();
                Thread.sleep(2000);
                secado.release();
                canvas.saleSecado();
                if(i != 2)
                    Thread.sleep(3000 + r.nextInt(2000));
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(Furgoneta.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
