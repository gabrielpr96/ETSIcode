package com.b0ve.solucionintegraciongenerica.adaptadores;

import com.b0ve.solucionintegraciongenerica.utils.Mensaje;
import com.b0ve.solucionintegraciongenerica.puertos.Puerto;

public abstract class Adaptador {
    private Puerto puerto;

    public Adaptador() {
    }

    public void setPuerto(Puerto puerto) {
        this.puerto = puerto;
    }
    
    public abstract void enviarApp(Mensaje m);
    
    protected void enviarPuerto(Mensaje m){
        puerto.enviar(m);
    }
    
    public abstract void detener();
}
