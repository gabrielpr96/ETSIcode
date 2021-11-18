package com.b0ve.solucionintegraciongenerica.tasks.modifiers;

import com.b0ve.solucionintegraciongenerica.flow.Buffer;
import com.b0ve.solucionintegraciongenerica.flow.Message;
import com.b0ve.solucionintegraciongenerica.tasks.Task;
import com.b0ve.solucionintegraciongenerica.utils.exceptions.SIGException;

/**
 * Sets the correlation ID messages
 * @author borja
 */
public abstract class CorrelationIDSetterTemplate extends Task {

    public CorrelationIDSetterTemplate() {
        super(1, 1);
    }

    @Override
    public void process() throws SIGException {
        Buffer output = output(0);
        Buffer input = input(0);
        while (!input.empty()) {
            Message m = input.retrive();
            giveCorrelationID(m);
            output.push(m);
        }
    }

    protected abstract void giveCorrelationID(Message m);

}
