package examen2018v1;

import static java.lang.Thread.sleep;
import java.util.Random;
import java.util.concurrent.Callable;

public class Turismo implements Callable<Integer> {

    private final Tayer tayer;

    public Turismo(Tayer piscina) {
        this.tayer = piscina;
    }

    @Override
    public Integer call() throws Exception {
        Random r = new Random();
        r.setSeed(System.nanoTime());
        System.out.println("Se crea Coche " + Thread.currentThread().getId());

        tayer.entraCoche();
        int tiempo = 2000 + r.nextInt(2000);
        sleep(tiempo);
        tayer.saleCoche();

        return tiempo;
    }
}
