package com.b0ve.solucionintegraciongenerica.tasks.routers;

import com.b0ve.solucionintegraciongenerica.flow.Message;
import com.b0ve.solucionintegraciongenerica.utils.condiciones.Checkeable;

public class Filter extends FilterTemplate {

    private final Checkeable condicion;

    public Filter(Checkeable condicion) {
        super();
        this.condicion = condicion;
    }

    @Override
    protected boolean comprobar(Message mensaje) {
        return condicion.checkCondition(mensaje);
    }

}
