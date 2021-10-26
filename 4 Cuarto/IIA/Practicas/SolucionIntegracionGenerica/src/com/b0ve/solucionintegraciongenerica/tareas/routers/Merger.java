package com.b0ve.solucionintegraciongenerica.tareas.routers;

import com.b0ve.solucionintegraciongenerica.tareas.Tarea;
import com.b0ve.solucionintegraciongenerica.utils.flujo.Buffer;

public class Merger extends Tarea {

    public Merger() {
        super(0, 1);
    }

    @Override
    public void procesar() {
        Buffer salida = salidas.get(0);
        for (Buffer entrada : entradas) {
            while (!entrada.empty()) {
                salida.push(entrada.retrive());
            }
        }
    }

}
