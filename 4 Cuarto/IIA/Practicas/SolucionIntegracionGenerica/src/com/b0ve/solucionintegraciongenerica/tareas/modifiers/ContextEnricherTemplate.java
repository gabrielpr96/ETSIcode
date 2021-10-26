package com.b0ve.solucionintegraciongenerica.tareas.modifiers;

import com.b0ve.solucionintegraciongenerica.tareas.Tarea;
import com.b0ve.solucionintegraciongenerica.utils.flujo.Buffer;
import com.b0ve.solucionintegraciongenerica.utils.flujo.Mensaje;

public abstract class ContextEnricherTemplate extends Tarea {

    public ContextEnricherTemplate() {
        super(2, 1);
    }

    @Override
    public final void procesar() {
        Buffer salida = salidas.get(0);
        Buffer entradaDato = entradas.get(0);
        Buffer entradaCondicion = entradas.get(1);
        while (!entradaDato.empty() && !entradaCondicion.empty()) {
            Mensaje mensajeDato = entradaDato.retrive();
            Mensaje mensajeCondicion = entradaCondicion.retrive();
            enrich(mensajeDato, mensajeCondicion);
            salida.push(mensajeDato);
        }
    }

    protected abstract void enrich(Mensaje mensaje, Mensaje condicion);

}
