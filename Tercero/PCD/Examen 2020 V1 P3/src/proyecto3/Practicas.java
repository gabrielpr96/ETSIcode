package proyecto3;

import java.util.Random;
import java.util.concurrent.Callable;

public class Practicas implements Callable<Integer> {

    private final Revision revision;

    public Practicas(Revision revision) {
        this.revision = revision;
    }

    @Override
    public Integer call() throws Exception {
        Random r = new Random();
        r.setSeed(System.nanoTime());
        System.out.println("Entra Alumno Practicas: " + Thread.currentThread().getId());
        revision.entraPracticas();
        int tiempo = 2000 + r.nextInt(3001);
        Thread.sleep(tiempo);
        revision.salePracticas();
        System.out.println("Sale Alumno Practicas: " + Thread.currentThread().getId());
        return tiempo;
    }

}
