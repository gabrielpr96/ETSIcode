package examen2019v3;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Piscina {

    public static final int CLIENTE_ADULTO = 1, CLIENTE_NINYO = 2;
    private final CanvasPiscina canvas;
    private int librePlazas, esperandoAdulto, dentroAdultos;
    private final ReentrantLock lock;
    private final Condition esperaAdulto, esperaNinyo;

    public Piscina(CanvasPiscina canvas) {
        librePlazas = 5;
        esperandoAdulto = 0;
        dentroAdultos = 0;
        this.canvas = canvas;
        lock = new ReentrantLock(true);
        esperaAdulto = lock.newCondition();
        esperaNinyo = lock.newCondition();
    }

    public void entraAdulto() throws InterruptedException {
        lock.lock();
        try {
            canvas.espera(CLIENTE_ADULTO);
            esperandoAdulto++;
            if (librePlazas == 0) {
                esperaAdulto.await();
            }
            esperandoAdulto--;
            librePlazas--;
            dentroAdultos++;
            canvas.entra(CLIENTE_ADULTO);
        } finally {
            lock.unlock();
        }
    }

    public boolean entraNinyo() throws InterruptedException {
        lock.lock();
        try {
            if (dentroAdultos == 0) {
                return false;
            }
            canvas.espera(CLIENTE_NINYO);
            if (esperandoAdulto > 0 || librePlazas < 2) {
                esperaNinyo.await();
            }
            librePlazas -= 2;
            canvas.entra(CLIENTE_NINYO);
            return true;
        } finally {
            lock.unlock();
        }
    }

    public void saleAdulto() {
        lock.lock();
        try {
            dentroAdultos--;
            librePlazas++;
            canvas.sale();
            if(dentroAdultos == 0 && esperandoAdulto == 0 && librePlazas >= 2){
                esperaNinyo.signal();
            }else{
                esperaAdulto.signal();
            }
        } finally {
            lock.unlock();
        }
    }

    public void saleNinyo() {
        lock.lock();
        try {
            librePlazas += 2;
            canvas.sale();
            if(dentroAdultos == 0 && esperandoAdulto == 0 && librePlazas >= 2){
                esperaNinyo.signal();
            }else{
                esperaAdulto.signal();
                esperaAdulto.signal();
            }
        } finally {
            lock.unlock();
        }
    }

}
