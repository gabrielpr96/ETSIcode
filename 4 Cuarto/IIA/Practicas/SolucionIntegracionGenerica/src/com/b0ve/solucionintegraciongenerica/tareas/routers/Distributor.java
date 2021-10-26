package com.b0ve.solucionintegraciongenerica.tareas.routers;

import com.b0ve.solucionintegraciongenerica.utils.condiciones.Comprobable;
import com.b0ve.solucionintegraciongenerica.utils.flujo.Mensaje;

public class Distributor extends DistributorTemplate {

    private final Comprobable[] condiciones;

    public Distributor(Comprobable[] condiciones) {
        super();
        this.condiciones = condiciones;
    }

    @Override
    protected int comprobar(Mensaje mensaje) {
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
