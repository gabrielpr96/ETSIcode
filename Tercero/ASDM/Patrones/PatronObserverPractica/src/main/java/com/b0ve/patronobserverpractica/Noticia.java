package com.b0ve.patronobserverpractica;

public final class Noticia {
    private final String titulo, cuerpo;

    public Noticia(String titulo, String cuerpo) {
        this.titulo = titulo;
        this.cuerpo = cuerpo;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getCuerpo() {
        return cuerpo;
    }
    
}
