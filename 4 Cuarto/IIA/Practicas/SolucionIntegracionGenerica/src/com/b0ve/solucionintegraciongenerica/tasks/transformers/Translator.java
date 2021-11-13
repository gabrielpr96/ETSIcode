package com.b0ve.solucionintegraciongenerica.tasks.transformers;

import com.b0ve.solucionintegraciongenerica.flow.Message;
import com.b0ve.solucionintegraciongenerica.utils.exceptions.SIGException;

public final class Translator extends TranslatorTemplate {

    private final String xslt;

    public Translator(String xslt) {
        super();
        this.xslt = xslt;
    }

    @Override
    protected void transform(Message m) throws SIGException {
        m.transformBody(xslt);
    }

}
