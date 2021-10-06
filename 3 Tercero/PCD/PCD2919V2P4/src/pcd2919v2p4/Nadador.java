package pcd2919v2p4;

import interfaz.IPiscina;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import piscina.Piscina;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Nadador implements Runnable{

    private final IPiscina piscina;
    
    public Nadador(Piscina piscina) throws NotBoundException, MalformedURLException, RemoteException{
        //this.piscina = piscina;
        this.piscina = (IPiscina) Naming.lookup("rmi://localhost:1999/recurso");
    }
    
    @Override
    public void run() {
        Random r = new Random();
        r.setSeed(System.nanoTime());
        try {
            piscina.entraPiscina();
            Thread.sleep(1000+r.nextInt(1001));
            piscina.cogeMaterial();
            Thread.sleep(2000+r.nextInt(1001));
            piscina.sueltaMaterial();
            piscina.salePiscina();
        } catch (InterruptedException | RemoteException ex) {
            Logger.getLogger(Nadador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
