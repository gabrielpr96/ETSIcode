package examen2018v4;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Vibradora {

    public static final int TIPO_INOX = 1, TIPO_HIERRO = 2, TIPO_VACIO = 0, MAX_INOX = 3, MAX_HIERRO = 2;
    private final CanvasVibradora canvas;
    private int espacioOcupado, dentro, esperandoInox;
    private final ReentrantLock lock;
    private final Condition esperaInox, esperaHierro;

    public Vibradora(CanvasVibradora canvas) {
        espacioOcupado = 0;
        esperandoInox = 0;
        dentro = TIPO_VACIO;
        this.canvas = canvas;
        lock = new ReentrantLock(true);
        esperaInox = lock.newCondition();
        esperaHierro = lock.newCondition();
    }

    public void entraInox() throws InterruptedException {
        lock.lock();
        try {
            canvas.espera(TIPO_INOX);
            esperandoInox++;
            if (dentro == TIPO_HIERRO || espacioOcupado >= MAX_INOX) {
                esperaInox.await();
            }
            esperandoInox--;
            espacioOcupado++;
            dentro = TIPO_INOX;
            canvas.entra();
            if (espacioOcupado < MAX_INOX) {
                esperaInox.signal();
            }
        } finally {
            lock.unlock();
        }
    }

    public void entraHierro() throws InterruptedException {
        lock.lock();
        try {
            canvas.espera(TIPO_HIERRO);
            if (dentro == TIPO_INOX || espacioOcupado >= MAX_HIERRO) {
                esperaHierro.await();
            }
            espacioOcupado++;
            dentro = TIPO_HIERRO;
            canvas.entra();
            if (espacioOcupado < MAX_HIERRO) {
                esperaHierro.signal();
            }
        } finally {
            lock.unlock();
        }
    }

    public void saleInox() {
        lock.lock();
        try {
            canvas.sale();
            espacioOcupado--;
            if (espacioOcupado == 0) {
                dentro = TIPO_VACIO;
                if(esperandoInox == 0){
                    esperaHierro.signal();
                }else{
                    esperaInox.signal();
                }
            } else {
                esperaInox.signal();
            }
        } finally {
            lock.unlock();
        }
    }

    public void saleHierro() {
        lock.lock();
        try {
            canvas.sale();
            espacioOcupado--;
            if (espacioOcupado == 0) {
                dentro = TIPO_VACIO;
                if(esperandoInox > 0){
                    esperaInox.signal();
                }else{
                    esperaHierro.signal();
                }
            } else {
                esperaHierro.signal();
            }
        } finally {
            lock.unlock();
        }
    }

}
