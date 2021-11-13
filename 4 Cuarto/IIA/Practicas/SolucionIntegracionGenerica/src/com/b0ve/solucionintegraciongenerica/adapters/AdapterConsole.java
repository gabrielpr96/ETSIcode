package com.b0ve.solucionintegraciongenerica.adapters;

import com.b0ve.solucionintegraciongenerica.utils.Process.PORTS;
import com.b0ve.solucionintegraciongenerica.flow.Message;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class AdapterConsole extends Adapter{
    private final Thread hilo;
    
    public AdapterConsole() {
        hilo = new Thread(){
            @Override
            public void run() {
                Scanner es = new Scanner(System.in);
                String texto = null;
                do{
                    texto = es.nextLine();
                    try {
                        sendPort(texto);
                    } catch (ParserConfigurationException | SAXException | IOException ex) {
                        Logger.getLogger(AdapterConsole.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }while(!texto.equals("salir") && !isInterrupted());
            }
        };
    }
    
    @Override
    public void iniciate() {
        hilo.start();
    }

    @Override
    public void halt() {
        hilo.interrupt();
    }

    @Override
    public PORTS getCompatiblePortType() {
        return PORTS.INPUT;
    }

    @Override
    public Document sendApp(Message m) {
        throw new UnsupportedOperationException("This adapter does not support sending messages to the app.");
    }
}
