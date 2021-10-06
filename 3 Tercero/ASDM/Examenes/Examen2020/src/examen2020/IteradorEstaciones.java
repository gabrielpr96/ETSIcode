package examen2020;

public class IteradorEstaciones implements Iterador {

    private final Estacion[] estaciones;
    private int pos;

    public IteradorEstaciones(Estacion[] estaciones) {
        this.estaciones = estaciones;
        pos = 0;
    }

    @Override
    public Estacion siguiente() {
        return haySiguiente() ? estaciones[pos++] : null;
    }

    @Override
    public boolean haySiguiente() {
        return pos >= 0 && pos < estaciones.length;
    }

    @Override
    public Estacion anterior() {
        return hayAnterior() ? estaciones[--pos] : null;
    }

    @Override
    public boolean hayAnterior() {
        return pos > 0 && pos <= estaciones.length;
    }

}
