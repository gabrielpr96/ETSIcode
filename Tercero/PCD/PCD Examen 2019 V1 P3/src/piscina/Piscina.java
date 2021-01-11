package piscina;

import examen2019v1.CanvasPiscina;
import interfaz.IPiscina;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;

public class Piscina extends UnicastRemoteObject implements IPiscina{

    public static final int CALLE_CLUB = 1, CALLE_LIBRE = 2;
    public static final int CLIENTE_CLUB = 1, CLIENTE_LIBRE = 2;
    private final CanvasPiscina canvas;
    private volatile int libreClub, libreLibre, esperandoLibre;

    public Piscina(CanvasPiscina canvas) throws RemoteException{
        super();
        libreClub = 4;
        libreLibre = 1;
        esperandoLibre = 0;
        this.canvas = canvas;
    }

    @Override
    public synchronized void entraLibre(int id) throws InterruptedException, RemoteException {
        canvas.espera(id, CLIENTE_LIBRE);
        esperandoLibre++;
        while (libreLibre == 0) {
            wait();
        }
        esperandoLibre--;
        libreLibre--;
        canvas.entra(id, CLIENTE_LIBRE, CALLE_LIBRE);
    }

    @Override
    public synchronized int entraClub(int id) throws InterruptedException, RemoteException {
        canvas.espera(id, CLIENTE_CLUB);
        while (libreClub == 0 && (libreLibre == 0 || esperandoLibre != 0)) {
            wait();
        }
        if (libreClub == 0) {
            libreLibre--;
            canvas.entra(id, CLIENTE_CLUB, CALLE_LIBRE);
            return CALLE_LIBRE;
        } else {
            libreClub--;
            canvas.entra(id, CLIENTE_CLUB, CALLE_CLUB);
            return CALLE_CLUB;
        }
    }

    @Override
    public synchronized void saleLibre(int id) throws RemoteException{
        libreLibre++;
        canvas.sale(id);
        notifyAll();
    }

    @Override
    public synchronized void saleClub(int id, int calle) throws RemoteException {
        if (calle == CALLE_CLUB) {
            libreClub++;
        } else {
            libreLibre++;
        }
        canvas.sale(id);
        notifyAll();
    }

}
