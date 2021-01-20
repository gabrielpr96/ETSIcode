package examen2019v1;

import java.awt.Canvas;
import java.util.ArrayList;
import static examen2019v1.Piscina.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

class Cliente {

    public final int tipo;
    public final long id;

    public Cliente(long id, int tipo) {
        this.id = id;
        this.tipo = tipo;
    }

    @Override
    public boolean equals(Object o) {
        if (o.getClass() != Cliente.class) {
            return false;
        }
        return ((Cliente) o).id == id;
    }
}

public class CanvasPiscina extends Canvas {

    private final ArrayList<Cliente> callesClub, callesLibres, esperando;
    private final Font fTexto = new Font("Courier New", Font.PLAIN, 20), fNumero = new Font("Consolas", Font.BOLD, 30);

    public CanvasPiscina() {
        callesClub = new ArrayList<>();
        callesLibres = new ArrayList<>();
        esperando = new ArrayList<>();
    }
    
    public void espera(int tipo){
        esperando.add(new Cliente(Thread.currentThread().getId(), tipo));
        repaint();
    }

    public void entra(int tipo, int calle) {
        Cliente c = new Cliente(Thread.currentThread().getId(), tipo);
        esperando.remove(c);
        if (calle == CALLE_CLUB) {
            callesClub.add(c);
        } else {
            callesLibres.add(c);
        }
        repaint();
    }

    public void sale() {
        Cliente c = new Cliente(Thread.currentThread().getId(), 0);
        callesClub.remove(c);
        callesLibres.remove(c);
        repaint();
    }
    
    @Override
    public void update(Graphics g){
        paint(g);
    }
    
    public void paint(Graphics og){
        Image img = createImage(getWidth(), getHeight());
        Graphics g = img.getGraphics();
        
        g.setColor(Color.red);
        g.setFont(fTexto);
        g.drawString("Club", 20, 40);
        for (int i = 0; i < 4; i++) {
            g.setColor(new Color(255, 200, 200));
            g.fillRect(20, 50+60*i, 50, 50);
            if(callesClub.size() > i){
                drawCliente(g, callesClub.get(i), 25, 85+60*i);
            }
        }
        
        g.setColor(Color.blue);
        g.setFont(fTexto);
        g.drawString("Libre", 15, 320);
        g.setColor(new Color(200, 200, 255));
        g.fillRect(20, 340, 50, 50);
        if(callesLibres.size() > 0){
            drawCliente(g, callesLibres.get(0), 25, 375);
        }
        
        g.setColor(Color.black);
        g.setFont(fTexto);
        g.drawString("Esperando", 100, 40);
        for (int i = 0; i < esperando.size(); i++) {
            drawCliente(g, esperando.get(i), 140, 75+35*i);
        }
        
        og.drawImage(img, 0, 0, null);
    }
    
    public void drawCliente(Graphics g, Cliente c, int x, int y){
        g.setColor(c.tipo==CLIENTE_CLUB?Color.red:Color.blue);
        g.setFont(fNumero);
        g.drawString(Long.toString(c.id), x, y);
    }
}
