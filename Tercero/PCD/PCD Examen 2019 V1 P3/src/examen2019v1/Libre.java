package examen2019v1;

import java.rmi.RemoteException;
import piscina.Piscina;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Libre extends Thread {

    private final Piscina piscina;

    public Libre(Piscina piscina) {
        this.piscina = piscina;
    }

    @Override
    public void run() {
        Random r = new Random();
        int id = r.nextInt(100);
        r.setSeed(System.nanoTime());
        System.out.println("Se crea Libre "+id);
        try {
            piscina.entraLibre(id);
            sleep(2000 + r.nextInt(2000));
            piscina.saleLibre(id);
        } catch (InterruptedException ex) {
            Logger.getLogger(Libre.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RemoteException ex) {
            Logger.getLogger(Libre.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
