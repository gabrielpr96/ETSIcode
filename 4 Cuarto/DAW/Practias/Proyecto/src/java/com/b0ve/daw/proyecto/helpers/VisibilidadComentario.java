/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.b0ve.daw.proyecto.helpers;

/**
 *
 * @author borja
 */
public enum VisibilidadComentario {
    PUBLICO("Publico"), VENDEDOR("Vendedor"), PERSONAL("Personal");
    
    private final String nombre;
    
    VisibilidadComentario(String nombre){
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }
}
