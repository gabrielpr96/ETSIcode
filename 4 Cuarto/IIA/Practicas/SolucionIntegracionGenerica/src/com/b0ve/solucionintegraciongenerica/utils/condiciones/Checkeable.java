package com.b0ve.solucionintegraciongenerica.utils.condiciones;

import com.b0ve.solucionintegraciongenerica.flow.Message;

public interface Checkeable {
    boolean checkCondition(Message mensaje);
}
