package com.b0ve.solucionintegraciongenerica.tareas.transformers;

import com.b0ve.solucionintegraciongenerica.utils.flujo.Mensaje;

public final class Translator extends TranslatorTemplate {

    private final String xslt;

    public Translator(String xslt) {
        super();
        this.xslt = xslt;
    }

    @Override
    protected void transform(Mensaje mensaje) {
        mensaje.transformBody(xslt);
    }

}
