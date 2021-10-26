package com.b0ve.solucionintegraciongenerica.tareas.modifiers;

import com.b0ve.solucionintegraciongenerica.tareas.Tarea;
import com.b0ve.solucionintegraciongenerica.utils.flujo.Buffer;
import com.b0ve.solucionintegraciongenerica.utils.flujo.Mensaje;

public abstract class ContextSlimmerTemplate extends Tarea {

    public ContextSlimmerTemplate() {
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
            slim(mensajeDato, mensajeCondicion);
            salida.push(mensajeDato);
        }
    }

    protected abstract void slim(Mensaje mensaje, Mensaje condicion);

}
