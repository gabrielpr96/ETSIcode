package libClases;

public class ExcepcionNoexiste extends Exception{
    
    int n;
    
    public ExcepcionNoexiste(int n){
        this.n = n;
    }
    
    @Override
    public String toString(){
        return "El alumno "+n+" no existe o se sale del rango";
    }
}
