package patronprototypepractica;

public class Persona implements SerVivo {

    private String nombre, apellidos, telefono, direccion, codigoPostal, ciudad, nivelEconomico, correoElectronico, nivelDeEstudios;
    private Persona cabezaDeFamilia;
    
    public Persona(String nombre, String apellidos, String telefono, String direccion, String codigoPostal, String ciudad, String nivelEconomico, String correoElectronico, String nivelDeEstudios) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.telefono = telefono;
        this.direccion = direccion;
        this.codigoPostal = codigoPostal;
        this.ciudad = ciudad;
        this.nivelEconomico = nivelEconomico;
        this.correoElectronico = correoElectronico;
        this.nivelDeEstudios = nivelDeEstudios;
        cabezaDeFamilia = this;
    }

    public Persona() {
        this(null, null, null, null, null, null, null, null, null);
    }

    @Override
    public Persona getCabezaDeFamilia() {
        return cabezaDeFamilia;
    }

    @Override
    public String getNombre() {
        return nombre;
    }

    @Override
    public String getDireccion() {
        return direccion;
    }

    public String getApellidos() {
        return apellidos;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public String getCiudad() {
        return ciudad;
    }

    public String getNivelEconomico() {
        return nivelEconomico;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public String getNivelDeEstudios() {
        return nivelDeEstudios;
    }

    @Override
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public void setNivelEconomico(String nivelEconomico) {
        this.nivelEconomico = nivelEconomico;
    }

    
    
    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public void setNivelDeEstudios(String nivelDeEstudios) {
        this.nivelDeEstudios = nivelDeEstudios;
    }
    
    public void setCabezaDeFamilia(Persona cabezaDeFamilia){
        this.cabezaDeFamilia = cabezaDeFamilia;
    }

    @Override
    public SerVivo clonar() {
        Persona clon = new Persona(nombre, apellidos, telefono, direccion, codigoPostal, ciudad, nivelEconomico, correoElectronico, nivelDeEstudios);
        clon.setCabezaDeFamilia(this);
        return clon;
    }

    @Override
    public String toString() {
        return nombre;
    }

}
