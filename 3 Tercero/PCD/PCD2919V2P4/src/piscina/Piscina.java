package piscina;

import interfaz.IPiscina;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import pcd2919v2p4.CanvasPiscina;

public class Piscina extends UnicastRemoteObject implements IPiscina{

    private int librePiscina, libreAletas, librePalas;
    private final CanvasPiscina canvas;

    public Piscina(CanvasPiscina canvas) throws RemoteException {
        super();
        this.canvas = canvas;
        librePiscina = 5;
        libreAletas = 6;
        librePalas = 5;
    }

    public synchronized void entraPiscina() throws InterruptedException, RemoteException {
        canvas.entra();
        while (librePiscina < 1) {
            wait();
        }
        librePiscina--;
        canvas.calienta();
    }

    public synchronized void cogeMaterial() throws InterruptedException, RemoteException {
        canvas.coge();
        while (libreAletas < 2) {
            wait();
        }
        libreAletas -= 2;
        canvas.obtieneAletas();
        while (librePalas < 2) {
            wait();
        }
        librePalas -= 2;
        canvas.obtienePalas();
    }

    public synchronized void sueltaMaterial() throws RemoteException {
        libreAletas+=2;
        librePalas+=2;
        notifyAll();
    }

    public synchronized void salePiscina() throws RemoteException {
        librePiscina++;
        canvas.sale();
        notifyAll();
    }
}
