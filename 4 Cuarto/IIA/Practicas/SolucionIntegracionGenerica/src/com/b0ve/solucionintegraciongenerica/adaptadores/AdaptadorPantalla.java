package com.b0ve.solucionintegraciongenerica.adaptadores;

import com.b0ve.solucionintegraciongenerica.utils.flujo.Mensaje;
import com.b0ve.solucionintegraciongenerica.puertos.Puerto;
import javax.swing.JOptionPane;

public class AdaptadorPantalla extends Adaptador{

    @Override
    public void enviarApp(Mensaje m) {
        JOptionPane.showMessageDialog(null, m.getBody(), m.getBody(), JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public void detener() {
        //Nada que detener
    }
    
}
