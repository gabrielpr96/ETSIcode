package patronprototypepractica;

public interface SerVivo {

    public Persona getCabezaDeFamilia();
    
    public String getNombre();

    public String getDireccion();

    public void setNombre(String nombre);

    public abstract SerVivo clonar();
}
