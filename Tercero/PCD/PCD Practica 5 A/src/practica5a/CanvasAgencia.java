package practica5a;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.util.LinkedList;
import java.util.Queue;

public class CanvasAgencia extends Canvas{
    private Thread A, B;
    private Queue<Thread> colaViajes, colaEntradas;
    private final static Font fMensaje = new Font("Courier New", Font.BOLD, 30);
    private final static Font fNumero = new Font("Consolas", Font.BOLD, 20);
    private final static Color cViaje = Color.red;
    private final static Color cEntrada = Color.blue;
    
    public CanvasAgencia(){
        A = null;
        B = null;
        colaViajes = new LinkedList<>();
        colaEntradas = new LinkedList<>();
    }
    
    @Override
    public void paint(Graphics og){
        Image oi = createImage(getWidth(), getHeight());
        Graphics g = oi.getGraphics();
        
        g.setFont(fMensaje);
        g.setColor(Color.black);
        g.drawString("A", 43, 50);
        g.drawString("B", 343, 50);
        
        g.setColor(cViaje);
        g.drawString("Viajes", 10, getHeight()-10);
        g.setColor(cEntrada);
        g.drawString("Entradas", 10, getHeight()-50);
        
        if(A != null){
            drawCliente(g, 50, 70, A);
        }
        if(B != null){
            drawCliente(g, 350, 70, B);
        }
        int y = 0;
        for (Thread cliente : colaViajes) {
            drawCliente(g, 150, 100+(y++)*60, cliente);
        }
        y = 0;
        for (Thread cliente : colaEntradas) {
            drawCliente(g, 250, 100+(y++)*60, cliente);
        }
        
        og.drawImage(oi, 0, 0, null);
    }
    
    private void drawCliente(Graphics g, int x, int y, Thread cliente){
        g.setColor(cliente.getClass()==Viajes.class?cViaje:cEntrada);
        g.fillPolygon(new int[] {x, x-10, x+10}, new int[] {y, y+25, y+25}, 3);
        g.setFont(fNumero);
        String id = Long.toString(cliente.getId());
        FontMetrics fm = g.getFontMetrics();
        g.drawString(id, x-fm.stringWidth(id)/2, y+25+fm.getHeight());
    }
    
    @Override
    public void update(Graphics g){
        paint(g);
    }
    
    /**
     * Actualiza el estado de la agenda y repinta el canvas.
     * @param A Hilo que está siendo atendido por A
     * @param B Hulo que está siendo atendido por B
     * @param colaViajes Cola de hilos de tipo viaje que están esperando
     * @param colaEntradas Cola de hilos de tipo entrada que están esperando
     */
    public void representa(Thread A, Thread B, Queue<Thread> colaViajes, Queue<Thread> colaEntradas){
        this.A = A;
        this.B = B;
        this.colaViajes = new LinkedList<>(colaViajes);
        this.colaEntradas = new LinkedList<>(colaEntradas);
        repaint();
    }
}
