package libClases;

public class Standard extends Alumno{
    
    private String apellido2;
    
    public Standard(int id, String nombre, String apellido1, String apellido2, String direccion) {
        super(id, nombre, apellido1, direccion);
        this.apellido2 = apellido2;
    }
    
    @Override
    public String toString(){
        return "[SRTANDARD] "+apellido2+" "+super.toString();
    }
    
}
