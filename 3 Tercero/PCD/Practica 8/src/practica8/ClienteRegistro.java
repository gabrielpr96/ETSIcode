package practica8;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClienteRegistro implements Runnable {

    Registro registro;

    public ClienteRegistro(Registro registro) {
        this.registro = registro;
    }

    /**
     * Intenta entrar, espera de 3 a 5 segundos y sale
     */
    @Override
    public void run() {
        Random r = new Random();
        r.setSeed(System.nanoTime());
        try {
            registro.entraRegistro();
            Thread.sleep(3000 + r.nextInt(2000));
            registro.sale();
        } catch (InterruptedException ex) {
            Logger.getLogger(ClienteRegistro.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
