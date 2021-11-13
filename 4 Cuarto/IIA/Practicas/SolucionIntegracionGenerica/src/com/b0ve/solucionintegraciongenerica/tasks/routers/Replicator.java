package com.b0ve.solucionintegraciongenerica.tasks.routers;

import com.b0ve.solucionintegraciongenerica.flow.Buffer;
import com.b0ve.solucionintegraciongenerica.flow.Message;
import com.b0ve.solucionintegraciongenerica.tasks.Task;
import com.b0ve.solucionintegraciongenerica.utils.exceptions.SIGException;

public class Replicator extends Task {

    public Replicator() {
        super(1, 0);
    }

    @Override
    public void process() throws SIGException {
        Buffer input = input(0);
        while (!input.empty()) {
            Message m = input.retrive();
            for (int i = 0; i < nOutputs(); i++) {
                output(i).push(new Message(m));
            }
        }
    }

}
