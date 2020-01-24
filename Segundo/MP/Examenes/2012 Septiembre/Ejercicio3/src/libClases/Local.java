package libClases;

public class Local extends Alumno{
    
    private String apellido2;
    
    public Local(String id, String nombre, String apellido1, String apellido2, String direccion, int maxAsignaturas) throws ExceptionFormatoInvalido{
        super(id, nombre, apellido1, direccion, maxAsignaturas);
        this.apellido2 = apellido2;
    }
    
    @Override
    public String toString(){
        return "[LOCAL] "+apellido2+" "+super.toString();
    }
    
}
