package examen2018v1;

import static java.lang.Thread.sleep;
import java.util.Random;
import java.util.concurrent.Callable;

public class Camion implements Callable<Integer> {

    private final Tayer tayer;

    public Camion(Tayer piscina) {
        this.tayer = piscina;
    }

    @Override
    public Integer call() throws Exception {
        Random r = new Random();
        r.setSeed(System.nanoTime());
        System.out.println("Se crea Camion " + Thread.currentThread().getId());
        
        tayer.entraCamion();
        int tiempo = 3000 + r.nextInt(2000);
        sleep(tiempo);
        tayer.saleCamion();
        
        return tiempo;
    }

}
