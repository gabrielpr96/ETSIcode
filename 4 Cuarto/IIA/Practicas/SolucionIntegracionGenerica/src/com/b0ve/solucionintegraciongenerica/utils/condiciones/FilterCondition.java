package com.b0ve.solucionintegraciongenerica.utils.condiciones;

import com.b0ve.solucionintegraciongenerica.utils.flujo.Mensaje;

public abstract class FilterCondition implements Comprobable{
    private final String xpath;

    public FilterCondition(String xpath) {
        this.xpath = xpath;
    }
    
    @Override
    public final boolean checkCondition(Mensaje mensaje){
        return testValue(mensaje.evaluateXPath(xpath).item(0).getTextContent());
    }
    
    protected abstract boolean testValue(String text);
}
