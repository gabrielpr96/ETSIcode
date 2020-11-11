package practica;

public class Linea {

    private final Punto p1, p2;
    private Double longitud;

    public Linea(Punto p1, Punto p2) {
        this.p1 = p1;
        this.p2 = p2;
        this.longitud = null;
    }

    public double longitud() {
        if (this.longitud == null) {
            this.longitud = p1.distancia(p2);
        }
        return longitud;
    }

    public Punto getP1() {
        return p1;
    }

    public Punto getP2() {
        return p2;
    }

    /**
     * Compara la longitud de una linea consigo misma
     *
     * @param l Linea a comparar
     * @return Verdadero si su longitud es menor.
     */
    public boolean comparar(Linea l) {
        return longitud() < l.longitud();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != Linea.class) {
            return false;
        } else {
            Linea lObj = (Linea) obj;
            return (p1.equals(lObj.p1) && p2.equals(lObj.p2)) || (p1.equals(lObj.p2) && p2.equals(lObj.p1));
        }
    }

    @Override
    public String toString() {
        return "[" + p1.toString() + ", " + p2.toString() + "]";
    }
}
