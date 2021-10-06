package examen2018v4;

import java.awt.Color;
import static java.lang.Thread.sleep;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import javax.swing.JFrame;

public class Generador {
    
    private static final int N_HILOS = 20;

    public static void main(String[] args) throws InterruptedException, ExecutionException {
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
        
        ExecutorService thp = Executors.newFixedThreadPool(4);
        ArrayList<Thread> hInox = new ArrayList<>();
        ArrayList<Future> hHierro = new ArrayList<>();
        
        for (int i = 0; i < N_HILOS; i++) {
            if(r.nextDouble() < 0.4d)
                hHierro.add(thp.submit(new Hierro(p)));
            else{
                Thread tmp = new Inoxidable(p);
                tmp.start();
                hInox.add(tmp);
            }
            Thread.sleep(1000+r.nextInt(1000));
        }
        
        int total = 0;
        for (Future fu : hHierro) {
            total += (int) fu.get();
        }
        
        for (Thread th : hInox) {
            th.join();
        }
        
        System.out.println("Los tornillos de hierro han estado un total de "+total+" ms en la pulidora");
        
        thp.shutdown();
        sleep(1000);
        System.exit(0);
    }
    
}
