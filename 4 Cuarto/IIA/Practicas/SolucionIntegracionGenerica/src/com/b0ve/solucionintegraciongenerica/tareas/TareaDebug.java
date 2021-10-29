package com.b0ve.solucionintegraciongenerica.tareas;

import com.b0ve.solucionintegraciongenerica.utils.flujo.Buffer;
import com.b0ve.solucionintegraciongenerica.utils.flujo.Mensaje;

public class TareaDebug extends Tarea {

    private final boolean continuar;
    
    public TareaDebug() {
        this(false);
    }
    
    public TareaDebug(boolean continuar) {
        super(1, 1);
        this.continuar = continuar;
    }

    @Override
    public final void procesar() {
        Buffer entrada = entradas.get(0);
        while (!entrada.empty()) {
            Mensaje mensaje = entrada.retrive();
            System.out.println("DEBUGG: "+mensaje.getBody());
            if(continuar){
                Buffer salida = salidas.get(0);
                salida.push(mensaje);
            }
        }
    }

}
