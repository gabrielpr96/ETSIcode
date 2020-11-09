package ejemploFrame;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

public class VistaCanvas extends Canvas {

    private int[] datos = {0, 0};

    public VistaCanvas(int ancho, int alto) {
        this.setSize(ancho, alto);
        this.setBackground(Color.CYAN);
    }

    public void representa(int[] datos) {
        this.datos = datos;
        repaint();
    }
    
    @Override
    public void update(Graphics g){
        paint(g);
    }

    @Override
    public void paint(Graphics g) {
        Font f1 = new Font("Arial", Font.BOLD + Font.ITALIC, 20);
        Font f2 = new Font("Courier New", Font.PLAIN, 20);
        
        Image offScreen = createImage(getWidth(), getHeight());
        Graphics og = offScreen.getGraphics();
        
        og.setFont(f1);
        og.setColor(Color.red);
        og.fillOval(50, 75, 20, 20);
        og.drawString("Contador 1: " + datos[0], 100, 100);
        og.setFont(f2);
        og.setColor(Color.blue);
        og.drawRect(50, 175, 20, 20);
        og.drawString("Contador 2: " + datos[1], 100, 200);
        
        g.drawImage(offScreen, 0, 0, null);
    }
}
