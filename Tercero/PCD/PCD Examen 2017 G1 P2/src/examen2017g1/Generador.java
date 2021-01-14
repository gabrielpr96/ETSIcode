package examen2017g1;

import java.awt.Color;
import static java.lang.Thread.sleep;
import java.util.Random;
import javax.swing.JFrame;

public class Generador {
    
    private static final int N_HILOS = 20;

    public static void main(String[] args) throws InterruptedException {
        JFrame f = new JFrame();
        f.setTitle("Examen 2017 G1 B0vE");
        CanvasParking c = new CanvasParking();
        c.setBackground(Color.white);
        c.setSize(500,420);
        f.add(c);
        f.pack();
        f.setResizable(false);
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
        
        Random r = new Random();
        r.setSeed(System.nanoTime());
        
        Parking p = new Parking(c);
        
        Thread[] hilos = new Thread[N_HILOS];
        for (int i = 0; i < N_HILOS; i++) {
            if(r.nextDouble() < 0.7d)
                hilos[i] = new Thread(new Turismo(p));
            else
                hilos[i] = new Autobus(p);
            hilos[i].start();
            Thread.sleep(1000+r.nextInt(2000));
        }
        for (int i = 0; i < N_HILOS; i++) {
            hilos[i].join();
        }
        
        sleep(1000);
        System.exit(0);
    }
    
}
