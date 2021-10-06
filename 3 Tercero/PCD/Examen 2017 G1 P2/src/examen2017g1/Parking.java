package examen2017g1;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Parking {

    public static final int TIPO_COCHE = 1, TIPO_BUS = 2;
    private final CanvasParking canvas;
    private int libreBus, libreCoche, esperandoBus;
    private final ReentrantLock lock;
    private final Condition esperaBus, esperaCoche;

    public Parking(CanvasParking canvas) {
        libreBus = 2;
        libreCoche = 5;
        esperandoBus = 0;
        this.canvas = canvas;
        lock = new ReentrantLock(true);
        esperaBus = lock.newCondition();
        esperaCoche = lock.newCondition();
    }

    public void entraBus() throws InterruptedException {
        lock.lock();
        try {
            canvas.espera(TIPO_BUS);
            esperandoBus++;
            if (libreBus < 1) {
                esperaBus.await();
            }
            esperandoBus--;
            libreBus--;
            canvas.entra(TIPO_BUS);
        } finally {
            lock.unlock();
        }
    }

    public int entraCoche() throws InterruptedException {
        lock.lock();
        try {
            canvas.espera(TIPO_COCHE);
            if (libreCoche < 1 && (libreBus < 1 || esperandoBus > 0)) {
                esperaCoche.await();
            }
            if (libreCoche > 0) {
                canvas.entra(TIPO_COCHE);
                libreCoche--;
                return TIPO_COCHE;
            } else {
                canvas.entra(TIPO_BUS);
                libreBus--;
                return TIPO_BUS;
            }
        } finally {
            lock.unlock();
        }
    }

    public void saleBus() {
        lock.lock();
        try {
            libreBus++;
            canvas.sale();
            if (esperandoBus > 0) {
                esperaBus.signal();
            } else {
                esperaCoche.signal();
            }
        } finally {
            lock.unlock();
        }
    }

    public void saleCoche(int lugar) {
        lock.lock();
        try {
            if (lugar == TIPO_COCHE) {
                libreCoche++;
                esperaCoche.signal();
            } else {
                libreBus++;
                if (esperandoBus > 0) {
                    esperaBus.signal();
                } else {
                    esperaCoche.signal();
                }
            }
            canvas.sale();
        } finally {
            lock.unlock();
        }
    }

}
