package com.b0ve.solucionintegraciongenerica.tasks.modifiers;

import com.b0ve.solucionintegraciongenerica.tasks.Task;
import com.b0ve.solucionintegraciongenerica.flow.Buffer;
import com.b0ve.solucionintegraciongenerica.flow.Message;

public abstract class ContextEnricherTemplate extends Task {

    public ContextEnricherTemplate() {
        super(2, 1);
    }

    @Override
    public final void procesar() {
        Buffer salida = salidas.get(0);
        Buffer entradaDato = entradas.get(0);
        Buffer entradaCondicion = entradas.get(1);
        while (!entradaDato.empty() && !entradaCondicion.empty()) {
            Message mensajeDato = entradaDato.retrive();
            Message mensajeCondicion = entradaCondicion.retrive();
            enrich(mensajeDato, mensajeCondicion);
            salida.push(mensajeDato);
        }
    }

    protected abstract void enrich(Message mensaje, Message condicion);

}
