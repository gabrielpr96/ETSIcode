package com.b0ve.solucionintegraciongenerica.ports;

import com.b0ve.solucionintegraciongenerica.flow.Buffer;
import com.b0ve.solucionintegraciongenerica.flow.Message;
import com.b0ve.solucionintegraciongenerica.adapters.Adapter;
import com.b0ve.solucionintegraciongenerica.tasks.Task;
import com.b0ve.solucionintegraciongenerica.utils.ProcessSync;
import com.b0ve.solucionintegraciongenerica.utils.exceptions.SIGException;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class Port extends Task {

    protected final Adapter adapter;

    public Port(int entradas, int salidas, Adapter adaptador) {
        super(entradas, salidas);
        this.adapter = adaptador;
        adaptador.setPuerto(this);
    }

    @Override
    public void process() throws SIGException {
        if (hasInputs()) {
            Buffer entrada = input(0);
            while (!entrada.empty()) {
                Message mensaje = entrada.retrive();
                if (process != null && process instanceof ProcessSync) {
                    sendAdapter(mensaje);
                } else {
                    (new Thread() {
                        @Override
                        public void run() {
                            try {
                                sendAdapter(mensaje);
                            } catch (SIGException ex) {
                                handleException(ex);
                            }
                        }
                    }).start();
                }
            }
        }
    }

    protected abstract void sendAdapter(Message m) throws SIGException;

    public Adapter getAdapter() {
        return adapter;
    }
}
