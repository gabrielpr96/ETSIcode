package pcd2919v2p3;

import java.awt.Color;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import javax.swing.JFrame;

public class Generador {

    private static final int N_HILOS = 20;
    
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        JFrame f = new JFrame("PCD 2019 V2 P3");
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
        ExecutorService thp = Executors.newFixedThreadPool(6);
        Future[] hilos = new Future[N_HILOS];
        for (int i = 0; i < N_HILOS; i++) {
            hilos[i] = thp.submit(new Nadador(piscina));
            Thread.sleep(1000+r.nextInt(1001));
        }
        int tiempoTotal = 0;
        for (int i = 0; i < N_HILOS; i++) {
            tiempoTotal += (int) hilos[i].get();
        }
        thp.shutdown();
        
        System.out.println("Tiempo total nadado: "+tiempoTotal+" ms.");
        
        Thread.sleep(1000);
        System.exit(0);
    }

}
