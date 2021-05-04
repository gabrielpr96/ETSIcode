package com.b0ve.patronobserverpractica.difusores;

import com.b0ve.patronobserverpractica.IMedioDifusion;
import com.b0ve.patronobserverpractica.Noticia;
import com.b0ve.patronobserverpractica.Logger;

public class Consola implements IMedioDifusion {

    @Override
    public void actualizar(Noticia noticia) {
        System.out.println("\nNueva noticia.");
        System.out.println("Titulo: " + noticia.getTitulo());
        System.out.println("Cuerpo: " + noticia.getCuerpo());

        Logger logger = Logger.getLogger();
        logger.log("Noticia publicada por consola");
    }

    @Override
    public String toString() {
        return "Consola";
    }

}
