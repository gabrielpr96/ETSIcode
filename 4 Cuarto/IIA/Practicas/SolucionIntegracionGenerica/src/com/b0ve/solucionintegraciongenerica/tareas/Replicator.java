package com.b0ve.solucionintegraciongenerica.tareas;

import com.b0ve.solucionintegraciongenerica.utils.Buffer;
import com.b0ve.solucionintegraciongenerica.utils.Mensaje;

public class Replicator extends Tarea {

    public Replicator() {
        super(1, 0);
    }

    @Override
    protected void procesar() {
        Buffer entrada = entradas.get(0);
        while (!entrada.empty()) {
            Mensaje m = entrada.retrive();
            for (Buffer salida : salidas) {
                salida.push(new Mensaje(m));
            }
        }
    }

}
