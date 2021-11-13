package com.b0ve.solucionintegraciongenerica.tasks.modifiers;

import com.b0ve.solucionintegraciongenerica.tasks.Task;
import com.b0ve.solucionintegraciongenerica.flow.Buffer;
import com.b0ve.solucionintegraciongenerica.flow.Message;

public abstract class SlimmerTemplate extends Task {

    public SlimmerTemplate() {
        super(1, 1);
    }

    @Override
    public final void procesar() {
        Buffer salida = salidas.get(0);
        Buffer entrada = entradas.get(0);
        while (!entrada.empty()) {
            Message mensaje = entrada.retrive();
            slim(mensaje);
            salida.push(mensaje);
        }
    }

    protected abstract void slim(Message mensaje);

}
