package patroniteratorpractica;

public abstract class Factoria {
    public abstract IIterador crearIterador(IIterable contenedor, String tipo);
}
