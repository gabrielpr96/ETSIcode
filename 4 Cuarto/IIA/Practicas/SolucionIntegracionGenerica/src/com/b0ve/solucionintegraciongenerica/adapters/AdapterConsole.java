package com.b0ve.solucionintegraciongenerica.adapters;

import com.b0ve.solucionintegraciongenerica.utils.Process.PORTS;
import com.b0ve.solucionintegraciongenerica.utils.exceptions.SIGException;
import java.util.Scanner;

public class AdapterConsole extends Adapter {

    private final Thread hilo;

    public AdapterConsole() {
        hilo = new Thread() {
            @Override
            public void run() {
                Scanner es = new Scanner(System.in);
                String texto = null;
                do {
                    texto = es.nextLine();
                    try {
                        sendPort(texto);
                    } catch (SIGException ex) {
                        handleException(ex);
                    }
                } while (!texto.equals("exit") && !isInterrupted());
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
}
