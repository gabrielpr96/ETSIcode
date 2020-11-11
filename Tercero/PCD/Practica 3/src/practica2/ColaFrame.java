package practica2;

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
     */
    public static void main(String args[]) throws InterruptedException {
        ColaFrame f = new ColaFrame();
        CanvasCola canvas = new CanvasCola(CAPACIDAD);
        canvas.setSize(ANCHO, ALTO);
        canvas.setBackground(new Color(205, 250, 212));
        f.setSize(ANCHO, ALTO);
        f.add(canvas);
        f.setTitle("Practica 3: Borja López");
        f.setResizable(false);
        f.setLocationRelativeTo(null);
        f.setVisible(true);

        ColaLenta c = new ColaLenta(CAPACIDAD, canvas);

        Thread productor = new Productor(c);
        Thread consumidor1 = new Thread(new Consumidor(c));

        productor.start();
        consumidor1.start();

        productor.join();
        consumidor1.join();
        
        Thread.sleep(5000);
        System.exit(0);
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
