package practica.geometria;

import java.util.List;

public class Camino {
    private final List<Punto> vertices;
    private final double coste;

    public Camino(List<Punto> vertices, double coste) {
        this.vertices = vertices;
        this.coste = coste;
    }

    public List<Punto> getVertices() {
        return vertices;
    }

    public double getCoste() {
        return coste;
    }
    
    
}
