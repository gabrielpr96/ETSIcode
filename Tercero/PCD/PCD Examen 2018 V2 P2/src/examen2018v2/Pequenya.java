package examen2018v2;

import static java.lang.Thread.sleep;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Pequenya extends Thread {

    private final Monton monton;
    private final int id;

    public Pequenya(Monton monton, int id) {
        this.monton = monton;
        this.id = id;
    }

    @Override
    public void run() {
        Random r = new Random();
        r.setSeed(System.nanoTime());
        System.out.println("Se crea Cargadora Pequenya "+id+" " + getId());
        try {
            for (int i = 0; i < 10; i++) {
                monton.cargaPoco(id);
                sleep(1000 + r.nextInt(2000));
            }
            monton.pequenyaSeVa(id);
        } catch (InterruptedException ex) {
            Logger.getLogger(Grande.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
