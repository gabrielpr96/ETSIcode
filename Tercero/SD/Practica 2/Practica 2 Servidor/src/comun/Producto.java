package comun;

import java.io.Serializable;

public final class Producto implements Serializable {

    private final String codProducto;
    private final int cantidad;
    private final String nombreProducto;
    private final float precio;
    private final String descripcion;
    private final Fecha caducidad;

    public Producto(String codProducto, int cantidad, String nombreProducto, float precio, String descripcion, Fecha caducidad) {
        this.codProducto = codProducto;
        this.cantidad = cantidad;
        this.nombreProducto = nombreProducto;
        this.precio = precio;
        this.descripcion = descripcion;
        this.caducidad = caducidad;
    }

    public String getCodProducto() {
        return codProducto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public float getPrecio() {
        return precio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Fecha getCaducidad() {
        return caducidad;
    }
    
    @Override
    public String toString(){
        return nombreProducto;
    }

}
