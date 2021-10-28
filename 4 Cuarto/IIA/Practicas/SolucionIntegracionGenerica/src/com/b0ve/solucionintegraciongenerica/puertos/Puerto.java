package com.b0ve.solucionintegraciongenerica.puertos;

import com.b0ve.solucionintegraciongenerica.tareas.Avisable;
import com.b0ve.solucionintegraciongenerica.utils.flujo.Buffer;
import com.b0ve.solucionintegraciongenerica.utils.flujo.Mensaje;
import com.b0ve.solucionintegraciongenerica.adaptadores.Adaptador;
import com.b0ve.solucionintegraciongenerica.tareas.Tarea;

public class Puerto extends Tarea {

    private final Adaptador adaptador;

    public Puerto(Adaptador adaptador) {
        super(1, 1);
        this.adaptador = adaptador;
        adaptador.setPuerto(this);
    }

    @Override
    public void procesar() {
        Buffer entrada = entradas.get(0);
        while (!entrada.empty()) {
            Mensaje mensaje = entrada.retrive();
            //TODO:Crear un hilo por cada peticion realizada
            (new Thread() {
                @Override
                public void run() {
                    adaptador.enviarApp(mensaje);
                }

            }).start();
        }
    }

    public void enviar(Mensaje m) {
        Buffer salida = salidas.get(0);
        salida.push(m);
    }
}
