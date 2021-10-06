package pcd2919v2p2;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Piscina {

    private int librePiscina, libreAletas, librePalas;
    private final CanvasPiscina canvas;
    private final ReentrantLock lock;
    private final Condition cPiscina, cAletas, cPalas;

    public Piscina(CanvasPiscina canvas) {
        this.canvas = canvas;
        librePiscina = 5;
        libreAletas = 6;
        librePalas = 5;
        lock = new ReentrantLock(true);
        cPiscina = lock.newCondition();
        cAletas = lock.newCondition();
        cPalas = lock.newCondition();
    }

    public void entraPiscina() throws InterruptedException {
        lock.lock();
        try {
            canvas.entra();
            while (librePiscina < 1) {
                cPiscina.await();
            }
            librePiscina--;
            canvas.calienta();
        } finally {
            lock.unlock();
        }
    }

    public void cogeMaterial() throws InterruptedException {
        lock.lock();
        try {
            canvas.coge();
            while (libreAletas < 2) {
                cAletas.await();
            }
            libreAletas -= 2;
            canvas.obtieneAletas();
            while (librePalas < 2) {
                cPalas.await();
            }
            librePalas -= 2;
            canvas.obtienePalas();
        } finally {
            lock.unlock();
        }
    }

    public void sueltaMaterial() {
        lock.lock();
        try {
            libreAletas += 2;
            cAletas.signal();
            librePalas += 2;
            cPalas.signal();
        } finally {
            lock.unlock();
        }
    }

    public void salePiscina() {
        lock.lock();
        try {
            librePiscina++;
            canvas.sale();
            cPiscina.signal();
        } finally {
            lock.unlock();
        }
    }
}
