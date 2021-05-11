package patroniteratorpractica;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class PilaIterador implements IIterador{
    private final Object[] datos;
    private int posicion;

    public PilaIterador(Object[] datos) {
        List<Object> elementos = Arrays.asList(datos);
        Collections.reverse(elementos);
        this.datos = elementos.toArray(new Object[0]);
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
