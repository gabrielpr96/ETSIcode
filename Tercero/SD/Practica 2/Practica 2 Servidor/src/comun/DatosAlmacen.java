package comun;

import java.io.Serializable;

public final class DatosAlmacen implements Serializable {
    private final String nombre;
    private final String direccion;
    private final String fichero;

    public DatosAlmacen(String nombre, String direccion, String fichero) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.fichero = fichero;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getFichero() {
        return fichero;
    }
    
    
}
