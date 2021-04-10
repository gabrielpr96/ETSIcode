package comun;

import java.io.Serializable;

public class TDatosAlmacen implements Serializable {

    private final String Nombre, Direccion, Fichero;

    public TDatosAlmacen(String Nombre, String Direccion, String Fichero) {
        this.Nombre = Nombre;
        this.Direccion = Direccion;
        this.Fichero = Fichero;
    }

    public String getNombre() {
        return Nombre;
    }

    public String getDireccion() {
        return Direccion;
    }

    public String getFichero() {
        return Fichero;
    }
}
