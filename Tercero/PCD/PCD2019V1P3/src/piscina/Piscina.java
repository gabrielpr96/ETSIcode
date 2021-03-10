package piscina;

import interfaz.IPiscina;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import pcd2019v1.CanvasPiscina;

public class Piscina extends UnicastRemoteObject implements IPiscina{
    public static final int CALLE_LIBRE = 1, CALLE_CLUB = 2, NADADOR_LIBRE = 1, NADADOR_CLUB = 2;
    private int libreLibre, libreClub, esperandoLibre;
    private final CanvasPiscina canvas;
    
    public Piscina(CanvasPiscina canvas) throws RemoteException{
        super();
        libreLibre = 1;
        libreClub = 4;
        esperandoLibre = 0;
        this.canvas = canvas;
    }
    
    public synchronized void entraLibre() throws InterruptedException, RemoteException{
        canvas.espera(NADADOR_LIBRE);
        esperandoLibre++;
        while(libreLibre < 1){
            wait();
        }
        esperandoLibre--;
        libreLibre--;
        canvas.entraLibre();
    }
    public synchronized int entraClub() throws InterruptedException, RemoteException{
        canvas.espera(NADADOR_CLUB);
        while(libreClub < 1 && (libreLibre < 1 || esperandoLibre > 0)){
            wait();
        }
        if(libreClub > 0){
            libreClub--;
            canvas.entraClub();
            return CALLE_CLUB;
        }else{
            libreLibre--;
            canvas.entraLibre();
            return CALLE_LIBRE;
        }
    }
    public synchronized void saleLibre() throws RemoteException{
        canvas.sale();
        libreLibre++;
        notifyAll();
    }
    public synchronized void saleClub(int cual) throws RemoteException{
        canvas.sale();
        if(cual == CALLE_CLUB){
            libreClub++;
        }else{
            libreLibre++;
        }
        notifyAll();
    }
}
