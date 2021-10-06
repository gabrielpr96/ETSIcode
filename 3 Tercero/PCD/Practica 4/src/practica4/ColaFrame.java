package practica4;

import java.awt.Color;

public class ColaFrame extends java.awt.Frame {

    private static final int ANCHO = 500, ALTO = 600, CAPACIDAD = 10;

    public ColaFrame() {
        initComponents();
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                exitForm(evt);
            }
        });

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void exitForm(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_exitForm
        System.exit(0);
    }//GEN-LAST:event_exitForm

    /**
     * @param args the command line arguments
     * @throws java.lang.InterruptedException
     */
    public static void main(String args[]) throws InterruptedException {
        ColaFrame f = new ColaFrame();
        CanvasCola canvas = new CanvasCola(CAPACIDAD);
        canvas.setSize(ANCHO, ALTO);
        canvas.setBackground(new Color(205, 250, 212));
        f.setSize(ANCHO, ALTO);
        f.add(canvas);
        f.setTitle("Practica 4: Borja López");
        f.setResizable(false);
        f.setLocationRelativeTo(null);
        f.setVisible(true);

        ColaLenta c = new ColaLenta(CAPACIDAD, canvas);

        Thread p1 = new Productor(c);
        Thread p2 = new Productor(c);
        Thread p3 = new Productor(c);
        Thread p4 = new Productor(c);
        Thread c1 = new Thread(new Consumidor(c));

        p1.start();
        p2.start();
        p3.start();
        p4.start();
        c1.start();
        
        c1.join();

        p1.interrupt();
        p2.interrupt();
        p3.interrupt();
        p4.interrupt();
        
        p1.join();
        p2.join();
        p3.join();
        p4.join();
        
        Thread.sleep(5000);
        System.exit(0);
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
