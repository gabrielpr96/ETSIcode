package patronmvc;

public class PatronMVC {

    public static void main(String[] args) {
        InterfazVista vista = new InterfazTextualConversor();
        ControlConversor controlador = new ControlConversor(vista, new ConversorEurosPesetas());
        vista.setControlador(controlador);
        vista.arranca();
    }
    
}
