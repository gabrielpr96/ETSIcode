package com.b0ve.solucionintegraciongenerica.tasks.transformers;

import com.b0ve.solucionintegraciongenerica.tasks.Task;
import com.b0ve.solucionintegraciongenerica.utils.exceptions.ExecutionException;
import com.b0ve.solucionintegraciongenerica.flow.Buffer;
import com.b0ve.solucionintegraciongenerica.flow.FragmentInfo;
import com.b0ve.solucionintegraciongenerica.flow.Message;
import com.b0ve.solucionintegraciongenerica.utils.exceptions.SIGException;
import org.w3c.dom.Document;

public abstract class SplitterTemplate extends Task {
    public SplitterTemplate() {
        super(1, 1);
    }

    @Override
    public final void process() throws SIGException {
        Buffer output = output(0);
        Buffer input = input(0);
        while (!input.empty()) {
            Message mensaje = input.retrive();
            Document[] parts = split(mensaje);
            long fragmentID = FragmentInfo.uniqueID();
            for (int i = 0; i < parts.length; i++) {
                Message part = new Message(parts[i]);
                part.addFragmentInfo(mensaje.getFragmentInfoStack());
                part.addFragmentInfo(new FragmentInfo(fragmentID, parts.length));
                output.push(part);
            }
        }
    }

    protected abstract Document[] split(Message m) throws SIGException ;

}
