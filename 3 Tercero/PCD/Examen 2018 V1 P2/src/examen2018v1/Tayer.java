package examen2018v1;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Tayer {

    public static final int COCHE_TURISMO = 1, COCHE_CAMION = 2;
    private final CanvasTayer canvas;
    private final ReentrantLock lock;
    private final Condition esperaCamion, esperaCoche;
    private int libreOperarios, esperandoCamiones;

    public Tayer(CanvasTayer canvas) {
        libreOperarios = 4;
        esperandoCamiones = 0;
        lock = new ReentrantLock(true);
        esperaCamion = lock.newCondition();
        esperaCoche = lock.newCondition();
        this.canvas = canvas;
    }

    public void entraCamion() throws InterruptedException {
        lock.lock();
        try {
            canvas.espera(COCHE_CAMION);
            esperandoCamiones++;
            if (libreOperarios < 2) {
                esperaCamion.await();
            }
            esperandoCamiones--;
            libreOperarios -= 2;
            canvas.entra();
        } finally {
            lock.unlock();
        }
    }

    public void entraCoche() throws InterruptedException {
        lock.lock();
        try {
            canvas.espera(COCHE_TURISMO);
            if (libreOperarios < 1 || esperandoCamiones > 0) {
                esperaCoche.await();
            }
            libreOperarios--;
            canvas.entra();
        } finally {
            lock.unlock();
        }
    }

    public void saleCamion() {
        lock.lock();
        try {
            libreOperarios += 2;
            canvas.sale();
            if(esperandoCamiones > 0){
                esperaCamion.signal();
            }else{
                esperaCoche.signal();
                esperaCoche.signal();
            }
        } finally {
            lock.unlock();
        }
    }

    public void saleCoche() {
        lock.lock();
        try {
            libreOperarios++;
            canvas.sale();
            if(libreOperarios >= 2 && esperandoCamiones > 0){
                esperaCamion.signal();
            }else if(esperandoCamiones == 0){
                esperaCoche.signal();
            }
        } finally {
            lock.unlock();
        }
    }

}
