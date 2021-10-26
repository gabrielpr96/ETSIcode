package com.b0ve.solucionintegraciongenerica.tareas.routers;

import com.b0ve.solucionintegraciongenerica.utils.condiciones.Comprobable;
import com.b0ve.solucionintegraciongenerica.utils.flujo.Mensaje;

public class Filter extends FilterTemplate {

    private final Comprobable condicion;

    public Filter(Comprobable condicion) {
        super();
        this.condicion = condicion;
    }

    @Override
    protected boolean comprobar(Mensaje mensaje) {
        return condicion.checkCondition(mensaje);
    }

}
