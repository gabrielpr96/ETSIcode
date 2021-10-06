package proyecto2;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import static proyecto2.Revision.*;

class Alumno{
    public long id;
    public int tipo;
    public Alumno(int tipo){
        this.id = Thread.currentThread().getId();
        this.tipo = tipo;
    }
}
public class CanvasRevision extends Canvas{
    private final ArrayList<Alumno> esperando, atendiendo;
    private static final Font fTexto = new Font("Verdana", Font.PLAIN, 18), fNumero = new Font("Consolsa", Font.BOLD, 28);
    
    public CanvasRevision(){
        esperando = new ArrayList<>();
        atendiendo = new ArrayList<>();
    }
    
    public void entra(int tipo){
        esperando.add(new Alumno(tipo));
        repaint();
    }
    
    public void atendiendo(){
        Alumno a = esperando.stream().filter(v -> v.id == Thread.currentThread().getId()).findAny().get();
        esperando.remove(a);
        atendiendo.add(a);
        repaint();
    }
    
    public void sale(){
        atendiendo.removeIf(v -> v.id == Thread.currentThread().getId());
        repaint();
    }
    
    @Override
    public void update(Graphics g){
        paint(g);
    }
    
    @Override
    public void paint(Graphics og){
        Image img = createImage(getWidth(), getHeight());
        Graphics g = img.getGraphics();
        
        g.setFont(fTexto);
        g.setColor(Color.BLACK);
        g.drawString("Esperando", 20, 40);
        for (int i = 0; i < esperando.size(); i++) {
            drawAlumno(g, esperando.get(i), 50, 70+35*i);
        }
        
        g.setFont(fTexto);
        g.setColor(Color.BLACK);
        g.drawString("Revisando", 150, 40);
        for (int i = 0; i < atendiendo.size(); i++) {
            drawAlumno(g, atendiendo.get(i), 180, 70+35*i);
        }
        
        g.setFont(fTexto);
        g.setColor(Color.BLUE);
        g.drawString("Teoria", 280, 80);
        g.setColor(Color.RED);
        g.drawString("Practicas", 280, 120);
        
        og.drawImage(img, 0, 0, null);
    }
    
    private void drawAlumno(Graphics g, Alumno a, int x, int y){
        g.setFont(fNumero);
        g.setColor(a.tipo==ALUMNO_TEORIA?Color.BLUE:Color.RED);
        g.drawString(Long.toString(a.id), x, y);
    }
}
