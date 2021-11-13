package ejercicio3Mejorado;

import com.b0ve.solucionintegraciongenerica.tasks.Task;
import com.b0ve.solucionintegraciongenerica.flow.Buffer;
import com.b0ve.solucionintegraciongenerica.flow.Message;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class Enricher extends Task {

    public Enricher() {
        super(1, 1);
    }

    @Override
    public final void procesar() {
        Buffer salida = salidas.get(0);
        Buffer entrada = entradas.get(0);
        while (!entrada.empty()) {
            Message mensaje = entrada.retrive();
            try {
                enrich(mensaje);
            } catch (Exception ex) {
                Logger.getLogger(Enricher.class.getName()).log(Level.SEVERE, null, ex);
            }
            salida.push(mensaje);
        }
    }

    protected abstract void enrich(Message mensaje) throws Exception;

}
