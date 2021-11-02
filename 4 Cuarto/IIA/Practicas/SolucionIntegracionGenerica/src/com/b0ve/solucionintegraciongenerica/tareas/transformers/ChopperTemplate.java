package com.b0ve.solucionintegraciongenerica.tareas.transformers;

import com.b0ve.solucionintegraciongenerica.tareas.Tarea;
import com.b0ve.solucionintegraciongenerica.utils.excepciones.ExecutionException;
import com.b0ve.solucionintegraciongenerica.utils.flujo.Buffer;
import com.b0ve.solucionintegraciongenerica.utils.flujo.Mensaje;

public abstract class ChopperTemplate extends Tarea {

    private int contador;
    
    public ChopperTemplate() {
        super(1, 0);
        contador = 0;
    }

    @Override
    public final void procesar() {
        Buffer entrada = entradas.get(0);
        while (!entrada.empty()) {
            Mensaje mensaje = entrada.retrive();
            //if(mensaje.getSequenceSize() != 0) throw new ExecutionException("No se puede fragmentar un fragmento de mensaje");
            String[] parts = split(mensaje);
            for (int i = 0; i < parts.length; i++) {
                Mensaje parte = new Mensaje(parts[i], contador, parts.length);
                salidas.get(i).push(parte);
            }
            contador++;
        }
    }

    protected abstract String[] split(Mensaje mensaje);

}
