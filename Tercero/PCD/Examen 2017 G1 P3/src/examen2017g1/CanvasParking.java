package examen2017g1;

import java.awt.Canvas;
import java.util.ArrayList;
import static examen2017g1.Parking.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

class Vehiculo {

    public final int tipo;
    public final long id;

    public Vehiculo(long id, int tipo) {
        this.id = id;
        this.tipo = tipo;
    }

    @Override
    public boolean equals(Object o) {
        if (o.getClass() != Vehiculo.class) {
            return false;
        }
        return ((Vehiculo) o).id == id;
    }
}

public class CanvasParking extends Canvas {

    private final ArrayList<Vehiculo> bus, coche, esperando;
    private int operariosLibres;
    private final Font fTexto = new Font("Courier New", Font.PLAIN, 20), fNumero = new Font("Consolas", Font.BOLD, 30);

    public CanvasParking() {
        bus = new ArrayList<>();
        coche = new ArrayList<>();
        esperando = new ArrayList<>();
        operariosLibres = 4;
    }

    public void espera(int tipo) {
        esperando.add(new Vehiculo(Thread.currentThread().getId(), tipo));
        repaint();
    }

    public void entra(int lugar) {
        Vehiculo c = esperando.stream().filter(v -> v.id == Thread.currentThread().getId()).findAny().get();
        esperando.remove(c);
        if (lugar == TIPO_COCHE) {
            coche.add(c);
        } else {
            bus.add(c);
        }
        repaint();
    }

    public void sale() {
        bus.removeIf(v -> v.id == Thread.currentThread().getId());
        coche.removeIf(v -> v.id == Thread.currentThread().getId());
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
        g.drawString("Plazas Bus", 20, 40);
        for (int i = 0; i < bus.size(); i++) {
            drawVehiculo(g, bus.get(i), 60, 80 + 40 * i);
        }

        g.setColor(Color.black);
        g.setFont(fTexto);
        g.drawString("Plazas Coche", 180, 40);
        for (int i = 0; i < coche.size(); i++) {
            drawVehiculo(g, coche.get(i), 230, 80 + 35 * i);
        }

        g.setColor(Color.black);
        g.setFont(fTexto);
        g.drawString("Esperando", 360, 40);
        for (int i = 0; i < esperando.size(); i++) {
            drawVehiculo(g, esperando.get(i), 400, 80 + 35 * i);
        }
        
        og.drawImage(img, 0, 0, null);
    }

    private void drawVehiculo(Graphics g, Vehiculo v, int x, int y) {
        g.setColor(v.tipo == TIPO_BUS ? Color.red : Color.blue);
        g.setFont(fNumero);
        g.drawString(Long.toString(v.id), x, y);
    }
}
