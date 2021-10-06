package pcd2919v2p5;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

class Cliente {

    public long id;
    public boolean aletas, palas;

    public Cliente() {
        this.id = Thread.currentThread().getId();
        aletas = false;
        palas = false;
    }
}

public class CanvasPiscina extends Canvas {

    private final ArrayList<Cliente> esperando, calentando, cogiendo, usando;
    private int libreAletas, librePalas;
    private static final Font fNombre = new Font("Verdana", Font.PLAIN, 18), fNumero = new Font("Consolas", Font.BOLD, 28);

    public CanvasPiscina() {
        esperando = new ArrayList<>();
        calentando = new ArrayList<>();
        cogiendo = new ArrayList<>();
        usando = new ArrayList<>();
        libreAletas = 6;
        librePalas = 5;
    }

    public void entra() {
        esperando.add(new Cliente());
        repaint();
    }

    public void calienta() {
        Cliente c = esperando.stream().filter(v -> v.id == Thread.currentThread().getId()).findAny().get();
        esperando.remove(c);
        calentando.add(c);
        repaint();
    }

    public void coge() {
        Cliente c = calentando.stream().filter(v -> v.id == Thread.currentThread().getId()).findAny().get();
        calentando.remove(c);
        cogiendo.add(c);
        repaint();
    }

    public void obtieneAletas() {
        cogiendo.stream().filter(v -> v.id == Thread.currentThread().getId()).findAny().get().aletas = true;
        libreAletas -= 2;
        repaint();
    }

    public void obtienePalas() {
        Cliente c = cogiendo.stream().filter(v -> v.id == Thread.currentThread().getId()).findAny().get();
        c.palas = true;
        cogiendo.remove(c);
        usando.add(c);
        librePalas -= 2;
        repaint();
    }

    public void sale() {
        usando.removeIf(v -> v.id == Thread.currentThread().getId());
        libreAletas += 2;
        librePalas += 2;
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

        g.setFont(fNombre);
        g.setColor(Color.BLACK);
        g.drawString("Esperando", 20, 40);
        for (int i = 0; i < esperando.size(); i++) {
            drawCliente(g, esperando.get(i), 50, 70 + 40 * i);
        }

        g.setFont(fNombre);
        g.setColor(Color.BLACK);
        g.drawString("Calentando", 140, 40);
        for (int i = 0; i < calentando.size(); i++) {
            drawCliente(g, calentando.get(i), 170, 70 + 40 * i);
        }

        g.setFont(fNombre);
        g.setColor(Color.BLACK);
        g.drawString("Cogiendo", 260, 40);
        for (int i = 0; i < cogiendo.size(); i++) {
            drawCliente(g, cogiendo.get(i), 290, 70 + 40 * i);
        }

        g.setFont(fNombre);
        g.setColor(Color.BLACK);
        g.drawString("Usando", 380, 40);
        for (int i = 0; i < usando.size(); i++) {
            drawCliente(g, usando.get(i), 400, 70 + 40 * i);
        }
        
        g.setFont(fNombre);
        g.setColor(Color.BLACK);
        g.drawString("Aletas", 500, 40);
        g.setColor(Color.RED);
        for (int i = 0; i < libreAletas; i++) {
            g.fillOval(520, 60 + 30 * i, 15, 15);
        }
        
        g.setFont(fNombre);
        g.setColor(Color.BLACK);
        g.drawString("Palas", 500, 260);
        g.setColor(Color.BLUE);
        for (int i = 0; i < librePalas; i++) {
            g.fillOval(520, 280 + 30 * i, 15, 15);
        }

        og.drawImage(img, 0, 0, null);
    }

    private void drawCliente(Graphics g, Cliente c, int x, int y) {
        g.setFont(fNumero);
        g.setColor(Color.BLACK);
        g.drawString(Long.toString(c.id), x, y);
        if (c.aletas) {
            g.setColor(Color.RED);
            g.fillOval(x - 20, y - 20, 15, 15);
        }
        if (c.palas) {
            g.setColor(Color.BLUE);
            g.fillOval(x + 35, y - 20, 15, 15);
        }
    }
}
