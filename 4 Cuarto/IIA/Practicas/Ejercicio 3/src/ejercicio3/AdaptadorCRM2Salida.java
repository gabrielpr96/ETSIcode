package ejercicio3;

import com.b0ve.solucionintegraciongenerica.adaptadores.Adaptador;
import com.b0ve.solucionintegraciongenerica.utils.flujo.Mensaje;

public class AdaptadorCRM2Salida extends Adaptador {

    @Override
    public void enviarApp(Mensaje m) {
        System.out.println("CAMBIO: "+m.getBody());
    }

    @Override
    public void detener() {
    }
}
