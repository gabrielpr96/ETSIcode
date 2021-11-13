package com.b0ve.solucionintegraciongenerica.tasks;

import com.b0ve.solucionintegraciongenerica.flow.Buffer;
import com.b0ve.solucionintegraciongenerica.flow.Message;
import com.b0ve.solucionintegraciongenerica.utils.exceptions.SIGException;

public class TaskDebug extends Task {

    private final boolean continuar;

    public TaskDebug() {
        this(false);
    }

    public TaskDebug(boolean continuar) {
        super(1, 1);
        this.continuar = continuar;
    }

    @Override
    public final void process() throws SIGException {
        Buffer entrada = input(0);
        while (!entrada.empty()) {
            Message mensaje = entrada.retrive();
            debugLog(mensaje.getBodyString());
            if (continuar) {
                output(0).push(mensaje);
            }
        }
    }

}
