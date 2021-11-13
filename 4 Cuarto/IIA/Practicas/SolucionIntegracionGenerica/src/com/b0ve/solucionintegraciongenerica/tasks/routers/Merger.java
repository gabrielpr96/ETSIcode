package com.b0ve.solucionintegraciongenerica.tasks.routers;

import com.b0ve.solucionintegraciongenerica.tasks.Task;
import com.b0ve.solucionintegraciongenerica.flow.Buffer;

public class Merger extends Task {

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
