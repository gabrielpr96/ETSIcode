package libProfesor;

public class ClaseB extends ClaseA{
    String cadena;
    public ClaseB(String cadena, int n){
        super(n);
        setCadena(cadena);
    }
    public ClaseB(String cadena){
        setCadena(cadena);
    }
    public ClaseB(ClaseB o){
        this(o.cadena, o.getN());
    }
    
    public final void setCadena(String cadena){
        this.cadena = cadena;
    }
    public String getCadena(){
        return cadena;
    }
}
