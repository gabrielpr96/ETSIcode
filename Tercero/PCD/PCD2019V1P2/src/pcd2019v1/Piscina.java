package pcd2019v1;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Piscina {

    public static final int CALLE_LIBRE = 1, CALLE_CLUB = 2, NADADOR_LIBRE = 1, NADADOR_CLUB = 2;
    private int libreLibre, libreClub, esperandoLibre;
    private final CanvasPiscina canvas;
    private final ReentrantLock lock;
    private final Condition cLibre, cClub;

    public Piscina(CanvasPiscina canvas) {
        libreLibre = 1;
        libreClub = 4;
        esperandoLibre = 0;
        this.canvas = canvas;
        lock = new ReentrantLock(true);
        cLibre = lock.newCondition();
        cClub = lock.newCondition();
    }

    public void entraLibre() throws InterruptedException {
        lock.lock();
        try {
            canvas.espera(NADADOR_LIBRE);
            esperandoLibre++;
            if (libreLibre < 1) {
                cLibre.await();
            }
            esperandoLibre--;
            libreLibre--;
            canvas.entraLibre();
        } finally {
            lock.unlock();
        }
    }

    public int entraClub() throws InterruptedException {
        lock.lock();
        try {

            canvas.espera(NADADOR_CLUB);
            if (libreClub < 1 && libreLibre < 1) {
                cClub.await();
            }
            if (libreClub > 0) {
                libreClub--;
                canvas.entraClub();
                return CALLE_CLUB;
            } else {
                libreLibre--;
                canvas.entraLibre();
                return CALLE_LIBRE;
            }
        } finally {
            lock.unlock();
        }
    }

    public void saleLibre() {
        lock.lock();
        try {
            canvas.sale();
            libreLibre++;
            if (esperandoLibre > 0) {
                cLibre.signal();
            } else {
                cClub.signal();
            }
        } finally {
            lock.unlock();
        }
    }

    public void saleClub(int cual) {
        lock.lock();
        try {
            canvas.sale();
            if (cual == CALLE_CLUB) {
                libreClub++;
                cClub.signal();
            } else {
                libreLibre++;
                if (esperandoLibre > 0) {
                    cLibre.signal();
                } else {
                    cClub.signal();
                }
            }
        } finally {
            lock.unlock();
        }
    }
}
