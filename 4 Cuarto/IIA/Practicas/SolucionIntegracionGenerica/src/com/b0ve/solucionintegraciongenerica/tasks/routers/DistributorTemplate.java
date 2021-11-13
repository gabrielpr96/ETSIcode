package com.b0ve.solucionintegraciongenerica.tasks.routers;

import com.b0ve.solucionintegraciongenerica.tasks.Task;
import com.b0ve.solucionintegraciongenerica.flow.Buffer;
import com.b0ve.solucionintegraciongenerica.flow.Message;

public abstract class DistributorTemplate extends Task {
    
    public DistributorTemplate() {
        super(1, 0);
    }

    @Override
    public final void procesar() {
        Buffer entrada = entradas.get(0);
        while(!entrada.empty()){
            Message mensaje = entrada.retrive();
            salidas.get(comprobar(mensaje)).push(mensaje);
        }
    }
    
    protected abstract int comprobar(Message mensaje);

}
