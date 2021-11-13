package com.b0ve.solucionintegraciongenerica.tasks.modifiers;

import com.b0ve.solucionintegraciongenerica.flow.Buffer;
import com.b0ve.solucionintegraciongenerica.flow.Message;
import com.b0ve.solucionintegraciongenerica.tasks.Task;
import com.b0ve.solucionintegraciongenerica.utils.exceptions.SIGException;

public abstract class ContextEnricherTemplate extends Task {

    public ContextEnricherTemplate() {
        super(2, 1);
    }

    @Override
    public final void process() throws SIGException {
        Buffer output = output(0);
        Buffer inputBase = input(0);
        Buffer inputModification = input(1);
        while (!inputBase.empty() && !inputModification.empty()) {
            Message mBase = inputBase.retrive();
            Message mCondition = inputModification.retrive();
            enrich(mBase, mCondition);
            output.push(mBase);
        }
    }

    protected abstract void enrich(Message m, Message condition) throws SIGException;

}
