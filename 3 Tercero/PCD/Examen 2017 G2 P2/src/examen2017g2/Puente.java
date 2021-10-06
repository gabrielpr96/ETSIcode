package examen2017g2;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Puente {

    public static final int TIPO_ADULTO = 1, TIPO_NINO = 2;
    private final CanvasPuente canvas;
    private int peso, esperandoNinos;
    private final ReentrantLock lock;
    private final Condition esperaAdulto, esperaNino;

    public Puente(CanvasPuente canvas) {
        peso = 30;
        esperandoNinos = 0;
        this.canvas = canvas;
        lock = new ReentrantLock();
        esperaAdulto = lock.newCondition();
        esperaNino = lock.newCondition();
    }

    public void entraAdulto() throws InterruptedException {
        lock.lock();
        try {
            canvas.espera(TIPO_ADULTO);
            if (esperandoNinos > 0 || peso < 15) {
                esperaAdulto.await();
            }
            peso -= 15;
            if (peso >= 15) {
                esperaAdulto.signal();
            }
            canvas.entra();
        } finally {
            lock.unlock();
        }
    }

    public void entraNino() throws InterruptedException {
        lock.lock();
        try {
            canvas.espera(TIPO_NINO);
            esperandoNinos++;
            if (peso < 10) {
                esperaNino.await();
            }
            esperandoNinos--;
            peso -= 10;
            if (peso >= 10) {
                esperaNino.signal();
            }
            canvas.entra();
        } finally {
            lock.unlock();
        }
    }

    public void saleAdulto() {
        lock.lock();
        try {
            peso += 15;
            canvas.sale();
            if (esperandoNinos > 0) {
                esperaNino.signal();
            } else {
                esperaAdulto.signal();
            }
        } finally {
            lock.unlock();
        }
    }

    public void saleNino() {
        lock.lock();
        try {
            peso += 10;
            canvas.sale();
            if (esperandoNinos > 0) {
                esperaNino.signal();
            } else {
                if (peso >= 15) {
                    esperaAdulto.signal();
                }
            }
        } finally {
            lock.unlock();
        }
    }

}
