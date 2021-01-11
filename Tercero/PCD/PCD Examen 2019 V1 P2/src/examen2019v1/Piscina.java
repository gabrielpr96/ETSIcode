package examen2019v1;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Piscina {

    public static final int CALLE_CLUB = 1, CALLE_LIBRE = 2;
    public static final int CLIENTE_CLUB = 1, CLIENTE_LIBRE = 2;
    private final CanvasPiscina canvas;
    private int libreClub, libreLibre, esperandoLibre;
    private final ReentrantLock lock;
    private final Condition esperaClub, esperaLibre;

    public Piscina(CanvasPiscina canvas) {
        lock = new ReentrantLock(true);
        esperaClub = lock.newCondition();
        esperaLibre = lock.newCondition();
        libreClub = 4;
        libreLibre = 1;
        esperandoLibre = 0;
        this.canvas = canvas;
    }

    public void entraLibre() throws InterruptedException {
        lock.lock();
        try {
            canvas.espera(CLIENTE_LIBRE);
            esperandoLibre++;
            if (libreLibre == 0) {
                esperaLibre.await();
            }
            esperandoLibre--;
            libreLibre--;
            canvas.entra(CLIENTE_LIBRE, CALLE_LIBRE);
        } finally {
            lock.unlock();
        }
    }

    public int entraClub() throws InterruptedException {
        lock.lock();
        try {
            canvas.espera(CLIENTE_CLUB);
            if (libreClub == 0 && (libreLibre == 0 || esperandoLibre != 0)) {
                esperaClub.await();
            }
            if (libreClub == 0) {
                libreLibre--;
                canvas.entra(CLIENTE_CLUB, CALLE_LIBRE);
                return CALLE_LIBRE;
            } else {
                libreClub--;
                canvas.entra(CLIENTE_CLUB, CALLE_CLUB);
                return CALLE_CLUB;
            }
        } finally {
            lock.unlock();
        }
    }

    public void saleLibre() {
        lock.lock();
        try {
            libreLibre++;
            canvas.sale();
            if (esperandoLibre > 0) {
                esperaLibre.signal();
            } else {
                esperaClub.signal();
            }
        } finally {
            lock.unlock();
        }

    }

    public void saleClub(int calle) {
        lock.lock();
        try {
            if (calle == CALLE_CLUB) {
                libreClub++;
                esperaClub.signal();
            } else {
                libreLibre++;
                if (esperandoLibre > 0) {
                    esperaLibre.signal();
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                } else {
                    esperaClub.signal();
                }
            }
            canvas.sale();
        } finally {
            lock.unlock();
        }
    }

}
