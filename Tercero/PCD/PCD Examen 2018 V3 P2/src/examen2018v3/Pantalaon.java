package examen2018v3;

import static java.lang.Thread.sleep;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Pantalaon implements Runnable {

    private final Linea linea;

    public Pantalaon(Linea linea) {
        this.linea = linea;
    }

    @Override
    public void run() {
        Random r = new Random();
        r.setSeed(System.nanoTime());
        System.out.println("Se crea Pantalon "+Thread.currentThread().getId());
        try {
            linea.entraCorte(Linea.TIPO_PANTALON);
            sleep(2000);
            linea.coserPantalon();
            sleep(3000);
            linea.saleCoser();
        } catch (InterruptedException ex) {
            Logger.getLogger(Pantalaon.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
