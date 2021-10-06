package patroniteratorpractica;

public interface IIterador {
    public Object siguiente() throws OperacionNoSoportada;
    public boolean haySiguiente();
    public Object anterior() throws OperacionNoSoportada;
}
