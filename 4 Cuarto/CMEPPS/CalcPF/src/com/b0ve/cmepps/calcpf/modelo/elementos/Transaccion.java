package com.b0ve.cmepps.calcpf.modelo.elementos;

import com.b0ve.cmepps.calcpf.enums.TipoElemento;

public abstract class Transaccion extends ElementoFuncional{

    public Transaccion(String nombre, TipoElemento tipo) {
        super(nombre, tipo);
    }
    
    @Override
    public String getNombreReferencias() {
        return "Ficheros referenciados";
    }

    @Override
    public String getNombreElementales() {
        return "Tipos de datos elementales";
    }
}
