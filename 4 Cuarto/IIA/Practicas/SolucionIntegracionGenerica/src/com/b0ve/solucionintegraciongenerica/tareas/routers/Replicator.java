package com.b0ve.solucionintegraciongenerica.tareas.routers;

import com.b0ve.solucionintegraciongenerica.tareas.Tarea;
import com.b0ve.solucionintegraciongenerica.utils.flujo.Buffer;
import com.b0ve.solucionintegraciongenerica.utils.flujo.Mensaje;

public class Replicator extends Tarea {

    public Replicator() {
        super(1, 0);
    }

    @Override
    public void procesar() {
        Buffer entrada = entradas.get(0);
        while (!entrada.empty()) {
            Mensaje m = entrada.retrive();
            for (Buffer salida : salidas) {
                salida.push(new Mensaje(m));
            }
        }
    }

}
