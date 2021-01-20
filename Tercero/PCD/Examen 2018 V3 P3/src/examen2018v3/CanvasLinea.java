package examen2018v3;

import java.awt.Canvas;
import java.util.ArrayList;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

class Ropa {

    public static final int ESTADO_PROCESO = 1, ESTADO_ESPERA = 2;
    public final int tipo;
    public final long id;
    public int estado;

    public Ropa(long id, int tipo) {
        this.id = id;
        this.tipo = tipo;
        this.estado = ESTADO_ESPERA;
    }

    @Override
    public boolean equals(Object o) {
        if (o.getClass() != Ropa.class) {
            return false;
        }
        return ((Ropa) o).id == id;
    }
}

public class CanvasLinea extends Canvas {

    public static final int TIPO_CAMISA = 2, TIPO_PANTALON = 1;
    private final ArrayList<Ropa> espera, corte, cosido;
    private final Font fTexto = new Font("Courier New", Font.PLAIN, 20), fNumero = new Font("Consolas", Font.BOLD, 30);

    public CanvasLinea() {
        espera = new ArrayList<>();
        corte = new ArrayList<>();
        cosido = new ArrayList<>();
    }

    public void espera(int tipo) {
        espera.add(new Ropa(Thread.currentThread().getId(), tipo));
        repaint();
    }

    public void corte() {
        Ropa r = espera.stream().filter(ropa -> ropa.id == Thread.currentThread().getId()).findAny().get();
        r.estado = Ropa.ESTADO_PROCESO;
        espera.remove(r);
        corte.add(r);
        repaint();
    }
    
    public void corteTermina(){
        corte.stream().filter(ropa -> ropa.id == Thread.currentThread().getId()).findAny().get().estado = Ropa.ESTADO_ESPERA;
        repaint();
    }

    public void cosido() {
        Ropa r = corte.stream().filter(ropa -> ropa.id == Thread.currentThread().getId()).findAny().get();
        r.estado = Ropa.ESTADO_PROCESO;
        corte.remove(r);
        cosido.add(r);
        repaint();
    }

    public void salida() {
       cosido.removeIf(ropa -> ropa.id == Thread.currentThread().getId());
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
        g.setColor(Color.black);
        g.drawString("Espera", 20, 40);
        for (int i = 0; i < espera.size(); i++) {
            drawRopa(g, espera.get(i), 40, 80 + 40 * i);
        }
        
        g.setFont(fTexto);
        g.setColor(Color.black);
        g.drawString("Corte", 170, 40);
        for (int i = 0; i < corte.size(); i++) {
            drawRopa(g, corte.get(i), 190, 80 + 40 * i);
        }
        
        g.setFont(fTexto);
        g.setColor(Color.black);
        g.drawString("Cosido", 320, 40);
        for (int i = 0; i < cosido.size(); i++) {
            drawRopa(g, cosido.get(i), 330, 80 + 40 * i);
        }
        
        og.drawImage(img, 0, 0, null);
    }

    private void drawRopa(Graphics g, Ropa r, int x, int y) {
        g.setColor(r.tipo == TIPO_PANTALON ? Color.red : Color.blue);
        g.setFont(fNumero);
        g.drawString(Long.toString(r.id), x, y);
        if (r.estado == Ropa.ESTADO_PROCESO) {
            g.setColor(Color.MAGENTA);
            g.fillOval(x - 25, y - 20, 20, 20);
        }
    }
}
