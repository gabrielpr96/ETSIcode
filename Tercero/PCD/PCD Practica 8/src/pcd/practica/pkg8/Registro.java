package pcd.practica.pkg8;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Registro {
    private final CanvasRegistro canvas;
    private final ReentrantLock l;
    private final Condition cRegistro, cNota;
    private int esperandoRegistro, esperandoNota;
    
    public Registro(CanvasRegistro canvas){
        this.canvas = canvas;
        l = new ReentrantLock(true);
        cRegistro = l.newCondition();
        cNota = l.newCondition();
        esperandoRegistro = 0;
        esperandoNota = 0;
    }
    
    public void entraRegistro(){
        l.lock();
        try {
            esperandoRegistro++;
            canvas.entraClienteRegistro();
            if(canvas.oficialesOcupados() || canvas.registradoresOcupados())
                cRegistro.await();
            esperandoRegistro--;
            canvas.atendidoPorOficial();
            canvas.atendidoPorRegistrador();
        } catch (InterruptedException ex) {
            Logger.getLogger(Registro.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            l.unlock();
        }
    }
    public void entraNota(){
        l.lock();
        try {
            esperandoNota++;
            canvas.entraClienteNota();
            if(canvas.oficialesOcupados() && canvas.registradoresOcupados())
                cNota.await();
            esperandoNota--;
            if(!canvas.oficialesOcupados())
                canvas.atendidoPorOficial();
            else if(!canvas.registradoresOcupados())
                canvas.atendidoPorRegistrador();
        } catch (InterruptedException ex) {
            Logger.getLogger(Registro.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            l.unlock();
        }
    }
    
    public void saleRegistro(){
        sale();
    }
    public void saleNota(){
        sale();
    }
    
    public void sale(){
        l.lock();
        try {
            canvas.saleCliente();
            if(!canvas.oficialesOcupados() && !canvas.registradoresOcupados()){
                //Pueden entrar clientes registro si los hay, si no puedo despertar a dos clientes nota
                if(esperandoRegistro > 0){
                    cRegistro.signal();
                }else{
                    cNota.signal();
                    cNota.signal();
                }
            }else{
                //Solo puede entrar un cliente nota, si no hay clientes registro esperasndo, porque esos tienen prioridad. Excepto si solo quedan oficiales, entonces sí
                if(esperandoRegistro < 0 || !canvas.oficialesOcupados())
                    cNota.signal();
            }
        } finally {
            l.unlock();
        }
    }
}
