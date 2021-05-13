package patronmvcpratica.controlador;

import patronmvcpratica.vista.InterfazVista;
import patronmvcpratica.vista.cli.InterfazTextualConversor;
import java.awt.event.*;
import patronmvcpratica.modelo.ConversorEurosDolares;
import patronmvcpratica.modelo.ConversorEurosLibras;
import patronmvcpratica.modelo.ConversorEurosPesetas;
import patronmvcpratica.vista.gui.InterfazGraficoConversor;

public class ControlConversor implements ActionListener {

    private InterfazVista vista;
    private final ConversorEurosPesetas modeloPesetas;
    private final ConversorEurosLibras modeloLibras;
    private final ConversorEurosDolares modeloDolares;

    public ControlConversor(ConversorEurosPesetas modeloPesetas, ConversorEurosLibras modeloLibras, ConversorEurosDolares modeloDolares) {
        this.modeloPesetas = modeloPesetas;
        this.modeloLibras = modeloLibras;
        this.modeloDolares = modeloDolares;
    }

    public void setVista(InterfazVista v) {
        if (vista != null) {
            vista.detener();
        }
        vista = v;
        vista.setControlador(this);
        vista.arranca();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case InterfazVista.SALIR:
                vista.detener();
                System.exit(0);
                break;
            case InterfazVista.PESETAS_A_EUROS:
                double pesetasEuros = vista.getCantidad();
                vista.escribeCambio(pesetasEuros + " pesetas son " + modeloPesetas.pesetaAeuro(pesetasEuros) + " euros");
                break;
            case InterfazVista.EUROS_A_PESETAS:
                double eurosPesetas = vista.getCantidad();
                vista.escribeCambio(eurosPesetas + " euros son " + modeloPesetas.euroApeseta(eurosPesetas) + " pesetas");
                break;
            case InterfazVista.LIBRAS_A_EUROS:
                double librasEuros = vista.getCantidad();
                vista.escribeCambio(librasEuros + " libras son " + modeloLibras.libraAeuro(librasEuros) + " euros");
                break;
            case InterfazVista.EUROS_A_LIBRAS:
                double eurosLibras = vista.getCantidad();
                vista.escribeCambio(eurosLibras + " euros son " + modeloLibras.euroAlibra(eurosLibras) + " libras");
                break;
            case InterfazVista.DOLARES_A_EUROS:
                double dolaresEuros = vista.getCantidad();
                vista.escribeCambio(dolaresEuros + " dolares son " + modeloDolares.dolarAeuro(dolaresEuros) + " euros");
                break;
            case InterfazVista.EUROS_A_DOLARES:
                double eurosDolares = vista.getCantidad();
                vista.escribeCambio(eurosDolares + " euros son " + modeloDolares.euroAdolar(eurosDolares) + " dolares");
                break;
            case InterfazVista.CAMBIO_INTERFAZ_CLI:
                setVista(new InterfazTextualConversor());
                break;
            case InterfazVista.CAMBIO_INTERFAZ_GUI:
                setVista(new InterfazGraficoConversor());
                break;
            default:
                vista.escribeCambio("ERROR");

        }
    }

}
