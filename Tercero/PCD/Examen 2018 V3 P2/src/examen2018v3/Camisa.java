package examen2018v3;

import static java.lang.Thread.sleep;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Camisa extends Thread{
private final Linea linea;

    public Camisa(Linea linea) {
        this.linea = linea;
    }
    @Override
    public void run() {
        System.out.println("Se crea Camisa "+getId());
        try {
            linea.entraCorte(Linea.TIPO_CAMISA);
            sleep(2000);
            linea.coserCamisa();
            sleep(2000);
            linea.saleCoser();
        } catch (InterruptedException ex) {
            Logger.getLogger(Pantalaon.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
