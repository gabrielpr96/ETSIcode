package com.b0ve.solucionintegraciongenerica.puertos;

import com.b0ve.solucionintegraciongenerica.utils.Avisable;
import com.b0ve.solucionintegraciongenerica.utils.Buffer;
import com.b0ve.solucionintegraciongenerica.utils.Mensaje;
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
    protected void procesar() {
        Buffer entrada = entradas.get(0);
        while (!entrada.empty()) {
            adaptador.enviarApp(entrada.retrive());
        }
    }
    
    public void enviar(Mensaje m){
        Buffer salida = salidas.get(0);
        salida.push(m);
    }
}
