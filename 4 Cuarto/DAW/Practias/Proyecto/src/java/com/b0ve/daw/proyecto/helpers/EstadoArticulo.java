package com.b0ve.daw.proyecto.helpers;

public enum EstadoArticulo {
    NUEVO("Nuevo"), SEMINUEVO("Seminuevo"), DETERIORADO("Deteriorado"), ANTIGUO("Antiguo");
    
    private final String nombre;
    
    EstadoArticulo(String nombre){
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }
}
