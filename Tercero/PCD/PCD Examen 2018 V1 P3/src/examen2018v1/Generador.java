package examen2018v1;

import java.awt.Color;
import static java.lang.Thread.sleep;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import javax.swing.JFrame;

public class Generador {
    
    private static final int N_HILOS = 20;

    public static void main(String[] args) throws InterruptedException, ExecutionException {
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
        
        ExecutorService thp = Executors.newFixedThreadPool(3);
        
        Future[] hilos = new Future[N_HILOS];
        for (int i = 0; i < N_HILOS; i++) {
            if(r.nextDouble() < 0.3d)
                hilos[i] = thp.submit(new Turismo(p));
            else
                hilos[i] = thp.submit(new Camion(p));
            Thread.sleep(1000+r.nextInt(3000));
        }
        int total = 0;
        for (int i = 0; i < N_HILOS; i++) {
            total += (int) hilos[i].get();
        }
        thp.shutdown();
        
        sleep(1000);
        System.exit(0);
    }
    
}
