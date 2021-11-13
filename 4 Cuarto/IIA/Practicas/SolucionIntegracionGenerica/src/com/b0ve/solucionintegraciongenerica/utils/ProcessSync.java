package com.b0ve.solucionintegraciongenerica.utils;

import com.b0ve.solucionintegraciongenerica.ports.Port;
import com.b0ve.solucionintegraciongenerica.tasks.Task;

public class ProcessSync extends Process{

    private Thread ejecucion;

    @Override
    public void execute() {
        for (Task tarea : tasks) {
            if (tarea instanceof Port) {
                ((Port) tarea).getAdapter().iniciate();
            }
        }
        ejecucion = new Thread(){
            @Override
            public void run() {
                while (!isInterrupted()) {                    
                    for (Task tarea : tasks) {
                        tarea.procesar();
                    }
                }
            }
            
        };
        ejecucion.start();
    }
    
    @Override
    public void shutdown() {
        ejecucion.interrupt();
        for (Task tarea : tasks) {
            if (tarea instanceof Port) {
                ((Port) tarea).getAdapter().halt();
            }
        }
    }

    @Override
    public void waitToEnd() throws InterruptedException {
        ejecucion.join();
    }
    
}
