package pcd2919v2p3;

import java.util.Random;
import java.util.concurrent.Callable;

public class Nadador implements Callable<Integer> {

    private final Piscina piscina;

    public Nadador(Piscina piscina) {
        this.piscina = piscina;
    }

    @Override
    public Integer call() throws Exception {
        Random r = new Random();
        r.setSeed(System.nanoTime());
        piscina.entraPiscina();
        Thread.sleep(1000 + r.nextInt(1001));
        piscina.cogeMaterial();
        int tiempo = 2000 + r.nextInt(1001);
        Thread.sleep(tiempo);
        piscina.sueltaMaterial();
        piscina.salePiscina();
        return tiempo;
    }

}
