package com.b0ve.solucionintegraciongenerica.tasks.modifiers;

import com.b0ve.solucionintegraciongenerica.tasks.Task;
import com.b0ve.solucionintegraciongenerica.flow.Buffer;
import com.b0ve.solucionintegraciongenerica.flow.Message;
import com.b0ve.solucionintegraciongenerica.utils.exceptions.SIGException;

public class CorrelationIDSetter extends Task {

    private int contador;
    
    public CorrelationIDSetter(){
        super(1, 1);
        contador = 0;
    }

    @Override
    public void process() throws SIGException {
        Buffer output = output(0);
        Buffer input = input(0);
        while (!input.empty()) {
            Message m = input.retrive();
            m.setCorrelationID(contador++);
            output.push(m);
        }
    }

}
