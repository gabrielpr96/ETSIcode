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

public class Club implements Runnable {

    private final IPiscina piscina;

    public Club(Piscina piscina) throws NotBoundException, MalformedURLException, RemoteException {
        //this.piscina = piscina;
        this.piscina = (IPiscina) Naming.lookup("rmi://localhost:1999/recurso");
    }

    @Override
    public void run() {
        Random r = new Random();
        r.setSeed(System.nanoTime());
        try {
            int cual = piscina.entraClub();
            Thread.sleep(3000+r.nextInt(3001));
            piscina.saleClub(cual);
        } catch (InterruptedException ex) {
            Logger.getLogger(Club.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RemoteException ex) {
            Logger.getLogger(Club.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
