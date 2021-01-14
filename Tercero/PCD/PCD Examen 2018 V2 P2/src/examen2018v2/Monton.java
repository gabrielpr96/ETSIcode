package examen2018v2;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Monton {

    private final CanvasMina canvas;
    private int cantidad;
    private int esperandoGrande;
    private final ReentrantLock lock;
    private final Condition esperaPequenya, esperaGrande;

    public Monton(CanvasMina canvas) {
        cantidad = 4;
        esperandoGrande = 0;
        this.canvas = canvas;
        canvas.cantidad(cantidad);
        lock = new ReentrantLock(true);
        esperaPequenya = lock.newCondition();
        esperaGrande = lock.newCondition();
    }

    public void cargaPoco(int id) throws InterruptedException {
        lock.lock();
        try {
            canvas.estado(id, 1);
            if (cantidad == 0 || (cantidad >= 2 && esperandoGrande > 0)) {
                esperaPequenya.await();
            }
            cantidad--;
            canvas.estado(id, 0);
            canvas.cantidad(cantidad);
        } finally {
            lock.unlock();
        }
    }

    public void cargaMucho() throws InterruptedException {
        lock.lock();
        try {
            canvas.estado(2, 1);
            esperandoGrande++;
            if (cantidad < 2) {
                esperaGrande.await();
            }
            esperandoGrande--;
            cantidad -= 2;
            canvas.estado(2, 0);
            canvas.cantidad(cantidad);
            if(cantidad > 1){
                esperaPequenya.signal();
                esperaPequenya.signal();
            }else if(cantidad > 0){
                esperaPequenya.signal();
            }
        } finally {
            lock.unlock();
        }
    }

    public void grandeSeVa() {
        lock.lock();
        try {
            esperandoGrande = 0;
            canvas.estado(2, 2);
        } finally {
            lock.unlock();
        }
    }

    public void pequenyaSeVa(int id) {
        lock.lock();
        try {
            canvas.estado(id, 2);
        } finally {
            lock.unlock();
        }
    }

    public void Rellena(int tn) {
        lock.lock();
        try {
            cantidad += tn;
            canvas.cantidad(cantidad);
            if(cantidad > 1){
                if(esperandoGrande > 0){
                    esperaGrande.signal();
                }else{
                    esperaPequenya.signal();
                    esperaPequenya.signal();
                }
            }else{
                esperaPequenya.signal();
            }
        } finally {
            lock.unlock();
        }
    }

}
