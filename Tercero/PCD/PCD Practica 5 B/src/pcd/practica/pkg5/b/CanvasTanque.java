package pcd.practica.pkg5.b;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class CanvasTanque extends Canvas{
    private ArrayList<Thread> tanquePC, tanqueLlanta;
    private Queue<Thread> colaPC, colaLlanta;
    private final static Font fMensaje = new Font("Courier New", Font.BOLD, 30);
    private final static Font fNumero = new Font("Consolas", Font.BOLD, 20);
    private final static Color cPC = Color.red;
    private final static Color cLlanta = Color.blue;
    
    public CanvasTanque(){
        tanquePC = new ArrayList<>();
        tanqueLlanta = new ArrayList<>();
        colaPC = new LinkedList<>();
        colaLlanta = new LinkedList<>();
    }
    
    @Override
    public void paint(Graphics og){
        Image oi = createImage(getWidth(), getHeight());
        Graphics g = oi.getGraphics();
        
        g.setColor(Color.lightGray);
        g.fillRect(10, 10, 380, 220);
        g.setFont(fMensaje);
        g.setColor(Color.black);
        g.drawString("Tanque", 20, 50);
        g.setColor(cPC);
        g.drawString("Parachoque", 10, getHeight()-10);
        g.setColor(cLlanta);
        g.drawString("Llanta", 10, getHeight()-50);
        
        g.setColor(cPC);
        for (int i = 0; i < tanquePC.size(); i++) {
            drawPC(g, 50+i*50, 75, tanquePC.get(i).getId());
        }
        g.setColor(cLlanta);
        for (int i = 0; i < tanqueLlanta.size(); i++) {
            drawLlanta(g, 50+i*50, 150, tanqueLlanta.get(i).getId());
        }
        
        int y = 0;
        for (Thread pc : colaPC) {
            drawPC(g, 150, 250+(y++)*60, pc.getId());
        }
        y = 0;
        for (Thread llanta : colaLlanta) {
            drawLlanta(g, 250, 250+(y++)*60, llanta.getId());
        }
        
        og.drawImage(oi, 0, 0, null);
    }
    
    private void drawPC(Graphics g, int x, int y, long id){
        g.setColor(cPC);
        g.fillRect(x-12, y, 24, 24);
        g.setFont(fNumero);
        String sid = Long.toString(id);
        FontMetrics fm = g.getFontMetrics();
        g.drawString(sid, x-fm.stringWidth(sid)/2, y+25+fm.getHeight());
    }
    private void drawLlanta(Graphics g, int x, int y, long id){
        g.setColor(cLlanta);
        g.fillOval(x-12, y, 24, 24);
        g.setFont(fNumero);
        String sid = Long.toString(id);
        FontMetrics fm = g.getFontMetrics();
        g.drawString(sid, x-fm.stringWidth(sid)/2, y+25+fm.getHeight());
    }
    
    @Override
    public void update(Graphics g){
        paint(g);
    }
    
    public void representa(ArrayList<Thread> tanquePC, ArrayList<Thread> tanqueLlanta, Queue<Thread> colaPC, Queue<Thread> colaLlanta){
        this.tanquePC = new ArrayList<>(tanquePC);
        this.tanqueLlanta = new ArrayList<>(tanqueLlanta);
        this.colaPC = new LinkedList<>(colaPC);
        this.colaLlanta = new LinkedList<>(colaLlanta);
        repaint();
    }
}
