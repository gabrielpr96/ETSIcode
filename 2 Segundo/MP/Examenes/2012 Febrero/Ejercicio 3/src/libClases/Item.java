package libClases;

public class Item {
    
    private final String codigo, nombre;
    private final int cantidad;
    private final float venta, compra;
    
    public Item(String codigo, String nombre, float venta, float compra, int cantidad){
        this.codigo = codigo;
        this.nombre = nombre;
        this.venta = venta;
        this.compra = compra;
        this.cantidad = cantidad;
    }
    
    public String getCodigo(){
        return codigo;
    }
    public String getNombre(){
        return nombre;
    }
    public float getCompra(){
        return compra;
    }
    public float getVenta(){
        return venta;
    }
    public int getCantidad(){
        return cantidad;
    }
}
