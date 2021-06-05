package patronprototypepractica;

public class Mascota implements SerVivo{

    private String nombre, direccion;
    private Persona dueno;

    public Mascota(String nombre, String direccion, Persona dueno) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.dueno = dueno;
    }
    
    public Mascota(){
        this(null, null, null);
    }

    @Override
    public Persona getCabezaDeFamilia() {
        return dueno.getCabezaDeFamilia();
    }
    
    @Override
    public String getNombre() {
        return nombre;
    }

    @Override
    public String getDireccion() {
        return direccion;
    }

    public Persona getDueno() {
        return dueno;
    }

    @Override
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setDueno(Persona dueno) {
        this.dueno = dueno;
    }
    
    
    
    @Override
    public SerVivo clonar() {
        return new Mascota(nombre, direccion, dueno);
    }

    @Override
    public String toString() {
        return nombre;
    }
    
}
