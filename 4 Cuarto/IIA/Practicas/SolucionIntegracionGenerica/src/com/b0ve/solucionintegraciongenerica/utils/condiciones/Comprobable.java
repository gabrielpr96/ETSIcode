package com.b0ve.solucionintegraciongenerica.utils.condiciones;

import com.b0ve.solucionintegraciongenerica.utils.flujo.Mensaje;

public interface Comprobable {
    boolean checkCondition(Mensaje mensaje);
}
