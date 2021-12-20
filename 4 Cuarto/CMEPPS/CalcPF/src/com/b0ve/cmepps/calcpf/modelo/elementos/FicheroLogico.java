package com.b0ve.cmepps.calcpf.modelo.elementos;

import com.b0ve.cmepps.calcpf.enums.TipoElemento;

public abstract class FicheroLogico extends ElementoFuncional{

    public FicheroLogico(String nombre, TipoElemento tipo) {
        super(nombre, tipo);
    }
    
    @Override
    public String getNombreReferencias() {
        return "Tipos de registros";
    }

    @Override
    public String getNombreElementales() {
        return "Tipos de datos elementales";
    }
}
