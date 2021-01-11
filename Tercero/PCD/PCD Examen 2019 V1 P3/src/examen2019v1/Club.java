package examen2019v1;

import piscina.Piscina;
import static java.lang.Thread.sleep;
import java.rmi.RemoteException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Club implements Runnable {

    private final Piscina piscina;

    public Club(Piscina piscina) {
        this.piscina = piscina;
    }

    @Override
    public void run() {
        Random r = new Random();
        int id = r.nextInt(100);
        r.setSeed(System.nanoTime());
        System.out.println("Se crea Club " + id);
        try {
            int calle = piscina.entraClub(id);
            sleep(2000 + r.nextInt(2000));
            piscina.saleClub(id, calle);
        } catch (InterruptedException ex) {
            Logger.getLogger(Libre.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RemoteException ex) {
            Logger.getLogger(Club.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
