package com.b0ve.solucionintegraciongenerica.tasks.transformers;

import com.b0ve.solucionintegraciongenerica.tasks.Task;
import com.b0ve.solucionintegraciongenerica.utils.exceptions.ExecutionException;
import com.b0ve.solucionintegraciongenerica.flow.Buffer;
import com.b0ve.solucionintegraciongenerica.flow.FragmentInfo;
import com.b0ve.solucionintegraciongenerica.flow.Message;
import org.w3c.dom.Document;

public abstract class SplitterTemplate extends Task {
    public SplitterTemplate() {
        super(1, 1);
    }

    @Override
    public final void procesar() {
        Buffer salida = salidas.get(0);
        Buffer entrada = entradas.get(0);
        while (!entrada.empty()) {
            Message mensaje = entrada.retrive();
            //if(mensaje.getSequenceSize() != 0) throw new ExecutionException("No se puede fragmentar un fragmento de mensaje");
            Document[] parts = split(mensaje);
            long fragmentID = FragmentInfo.uniqueID();
            for (int i = 0; i < parts.length; i++) {
                Message parte = new Message(parts[i]);
                parte.addFragmentInfo(mensaje.getFragmentInfoStack());
                parte.addFragmentInfo(new FragmentInfo(fragmentID, parts.length));
                salida.push(parte);
            }
        }
    }

    protected abstract Document[] split(Message mensaje);

}
