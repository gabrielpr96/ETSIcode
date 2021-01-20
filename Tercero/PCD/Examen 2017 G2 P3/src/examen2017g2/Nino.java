package examen2017g2;

import static java.lang.Thread.sleep;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Nino implements Callable<Integer> {

    private final Puente puente;

    public Nino(Puente puente) {
        this.puente = puente;
    }

    @Override
    public Integer call() throws Exception {
        Random r = new Random();
        r.setSeed(System.nanoTime());
        System.out.println("Se crea Nino " + Thread.currentThread().getId());
        puente.entraNino();
        int tiempo = 2000 + r.nextInt(3000);
        sleep(tiempo);
        puente.saleNino();
        return tiempo;
    }
}
