package com.b0ve.solucionintegraciongenerica.tasks.modifiers;

import com.b0ve.solucionintegraciongenerica.flow.Buffer;
import com.b0ve.solucionintegraciongenerica.flow.Message;
import com.b0ve.solucionintegraciongenerica.tasks.Task;
import com.b0ve.solucionintegraciongenerica.utils.exceptions.SIGException;

public abstract class EnricherTemplate extends Task {

    public EnricherTemplate() {
        super(1, 1);
    }

    @Override
    public final void process() throws SIGException {
        Buffer output = output(0);
        Buffer input = input(0);
        while (!input.empty()) {
            Message mensaje = input.retrive();
            enrich(mensaje);
            output.push(mensaje);
        }
    }

    protected abstract void enrich(Message m) throws SIGException;

}
