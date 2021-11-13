package com.b0ve.solucionintegraciongenerica.tasks;

import com.b0ve.solucionintegraciongenerica.flow.Buffer;
import com.b0ve.solucionintegraciongenerica.flow.Message;

public class TaskDebug extends Task {

    private final boolean continuar;
    
    public TaskDebug() {
        this(false);
    }
    
    public TaskDebug(boolean continuar) {
        super(1, 1);
        this.continuar = continuar;
    }

    @Override
    public final void procesar() {
        Buffer entrada = entradas.get(0);
        while (!entrada.empty()) {
            Message mensaje = entrada.retrive();
            debugLog(mensaje.getBodyString());
            if(continuar){
                Buffer salida = salidas.get(0);
                salida.push(mensaje);
            }
        }
    }

}
