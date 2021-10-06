package patronmvcpratica;

import patronmvcpratica.modelo.ConversorEurosLibras;
import patronmvcpratica.modelo.ConversorEurosPesetas;
import patronmvcpratica.modelo.ConversorEurosDolares;
import patronmvcpratica.controlador.ControlConversor;
import patronmvcpratica.vista.cli.InterfazTextualConversor;

public class PatronMVCPratica {

    public static void main(String[] args) {
        ControlConversor controlador = new ControlConversor(new ConversorEurosPesetas(), new ConversorEurosLibras(), new ConversorEurosDolares());
        controlador.setVista(new InterfazTextualConversor());
    }

}
