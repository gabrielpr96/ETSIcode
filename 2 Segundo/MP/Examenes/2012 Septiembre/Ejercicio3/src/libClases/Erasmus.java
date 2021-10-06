package libClases;

public class Erasmus extends Alumno{
    
    private String pais;
    
    public Erasmus(String id, String nombre, String apellido1, String pais, String direccion, int maxAsignaturas) throws ExceptionFormatoInvalido{
        super(id, nombre, apellido1, direccion, maxAsignaturas);
        this.pais = pais;
    }
    
    @Override
    public String toString(){
        return "[ERASMUS] "+pais+" "+super.toString();
    }
    
}
