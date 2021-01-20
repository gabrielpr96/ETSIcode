package proyecto2;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Revision {

    public static final int ALUMNO_TEORIA = 1, ALUMNO_PRACTICAS = 2;
    private int libreProfesor, librePracticas, esperandoPracticas;
    private final CanvasRevision canvas;
    private final ReentrantLock lock;
    private final Condition cTeoria, cPracticas;

    public Revision(CanvasRevision canvas) {
        this.canvas = canvas;
        libreProfesor = 3;
        librePracticas = 2;
        esperandoPracticas = 0;
        lock = new ReentrantLock(true);
        cTeoria = lock.newCondition();
        cPracticas = lock.newCondition();
    }

    public void entraTeoria() throws InterruptedException {
        lock.lock();
        try {
            canvas.entra(ALUMNO_TEORIA);
            if (libreProfesor < 1) {
                cTeoria.await();
            }
            libreProfesor--;
            canvas.atendiendo();
        } finally {
            lock.unlock();
        }
    }

    public void entraPracticas() throws InterruptedException {
        lock.lock();
        try {
            canvas.entra(ALUMNO_PRACTICAS);
            esperandoPracticas++;
            if (libreProfesor < 1 || librePracticas < 1) {
                cPracticas.await();
            }
            esperandoPracticas--;
            libreProfesor--;
            librePracticas--;
            canvas.atendiendo();
        } finally {
            lock.unlock();
        }
    }

    public void saleTeoria() {
        lock.lock();
        try {
            libreProfesor++;
            canvas.sale();
            if (esperandoPracticas > 0 && librePracticas > 0) {
                cPracticas.signal();
            } else {
                cTeoria.signal();
            }
        } finally {
            lock.unlock();
        }
    }

    public void salePracticas() {
        lock.lock();
        try {
            libreProfesor++;
            librePracticas++;
            canvas.sale();
            if (esperandoPracticas > 0) {
                cPracticas.signal();
            } else {
                cTeoria.signal();
            }
        } finally {
            lock.unlock();
        }
    }
}
