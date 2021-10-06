package libClases;

public class ExceptionFormatoInvalido extends Exception{
    
    private String mensaje;
    
    public ExceptionFormatoInvalido(String mensaje){
        this.mensaje = mensaje;
    }
    
    @Override
    public String toString(){
        return "Formato invalido: "+mensaje;
    }
}
