package examen2017g1;

import static java.lang.Thread.sleep;
import java.util.Random;
import java.util.concurrent.Callable;

public class Turismo implements Callable<Integer> {

    private final Parking parking;

    public Turismo(Parking parking) {
        this.parking = parking;
    }

    @Override
    public Integer call() throws Exception {
        Random r = new Random();
        r.setSeed(System.nanoTime());
        System.out.println("Se crea Coche " + Thread.currentThread().getId());
        int pos = parking.entraCoche();
        int tiempo = 2000 + r.nextInt(3000);
        sleep(tiempo);
        parking.saleCoche(pos);
        return tiempo;
    }
}
