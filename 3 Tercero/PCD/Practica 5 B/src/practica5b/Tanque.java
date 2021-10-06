package practica5b;

import java.awt.List;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Tanque {
    //Null indica que el empleado está libre
    private final ArrayList<Thread> tanquePC, tanqueLlanta;
    private final Queue<Thread> colaPC, colaLlanta;
    private final CanvasTanque canvas;
    
    public Tanque(CanvasTanque canvas){
        tanquePC = new ArrayList<>();
        tanqueLlanta = new ArrayList<>();
        colaPC = new LinkedList<>();
        colaLlanta = new LinkedList<>();
        this.canvas = canvas;
    }
    
    public synchronized void entraPC(Thread pc) throws InterruptedException{
        canvas.representa(tanquePC, tanqueLlanta, colaPC, colaLlanta);
        colaPC.add(pc);
        while(colaPC.element() != pc || !((tanquePC.size() == 0 && tanqueLlanta.size() <= 3) || (tanquePC.size() == 1 && tanqueLlanta.size() == 0))){
            wait();
        }
        colaPC.remove();
        tanquePC.add(pc);
        canvas.representa(tanquePC, tanqueLlanta, colaPC, colaLlanta);
    }
    public synchronized void salePC(Thread pc){
       tanquePC.remove(pc);
        canvas.representa(tanquePC, tanqueLlanta, colaPC, colaLlanta);
        notifyAll();
    }
    public synchronized void entraLLanta(Thread llanta) throws InterruptedException{
        canvas.representa(tanquePC, tanqueLlanta, colaPC, colaLlanta);
        colaLlanta.add(llanta);
        while(colaLlanta.element() != llanta || !((tanquePC.size() == 0 && tanqueLlanta.size() < 5) || (tanquePC.size() == 1 && tanqueLlanta.size() < 3))){
            wait();
        }
        colaLlanta.remove();
        tanqueLlanta.add(llanta);
        canvas.representa(tanquePC, tanqueLlanta, colaPC, colaLlanta);
    }
    public synchronized void saleLLanta(Thread llanta){
        tanqueLlanta.remove(llanta);
        canvas.representa(tanquePC, tanqueLlanta, colaPC, colaLlanta);
        notifyAll();
    }
}
