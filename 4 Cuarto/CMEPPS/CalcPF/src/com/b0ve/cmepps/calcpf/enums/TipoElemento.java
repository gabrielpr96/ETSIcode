package com.b0ve.cmepps.calcpf.enums;

import com.b0ve.cmepps.calcpf.modelo.elementos.ElementoFuncional;
import com.b0ve.cmepps.calcpf.modelo.elementos.tipos.EntradaExterna;

public enum TipoElemento {
    EE("EE", "Entrada externa", EntradaExterna.class),
    SE("SE", "Salida externa", EntradaExterna.class),
    CE("CE", "Consulta externa", EntradaExterna.class),
    FLI("FLI", "Fichero lógico interno", EntradaExterna.class),
    FLE("FLE", "Fichero lógico externo", EntradaExterna.class);

    private final String nombreCorto, nombreCompleto;
    private final Class<ElementoFuncional> clase;

    TipoElemento(String nombreCorto, String nombreCompleto, Class clase) {
        this.nombreCorto = nombreCorto;
        this.nombreCompleto = nombreCompleto;
        this.clase = clase;
    }

    public String getNombreCorto() {
        return nombreCorto;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public Class<ElementoFuncional> getClase() {
        return clase;
    }
}
