package com.b0ve.patronobserverpractica;

import java.util.ArrayList;

public class PublicadorFootball implements IPublicador{

    private final ArrayList<IMedioDifusion> observadores;
    
    public PublicadorFootball(){
        observadores = new ArrayList<>();
    }

    @Override
    public void agregarDifusor(IMedioDifusion observador) {
        observadores.add(observador);
    }

    @Override
    public void eliminarDifusor(IMedioDifusion observador) {
        observadores.remove(observador);
    }

    @Override
    public void publicar(Noticia noticia) {
        observadores.stream().forEach(observador -> observador.actualizar(noticia));
    }
    
}
