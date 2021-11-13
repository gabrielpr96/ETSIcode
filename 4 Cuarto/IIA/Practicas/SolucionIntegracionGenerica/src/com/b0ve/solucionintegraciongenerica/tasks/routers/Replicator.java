package com.b0ve.solucionintegraciongenerica.tasks.routers;

import com.b0ve.solucionintegraciongenerica.tasks.Task;
import com.b0ve.solucionintegraciongenerica.flow.Buffer;
import com.b0ve.solucionintegraciongenerica.flow.Message;

public class Replicator extends Task {

    public Replicator() {
        super(1, 0);
    }

    @Override
    public void procesar() {
        Buffer entrada = entradas.get(0);
        while (!entrada.empty()) {
            Message m = entrada.retrive();
            for (Buffer salida : salidas) {
                salida.push(new Message(m));
            }
        }
    }

}
