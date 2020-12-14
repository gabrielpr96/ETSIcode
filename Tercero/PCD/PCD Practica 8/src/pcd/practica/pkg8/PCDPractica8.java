package pcd.practica.pkg8;

import java.awt.Color;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class PCDPractica8 {
    
    private static final int MAX_CLIENTES = 25;
    
    public static void main(String[] args) throws InterruptedException {
        CanvasRegistro canvas = new CanvasRegistro(3, 1);
        
        JFrame f = new JFrame();
        canvas.setSize(390, 700);
        canvas.setBackground(new Color(205, 250, 212));
        f.add(canvas);
        f.setTitle("Practica 8: Borja López");
        f.setResizable(false);
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        f.pack();
        f.setLocationRelativeTo(null);
        f.setVisible(true);
        
        Registro registro = new Registro(canvas);
        
        Random r = new Random();
        r.setSeed(System.nanoTime());
        
        Thread[] clientes = new Thread[MAX_CLIENTES];
        for (int i = 0; i < MAX_CLIENTES; i++) {
            clientes[i] = new Thread(r.nextFloat() < 0.35?new ClienteRegistro(registro):new ClienteNota(registro));
            clientes[i].start();
            Thread.sleep(1000+r.nextInt(1000));
        }
        for (int i = 0; i < MAX_CLIENTES; i++) {
            clientes[i].join();
        }
        
        Thread.sleep(5000);
        System.exit(0);
    }
    
}
