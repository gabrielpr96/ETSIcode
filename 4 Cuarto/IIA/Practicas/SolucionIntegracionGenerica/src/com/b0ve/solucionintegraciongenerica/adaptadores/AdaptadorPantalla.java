package com.b0ve.solucionintegraciongenerica.adaptadores;

import com.b0ve.solucionintegraciongenerica.utils.flujo.Mensaje;
import javax.swing.JOptionPane;

public class AdaptadorPantalla extends Adaptador{

    @Override
    public void enviarApp(Mensaje m) {
        JOptionPane.showMessageDialog(null, m.getBody(), m.getBody(), JOptionPane.INFORMATION_MESSAGE);
    }
    
}
