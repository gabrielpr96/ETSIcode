package com.b0ve.patronobserverpractica.difusores;

import com.b0ve.patronobserverpractica.IMedioDifusion;
import com.b0ve.patronobserverpractica.Noticia;
import com.b0ve.patronobserverpractica.Logger;
import com.b0ve.patronobserverpractica.interfaz.PantallaNoticia;

public class InterfazGrafica implements IMedioDifusion {

    @Override
    public void actualizar(Noticia noticia) {
        PantallaNoticia pantallaNoticia = new PantallaNoticia(noticia);
        pantallaNoticia.setLocationRelativeTo(null);
        pantallaNoticia.setVisible(true);

        Logger logger = Logger.getLogger();
        logger.log("Noticia publicada por pantalla en interfaz gráfica");
    }

    @Override
    public String toString() {
        return "Interfaz gráfica";
    }

}
