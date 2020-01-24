package libClases;

public class NoStockExcepcion extends Exception{
    private String nombre;
    
    public NoStockExcepcion(String nombre){
        this.nombre = nombre;
    }
    
    @Override
    public String toString(){
        return "Producto "+nombre+" sin existencias";
    }
}
