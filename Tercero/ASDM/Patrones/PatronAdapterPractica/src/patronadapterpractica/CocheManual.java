package patronadapterpractica;

import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.showMessageDialog;

public class CocheManual extends Coche {

    protected int marcha;

    public CocheManual() {
        super();
        marcha = 0;
    }

    public void Encender() {
        if(!averia){
            encendido = true;
        }
    }

    public void Acelerar() {
        if (encendido) {
            revoluciones += 1000;
            if (velocidad == 0) {
                velocidad = 20;
            } else {
                velocidad *= 1 + (revoluciones / 5500.0f);
            }
            ComprobarEstado();
        }
    }

    public void Frenar() {
        if (encendido) {
            if (revoluciones > 0) {
                revoluciones -= 1000;
                if (revoluciones == 0) {
                    velocidad = 0;
                } else {
                    velocidad /= 1.5f;
                }
            }
            ComprobarEstado();
        }
    }

    //Superar la 5º marcha causa un apagado repentino que proboca una averia por no haber apagado en punto muerto.
    public void SubirMarcha() {
        if (encendido) {
            marcha++;
            if (marcha > 5) {
                showMessageDialog(null, "El motor se ha apagado por exceder la quinta marcha", "Alerta de coche manual", JOptionPane.ERROR_MESSAGE);
                Apagar();
            }
            ComprobarEstado();
        }
    }

    public void BajarMarcha() {
        if (encendido) {
            if (marcha > 0) {
                marcha--;
            }
            ComprobarEstado();
        }
    }

    //Podemos ir una marcha más o menos por debajo de las RPM aceptadas, si lo excedemos se produce uan avería.
    //Este metodo realiza la comprovacion
    private void ComprobarEstado() {
        if (encendido) {
            if ((marcha == 0 && revoluciones > 0) || (marcha > 0 && (revoluciones < (marcha - 1) * 1000 || revoluciones > (marcha + 1) * 1000))) {
                showMessageDialog(null, "Se ha producido una avería por tener el motor a " + revoluciones + " RPM e ir en " + marcha + "ª", "Alerta de coche manual", JOptionPane.ERROR_MESSAGE);
                averia = true;
                revoluciones = 0;
                velocidad = 0;
                marcha = 0;
                encendido = false;
            }
        }
    }

    public void Reparar() {
        if (!encendido) {
            averia = false;
        } else {
            showMessageDialog(null, "No se puede reparar el coche mientras este encendido", "Alerta de coche manual", JOptionPane.ERROR_MESSAGE);
        }
    }

    //Apagar el coche mientras no está en punto muerto causa una avería
    public void Apagar() {
        if (marcha != 0) {
            averia = true;
            showMessageDialog(null, "Se ha producido una avería por apagar el coche no estando en punto muerto", "Alerta de coche manual", JOptionPane.ERROR_MESSAGE);
        }
        marcha = 0;
        revoluciones = 0;
        velocidad = 0;
        encendido = false;
    }

    public int getMarcha() {
        return marcha;
    }

}
