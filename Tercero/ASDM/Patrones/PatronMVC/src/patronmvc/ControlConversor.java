package patronmvc;

import java.awt.event.*;

public class ControlConversor implements ActionListener {

    private InterfazVista vista;
    private ConversorEurosPesetas modelo;

    public ControlConversor(InterfazVista vista, ConversorEurosPesetas modelo) {
        this.vista = vista;
        this.modelo = modelo;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        double cantidad = vista.getCantidad();
        if (e.getActionCommand().equals(InterfazVista.AEUROS)) {
            vista.escribeCambio(cantidad + " pesetas son " + modelo.pesetaAeuro(cantidad) + " euros");
        }else if(e.getActionCommand().equals(InterfazVista.APESETAS)){
            vista.escribeCambio(cantidad + " euros son " + modelo.euroApeseta(cantidad) + " pesetas");
        }else{
            vista.escribeCambio("ERROR");
        }
    }

}
