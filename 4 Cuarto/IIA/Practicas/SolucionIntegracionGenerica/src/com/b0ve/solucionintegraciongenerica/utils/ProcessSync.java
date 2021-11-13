package com.b0ve.solucionintegraciongenerica.utils;

import com.b0ve.solucionintegraciongenerica.ports.Port;
import com.b0ve.solucionintegraciongenerica.tasks.Task;
import com.b0ve.solucionintegraciongenerica.utils.exceptions.SIGException;
import java.util.logging.Level;
import java.util.logging.Logger;

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
                        try {
                            tarea.process();
                        } catch (SIGException ex) {
                            handleException(ex);
                        }
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
