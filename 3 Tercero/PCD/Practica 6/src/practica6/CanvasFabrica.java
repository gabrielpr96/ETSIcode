package practica6;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

public class CanvasFabrica extends Canvas {

    private int m1, m2, m1a, m2a, m1b, m2b, a, b, estadoA, estadoB;
    private final static Font fRobot = new Font("Courier New", Font.BOLD, 30);
    private final static Font fNumero = new Font("Consolas", Font.BOLD, 30);
    private final static Font fMensaje = new Font("Verdana", Font.BOLD, 20);
    private final static Color cM1 = Color.red;
    private final static Color cM2 = Color.blue;

    public CanvasFabrica() {
        m1 = m2 = m1a = m2a = m1b = m2b = a = b = estadoA = estadoB = 0;
    }

    public void agregarM1() {
        m1++;
        repaint();
    }

    public void agregarM2() {
        m2++;
        repaint();
    }

    public void tomarM1A() {
        m1--;
        m1a++;
        repaint();
    }

    public void tomarM1B() {
        m1--;
        m1b++;
        repaint();
    }

    public void tomarM2A() {
        m2--;
        m2a++;
        repaint();
    }

    public void tomarM2B() {
        m2--;
        m2b++;
        repaint();
    }

    public void fabricarA() {
        estadoA = 1;
        repaint();
    }

    public void fabricarB() {
        estadoB = 1;
        repaint();
    }

    public void fabricadoA() {
        m1a = m2a = 0;
        estadoA = 0;
        a++;
        repaint();
    }

    public void fabricadoB() {
        m1b = m2b = 0;
        estadoB = 0;
        b++;
        repaint();
    }

    public void terminaA() {
        estadoA = 2;
        repaint();
    }

    public void terminaB() {
        estadoB = 2;
        repaint();
    }

    @Override
    public void paint(Graphics og) {
        Image oi = createImage(getWidth(), getHeight());
        Graphics g = oi.getGraphics();

        g.setColor(Color.lightGray);
        g.fillRect(10, 10, 200, 120);
        g.setFont(fRobot);
        g.setColor(Color.black);
        g.drawString("A", 20, 40);
        g.setFont(fNumero);
        g.drawString(Integer.toString(a), 20, 90);
        g.setFont(fMensaje);
        g.drawString(estadoA==0?"Buscando":(estadoA==1?"Fabricando":"Finalizado"), 50, 115);
        g.setColor(cM1);
        for (int i = 0; i < m1a; i++) {
            g.fillOval(65+i*45, 30, 20, 20);
        }
        g.setColor(cM2);
        for (int i = 0; i < m2a; i++) {
            g.fillOval(65+i*45, 60, 20, 20);
        }
        
        g.setColor(Color.lightGray);
        g.fillRect(10, 150, 200, 120);
        g.setFont(fRobot);
        g.setColor(Color.black);
        g.drawString("B", 20, 180);
        g.setFont(fNumero);
        g.drawString(Integer.toString(b), 20, 230);
        g.setFont(fMensaje);
        g.drawString(estadoB==0?"Buscando":(estadoB==1?"Fabricando":"Finalizado"), 50, 255);
        g.setColor(cM1);
        for (int i = 0; i < m1b; i++) {
            g.fillOval(65+i*45, 170, 20, 20);
        }
        g.setColor(cM2);
        for (int i = 0; i < m2b; i++) {
            g.fillOval(65+i*45, 200, 20, 20);
        }
        
        g.setColor(Color.lightGray);
        g.fillRect(230, 20, 300, 86);
        g.setFont(fRobot);
        g.setColor(Color.black);
        g.drawString("Silo M1", 240, 50);
        g.setColor(cM1);
        if(m1 <= 8){
            for (int i = 0; i < m1; i++) {
                g.fillOval(250+i*35, 70, 20, 20);
            }
        }else{
            g.setFont(fNumero);
            g.drawString(Integer.toString(m1), 390, 50);
        }
        
        g.setColor(Color.lightGray);
        g.fillRect(230, 160, 300, 86);
        g.setFont(fRobot);
        g.setColor(Color.black);
        g.drawString("Silo M2", 240, 190);
        g.setColor(cM2);
        if(m2 <= 8){
            for (int i = 0; i < m2; i++) {
                g.fillOval(250+i*35, 210, 20, 20);
            }
        }else{
            g.setFont(fNumero);
            g.drawString(Integer.toString(m2), 390, 190);
        }
        
        og.drawImage(oi, 0, 0, null);
    }

    @Override
    public void update(Graphics g) {
        paint(g);
    }
}
