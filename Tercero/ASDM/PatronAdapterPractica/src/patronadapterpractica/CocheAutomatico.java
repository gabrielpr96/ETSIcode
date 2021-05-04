package patronadapterpractica;

//Este vehiculo no se puede averiar

import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.showMessageDialog;

public class CocheAutomatico extends Coche implements IMandos{

    @Override
    public void Acelerar() {
        encendido = true;
        if (revoluciones < 5000) {
            revoluciones += 1000;
            if (velocidad == 0) {
                velocidad = 20;
            } else {
                velocidad *= 1 + (revoluciones / 5500.0f);
            }
        }else{
            showMessageDialog(null, "El coche ha prevenido una avería cancelando la aceleración", "Alerta de coche automático", JOptionPane.WARNING_MESSAGE);
        }
    }

    //El coche automatico se apaga si al frenar la velcidad es 0.
    @Override
    public void Frenar() {
        if (encendido) {
            if (revoluciones > 0) {
                revoluciones -= 1000;
                if (revoluciones == 0) {
                    velocidad = 0;
                    encendido = false;
                } else {
                    velocidad /= 1.5f;
                }
            }
        }
    }

}
