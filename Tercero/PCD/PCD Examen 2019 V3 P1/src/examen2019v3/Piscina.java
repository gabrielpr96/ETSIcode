package examen2019v3;

import examen2019v3.CanvasPiscina;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;

public class Piscina {

    public static final int CLIENTE_ADULTO = 1, CLIENTE_NINYO = 2;
    private final CanvasPiscina canvas;
    private int librePlazas, esperandoAdulto, dentroAdultos;

    public Piscina(CanvasPiscina canvas) {
        librePlazas = 5;
        esperandoAdulto = 0;
        dentroAdultos = 0;
        this.canvas = canvas;
    }

    public synchronized void entraAdulto() throws InterruptedException {
        canvas.espera(CLIENTE_ADULTO);
        esperandoAdulto++;
        while (librePlazas == 0) {
            wait();
        }
        esperandoAdulto--;
        librePlazas--;
        dentroAdultos++;
        canvas.entra(CLIENTE_ADULTO);
    }

    public synchronized boolean entraNinyo() throws InterruptedException {
        if (dentroAdultos == 0) {
            return false;
        }
        canvas.espera(CLIENTE_NINYO);
        while (esperandoAdulto > 0 || librePlazas < 2) {
            wait();
        }
        librePlazas -= 2;
        canvas.entra(CLIENTE_NINYO);
        return true;
    }

    public synchronized void saleAdulto() {
        dentroAdultos--;
        librePlazas++;
        canvas.sale();
        notifyAll();
    }

    public synchronized void saleNinyo() {
        librePlazas += 2;
        canvas.sale();
        notifyAll();
    }

}
