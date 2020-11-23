package pcd.practica.pkg5.b;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LLanta implements Runnable{
    
    Tanque a;
    public LLanta(Tanque a){
        this.a = a;
    }
    
    @Override
    public void run(){
        System.out.println("Inicio del hilo LLanta "+Thread.currentThread().getId());
        Random r = new Random();
        r.setSeed(System.nanoTime());
        try {
            a.entraLLanta(Thread.currentThread());
            Thread.sleep(2000+r.nextInt(1000));
            a.saleLLanta(Thread.currentThread());
        } catch (InterruptedException ex) {
            Logger.getLogger(LLanta.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
