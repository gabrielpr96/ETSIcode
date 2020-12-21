package test;

import java.util.concurrent.Callable;

public class ConRetorno implements Callable<Integer>{
    private final int ID;
    
    public ConRetorno(int ID){
        this.ID = ID;
    }
    
    @Override
    public Integer call() {
        for (int i = 0; i < 2; i++) {
            System.out.println("Soy la tarea "+ID+" corriendo en el hilo "+Thread.currentThread().getId());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                System.out.println("Me interrumpen "+ID+" corriendo en el hilo "+Thread.currentThread().getId());
            }
        }
        return ID*10;
    }
    
}
