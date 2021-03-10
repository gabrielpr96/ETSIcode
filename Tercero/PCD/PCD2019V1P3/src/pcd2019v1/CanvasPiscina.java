package pcd2019v1;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import static piscina.Piscina.*;

class Nadador {

    public long id;
    public int tipo;

    public Nadador(long id, int tipo) {
        this.id = id;
        this.tipo = tipo;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj.getClass() == Nadador.class) {
            return ((Nadador) obj).id == id;
        } else {
            return false;
        }
    }
}

public class CanvasPiscina extends Canvas {
    private final ArrayList<Nadador> esperando, libre, club;
    private final Font fTexto = new Font("Verdana", Font.PLAIN, 18), fNumero = new Font("Consolas", Font.BOLD, 28);
    
    public CanvasPiscina(){
        esperando = new ArrayList<>();
        libre = new ArrayList<>();
        club = new ArrayList<>();
    }
    
    public void espera(int tipo){
        esperando.add(new Nadador(Thread.currentThread().getId(), tipo));
        repaint();
    }
    
    public void entraLibre(){
        Nadador nadador = esperando.stream().filter(v -> v.id == Thread.currentThread().getId()).findAny().get();
        esperando.remove(nadador);
        libre.add(nadador);
        repaint();
    }
    
    public void entraClub(){
        Nadador nadador = esperando.stream().filter(v -> v.id == Thread.currentThread().getId()).findAny().get();
        esperando.remove(nadador);
        club.add(nadador);
        repaint();
    }
    
    public void sale(){
        libre.removeIf(v -> v.id == Thread.currentThread().getId());
        club.removeIf(v -> v.id == Thread.currentThread().getId());
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
        g.drawString("Calle Club", 10, 40);
        for (int i = 0; i < club.size(); i++) {
            drawNadador(g, club.get(i), 40, 70+40*i);
        }
        
        g.setColor(Color.BLACK);
        g.setFont(fTexto);
        g.drawString("Calle Libre", 150, 40);
        for (int i = 0; i < libre.size(); i++) {
            drawNadador(g, libre.get(i), 180, 70+40*i);
        }
        
        g.setColor(Color.BLACK);
        g.setFont(fTexto);
        g.drawString("Esperando", 290, 40);
        for (int i = 0; i < esperando.size(); i++) {
            drawNadador(g, esperando.get(i), 320, 70+40*i);
        }
        
        og.drawImage(img, 0, 0, null);
    }
    
    private void drawNadador(Graphics g, Nadador nadador, int x, int y){
        g.setFont(fNumero);
        g.setColor(nadador.tipo==NADADOR_CLUB?Color.RED:Color.BLUE);
        g.drawString(Long.toString(nadador.id), x, y);
    }
}
