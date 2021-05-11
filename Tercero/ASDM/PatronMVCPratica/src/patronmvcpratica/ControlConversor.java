package patronmvcpratica;

import java.awt.event.*;

public class ControlConversor implements ActionListener {

    private InterfazVista vista;
    private ConversorEurosPesetas modeloPesetas;
    private ConversorEurosLibras modeloLibras;
    private ConversorEurosDolares modeloDolares;

    public ControlConversor(ConversorEurosPesetas modeloPesetas, ConversorEurosLibras modeloLibras, ConversorEurosDolares modeloDolares) {
        this.modeloPesetas = modeloPesetas;
        this.modeloLibras = modeloLibras;
        this.modeloDolares = modeloDolares;
    }

    public void setVista(InterfazVista v) {
        if (vista != null) {
            v.detener();
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
                vista.escribeCambio(librasEuros + " pesetas son " + modeloPesetas.pesetaAeuro(librasEuros) + " euros");
                break;
            case InterfazVista.EUROS_A_LIBRAS:
                double eurosLibras = vista.getCantidad();
                vista.escribeCambio(eurosLibras + " euros son " + modeloPesetas.euroApeseta(eurosLibras) + " pesetas");
                break;
            case InterfazVista.DOLARES_A_EUROS:
                double dolaresEuros = vista.getCantidad();
                vista.escribeCambio(dolaresEuros + " pesetas son " + modeloPesetas.pesetaAeuro(dolaresEuros) + " euros");
                break;
            case InterfazVista.EUROS_A_DOLARES:
                double eurosDolares = vista.getCantidad();
                vista.escribeCambio(eurosDolares + " euros son " + modeloPesetas.euroApeseta(eurosDolares) + " pesetas");
                break;
            case InterfazVista.CCAMBIO_INTERFAZ_CLI:
                setVista(new InterfazTextualConversor());
                break;
            case InterfazVista.CCAMBIO_INTERFAZ_GUI:
                setVista(new InterfazTextualConversor());
                break;
            default:
                vista.escribeCambio("ERROR");

        }
    }

}
