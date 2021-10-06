package examen2018v4;

import java.awt.Canvas;
import java.util.ArrayList;
import static examen2018v4.Vibradora.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

class Tornillo {

    public final int tipo;
    public final long id;

    public Tornillo(long id, int tipo) {
        this.id = id;
        this.tipo = tipo;
    }

    @Override
    public boolean equals(Object o) {
        if (o.getClass() != Tornillo.class) {
            return false;
        }
        return ((Tornillo) o).id == id;
    }
}

public class CanvasVibradora extends Canvas {

    private final ArrayList<Tornillo> atendiendo, esperando;
    private final Font fTexto = new Font("Courier New", Font.PLAIN, 20), fNumero = new Font("Consolas", Font.BOLD, 30);

    public CanvasVibradora() {
        atendiendo = new ArrayList<>();
        esperando = new ArrayList<>();
    }

    public void espera(int tipo) {
        esperando.add(new Tornillo(Thread.currentThread().getId(), tipo));
        repaint();
    }

    public void entra() {
        Tornillo c = esperando.stream().filter(tornillo -> tornillo.id == Thread.currentThread().getId()).findAny().get();
        esperando.remove(c);
        atendiendo.add(c);
        repaint();
    }

    public void sale() {
        atendiendo.removeIf(tornillo -> tornillo.id == Thread.currentThread().getId());
        repaint();
    }

    @Override
    public void update(Graphics g) {
        paint(g);
    }

    @Override
    public void paint(Graphics og) {
        Image img = createImage(getWidth(), getHeight());
        Graphics g = img.getGraphics();

        g.setColor(Color.black);
        g.setFont(fTexto);
        g.drawString("Vibradora", 20, 40);
        for (int i = 0; i < atendiendo.size(); i++) {
            drawTornillo(g, atendiendo.get(i), 60, 80 + 40 * i);
        }

        g.setColor(Color.black);
        g.setFont(fTexto);
        g.drawString("Esperando", 180, 40);
        for (int i = 0; i < esperando.size(); i++) {
            drawTornillo(g, esperando.get(i), 220, 80 + 35 * i);
        }
        
        g.setFont(fTexto);
        g.setColor(Color.red);
        g.drawString("Hierro", 10, getHeight()-10);
        g.setColor(Color.blue);
        g.drawString("Inoxidable", 10, getHeight()-40);
        
        og.drawImage(img, 0, 0, null);
    }

    private void drawTornillo(Graphics g, Tornillo c, int x, int y) {
        g.setColor(c.tipo == TIPO_HIERRO ? Color.red : Color.blue);
        g.setFont(fNumero);
        g.drawString(Long.toString(c.id), x, y);
    }
}
