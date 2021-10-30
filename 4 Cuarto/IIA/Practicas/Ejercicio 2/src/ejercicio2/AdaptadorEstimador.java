package ejercicio2;

import com.b0ve.solucionintegraciongenerica.adaptadores.Adaptador;
import com.b0ve.solucionintegraciongenerica.utils.flujo.Mensaje;

public class AdaptadorEstimador extends Adaptador {

    @Override
    public void enviarApp(Mensaje m) {
        String ts = m.evaluateXPathString("/medida/ts");
        String lugar = m.evaluateXPathString("/medida/lugar");
        String valor = m.evaluateXPathString("/medida/valor");
        System.out.println("Procesar medida: "+ts+" "+lugar+" -> "+valor);
    }
}
