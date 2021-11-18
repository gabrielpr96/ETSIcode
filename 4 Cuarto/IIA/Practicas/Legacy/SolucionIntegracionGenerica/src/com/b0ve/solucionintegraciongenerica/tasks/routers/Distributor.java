package com.b0ve.solucionintegraciongenerica.tasks.routers;

import com.b0ve.solucionintegraciongenerica.flow.Message;
import com.b0ve.solucionintegraciongenerica.utils.condiciones.Checkeable;
import com.b0ve.solucionintegraciongenerica.utils.exceptions.XPathEvaluationException;

/**
 * Routes messages based on an array of Checkeable conditions.
 * @author borja
 */
public class Distributor extends DistributorTemplate {

    private final Checkeable[] conditions;

    public Distributor(Checkeable[] conditions) {
        super();
        this.conditions = conditions;
    }

    @Override
    protected int check(Message m) throws XPathEvaluationException {
        int outPin = -1, i = 0;
        while (outPin == -1 && i < conditions.length) {
            if (conditions[i].checkCondition(m)) {
                outPin = i;
            } else {
                i++;
            }
        }
        if (outPin == -1) {
            outPin = nOutputs() - 1;
        }
        return outPin;
    }

}
