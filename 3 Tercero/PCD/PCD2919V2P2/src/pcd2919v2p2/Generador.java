package pcd2919v2p2;

import java.awt.Color;
import java.util.Random;
import javax.swing.JFrame;

public class Generador {

    private static final int N_HILOS = 20;
    
    public static void main(String[] args) throws InterruptedException {
        JFrame f = new JFrame("PCD 2019 V2 P2");
        f.setResizable(false);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        CanvasPiscina canvas = new CanvasPiscina();
        canvas.setBackground(Color.WHITE);
        canvas.setSize(600, 500);
        f.add(canvas);
        f.pack();
        f.setVisible(true);
        
        Piscina piscina = new Piscina(canvas);
        Random r = new Random();
        r.setSeed(System.nanoTime());
        Thread[] hilos = new Thread[N_HILOS];
        for (int i = 0; i < N_HILOS; i++) {
            hilos[i] = new Thread(new Nadador(piscina));
            hilos[i].start();
            Thread.sleep(1000+r.nextInt(1001));
        }
        for (int i = 0; i < N_HILOS; i++) {
            hilos[i].join();
        }
        Thread.sleep(1000);
        System.exit(0);
    }

}
