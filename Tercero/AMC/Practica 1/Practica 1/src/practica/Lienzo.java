package practica;

import java.awt.BasicStroke;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

/**
 * Clase que hereda de Canvas y sirve para dibujar una linea.
 */
public class Lienzo extends Canvas {

    private static final int GROSOR = 6, PADDING = 10;

    private final ArrayList<Punto> puntos;
    private final ArrayList<Linea> lineas;
    private Linea linea, lineaMejor;
    private Triangulo triangulo, trianguloMejor;
    private boolean mejor, especial;
    private int lado;
    private double resNegX, resPosX, resNegY, resPosY;

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
     * Agrega los puntos y calcula el rango que debe tener el lienzo. Restablece
     * la linea y el triangulo. Manda a repintar.
     *
     * @param puntos
     */
    public void drawMap(Punto[] puntos) {
        this.puntos.clear();
        this.lineas.clear();
        resNegX = 0;
        resPosX = 0;
        resNegY = 0;
        resPosY = 0;
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
    public void paint(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(0, 0, lado + PADDING * 2, lado + PADDING * 2);
        g.setColor(Color.black);
        for (Punto punto : puntos) {
            g.fillOval(cordX2pix(punto.getX()) - GROSOR / 2, cordY2pix(punto.getY()) - GROSOR / 2, GROSOR, GROSOR);
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
        return (int) (((resNegX + cord) * lado) / (resNegX + resPosX)) + PADDING;
    }

    private int cordY2pix(double cord) {
        return (int) (((resNegY + cord) * lado) / (resNegY + resPosY)) + PADDING;
    }
}
