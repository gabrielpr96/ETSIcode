package proyecto2;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Tunel {

    public static final int VEHICULO_COCHE = 1, VEHICULO_FURGONETA = 2;
    private boolean librePrelavado, libreLavado, libreSecado;
    private int cocheEsperandoPrelavado, cocheEsperandoLavado, cocheEsperandoSecado;
    private final CanvasTunel canvas;
    private final ReentrantLock lock;
    private final Condition cCochePrelavado, cCocheLavado, cCocheSecado, cFurgonetaPrelavado, cFurgonetaLavado, cFurgonetaSecado;

    public Tunel(CanvasTunel canvas) {
        this.canvas = canvas;
        librePrelavado = true;
        libreLavado = true;
        libreSecado = true;
        cocheEsperandoPrelavado = 0;
        cocheEsperandoLavado = 0;
        cocheEsperandoSecado = 0;
        lock = new ReentrantLock(true);
        cCochePrelavado = lock.newCondition();
        cCocheLavado = lock.newCondition();
        cCocheSecado = lock.newCondition();
        cFurgonetaPrelavado = lock.newCondition();
        cFurgonetaLavado = lock.newCondition();
        cFurgonetaSecado = lock.newCondition();
    }

    public void entraPrelavadoCoche() throws InterruptedException {
        lock.lock();
        try {
            canvas.entraEspera(VEHICULO_COCHE);
            cocheEsperandoPrelavado++;
            if (!librePrelavado) {
                cCochePrelavado.await();
            }
            cocheEsperandoPrelavado--;
            librePrelavado = false;
            canvas.entraPrelavado();
        } finally {
            lock.unlock();
        }
    }

    public void entraLavadoCoche() throws InterruptedException {
        lock.lock();
        try {
            cocheEsperandoLavado++;
            if (!libreLavado) {
                cCocheLavado.await();
            }
            cocheEsperandoLavado--;
            librePrelavado = true;
            libreLavado = false;
            canvas.entraLavado();
            if (cocheEsperandoPrelavado > 0) {
                cCochePrelavado.signal();
            } else {
                cFurgonetaPrelavado.signal();
            }
        } finally {
            lock.unlock();
        }
    }

    public void entraSecadoCoche() throws InterruptedException {
        lock.lock();
        try {
            cocheEsperandoSecado++;
            if (!libreSecado) {
                cCocheSecado.await();
            }
            cocheEsperandoSecado--;
            libreLavado = true;
            libreSecado = false;
            canvas.entraSecado();
            if (cocheEsperandoLavado > 0) {
                cCocheLavado.signal();
            } else {
                cFurgonetaLavado.signal();
            }
        } finally {
            lock.unlock();
        }
    }

    public void saleSecadoCoche() {
        lock.lock();
        try {
            libreSecado = true;
            canvas.saleSecado();
            if (cocheEsperandoSecado > 0) {
                cCocheSecado.signal();
            } else {
                cFurgonetaSecado.signal();
            }
        } finally {
            lock.unlock();
        }
    }

    public void entraPrelavadoFurgoneta() throws InterruptedException {
        lock.lock();
        try {
            canvas.entraEspera(VEHICULO_FURGONETA);
            if (!librePrelavado) {
                cFurgonetaPrelavado.await();
            }
            librePrelavado = false;
            canvas.entraPrelavado();
        } finally {
            lock.unlock();
        }
    }

    public void entraLavadoFurgoneta() throws InterruptedException {
        lock.lock();
        try {
            if (!libreLavado) {
                cFurgonetaLavado.await();
            }
            librePrelavado = true;
            libreLavado = false;
            canvas.entraLavado();
            if (cocheEsperandoPrelavado > 0) {
                cCochePrelavado.signal();
            } else {
                cFurgonetaPrelavado.signal();
            }
        } finally {
            lock.unlock();
        }
    }

    public void entraSecadoFurgoneta() throws InterruptedException {
        lock.lock();
        try {
            if (!libreSecado) {
                cFurgonetaSecado.await();
            }
            libreLavado = true;
            libreSecado = false;
            canvas.entraSecado();
            if (cocheEsperandoLavado > 0) {
                cCocheLavado.signal();
            } else {
                cFurgonetaLavado.signal();
            }
        } finally {
            lock.unlock();
        }
    }

    public void saleSecadoFurgoneta() {
        lock.lock();
        try {
            libreSecado = true;
            canvas.saleSecado();
            if (cocheEsperandoSecado > 0) {
                cCocheSecado.signal();
            } else {
                cFurgonetaSecado.signal();
            }
        } finally {
            lock.unlock();
        }
    }

}
