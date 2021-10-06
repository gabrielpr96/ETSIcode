/*PEDRO
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package examen2018v3;

import java.awt.Color;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

/**
 *
 * @author pedro
 */
public class Generador {

    /**
     * @param args the command line arguments
     */
   public static void main(String[] args) {
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
       
        // AL CODIGO DEL PROFESOR TAMBIEN LE PASA. (O lo he fastidiado poniendole el canvas)
        
        Thread[] prenda = new Thread[10];
        Linea maquinas = new Linea(c);
        Random rnd = new Random();
        rnd.setSeed(System.currentTimeMillis());
        try {

            for (int i = 0; i < 10; i++) {
                if (rnd.nextInt(10) < 3) {
                    prenda[i] = new Thread(new Pantalon(maquinas));
                    prenda[i].start();
                } else {                    
                    prenda[i] =  new Camisa(maquinas);
                    prenda[i].start();
                }
                //Thread.sleep((rnd.nextInt(3) + 1) * 1000);                
            }
            for (int i = 0; i < 10; i++) {
                prenda[i].join();
            }            
        } catch (InterruptedException ex) {
            Logger.getLogger(Generador.class.getName()).log(Level.SEVERE, null, ex);


        }
    }
}
