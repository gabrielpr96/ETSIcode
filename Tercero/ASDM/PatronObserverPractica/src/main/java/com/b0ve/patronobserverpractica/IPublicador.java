package com.b0ve.patronobserverpractica;

public interface IPublicador {
    public void agregarDifusor(IMedioDifusion observador);
    public void eliminarDifusor(IMedioDifusion observador);
    public void publicar(Noticia noticia);
}
