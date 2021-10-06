package examen2019v3;

import java.awt.Canvas;
import java.util.ArrayList;
import static piscina.Piscina.*;
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

    private final ArrayList<Cliente> dentro, fuera;
    private final Font fTexto = new Font("Courier New", Font.PLAIN, 20), fNumero = new Font("Consolas", Font.BOLD, 30);

    public CanvasPiscina() {
        dentro = new ArrayList<>();
        fuera = new ArrayList<>();
    }
    
    public void espera(int id, int tipo){
        fuera.add(new Cliente(Thread.currentThread().getId(), tipo));
        repaint();
    }

    public void entra(int id, int tipo) {
        Cliente c = new Cliente(Thread.currentThread().getId(), tipo);
        fuera.remove(c);
        dentro.add(c);
    }

    public void sale(int id) {
        Cliente c = new Cliente(Thread.currentThread().getId(), 0);
        dentro.remove(c);
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
        
        g.setColor(Color.black);
        g.setFont(fTexto);
        g.drawString("Piscina", 20, 40);
        for (int i = 0; i < dentro.size(); i++) {
            drawCliente(g, dentro.get(i), 40, 80+35*i);
        }
        
        g.setColor(Color.black);
        g.setFont(fTexto);
        g.drawString("Esperando", 220, 40);
        for (int i = 0; i < fuera.size(); i++) {
            drawCliente(g, fuera.get(i), 240, 80+35*i);
        }
        
        og.drawImage(img, 0, 0, null);
    }
    
    private void drawCliente(Graphics g, Cliente c, int x, int y){
        g.setColor(c.tipo==CLIENTE_ADULTO?Color.red:Color.blue);
        g.setFont(fNumero);
        g.drawString(Long.toString(c.id), x, y);
    }
}
