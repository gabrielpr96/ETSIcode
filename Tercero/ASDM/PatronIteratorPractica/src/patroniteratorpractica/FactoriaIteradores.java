package patroniteratorpractica;

public class FactoriaIteradores extends Factoria {

    @Override
    public IIterador crearIterador(IIterable contenedor, String tipo) {
        switch (tipo.toLowerCase()) {
            case "lista":
                return new ListaIterador(contenedor.getDatos());
            case "cola":
                return new ColaIterador(contenedor.getDatos());
            case "pila":
                return new PilaIterador(contenedor.getDatos());
            default:
                return null;
        }
    }

}
