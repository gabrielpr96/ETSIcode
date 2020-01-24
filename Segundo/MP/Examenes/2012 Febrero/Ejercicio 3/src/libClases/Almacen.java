package libClases;

public class Almacen {
    
    private Producto[] productos;
    private int nProds;
    
    public Almacen(int maxProductos){
        productos = new Producto[maxProductos];
        nProds = 0;
    }
    
    public void addProducto(Producto p){
        if(nProds < productos.length)
            productos[nProds++] = p;
    }
    
    public Producto getProducto(String codigo){
        Producto c = null;
        int i = 0;
        while(i < productos.length && c == null)
            if(productos[i].getCodigo().equals(codigo))
                c = productos[i];
            else i++;
        return c;
    }
}
