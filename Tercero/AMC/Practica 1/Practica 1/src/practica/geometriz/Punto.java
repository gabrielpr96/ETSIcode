package practica.geometriz;

import practica.ComparableParametro;

public class Punto implements ComparableParametro {

    public static final double MINIMO_COMPARACION = 0.000001;

    private final double x, y;

    public Punto(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double distancia(Punto p) {
        return Math.sqrt(Math.pow(x - p.x, 2) + Math.pow(y - p.y, 2));
    }

    /**
     * Comparar un punto con sigo mismo.
     *
     * @param o El punto con el que se compara
     * @param sortX Si es verdadero, compara en el eje x. Si es falso, compara
     * en el eje y.
     * @return Verdadero si es menor que el punto pasado por parametro.
     */
    @Override
    public boolean comparar(Object o, boolean sortX) {
        if (o == null || o.getClass() != Punto.class) {
            return false;
        }
        Punto p = (Punto) o;
        return (sortX && (x < p.x || (x == p.x && y <= p.y))) || (!sortX && (y < p.y || (y == p.y && x <= p.x)));
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != Punto.class) {
            return false;
        } else {
            Punto pObj = (Punto) obj;
            return Math.abs(x - pObj.x) <= MINIMO_COMPARACION && Math.abs(y - pObj.y) <= MINIMO_COMPARACION;
        }
    }
}
