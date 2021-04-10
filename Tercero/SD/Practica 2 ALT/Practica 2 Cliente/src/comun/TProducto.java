package comun;

import java.io.Serializable;

public class TProducto implements Serializable {

    private final String CodProducto, NombreProducto, Descripcion;
    private final float Precio;
    private final int Cantidad;
    private final TFecha Caducidad;

    public TProducto(String CodProducto, String NombreProducto, String Descripcion, float Precio, int Cantidad, TFecha Caducidad) {
        this.CodProducto = CodProducto;
        this.NombreProducto = NombreProducto;
        this.Descripcion = Descripcion;
        this.Precio = Precio;
        this.Cantidad = Cantidad;
        this.Caducidad = Caducidad;
    }

    public String getCodProducto() {
        return CodProducto;
    }

    public String getNombreProducto() {
        return NombreProducto;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public float getPrecio() {
        return Precio;
    }

    public int getCantidad() {
        return Cantidad;
    }

    public TFecha getCaducidad() {
        return Caducidad;
    }

}
