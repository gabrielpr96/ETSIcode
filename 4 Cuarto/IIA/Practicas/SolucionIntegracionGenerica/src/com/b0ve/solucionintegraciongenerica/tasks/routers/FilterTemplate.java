package com.b0ve.solucionintegraciongenerica.tasks.routers;

import com.b0ve.solucionintegraciongenerica.flow.Buffer;
import com.b0ve.solucionintegraciongenerica.flow.Message;
import com.b0ve.solucionintegraciongenerica.tasks.Task;
import com.b0ve.solucionintegraciongenerica.utils.exceptions.SIGException;

public abstract class FilterTemplate extends Task {

    public FilterTemplate() {
        super(1, 1);
    }

    @Override
    public final void process() throws SIGException {
        Buffer output = output(0);
        Buffer input = input(0);
        while (!input.empty()) {
            Message mensaje = input.retrive();
            if (check(mensaje)) {
                output.push(mensaje);
            } else {
                debugLog("Filtro deleted: " + mensaje.toString());
            }
        }
    }

    protected abstract boolean check(Message mensaje) throws SIGException;

}
