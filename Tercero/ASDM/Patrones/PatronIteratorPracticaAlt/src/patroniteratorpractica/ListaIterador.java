package patroniteratorpractica;

public class ListaIterador implements IIterador{
    private final Object[] datos;
    private int posicion;

    public ListaIterador(Object[] datos) {
        this.datos = datos;
        posicion = 0;
    }

    @Override
    public Object siguiente() {
        return posicion < datos.length ?  datos[posicion++] : null;
    }

    @Override
    public boolean haySiguiente() {
        return posicion < datos.length;
    }

    @Override
    public Object anterior() {
        return posicion > 0 ?  datos[--posicion] : null;
    }
    
    
}
