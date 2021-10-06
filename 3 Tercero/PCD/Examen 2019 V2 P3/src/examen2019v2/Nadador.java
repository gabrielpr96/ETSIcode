package examen2019v2;

import static java.lang.Thread.sleep;
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
        System.out.println("Se crea nadador " + Thread.currentThread().getId());

        piscina.entraPiscina();
        sleep(1000 + r.nextInt(1000));
        piscina.cogeMaterial();
        int nadado = 2000 + r.nextInt(1000);
        sleep(nadado);
        piscina.sueltaMaterial();
        piscina.salePiscina();

        return nadado;
    }

}
