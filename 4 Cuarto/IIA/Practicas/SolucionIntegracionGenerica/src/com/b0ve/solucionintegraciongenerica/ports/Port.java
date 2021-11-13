package com.b0ve.solucionintegraciongenerica.ports;

import com.b0ve.solucionintegraciongenerica.flow.Buffer;
import com.b0ve.solucionintegraciongenerica.flow.Message;
import com.b0ve.solucionintegraciongenerica.adapters.Adapter;
import com.b0ve.solucionintegraciongenerica.tasks.Task;
import com.b0ve.solucionintegraciongenerica.utils.ProcessSync;

public abstract class Port extends Task {

    protected final Adapter adapter;

    public Port(int entradas, int salidas, Adapter adaptador) {
        super(entradas, salidas);
        this.adapter = adaptador;
        adaptador.setPuerto(this);
    }

    @Override
    public void procesar() {
        if (entradas.size() > 0) {
            Buffer entrada = entradas.get(0);
            while (!entrada.empty()) {
                Message mensaje = entrada.retrive();
                if (proceso != null && proceso instanceof ProcessSync) {
                    sendAdapter(mensaje);
                } else {
                    (new Thread() {
                        @Override
                        public void run() {
                            sendAdapter(mensaje);
                        }
                    }).start();
                }
            }
        }
    }

    protected abstract void sendAdapter(Message m);

    public Adapter getAdapter() {
        return adapter;
    }
}
