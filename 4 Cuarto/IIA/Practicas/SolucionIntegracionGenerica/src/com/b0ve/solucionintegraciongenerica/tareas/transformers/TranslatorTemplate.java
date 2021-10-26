package com.b0ve.solucionintegraciongenerica.tareas.transformers;

import com.b0ve.solucionintegraciongenerica.tareas.Tarea;
import com.b0ve.solucionintegraciongenerica.utils.flujo.Buffer;
import com.b0ve.solucionintegraciongenerica.utils.flujo.Mensaje;

public abstract class TranslatorTemplate extends Tarea {

    public TranslatorTemplate() {
        super(1, 1);
    }

    @Override
    public final void procesar() {
        Buffer salida = salidas.get(0);
        Buffer entrada = entradas.get(0);
        while (!entrada.empty()) {
            Mensaje mensaje = entrada.retrive();
            transform(mensaje);
            salida.push(mensaje);
        }
    }

    protected abstract void transform(Mensaje mensaje);

}
