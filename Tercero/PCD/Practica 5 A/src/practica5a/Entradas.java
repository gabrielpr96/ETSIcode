package practica5a;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Entradas implements Runnable{
    
    Agencia a;
    public Entradas(Agencia a){
        this.a = a;
    }
    
    @Override
    public void run(){
        System.out.println("Inicio del hilo Entradas "+Thread.currentThread().getId());
        Random r = new Random();
        r.setSeed(System.nanoTime());
        try {
            a.entraEntrada();
            Thread.sleep(2000+r.nextInt(1000));
            a.saleEntrada();
        } catch (InterruptedException ex) {
            Logger.getLogger(Entradas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
