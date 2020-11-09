package practica2;

import java.util.Random;

/**
 * Clase que
 *
 * @author borja
 */
public class Productor extends Thread {

    private final ICola c;

    public Productor(ICola c) {
        this.c = c;
    }

    private void producir() {
        Random r = new Random();
        r.setSeed(System.nanoTime());
        for (int i = 0; i < 50; i++) {
            //float n = r.nextFloat() * 100;
            int n = r.nextInt() % 100;
            try {
                Thread.sleep(1000 + Math.abs(r.nextInt()) % 1000);
                c.Acola(n);
                System.out.println("Hilo " + getId() + " Inserto: " + n);
            } catch (Exception ex) {
                System.out.println("Hilo " + getId() + " Error al insertar: " + ex.getMessage());
            }
        }
    }

    @Override
    public void run() {
        producir();
    }
}
