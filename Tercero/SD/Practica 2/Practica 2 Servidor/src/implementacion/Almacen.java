package implementacion;

import comun.DatosAlmacen;
import comun.Producto;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Almacen implements Serializable{

    private int nAbierto;
    private final DatosAlmacen datos;
    private final Map<String, Producto> productos;

    public Almacen(int nAbierto, DatosAlmacen Datos) {
        this.nAbierto = nAbierto;
        this.datos = Datos;
        this.productos = new HashMap<>();
    }

    public int getnAbierto() {
        return nAbierto;
    }

    public DatosAlmacen getDatos() {
        return datos;
    }

    public Map<String, Producto> getProductos() {
        return productos;
    }
    
    public void resetAbiertos(){
        nAbierto = 1;
    }
    
    public void abrir(){
        nAbierto++;
    }
    
    public void cerrar(){
        nAbierto--;
    }
    
    public boolean abierto(){
        return nAbierto != 0;
    }
}
