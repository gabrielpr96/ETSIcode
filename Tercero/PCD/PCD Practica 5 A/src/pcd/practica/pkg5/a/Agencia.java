package pcd.practica.pkg5.a;

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
    
    public synchronized void entraViaje(Thread cliente) throws InterruptedException{
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
    public synchronized void saleViaje(Thread cliente){
        if(cliente == A){
            A = null;
        }else if(cliente == B){
            B = null;
        }
        canvas.representa(A, B, colaViajes, colaEntradas);
        notifyAll();
    }
    public synchronized void entraEntrada(Thread cliente) throws InterruptedException{
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
    public synchronized void saleEntrada(Thread cliente){
        if(cliente == A){
            A = null;
        }else if(cliente == B){
            B = null;
        }
        canvas.representa(A, B, colaViajes, colaEntradas);
        notifyAll();
    }
}
