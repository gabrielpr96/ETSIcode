package com.b0ve.solucionintegraciongenerica.utils;

import com.b0ve.solucionintegraciongenerica.puertos.Puerto;
import com.b0ve.solucionintegraciongenerica.tareas.Tarea;

public class ProcesoSync extends Proceso{

    private Thread ejecucion;

    @Override
    public void ejecutar() {
        for (Tarea tarea : tareas) {
            if (tarea instanceof Puerto) {
                ((Puerto) tarea).getAdaptador().iniciar();
            }
        }
        ejecucion = new Thread(){
            @Override
            public void run() {
                while (!isInterrupted()) {                    
                    for (Tarea tarea : tareas) {
                        tarea.procesar();
                    }
                }
            }
            
        };
        ejecucion.start();
    }
    
    @Override
    public void terminar() {
        ejecucion.interrupt();
        for (Tarea tarea : tareas) {
            if (tarea instanceof Puerto) {
                ((Puerto) tarea).getAdaptador().detener();
            }
        }
    }

    @Override
    public void esperar() throws InterruptedException {
        ejecucion.join();
    }
    
}
