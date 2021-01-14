package examen2018v3;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Linea {

    public static final int TIPO_PANTALON = 1, TIPO_CAMISA = 2;
    private final CanvasLinea canvas;
    private volatile int libreCorte, libreCosido, esperandoPantalones;
    private final ReentrantLock lock;
    private final Condition esperaCorte, esperaCoserPantalon, esperaCoserCamisa;

    public Linea(CanvasLinea canvas) {
        libreCorte = 2;
        libreCosido = 1;
        esperandoPantalones = 0;
        this.canvas = canvas;
        lock = new ReentrantLock(true);
        esperaCorte = lock.newCondition();
        esperaCoserPantalon = lock.newCondition();
        esperaCoserCamisa = lock.newCondition();
    }

    public void entraCorte(int tipo) throws InterruptedException {
        lock.lock();
        try {
            canvas.espera(tipo);
            if (libreCorte < 1) {
                esperaCorte.await();
            }
            libreCorte--;
            canvas.corte();
        } finally {
            lock.unlock();
        }
    }

    public void coserPantalon() throws InterruptedException {
        System.out.println(Thread.currentThread().getId()+" P encuentra "+lock.isLocked());
        lock.lock();
        System.out.println(Thread.currentThread().getId()+" P Adquiero el bloqueo");
        try {
            canvas.corteTermina();
            esperandoPantalones++;
            if (libreCosido < 1) {
                esperaCoserPantalon.await();
                System.out.println(Thread.currentThread().getId()+" P Me despiertan con "+libreCosido+" "+lock.isHeldByCurrentThread());
            }else System.out.println(Thread.currentThread().getId()+" P Entro porque "+libreCosido+" "+lock.isHeldByCurrentThread());
            esperandoPantalones--;
            libreCosido--;
            libreCorte++;
            canvas.cosido();
            esperaCorte.signal();
        } finally {
            lock.unlock();
        }
    }

    public void coserCamisa() throws InterruptedException {
        System.out.println(Thread.currentThread().getId()+" C encuentra "+lock.isLocked());
        lock.lock();
        System.out.println(Thread.currentThread().getId()+" C Adquiero el bloqueo");
        try {
            canvas.corteTermina();
            if (libreCosido < 1 || esperandoPantalones > 0) {
                esperaCoserCamisa.await();
                System.out.println(Thread.currentThread().getId()+" C Me despiertan con "+libreCosido+" "+lock.isHeldByCurrentThread());
            }else System.out.println(Thread.currentThread().getId()+" C Entro porque "+libreCosido+" "+lock.isHeldByCurrentThread());
            libreCosido--;
            libreCorte++;
            canvas.cosido();
            esperaCorte.signal();
        } finally {
            lock.unlock();
        }
    }

    public void saleCoser() {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getId()+" Termino "+libreCosido);
            libreCosido++;
            canvas.salida();
            if(esperandoPantalones > 0){
                esperaCoserPantalon.signal();
                System.out.println(Thread.currentThread().getId()+" Despierto pantalon");
            }else{
                esperaCoserCamisa.signal();
                System.out.println(Thread.currentThread().getId()+" Despierto camisa");
            }
        } finally {
            lock.unlock();
        }
    }

}
