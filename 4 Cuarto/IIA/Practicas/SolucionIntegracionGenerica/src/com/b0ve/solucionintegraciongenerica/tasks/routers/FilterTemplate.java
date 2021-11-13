package com.b0ve.solucionintegraciongenerica.tasks.routers;

import com.b0ve.solucionintegraciongenerica.tasks.Task;
import com.b0ve.solucionintegraciongenerica.flow.Buffer;
import com.b0ve.solucionintegraciongenerica.flow.Message;

public abstract class FilterTemplate extends Task {

    public FilterTemplate() {
        super(1, 1);
    }

    @Override
    public final void procesar() {
        Buffer salida = salidas.get(0);
        Buffer entrada = entradas.get(0);
        while (!entrada.empty()) {
            Message mensaje = entrada.retrive();
            if (comprobar(mensaje)) {
                salida.push(mensaje);
            } else {
                debugLog("Filtro elimino: " + mensaje.toString());
            }
        }
    }

    protected abstract boolean comprobar(Message mensaje);

}
