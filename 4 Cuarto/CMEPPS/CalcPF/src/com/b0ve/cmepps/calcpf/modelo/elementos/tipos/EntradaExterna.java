package com.b0ve.cmepps.calcpf.modelo.elementos.tipos;

import com.b0ve.cmepps.calcpf.modelo.elementos.Transaccion;
import com.b0ve.cmepps.calcpf.enums.Complejidad;
import com.b0ve.cmepps.calcpf.enums.TipoElemento;

public class EntradaExterna extends Transaccion{

    public EntradaExterna(String nombre) {
        super(nombre, TipoElemento.EE);
    }
    
    @Override
    public Complejidad getComplegidad() {
        return consultarTabla(1, 2, 4, 15, null);
    }
    
}
