package com.b0ve.solucionintegraciongenerica.tasks.modifiers;

import com.b0ve.solucionintegraciongenerica.flow.Message;

public class CorrelationIDSetter extends CorrelationIDSetterTemplate {

    private int contador;

    public CorrelationIDSetter() {
        super();
        contador = 0;
    }

    @Override
    protected void giveCorrelationID(Message m) {
        m.setCorrelationID(contador++);
    }

}
