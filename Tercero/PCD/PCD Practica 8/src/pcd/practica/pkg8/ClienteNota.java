package pcd.practica.pkg8;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClienteNota implements Runnable {

    Registro registro;

    public ClienteNota(Registro registro) {
        this.registro = registro;
    }

    /**
     * Intenta entrar, tarda de 4 a 6 segundos en ser atendido y sale
     */
    @Override
    public void run() {
        Random r = new Random();
        r.setSeed(System.nanoTime());
        try {
            registro.entraNota();
            Thread.sleep(4000 + r.nextInt(2000));
            registro.sale();
        } catch (InterruptedException ex) {
            Logger.getLogger(ClienteNota.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
