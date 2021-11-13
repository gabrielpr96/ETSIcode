package com.b0ve.solucionintegraciongenerica.adapters;

import com.b0ve.solucionintegraciongenerica.flow.Message;
import com.b0ve.solucionintegraciongenerica.utils.Process.PORTS;
import javax.swing.JOptionPane;
import org.w3c.dom.Document;

public class AdapterScreen extends Adapter {

    @Override
    public Document sendApp(Message m) {
        JOptionPane.showMessageDialog(null, m.getBody(), m.getBodyString(), JOptionPane.INFORMATION_MESSAGE);
        return null;
    }

    @Override
    public PORTS getCompatiblePortType() {
        return PORTS.OUTPUT;
    }

}
