package com.b0ve.solucionintegraciongenerica.utils;

import com.b0ve.solucionintegraciongenerica.utils.flujo.Buffer;
import com.b0ve.solucionintegraciongenerica.utils.excepciones.ConfigurationException;
import com.b0ve.solucionintegraciongenerica.tareas.Tarea;
import java.util.ArrayList;
import java.util.List;

public class dep_Constructor {
    private final List<Tarea> tareas;

    public dep_Constructor() {
        this.tareas = new ArrayList<>();
    }
    
    public static void encadenar(Tarea t1, Tarea t2) throws ConfigurationException{
        Buffer b = new Buffer(t2);
        t1.addSalida(b);
        t2.addEntrada(b);
    }
    
    public static Thread[] iniciarHilos(List<Tarea> tareas){
        Thread[] hilos = new Thread[tareas.size()];
        for (int i = 0; i < tareas.size(); i++) {
            hilos[i] = new Thread(tareas.get(i));
            hilos[i].start();
        }
        return hilos;
    }
}
