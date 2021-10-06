package examen2018v1;

import java.awt.Canvas;
import java.util.ArrayList;
import static examen2018v1.Tayer.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

class Coche {

    public final int tipo;
    public final long id;

    public Coche(long id, int tipo) {
        this.id = id;
        this.tipo = tipo;
    }

    @Override
    public boolean equals(Object o) {
        if (o.getClass() != Coche.class) {
            return false;
        }
        return ((Coche) o).id == id;
    }
}

public class CanvasTayer extends Canvas {

    private final ArrayList<Coche> atendiendo, esperando;
    private int operariosLibres;
    private final Font fTexto = new Font("Courier New", Font.PLAIN, 20), fNumero = new Font("Consolas", Font.BOLD, 30);

    public CanvasTayer() {
        atendiendo = new ArrayList<>();
        esperando = new ArrayList<>();
        operariosLibres = 4;
    }

    public void espera(int tipo) {
        esperando.add(new Coche(Thread.currentThread().getId(), tipo));
        repaint();
    }

    public void entra() {
        Coche c = esperando.stream().filter(coche -> coche.id == Thread.currentThread().getId()).findAny().get();
        esperando.remove(c);
        atendiendo.add(c);
        if (c.tipo == COCHE_CAMION) {
            operariosLibres -= 2;
        } else {
            operariosLibres--;
        }
        repaint();
    }

    public void sale() {
        //atendiendo.removeIf(coche -> coche.id == Thread.currentThread().getId());
        Coche c = atendiendo.stream().filter(coche -> coche.id == Thread.currentThread().getId()).findAny().get();
        atendiendo.remove(c);
        if (c.tipo == COCHE_CAMION) {
            operariosLibres += 2;
        } else {
            operariosLibres++;
        }
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

        g.setFont(fTexto);
        g.drawString("Operarios", 20, 40);
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(20, 50, 120, 130);
        g.setColor(Color.MAGENTA);
        for (int i = 0; i < operariosLibres; i++) {
            g.fillOval(70, 60+30*i, 20, 20);
        }

        g.setColor(Color.black);
        g.drawString("Atendiendo", 180, 40);
        for (int i = 0; i < atendiendo.size(); i++) {
            drawCoche(g, atendiendo.get(i), 220, 80 + 40 * i, true);
        }

        g.setColor(Color.black);
        g.setFont(fTexto);
        g.drawString("Esperando", 350, 40);
        for (int i = 0; i < esperando.size(); i++) {
            drawCoche(g, esperando.get(i), 390, 80 + 35 * i, false);
        }
        
        og.drawImage(img, 0, 0, null);
    }

    private void drawCoche(Graphics g, Coche c, int x, int y, boolean atendido) {
        g.setColor(c.tipo == COCHE_CAMION ? Color.red : Color.blue);
        g.setFont(fNumero);
        g.drawString(Long.toString(c.id), x, y);
        if (atendido) {
            g.setColor(Color.MAGENTA);
            g.fillOval(x - 25, y - 20, 20, 20);
            if (c.tipo == COCHE_CAMION) {
                g.fillOval(x + 40, y - 20, 20, 20);
            }
        }
    }
}
