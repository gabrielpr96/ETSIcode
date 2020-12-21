package test;

import java.util.logging.Level;
import java.util.logging.Logger;

public class SinRetorno implements Runnable{
    private final int ID;
    
    public SinRetorno(int ID){
        this.ID = ID;
    }
    
    @Override
    public void run() {
        for (int i = 0; i < 2; i++) {
            System.out.println("Soy la tarea "+ID+" corriendo en el hilo "+Thread.currentThread().getId());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                System.out.println("Me interrumpen "+ID+" corriendo en el hilo "+Thread.currentThread().getId());
            }
        }
    }
    
}
