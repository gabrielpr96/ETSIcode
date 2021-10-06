package practica4;

import java.util.Random;

public class Consumidor implements Runnable {

    private final ICola c;

    public Consumidor(ICola c) {
        this.c = c;
    }

    /**
     * Extrae 15 un elementos de la cola
     * Espera entre 1 y 3 segundos.
     */
    private void consumir() {
        Random r = new Random();
        r.setSeed(System.nanoTime());
        try {
            for (int i = 0; i < 15; i++) {
                Thread.sleep(1000 + Math.abs(r.nextInt()) % 2000);
                System.out.println("Hilo " + Thread.currentThread().getId() + " consumidor extrae: " + c.Desacola());
            }
        } catch (InterruptedException ex) {
            System.out.println("Hilo "+Thread.currentThread().getId()+" consumidor interrumpido");
        } catch (Exception ex) {
            System.out.println("Hilo "+Thread.currentThread().getId()+" consumidor termina por no poder extraer");
        }
        System.out.println("TERMINA HILO "+Thread.currentThread().getId());
    }

    @Override
    public void run() {
        consumir();
    }
}
