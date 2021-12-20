package com.b0ve.cmepps.calcpf.enums;

public enum Complejidad {
    SIMPLE(1), MEDIA(2), COMPLEJA(3);
    
    private final int valor;

    private Complejidad(int valor) {
        this.valor = valor;
    }

    public int getValor() {
        return valor;
    }
    
}
