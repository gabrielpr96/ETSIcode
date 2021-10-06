package libClases;

public class Erasmus extends Alumno{
    
    private String pais;
    
    public Erasmus(int id, String nombre, String apellido1, String direccion, String pais) {
        super(id, nombre, apellido1, direccion);
        this.pais = pais;
    }
    
    @Override
    public String toString(){
        return "[ERASMUS] "+pais+" "+super.toString();
    }
    
}
