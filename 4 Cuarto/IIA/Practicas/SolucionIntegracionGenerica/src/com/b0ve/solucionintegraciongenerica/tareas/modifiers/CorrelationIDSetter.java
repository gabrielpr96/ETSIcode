package com.b0ve.solucionintegraciongenerica.tareas.modifiers;

import com.b0ve.solucionintegraciongenerica.tareas.Tarea;
import com.b0ve.solucionintegraciongenerica.utils.flujo.Buffer;
import com.b0ve.solucionintegraciongenerica.utils.flujo.Mensaje;

public class CorrelationIDSetter extends Tarea {

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
            Mensaje mensaje = entrada.retrive();
            mensaje.setCorrelationID(contador++);
            salida.push(mensaje);
        }
    }

}
