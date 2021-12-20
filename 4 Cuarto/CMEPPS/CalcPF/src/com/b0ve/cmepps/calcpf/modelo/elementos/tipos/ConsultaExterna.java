package com.b0ve.cmepps.calcpf.modelo.elementos.tipos;

import com.b0ve.cmepps.calcpf.modelo.elementos.Transaccion;
import com.b0ve.cmepps.calcpf.enums.Complejidad;
import com.b0ve.cmepps.calcpf.enums.TipoElemento;

public class ConsultaExterna extends Transaccion{

    public ConsultaExterna(String nombre) {
        super(nombre, TipoElemento.CE);
    }
    
    @Override
    public Complejidad getComplegidad() {
        Complejidad complegidadEntrada = consultarTabla(1, 2, 4, 15, "Entrada");
        Complejidad complegidadSalida = consultarTabla(1, 2, 4, 15, "Salida");
        
        return complegidadEntrada.getValor() > complegidadSalida.getValor() ? complegidadEntrada : complegidadSalida;
    }
    
}
