package patronadapterpractica;

import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.showMessageDialog;

public class AdaptadorCocheManual extends CocheManual implements IMandos {

    @Override
    public void Encender() {
        if (averia) {
            showMessageDialog(null, "Coche reparado", "Mensaje de coche manual", JOptionPane.INFORMATION_MESSAGE);
            Reparar();
        }
        super.Encender();
    }

    @Override
    public void Acelerar() {
        if (!encendido) {
            Encender();
        }
        super.SubirMarcha();
        super.Acelerar();
    }

    @Override
    public void Frenar() {
        if (marcha > 1) {
            super.BajarMarcha();
        }
        super.Frenar();
        if (velocidad == 0) {
            super.BajarMarcha();
            Apagar();
        }
    }
}
