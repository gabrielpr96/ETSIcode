package com.b0ve.patronobserverpractica;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JTextArea;

public class Logger {
    private static final Logger logger = new Logger();
    private static final SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    private JTextArea area;

    public void log(String texto){
        if(area != null)
            area.setText(area.getText()+formatoFecha.format(new Date())+" -> "+texto+"\n");
    }
    
    public void setArea(JTextArea area){
        this.area = area;
    }

    public static Logger getLogger() {
        return logger;
    }
}
