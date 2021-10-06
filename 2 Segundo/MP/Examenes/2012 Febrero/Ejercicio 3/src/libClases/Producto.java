package libClases;

public class Producto {
    private final String codigo, nombre;
    private final int stockMin, stockMax;
    private final float venta, compra;
    private int stock;
    
    public Producto(String codigo, String nombre, int stockMin, int stockMax, float venta, float compra){
        this.codigo = codigo;
        this.nombre = nombre;
        this.stockMin = stockMin;
        this.stockMax = stockMax;
        this.venta = venta;
        this.compra = compra;
        stock = stockMax;
    }
    
    public String getCodigo(){
        return codigo;
    }
    public String getNombre(){
        return nombre;
    }
    public int getStockMin(){
        return stockMin;
    }
    public int getStockMax(){
        return stockMax;
    }
    public float getCompra(){
        return compra;
    }
    public float getVenta(){
        return venta;
    }
    
    public int getStock(){
        return stock;
    }
    public void addStock(int n){
        if(stock + n < stockMax)
            stock += n;
        else stock = stockMax;
    }
    public void reduceStock(int n) throws NoStockExcepcion{
        if(stock - n < stockMin){
            throw new NoStockExcepcion(nombre);
        }else{
            stock -= n;
        }
    }
}
