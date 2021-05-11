package patronmvcpratica;

public class PatronMVCPratica {

    public static void main(String[] args) {
        InterfazVista vista = new InterfazTextualConversor();
        ControlConversor controlador = new ControlConversor(new ConversorEurosPesetas(), new ConversorEurosLibras(), new ConversorEurosDolares());
        controlador.setVista(vista);
    }

}
