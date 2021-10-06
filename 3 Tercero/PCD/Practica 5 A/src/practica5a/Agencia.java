package practica5a;

import java.util.LinkedList;
import java.util.Queue;

public class Agencia {
    //Null indica que el empleado está libre
    private Thread A, B;
    private final Queue<Thread> colaViajes, colaEntradas;
    private final CanvasAgencia canvas;
    
    public Agencia(CanvasAgencia canvas){
        colaViajes = new LinkedList<>();
        colaEntradas = new LinkedList<>();
        A = null;
        B = null;
        this.canvas = canvas;
    }
    
    /**
     * Introduce un viaje en la cola de viajes y esperará a ser atendido por A o por B.
     * @throws InterruptedException 
     */
    public synchronized void entraViaje() throws InterruptedException{
        Thread cliente = Thread.currentThread();
        canvas.representa(A, B, colaViajes, colaEntradas);
        colaViajes.add(cliente);
        while(colaViajes.element() != cliente || (A != null && B != null)){
            wait();
        }
        colaViajes.remove();
        if(B == null){
            B = cliente;
        }else if(A == null){
            A = cliente;
        }
        canvas.representa(A, B, colaViajes, colaEntradas);
    }
    /**
     * Un viaje que está siendo atendido deja de estarlo.
     */
    public synchronized void saleViaje(){
        Thread cliente = Thread.currentThread();
        if(cliente == A){
            A = null;
        }else if(cliente == B){
            B = null;
        }
        canvas.representa(A, B, colaViajes, colaEntradas);
        notifyAll();
    }
    
    /**
     * Introduce una entrada en la cola de entradas y esperará a ser atendido por A (Solo si la cola de viajes está vacía) o por B.
     * @throws InterruptedException 
     */
    public synchronized void entraEntrada() throws InterruptedException{
        Thread cliente = Thread.currentThread();
        canvas.representa(A, B, colaViajes, colaEntradas);
        colaEntradas.add(cliente);
        while(colaEntradas.element() != cliente || (!(A == null && colaViajes.isEmpty()) && B != null)){
            wait();
        }
        colaEntradas.remove();
        if(B == null){
            B = cliente;
        }else if(A == null && colaViajes.isEmpty()){
            A = cliente;
        }
        canvas.representa(A, B, colaViajes, colaEntradas);
    }
    /**
     * Una entrada que está siendo atendido deja de estarlo.
     */
    public synchronized void saleEntrada(){
        Thread cliente = Thread.currentThread();
        if(cliente == A){
            A = null;
        }else if(cliente == B){
            B = null;
        }
        canvas.representa(A, B, colaViajes, colaEntradas);
        notifyAll();
    }
}
