package practica1;

import java.util.Random;

/**
 * Clase principal de la primera práctica de PCD
 * @author Borja López
 */
public class UsaCola {

    /**
     * Método que crea una cola de 4 elementos y aleatoriamente introduce números del 0 al 9 o los saca.
     * @param args No se utilizan
     */
    public static void main(String[] args) {
        Random r = new Random();
        r.setSeed(System.nanoTime());
        Cola c = new Cola(4);
        
        for (int i = 0; i < 10; i++) {
            try {
                if (r.nextInt(2) == 0) {
                    c.Desacola();
                } else {
                    c.Acola(i);
                }
            } catch (Exception e) {
                System.out.println("ERROR: "+e.getMessage());
            }
        }
        
        System.out.println("Cola: "+c);
    }

}
