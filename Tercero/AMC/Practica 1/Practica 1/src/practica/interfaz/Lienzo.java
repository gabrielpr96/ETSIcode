package practica.interfaz;

import practica.geometriz.Punto;
import practica.geometriz.Triangulo;
import practica.geometriz.Linea;
import java.awt.BasicStroke;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.ArrayList;

/**
 * Clase que hereda de Canvas y sirve para dibujar una linea.
 */
public class Lienzo extends Canvas {

    private static final int GROSOR = 6, PADDING = 10;
    private static final Font fNumeracion = new Font("Consolas", Font.BOLD, 20);
    private final FontMetrics fmNumeracion = getFontMetrics(fNumeracion);

    private final ArrayList<Punto> puntos;
    private final ArrayList<Linea> lineas;
    private Linea linea, lineaMejor;
    private Triangulo triangulo, trianguloMejor;
    private boolean mejor, especial, numerado;
    private int lado;
    private double escala, resNegX, resNegY;

    /**
     * Crea el lienzo
     */
    public Lienzo() {
        puntos = new ArrayList<>();
        lineas = new ArrayList<>();
        linea = null;
        triangulo = null;
        lineaMejor = null;
        trianguloMejor = null;
        mejor = especial = false;
    }

    /**
     * Agrega los puntos y calcula el rango que debe tener el lienzo.Restablece
     * la linea y el triangulo. Manda a repintar. Por defecto no pinta la
     * numeración en los puntos.
     *
     * @param puntos
     */
    public void drawMap(Punto[] puntos) {
        drawMap(puntos, false);
    }

    /**
     * Agrega los puntos y calcula el rango que debe tener el lienzo.Restablece
     * la linea y el triangulo. Manda a repintar.
     *
     * @param puntos Los puntos arepresentar
     * @param numerado Si es verdaero se dibujará la numeración
     */
    public void drawMap(Punto[] puntos, boolean numerado) {
        this.numerado = numerado;
        this.puntos.clear();
        this.lineas.clear();
        double resPosX = 0, resPosY = 0;
        resNegX = 0;
        resNegY = 0;
        for (Punto punto : puntos) {
            if (punto.getX() < resNegX) {
                resNegX = punto.getX();
            } else if (punto.getX() > resPosX) {
                resPosX = punto.getX();
            }
            if (punto.getY() < resNegY) {
                resNegY = punto.getY();
            } else if (punto.getY() > resPosY) {
                resPosY = punto.getY();
            }
            this.puntos.add(punto);
        }
        resNegX = -resNegX;
        resNegY = -resNegY;
        if (resNegX + resPosX > resNegY + resPosY) {
            escala = resNegX + resPosX;
        } else {
            escala = resNegY + resPosY;
        }
        linea = null;
        triangulo = null;
        lineaMejor = null;
        trianguloMejor = null;
        mejor = false;
        mejor = especial = false;
        repaint();
    }

    /**
     * Establece la linea. Manda a repintar.
     *
     * @param linea
     */
    public void drawLinea(Linea linea) {
        this.linea = linea;
        repaint();
    }

    /**
     * Establece el triangulo. Manda a repintar.
     *
     * @param triangulo
     */
    public void drawTriangulo(Triangulo triangulo) {
        this.triangulo = triangulo;
        repaint();
    }

    /**
     * Establece la linea. Manda a repintar.
     *
     * @param linea
     */
    public void drawMejorLinea(Linea linea) {
        this.lineaMejor = linea;
        repaint();
    }

    /**
     * Establece el triangulo. Manda a repintar.
     *
     * @param triangulo
     */
    public void drawMejorTriangulo(Triangulo triangulo) {
        this.trianguloMejor = triangulo;
        repaint();
    }

    /**
     * Agrega una linea al camino de lineas. Manda a repintar.
     *
     * @param l
     */
    public void addDrawLinea(Linea l) {
        this.lineas.add(l);
        repaint();
    }

    public void eliminarLineasPorPuntos(Punto p) {
        ArrayList<Linea> lista = new ArrayList<>();
        for (Linea linea : lineas) {
            if (p.equals(linea.getP1()) || p.equals(linea.getP2())) {
                lista.add(linea);
            }
        }
        lineas.removeAll(lista);
    }

    /**
     * Establece si el triangulo o linea es especial o mejor. Mejor tiene
     * prioridad sobre especial. No manda a repintar.
     *
     * @param especial
     * @param mejor
     */
    public void setTipo(boolean especial, boolean mejor) {
        this.mejor = mejor;
        this.especial = especial;
    }

    /**
     * Establece el tamaño real del lienzo, es un cuadrado.
     *
     * @param lado
     */
    public void setLado(int lado) {
        this.lado = lado - (PADDING * 2);
    }

    /**
     * Pinta los puntos, la linea y el triangulo. Deja un marges en todos los
     * bordes.
     *
     * @param g
     */
    @Override
    public void paint(Graphics rg) {
        Image oi = createImage(getWidth(), getHeight());
        Graphics g = oi.getGraphics();

        g.setColor(Color.white);
        g.fillRect(0, 0, lado + PADDING * 2, lado + PADDING * 2);
        g.setColor(Color.black);

        if (!numerado) {
            for (Punto punto : puntos) {
                g.fillOval(cordX2pix(punto.getX()) - GROSOR / 2, cordY2pix(punto.getY()) - GROSOR / 2, GROSOR, GROSOR);
            }
        }

        ((Graphics2D) g).setStroke(new BasicStroke(GROSOR / 2));

        g.setColor(mejor ? Color.red : especial ? Color.green : Color.blue);
        if (linea != null) {
            gLinea(g, linea);
        }
        if (triangulo != null) {
            gTriangulo(g, triangulo);
        }

        g.setColor(Color.magenta);
        if (lineaMejor != null) {
            gLinea(g, lineaMejor);
        }
        if (trianguloMejor != null) {
            gTriangulo(g, trianguloMejor);
        }
        for (Linea l : lineas) {
            gLinea(g, l);
        }

        if (numerado) {
            g.setFont(fNumeracion);
            for (int i = 1; i <= puntos.size(); i++) {
                Punto punto = puntos.get(i - 1);
                String n = Integer.toString(i);
                int cordX = cordX2pix(punto.getX()) - (fmNumeracion.stringWidth(Integer.toString(i)) / 2), cordY = cordY2pix(punto.getY()) + (fmNumeracion.getHeight() / 4);
                g.setColor(Color.black);
                g.drawString(n, cordX + 1, cordY + 1);
                g.drawString(n, cordX + 1, cordY - 1);
                g.drawString(n, cordX - 1, cordY + 1);
                g.drawString(n, cordX - 1, cordY - 1);
                g.setColor(Color.white);
                g.drawString(n, cordX, cordY);
            }
        }

        rg.drawImage(oi, 0, 0, null);
    }

    @Override
    public void update(Graphics g) {
        paint(g);
    }

    private void gLinea(Graphics g, Linea l) {
        g.drawLine(cordX2pix(l.getP1().getX()), cordY2pix(l.getP1().getY()), cordX2pix(l.getP2().getX()), cordY2pix(l.getP2().getY()));
    }

    private void gTriangulo(Graphics g, Triangulo t) {
        g.drawLine(cordX2pix(t.getP1().getX()), cordY2pix(t.getP1().getY()), cordX2pix(t.getP2().getX()), cordY2pix(t.getP2().getY()));
        g.drawLine(cordX2pix(t.getP2().getX()), cordY2pix(t.getP2().getY()), cordX2pix(t.getP3().getX()), cordY2pix(t.getP3().getY()));
        g.drawLine(cordX2pix(t.getP3().getX()), cordY2pix(t.getP3().getY()), cordX2pix(t.getP1().getX()), cordY2pix(t.getP1().getY()));
    }

    private int cordX2pix(double cord) {
        return (int) (((resNegX + cord) * lado) / (escala)) + PADDING;
    }

    private int cordY2pix(double cord) {
        return (int) (((resNegY + cord) * lado) / (escala)) + PADDING;
    }
}
