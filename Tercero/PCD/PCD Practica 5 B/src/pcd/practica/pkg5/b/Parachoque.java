package pcd.practica.pkg5.b;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Parachoque extends Thread{
    
    Tanque a;
    public Parachoque(Tanque a){
        this.a = a;
    }
    
    @Override
    public void run(){
        System.out.println("Inicio del hilo Parachoque "+getId());
        try {
            a.entraPC(this);
            Thread.sleep(4000);
            a.salePC(this);
        } catch (InterruptedException ex) {
            Logger.getLogger(Parachoque.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
