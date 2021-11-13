package com.b0ve.solucionintegraciongenerica.tasks.modifiers;

import com.b0ve.solucionintegraciongenerica.tasks.Task;
import com.b0ve.solucionintegraciongenerica.flow.Buffer;
import com.b0ve.solucionintegraciongenerica.flow.Message;

public class CorrelationIDSetter extends Task {

    private int contador;
    
    public CorrelationIDSetter(){
        super(1, 1);
        contador = 0;
    }

    @Override
    public void procesar() {
        Buffer salida = salidas.get(0);
        Buffer entrada = entradas.get(0);
        while (!entrada.empty()) {
            Message mensaje = entrada.retrive();
            mensaje.setCorrelationID(contador++);
            salida.push(mensaje);
        }
    }

}
