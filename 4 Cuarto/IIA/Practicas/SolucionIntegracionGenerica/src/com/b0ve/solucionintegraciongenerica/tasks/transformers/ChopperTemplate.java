package com.b0ve.solucionintegraciongenerica.tasks.transformers;

import com.b0ve.solucionintegraciongenerica.tasks.Task;
import com.b0ve.solucionintegraciongenerica.utils.exceptions.ExecutionException;
import com.b0ve.solucionintegraciongenerica.flow.Buffer;
import com.b0ve.solucionintegraciongenerica.flow.FragmentInfo;
import com.b0ve.solucionintegraciongenerica.flow.Message;
import com.b0ve.solucionintegraciongenerica.utils.exceptions.SIGException;
import org.w3c.dom.Document;

public abstract class ChopperTemplate extends Task {
    public ChopperTemplate() {
        super(1, 0);
    }

    @Override
    public final void process() throws SIGException {
        Buffer input = input(0);
        while (!input.empty()) {
            Message mensaje = input.retrive();
            //if(mensaje.getSequenceSize() != 0) throw new ExecutionException("No se puede fragmentar un fragmento de mensaje");
            Document[] parts = chop(mensaje);
            long fragmentID = FragmentInfo.uniqueID();
            for (int i = 0; i < parts.length; i++) {
                Message parte = new Message(parts[i]);
                parte.addFragmentInfo(mensaje.getFragmentInfoStack());
                parte.addFragmentInfo(new FragmentInfo(fragmentID, parts.length));
                output(i).push(parte);
            }
        }
    }

    protected abstract Document[] chop(Message mensaje) throws SIGException;

}
