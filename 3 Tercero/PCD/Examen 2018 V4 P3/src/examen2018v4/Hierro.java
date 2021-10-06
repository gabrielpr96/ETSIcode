package examen2018v4;

import static java.lang.Thread.sleep;
import java.util.Random;
import java.util.concurrent.Callable;

public class Hierro implements Callable<Integer> {

    private final Vibradora vibradora;

    public Hierro(Vibradora vibradora) {
        this.vibradora = vibradora;
    }

    @Override
    public Integer call() throws Exception {
        Random r = new Random();
        r.setSeed(System.nanoTime());
        System.out.println("Se crea Hierro " + Thread.currentThread().getId());
        vibradora.entraHierro();
        int tiempo = 2000 + r.nextInt(3000);
        sleep(tiempo);
        vibradora.saleHierro();
        return tiempo;
    }
}
