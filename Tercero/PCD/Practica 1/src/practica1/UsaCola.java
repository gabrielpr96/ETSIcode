package practica1;

import java.util.Random;

public class UsaCola {

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
