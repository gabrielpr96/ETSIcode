package com.b0ve.solucionintegraciongenerica.tasks.routers;

import com.b0ve.solucionintegraciongenerica.tasks.Task;
import com.b0ve.solucionintegraciongenerica.flow.Buffer;
import com.b0ve.solucionintegraciongenerica.utils.exceptions.SIGException;

public class Merger extends Task {

    public Merger() {
        super(0, 1);
    }

    @Override
    public void process() throws SIGException {
        Buffer output = output(0);
        for (int i = 0; i < nInputs(); i++) {
            Buffer input = input(i);
            while (!input.empty()) {
                output.push(input.retrive());
            }
        }
    }

}
