package com.b0ve.cmepps.calcpf.modelo.elementos.tipos;

import com.b0ve.cmepps.calcpf.modelo.elementos.FicheroLogico;
import com.b0ve.cmepps.calcpf.enums.Complejidad;
import com.b0ve.cmepps.calcpf.enums.TipoElemento;

public class FicheroLogicoExterno extends FicheroLogico{

    public FicheroLogicoExterno(String nombre) {
        super(nombre, TipoElemento.FLE);
    }

    @Override
    public Complejidad getComplegidad() {
        return consultarTabla(1, 5, 19, 50, null);
    }
    
}
