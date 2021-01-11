package examen2018v1;

import java.awt.Color;
import static java.lang.Thread.sleep;
import java.util.Random;
import javax.swing.JFrame;

public class Generador {
    
    private static final int N_HILOS = 20;

    public static void main(String[] args) throws InterruptedException {
        JFrame f = new JFrame();
        f.setTitle("Examen 2018 V1 B0vE");
        CanvasTayer c = new CanvasTayer();
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
        
        Tayer p = new Tayer(c);
        
        Thread[] hilos = new Thread[N_HILOS];
        for (int i = 0; i < N_HILOS; i++) {
            if(r.nextDouble() < 0.3d)
                hilos[i] = new Thread(new Turismo(p));
            else
                hilos[i] = new Camion(p);
            hilos[i].start();
            Thread.sleep(1000+r.nextInt(3000));
        }
        for (int i = 0; i < N_HILOS; i++) {
            hilos[i].join();
        }
        
        sleep(1000);
        System.exit(0);
    }
    
}
