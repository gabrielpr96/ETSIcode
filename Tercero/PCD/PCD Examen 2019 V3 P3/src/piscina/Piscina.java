package piscina;

import examen2019v3.CanvasPiscina;
import interfaz.IPiscina;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;

public class Piscina extends UnicastRemoteObject implements IPiscina {

    public static final int CLIENTE_ADULTO = 1, CLIENTE_NINYO = 2;
    private final CanvasPiscina canvas;
    private int librePlazas, esperandoAdulto, dentroAdultos;

    public Piscina(CanvasPiscina canvas) throws RemoteException {
        super();
        librePlazas = 5;
        esperandoAdulto = 0;
        dentroAdultos = 0;
        this.canvas = canvas;
    }

    public synchronized void entraAdulto(int id) throws InterruptedException, RemoteException {
        canvas.espera(id, CLIENTE_ADULTO);
        esperandoAdulto++;
        while (librePlazas == 0) {
            wait();
        }
        esperandoAdulto--;
        librePlazas--;
        dentroAdultos++;
        canvas.entra(id, CLIENTE_ADULTO);
    }

    public synchronized boolean entraNinyo(int id) throws InterruptedException, RemoteException {
        if (dentroAdultos == 0) {
            return false;
        }
        canvas.espera(id, CLIENTE_NINYO);
        while (esperandoAdulto > 0 || librePlazas < 2) {
            wait();
        }
        librePlazas -= 2;
        canvas.entra(id, CLIENTE_NINYO);
        return true;
    }

    public synchronized void saleAdulto(int id) throws RemoteException {
        dentroAdultos--;
        librePlazas++;
        canvas.sale(id);
        notifyAll();
    }

    public synchronized void saleNinyo(int id) throws RemoteException {
        librePlazas += 2;
        canvas.sale(id);
        notifyAll();
    }

}
