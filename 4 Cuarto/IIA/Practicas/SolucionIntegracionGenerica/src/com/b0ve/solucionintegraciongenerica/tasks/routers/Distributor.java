package com.b0ve.solucionintegraciongenerica.tasks.routers;

import com.b0ve.solucionintegraciongenerica.flow.Message;
import com.b0ve.solucionintegraciongenerica.utils.condiciones.Checkeable;

public class Distributor extends DistributorTemplate {

    private final Checkeable[] condiciones;

    public Distributor(Checkeable[] condiciones) {
        super();
        this.condiciones = condiciones;
    }

    @Override
    protected int comprobar(Message mensaje) {
        int salida = -1, i = 0;
        while (salida == -1 && i < condiciones.length) {
            if (condiciones[i].checkCondition(mensaje)) {
                salida = i;
            }else{
                i++;
            }
        }
        if (salida == -1) {
            salida = salidas.size()-1;
        }
        return salida;
    }

}
