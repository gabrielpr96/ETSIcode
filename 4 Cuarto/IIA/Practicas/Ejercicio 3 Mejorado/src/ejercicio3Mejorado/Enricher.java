package ejercicio3Mejorado;

import com.b0ve.solucionintegraciongenerica.tareas.Tarea;
import com.b0ve.solucionintegraciongenerica.utils.flujo.Buffer;
import com.b0ve.solucionintegraciongenerica.utils.flujo.Mensaje;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class Enricher extends Tarea {

    public Enricher() {
        super(1, 1);
    }

    @Override
    public final void procesar() {
        Buffer salida = salidas.get(0);
        Buffer entrada = entradas.get(0);
        while (!entrada.empty()) {
            Mensaje mensaje = entrada.retrive();
            try {
                enrich(mensaje);
            } catch (Exception ex) {
                Logger.getLogger(Enricher.class.getName()).log(Level.SEVERE, null, ex);
            }
            salida.push(mensaje);
        }
    }

    protected abstract void enrich(Mensaje mensaje) throws Exception;

}
