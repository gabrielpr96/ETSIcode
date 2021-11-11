package com.b0ve.solucionintegraciongenerica.adaptadores;

import com.b0ve.solucionintegraciongenerica.utils.flujo.Mensaje;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.xml.transform.TransformerException;

public class AdaptadorPantalla extends Adaptador{

    @Override
    public void enviarApp(Mensaje m) {
        JOptionPane.showMessageDialog(null, m.getBody(), m.getBodyString(), JOptionPane.INFORMATION_MESSAGE);
    }
    
}
