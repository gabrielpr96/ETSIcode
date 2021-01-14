package examen2018v4;

import java.awt.Color;
import static java.lang.Thread.sleep;
import java.util.Random;
import javax.swing.JFrame;

public class Generador {
    
    private static final int N_HILOS = 20;

    public static void main(String[] args) throws InterruptedException {
        JFrame f = new JFrame();
        f.setTitle("Examen 2018 V4 B0vE");
        CanvasVibradora c = new CanvasVibradora();
        c.setBackground(Color.white);
        c.setSize(320,500);
        f.add(c);
        f.pack();
        f.setResizable(false);
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
        
        Random r = new Random();
        r.setSeed(System.nanoTime());
        
        Vibradora p = new Vibradora(c);
        
        Thread[] hilos = new Thread[N_HILOS];
        for (int i = 0; i < N_HILOS; i++) {
            if(r.nextDouble() < 0.4d)
                hilos[i] = new Thread(new Hierro(p));
            else
                hilos[i] = new Inoxidable(p);
            hilos[i].start();
            Thread.sleep(1000+r.nextInt(1000));
        }
        for (int i = 0; i < N_HILOS; i++) {
            hilos[i].join();
        }
        
        sleep(1000);
        System.exit(0);
    }
    
}
