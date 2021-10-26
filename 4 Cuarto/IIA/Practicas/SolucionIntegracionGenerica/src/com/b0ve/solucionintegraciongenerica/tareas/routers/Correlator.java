package com.b0ve.solucionintegraciongenerica.tareas.routers;

import com.b0ve.solucionintegraciongenerica.utils.excepciones.ConfigurationException;
import com.b0ve.solucionintegraciongenerica.utils.flujo.Mensaje;

public final class Correlator extends CorrelatorTemplate {

    private final String expresion;

    public Correlator(String expresion) {
        super();
        this.expresion = expresion;
    }

    public Correlator() {
        this(null);
    }

    @Override
    protected boolean comparar(Mensaje m1, Mensaje m2) {
        if (expresion == null) {
            return super.comparar(m1, m2);
        } else {
            return m1.evaluateXPath(expresion).item(0).getTextContent().equals(m2.evaluateXPath(expresion).item(0).getTextContent());
        }
    }

    @Override
    public void validar() throws ConfigurationException {
        super.validar();
        if(this.entradas.size() != this.salidas.size()) throw new ConfigurationException("Error Correlator requiere mismo numero de entradas que de salidas");
    }
    
    

}
