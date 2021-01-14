/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package examen2018v3;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pedro
 */
public class Linea {

    public static final int TIPO_PANTALON = 1, TIPO_CAMISA = 2;
    private boolean librecoser = true;
    private int librecorte = 2, esperapantalon = 0;
    ReentrantLock mutex = new ReentrantLock();
    Condition colacorte = mutex.newCondition();
    Condition colacosercamisa = mutex.newCondition();
    Condition colacoserpantalon = mutex.newCondition();
    private CanvasLinea canvas;
    
    public Linea(CanvasLinea canvas){
        this.canvas = canvas;
    }

    public void EntraCorte(int tipo) {
        mutex.lock();
        try {
            canvas.espera(tipo);
            if (librecorte == 0) {
                try {
                    colacorte.await();
                } catch (InterruptedException ex) {
                    Logger.getLogger(Linea.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            librecorte--;
            canvas.corte();
        } finally {
            mutex.unlock();
        }
    }

    public void CoserCamisa() {
        mutex.lock();
        try {
            canvas.corteTermina();
            if (!librecoser) {
                try {
                    colacosercamisa.await();
                } catch (InterruptedException ex) {
                    Logger.getLogger(Linea.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            librecoser = false;
            librecorte++;
            canvas.cosido();
            colacorte.signal();
        } finally {
            mutex.unlock();
        }
    }

    public void CoserPantalon() {
        mutex.lock();
        try {
            canvas.corteTermina();
            esperapantalon++;
            if (!librecoser) {
                try {
                    colacoserpantalon.await();
                } catch (InterruptedException ex) {
                    Logger.getLogger(Linea.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            esperapantalon--;
            librecoser = false;
            librecorte++;
            canvas.cosido();
            colacorte.signal();
        } finally {
            mutex.unlock();
        }
    }

    public void SaleCoser() {
        mutex.lock();
        try {
            canvas.salida();
            librecoser = true;
            if(esperapantalon>0) colacoserpantalon.signal();
            else colacosercamisa.signal();
        } finally {
            mutex.unlock();
        }
    }
}
