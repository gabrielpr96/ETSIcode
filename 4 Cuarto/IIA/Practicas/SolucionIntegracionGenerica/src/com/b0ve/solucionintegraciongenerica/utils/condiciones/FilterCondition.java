package com.b0ve.solucionintegraciongenerica.utils.condiciones;

import com.b0ve.solucionintegraciongenerica.utils.flujo.Mensaje;
import com.b0ve.solucionintegraciongenerica.utils.condiciones.Comprobable;

public class FilterCondition implements Comprobable{
    private final String xpath, value;

    public FilterCondition(String xpath, String value) {
        this.xpath = xpath;
        this.value = value;
    }
    
    @Override
    public boolean checkCondition(Mensaje mensaje){
        return mensaje.evaluateXPath(xpath).item(0).getTextContent().equals(value);
    }
}
