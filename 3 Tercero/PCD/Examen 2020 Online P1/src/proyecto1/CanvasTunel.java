package proyecto1;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import static proyecto1.Tunel.*;

class Vehiculo{
    public long id;
    public int tipo;
    public Vehiculo(int tipo){
        this.id = Thread.currentThread().getId();
        this.tipo = tipo;
    }
}
public class CanvasTunel extends Canvas{
    private final ArrayList<Vehiculo> esperando;
    private Vehiculo prelavado, lavado, secado;
    private static final Font fTexto = new Font("Verdana", Font.PLAIN, 18), fNumero = new Font("Consolsa", Font.BOLD, 28);
    
    public CanvasTunel(){
        esperando = new ArrayList<>();
        prelavado = null;
        lavado = null;
        secado = null;
    }
    
    public void entraEspera(int tipo){
        esperando.add(new Vehiculo(tipo));
        repaint();
    }
    
    public void entraPrelavado(){
        Vehiculo a = esperando.stream().filter(v -> v.id == Thread.currentThread().getId()).findAny().get();
        esperando.remove(a);
        prelavado = a;
        repaint();
    }
    
    public void entraLavado(){
        lavado = prelavado;
        prelavado = null;
        repaint();
    }
    
    public void entraSecado(){
        secado = lavado;
        lavado = null;
        repaint();
    }
    
    public void saleSecado(){
        secado = null;
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
            drawVehiculo(g, esperando.get(i), 50, 70+35*i);
        }
        
        g.setFont(fTexto);
        g.setColor(Color.BLACK);
        g.drawString("Prelavado", 150, 40);
        if(prelavado != null){
            drawVehiculo(g, prelavado, 180, 70);
        }
        
        g.setFont(fTexto);
        g.setColor(Color.BLACK);
        g.drawString("Lavado", 150, 140);
        if(lavado != null){
            drawVehiculo(g, lavado, 180, 170);
        }
        
        g.setFont(fTexto);
        g.setColor(Color.BLACK);
        g.drawString("Secado", 150, 240);
        if(secado != null){
            drawVehiculo(g, secado, 180, 270);
        }
        
        g.setFont(fTexto);
        g.setColor(Color.BLUE);
        g.drawString("Coches", 280, 80);
        g.setColor(Color.RED);
        g.drawString("Furgonetas", 280, 120);
        
        og.drawImage(img, 0, 0, null);
    }
    
    private void drawVehiculo(Graphics g, Vehiculo a, int x, int y){
        g.setFont(fNumero);
        g.setColor(a.tipo==VEHICULO_COCHE?Color.BLUE:Color.RED);
        g.drawString(Long.toString(a.id), x, y);
    }
}
