package practica5a;

import java.awt.Color;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class Generador {

    private static final int MAX_CLIENTES = 50, ANCHO = 400, ALTO = 800;
    
    public static void main(String[] args) throws InterruptedException {
        JFrame f = new JFrame();
        CanvasAgencia canvas = new CanvasAgencia();
        canvas.setSize(ANCHO, ALTO);
        canvas.setBackground(new Color(205, 250, 212));
        f.add(canvas);
        f.setTitle("Practica 5: Borja López");
        f.setResizable(false);
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        f.pack();
        f.setLocationRelativeTo(null);
        f.setVisible(true);
        
        Random r = new Random();
        r.setSeed(System.nanoTime());
        Agencia a = new Agencia(canvas);
        Thread[] hilos = new Thread[MAX_CLIENTES];
        for (int i = 0; i < MAX_CLIENTES; i++) {
            hilos[i] = r.nextInt(10) < 3 ? new Viajes(a) : new Thread(new Entradas(a));
            hilos[i].start();
            Thread.sleep(1000+r.nextInt(2000));
        }
        for (int i = 0; i < MAX_CLIENTES; i++) {
            hilos[i].join();
        }
    }
    
}
