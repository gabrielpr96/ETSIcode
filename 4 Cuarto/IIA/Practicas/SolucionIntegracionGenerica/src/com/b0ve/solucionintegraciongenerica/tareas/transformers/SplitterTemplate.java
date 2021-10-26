package com.b0ve.solucionintegraciongenerica.tareas.transformers;

import com.b0ve.solucionintegraciongenerica.tareas.Tarea;
import com.b0ve.solucionintegraciongenerica.utils.flujo.Buffer;
import com.b0ve.solucionintegraciongenerica.utils.flujo.Mensaje;

public abstract class SplitterTemplate extends Tarea {

    private int contador;
    
    public SplitterTemplate() {
        super(1, 1);
        contador = 0;
    }

    @Override
    public final void procesar() {
        Buffer salida = salidas.get(0);
        Buffer entrada = entradas.get(0);
        while (!entrada.empty()) {
            Mensaje mensaje = entrada.retrive();
            String[] parts = split(mensaje);
            for (int i = 0; i < parts.length; i++) {
                Mensaje parte = new Mensaje(parts[i], contador, parts.length);
                salida.push(parte);
            }
            contador++;
        }
    }

    protected abstract String[] split(Mensaje mensaje);

}
