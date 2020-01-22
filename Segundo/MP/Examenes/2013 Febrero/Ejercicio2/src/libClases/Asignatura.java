package libClases;

public class Asignatura {
    private final int codigo;
    private float calificacion;
    private boolean evaluada;
    
    public Asignatura(int codigo, float calificacion, boolean evaluada){
        this.codigo = codigo;
        this.calificacion = calificacion;
        this.evaluada = evaluada;
    }
    
    public void setCalificacion(float c){
        calificacion = c;
    }
    public void setEvaluada(boolean e){
        evaluada = e;
    }
    public float getCalificacion(){
        return calificacion;
    }
    public boolean getEvaluada(){
        return evaluada;
    }
    public int getCodigo(){
        return codigo;
    }
    
    @Override
    public String toString(){
        return "Asignatura "+(codigo<10?"0":"")+codigo+" >> "+(evaluada?calificacion:"no evaluada");  
    }
}
