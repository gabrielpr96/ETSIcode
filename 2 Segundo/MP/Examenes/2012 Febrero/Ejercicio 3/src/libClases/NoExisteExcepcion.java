package libClases;

public class NoExisteExcepcion extends Exception{
    private String codigo;
    
    public NoExisteExcepcion(String codigo){
        this.codigo = codigo;
    }
    
    @Override
    public String toString(){
        return "No existe Producto con código "+codigo;
    }
}
