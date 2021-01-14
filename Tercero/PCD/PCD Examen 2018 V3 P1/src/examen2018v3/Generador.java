package examen2018v3;

import java.awt.Color;
import static java.lang.Thread.sleep;
import java.util.Random;
import javax.swing.JFrame;

public class Generador {
    
    private static final int N_HILOS = 20;

    public static void main(String[] args) throws InterruptedException {
        JFrame f = new JFrame();
        f.setTitle("Examen 2018 V3 B0vE");
        CanvasLinea c = new CanvasLinea();
        c.setBackground(Color.white);
        c.setSize(420,500);
        f.add(c);
        f.pack();
        f.setResizable(false);
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
        
        Random r = new Random();
        r.setSeed(System.nanoTime());
        
        Linea p = new Linea(c);
        
        Thread[] hilos = new Thread[N_HILOS];
        for (int i = 0; i < N_HILOS; i++) {
            if(r.nextDouble() < 0.3d)
                hilos[i] = new Thread(new Pantalaon(p));
            else
                hilos[i] = new Camisa(p);
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
