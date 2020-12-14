package pcd.practica.pkg8;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClienteRegistro implements Runnable{
    
    Registro registro;
    public ClienteRegistro(Registro registro){
        this.registro = registro;
    }
    
    
    @Override
    public void run() {
        Random r = new Random();
        r.setSeed(System.nanoTime());
        try {
            registro.entraRegistro();
            Thread.sleep(3000+r.nextInt(1000));
            registro.saleRegistro();
        } catch (InterruptedException ex) {
            Logger.getLogger(ClienteRegistro.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
