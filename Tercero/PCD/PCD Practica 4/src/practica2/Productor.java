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

    /**
     * Introduce un elemento aleatorio en la cola
     */
    private void producir() {
        Random r = new Random();
        r.setSeed(System.nanoTime());
        try {
            for (int i = 0; i < 15; i++) {
                Thread.sleep(1000 + Math.abs(r.nextInt()) % 2000);
                //float n = r.nextFloat() * 100;
                int n = r.nextInt() % 100;
                c.Acola(n);
                System.out.println("Hilo " + getId() + " productor inserta: " + n);
            }
        } catch (InterruptedException ex) {
            System.out.println("Hilo " + getId() + " productor interrumpido");
            //Se debe terminar el hilo
        } catch (Exception ex) {
            System.out.println("Hilo " + getId() + " productor termina por no poder insertar");
        }
        System.out.println("TERMINA HILO "+getId());
    }

    @Override
    public void run() {
        producir();
    }
}
