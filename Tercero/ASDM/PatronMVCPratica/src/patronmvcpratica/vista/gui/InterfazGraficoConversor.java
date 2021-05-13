package patronmvcpratica.vista.gui;

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import patronmvcpratica.controlador.ControlConversor;
import patronmvcpratica.vista.InterfazVista;

public class InterfazGraficoConversor implements InterfazVista {

    private ControlConversor controlador;
    private JFrame frame;

    void procesaOperacion(int nop, String op) {
        if (frame != null) {
            frame.setVisible(false);
        }
        controlador.actionPerformed(new ActionEvent(this, nop, op));
        if (frame != null) {
            frame.setVisible(true);
        }
    }

    private void OperacionIncorrecta() {
        JOptionPane.showMessageDialog(null, "Error en la operación, intentelo de nuevo");
    }

    @Override
    public void setControlador(ControlConversor c) {
        controlador = c;
    }

    @Override
    public void arranca() {
        if (frame == null) {
            frame = (JFrame) new MenuPrincipal(this);
            frame.setLocationRelativeTo(null);
        }
        frame.setVisible(true);
    }

    @Override
    public double getCantidad() {
        try {
            return Double.parseDouble(JOptionPane.showInputDialog("Cantidad a convertir"));
        } catch (HeadlessException | NumberFormatException e) {
            OperacionIncorrecta();
            System.out.println("El formato debe ser 0.0");
            return getCantidad();
        }
    }

    @Override
    public void escribeCambio(String s) {
        JOptionPane.showMessageDialog(null, s);
        arranca();
    }

    @Override
    public void detener() {
        if (frame != null) {
            frame.dispose();
        }
    }

}
