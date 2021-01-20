package practica5a;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Viajes extends Thread{
    
    Agencia a;
    public Viajes(Agencia a){
        this.a = a;
    }
    
    @Override
    public void run(){
        System.out.println("Inicio del hilo Viajes "+getId());
        Random r = new Random();
        r.setSeed(System.nanoTime());
        try {
            a.entraViaje();
            Thread.sleep(3000+r.nextInt(2000));
            a.saleViaje();
        } catch (InterruptedException ex) {
            Logger.getLogger(Viajes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
