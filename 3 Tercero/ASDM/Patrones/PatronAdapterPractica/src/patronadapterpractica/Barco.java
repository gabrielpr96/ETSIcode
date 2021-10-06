package patronadapterpractica;

import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.showMessageDialog;

public class Barco {

    protected int velocidad;
    protected boolean grifo;

    public Barco() {
        velocidad = 0;
        grifo = false;
        showMessageDialog(null, "Barco iniciado", "Mensaje de barco", JOptionPane.INFORMATION_MESSAGE);
    }

    public void Conectar() {
        if (!grifo) {
            grifo = true;
            showMessageDialog(null, "El grifo se ha abierto", "Mensaje de barco", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void Activar() {
        if (grifo) {
            showMessageDialog(null, "Barco activado", "Mensaje de barco", JOptionPane.INFORMATION_MESSAGE);
            velocidad = 1;
        } else {
            showMessageDialog(null, "No se puede activar el barco con el grifo cerrado", "Alerta de barco", JOptionPane.WARNING_MESSAGE);
        }
    }

    public void MoverMasRapido() {
        if (grifo) {
            velocidad += 1;
            showMessageDialog(null, "Velocidad del barco aumentada", "Mensaje de barco", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void Detener() {
        if (velocidad > 0) {
            velocidad = Math.max(0, velocidad-5);
            showMessageDialog(null, "Velocidad del barco disminuida", "Mensaje de barco", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void Desconectar() {
        grifo = false;
        velocidad = 0;
        showMessageDialog(null, "Grifo cerrado y barco desconectado", "Mensaje de barco", JOptionPane.INFORMATION_MESSAGE);
    }

    public int getVelocidad() {
        return velocidad;
    }

    public boolean isGrifo() {
        return grifo;
    }
    
    
}
