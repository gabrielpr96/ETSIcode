package com.b0ve.solucionintegraciongenerica.tareas.routers;

import com.b0ve.solucionintegraciongenerica.tareas.Tarea;
import com.b0ve.solucionintegraciongenerica.utils.flujo.Buffer;
import com.b0ve.solucionintegraciongenerica.utils.condiciones.FilterCondition;
import com.b0ve.solucionintegraciongenerica.utils.flujo.Mensaje;

public abstract class DistributorTemplate extends Tarea {
    
    public DistributorTemplate() {
        super(1, 0);
    }

    @Override
    public final void procesar() {
        Buffer entrada = entradas.get(0);
        while(!entrada.empty()){
            Mensaje mensaje = entrada.retrive();
            salidas.get(comprobar(mensaje)).push(mensaje);
        }
    }
    
    protected abstract int comprobar(Mensaje mensaje);

}
