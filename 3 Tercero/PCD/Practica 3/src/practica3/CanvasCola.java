package practica3;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;

public class CanvasCola extends Canvas{
    private int head, tail, capacidad, numelementos;
    private Object[] datos;
    private String mensaje;
    private final static Font fMensaje = new Font("Courier New", Font.BOLD, 30);
    private final static Font fNumero = new Font("Consolas", Font.BOLD, 30);
    
    public CanvasCola(int capacidad){
        head = tail = numelementos = 0;
        datos = new Object[capacidad];
        mensaje = "";
        this.capacidad = capacidad;
    }
    
    @Override
    public void paint(Graphics g){
        Image oi = createImage(getWidth(), getHeight());
        Graphics og = oi.getGraphics();
        
        og.drawOval(50, 50, 400, 400);
        og.drawOval(150, 150, 200, 200);

        FontMetrics fm = g.getFontMetrics(fNumero);
        og.setFont(fNumero);
        double franja = (Math.PI*2)/capacidad;
        og.drawLine((int) (250+(Math.cos(-franja/2)*100)), (int) (250+(Math.sin(-franja/2)*100)), (int) (250+(Math.cos(-franja/2)*200)), (int) (250+(Math.sin(-franja/2)*200)));
        for (int i = 0; i < capacidad; i++) {
            double mid = franja*(i+1), izq = mid-(franja/2), der = izq+franja;
            og.drawLine((int) (250+(Math.cos(der)*100)), (int) (250+(Math.sin(der)*100)), (int) (250+(Math.cos(der)*200)), (int) (250+(Math.sin(der)*200)));
            if(datos[i] != null)
            og.drawString(datos[i].toString(), (int) (250+(Math.cos(mid)*150)-(fm.stringWidth(datos[i].toString())/2)), (int) (250+(Math.sin(mid)*150)));
        }
        
        og.setColor(new Color(219, 175, 227));
        og.fillPolygon(new int[] {(int) (250+(Math.cos(head*franja)*210)),(int) (250+(Math.cos(head*franja-0.07)*230)), (int) (250+(Math.cos(head*franja+0.07)*230))}, new int[] {(int) (250+(Math.sin(head*franja)*210)),(int) (250+(Math.sin(head*franja-0.07)*230)), (int) (250+(Math.sin(head*franja+0.07)*230))}, 3);
        
        og.setColor(new Color(230, 123, 91));
        og.fillPolygon(new int[] {(int) (250+(Math.cos(tail*franja)*90)),(int) (250+(Math.cos(tail*franja-0.2)*70)), (int) (250+(Math.cos(tail*franja+0.2)*70))}, new int[] {(int) (250+(Math.sin(tail*franja)*90)),(int) (250+(Math.sin(tail*franja-0.2)*70)), (int) (250+(Math.sin(tail*franja+0.2)*70))}, 3);
        
        og.setFont(fMensaje);
        og.setColor(mensaje.equals("COLA LLENA")?Color.MAGENTA:Color.gray);
        og.drawString("COLA LLENA", 20, 530);
        og.setColor(mensaje.equals("COLA VACIA")?Color.MAGENTA:Color.gray);
        og.drawString("COLA VACIA", 280, 530);
        
        g.drawImage(oi, 0, 0, null);
    }
    
    @Override
    public void update(Graphics g){
        paint(g);
    }
    
    public void avisa(String mensaje){
        this.mensaje = mensaje;
        repaint();
    }
    
    public void representa(Object[] buf, int head, int tail, int numele){
        this.head = head;
        this.tail = tail;
        this.numelementos = numele;
        System.arraycopy(buf, 0, datos, 0, capacidad);
        this.mensaje = "";
        repaint();
    }
}
