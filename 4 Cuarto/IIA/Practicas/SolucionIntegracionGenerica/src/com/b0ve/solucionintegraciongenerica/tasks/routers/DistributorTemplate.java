package com.b0ve.solucionintegraciongenerica.tasks.routers;

import com.b0ve.solucionintegraciongenerica.flow.Buffer;
import com.b0ve.solucionintegraciongenerica.flow.Message;
import com.b0ve.solucionintegraciongenerica.tasks.Task;
import com.b0ve.solucionintegraciongenerica.utils.exceptions.SIGException;

public abstract class DistributorTemplate extends Task {

    public DistributorTemplate() {
        super(1, 0);
    }

    @Override
    public final void process() throws SIGException {
        Buffer ouput = input(0);
        while (!ouput.empty()) {
            Message m = ouput.retrive();
            output(check(m)).push(m);
        }
    }

    protected abstract int check(Message mensaje) throws SIGException;

}
