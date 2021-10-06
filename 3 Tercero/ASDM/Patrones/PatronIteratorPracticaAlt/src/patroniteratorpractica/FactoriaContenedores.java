package patroniteratorpractica;

public class FactoriaContenedores extends Factoria {

    @Override
    public IIterable crearContenedor(String tipo) {
        switch (tipo.toLowerCase()) {
            case "lista":
                return new Lista();
            case "cola":
                return new Cola();
            case "pila":
                return new Pila();
            case "diccionario":
                return new Diccionario();
            default:
                return new Lista();
        }
    }

}
