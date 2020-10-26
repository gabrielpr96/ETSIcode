package practica2;

import java.util.Random;

/**
 * Clase que 
 * @author borja
 */
public class Productor extends Thread{
    private final ICola c;
    public Productor(ICola c){
        this.c = c;
    }
    
    @Override
    public void run(){
        Random r = new Random();
        r.setSeed(System.nanoTime());
        for (int i = 0; i < 10; i++) {
            float n = r.nextFloat() * 100;
            try {
                c.Acola(n);
                System.out.println("Hilo "+getId()+" Inserto: "+n);
            } catch (Exception ex) {
                System.out.println("Hilo "+getId()+" Error al insertar: "+ex.getMessage());
            }
        }
    }
}
