package patronsingletronjuegomarcianos;

import java.util.Random;

public class Ordenador {

    private final Marcianos marcianos;
    private final Random rand;

    public Ordenador() {
        marcianos = Marcianos.obtenerMarcianos();
        rand = new Random();
        rand.setSeed(System.nanoTime());
    }

    public void nuevosMarcianos() {
        marcianos.creaMarcianos(rand.nextInt(10));
    }
}
