package pcd2019v1;

import interfaz.IPiscina;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import piscina.Piscina;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Libre extends Thread {

    private final IPiscina piscina;

    public Libre(Piscina piscina) throws NotBoundException, MalformedURLException, RemoteException {
        //this.piscina = piscina;
        this.piscina = (IPiscina) Naming.lookup("rmi://localhost:1999/recurso");
    }

    @Override
    public void run() {
        Random r = new Random();
        r.setSeed(System.nanoTime());
        try {
            piscina.entraLibre();
            Thread.sleep(2000 + r.nextInt(2001));
            piscina.saleLibre();
        } catch (InterruptedException ex) {
            Logger.getLogger(Club.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RemoteException ex) {
            Logger.getLogger(Libre.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
