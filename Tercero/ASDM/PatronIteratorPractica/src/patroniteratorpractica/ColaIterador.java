package patroniteratorpractica;

public class ColaIterador implements IIterador{
    private final Object[] datos;
    private int posicion;

    public ColaIterador(Object[] datos) {
        this.datos = datos;
        posicion = 0;
    }

    @Override
    public Object siguiente() throws OperacionNoSoportada {
        if(posicion < datos.length)
            return datos[posicion++];
        else 
            throw new OperacionNoSoportada("No hay siguiente");
    }

    @Override
    public boolean haySiguiente() {
        return posicion < datos.length;
    }

    @Override
    public Object anterior() throws OperacionNoSoportada {
        throw new OperacionNoSoportada("No se puede volver atras en una cola");
    }
    
    
}
