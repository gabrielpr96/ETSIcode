package examen2017g2;

import java.awt.Canvas;
import java.util.ArrayList;
import static examen2017g2.Puente.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

class Persona {

    public final int tipo;
    public final long id;

    public Persona(long id, int tipo) {
        this.id = id;
        this.tipo = tipo;
    }

    @Override
    public boolean equals(Object o) {
        if (o.getClass() != Persona.class) {
            return false;
        }
        return ((Persona) o).id == id;
    }
}

public class CanvasPuente extends Canvas {

    private final ArrayList<Persona> dentro, esperando;
    private final Font fTexto = new Font("Courier New", Font.PLAIN, 20), fNumero = new Font("Consolas", Font.BOLD, 30);

    public CanvasPuente() {
        dentro = new ArrayList<>();
        esperando = new ArrayList<>();
    }

    public void espera(int tipo) {
        esperando.add(new Persona(Thread.currentThread().getId(), tipo));
        repaint();
    }

    public void entra() {
        Persona c = esperando.stream().filter(v -> v.id == Thread.currentThread().getId()).findAny().get();
        esperando.remove(c);
        dentro.add(c);
        repaint();
    }

    public void sale() {
        dentro.removeIf(v -> v.id == Thread.currentThread().getId());
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
        g.drawString("Puente", 20, 40);
        for (int i = 0; i < dentro.size(); i++) {
            drawPersona(g, dentro.get(i), 20, 80 + 40 * i);
        }

        g.setColor(Color.black);
        g.setFont(fTexto);
        g.drawString("Esperando", 180, 40);
        for (int i = 0; i < esperando.size(); i++) {
            drawPersona(g, esperando.get(i), 200, 80 + 35 * i);
        }
        
        og.drawImage(img, 0, 0, null);
    }

    private void drawPersona(Graphics g, Persona v, int x, int y) {
        g.setColor(v.tipo == TIPO_NINO ? Color.red : Color.blue);
        g.setFont(fNumero);
        g.drawString((v.tipo==TIPO_NINO?"N ":"A ")+Long.toString(v.id), x, y);
    }
}
