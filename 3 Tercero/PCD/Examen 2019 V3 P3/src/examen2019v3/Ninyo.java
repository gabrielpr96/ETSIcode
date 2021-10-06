package examen2019v3;

import java.rmi.RemoteException;
import piscina.Piscina;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Ninyo extends Thread {

    private final Piscina piscina;

    public Ninyo(Piscina piscina) {
        this.piscina = piscina;
    }

    @Override
    public void run() {
        Random r = new Random();
        r.setSeed(System.nanoTime());
        int id = r.nextInt(100);
        System.out.println("Se crea Ninyo "+getId());
        try {
            if(piscina.entraNinyo(id)){
                sleep(2000 + r.nextInt(2000));
                piscina.saleNinyo(id);
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(Ninyo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RemoteException ex) {
            Logger.getLogger(Ninyo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
