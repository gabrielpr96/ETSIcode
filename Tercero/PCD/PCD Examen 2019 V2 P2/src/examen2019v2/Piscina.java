package examen2019v2;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Piscina {

    private final CanvasPiscina canvas;
    private int librePiscina, libreAletas, librePalas;
    private final ReentrantLock lock;
    private final Condition esperaPiscina, esperaAletas, esperaPalas;

    public Piscina(CanvasPiscina canvas) {
        librePiscina = 5;
        libreAletas = 6;
        librePalas = 5;
        lock = new ReentrantLock(true);
        esperaPiscina = lock.newCondition();
        esperaAletas = lock.newCondition();
        esperaPalas = lock.newCondition();
        this.canvas = canvas;
    }

    public void entraPiscina() throws InterruptedException {
        lock.lock();
        try {
            canvas.entraSistema();
            if (librePiscina == 0) {
                esperaPiscina.await();
            }
            librePiscina--;
            canvas.entraPiscina();
        } finally {
            lock.unlock();
        }
    }

    public void cogeMaterial() throws InterruptedException {
        lock.lock();
        try {
            canvas.comienzaBusqueda();
            if (libreAletas < 2) {
                esperaAletas.await();
            }
            libreAletas -= 2;
            canvas.obtieneAletas();
            if (librePalas < 2) {
                esperaPalas.await();
            }
            canvas.obtienePalas();
            librePalas -= 2;
        } finally {
            lock.unlock();
        }

    }

    public void sueltaMaterial() {
        lock.lock();
        try {
            libreAletas += 2;
            librePalas += 2;
            esperaAletas.signal();
            esperaPalas.signal();
        } finally {
            lock.unlock();
        }
    }

    public void salePiscina() {
        lock.lock();
        try {
            librePiscina++;
            esperaPiscina.signal();
            canvas.saleSistema();
        } finally {
            lock.unlock();
        }
    }

}
