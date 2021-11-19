package juegodados;

import java.util.Random;

public class DadoDoble {

    private int dado1, dado2;
    private static final Random r;

    static {
        r = new Random();
        r.setSeed(System.nanoTime());
    }

    public DadoDoble() {
        dado1 = 1;
        dado2 = 1;
    }

    public void tirar() {
        dado1 = r.nextInt(6) + 1;
        dado2 = r.nextInt(6) + 1;
    }

    public int getDado1() {
        return dado1;
    }

    public int getDado2() {
        return dado2;
    }

    public int getDados() {
        return dado1 + dado2;
    }

}
