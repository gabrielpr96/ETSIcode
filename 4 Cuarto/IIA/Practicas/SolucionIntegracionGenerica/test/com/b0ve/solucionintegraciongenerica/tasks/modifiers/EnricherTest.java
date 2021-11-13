/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.b0ve.solucionintegraciongenerica.tasks.modifiers;

import com.b0ve.solucionintegraciongenerica.flow.Buffer;
import com.b0ve.solucionintegraciongenerica.flow.Message;
import static com.b0ve.solucionintegraciongenerica.flow.Message.newMessage;
import com.b0ve.solucionintegraciongenerica.utils.exceptions.SIGException;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author borja
 */
public class EnricherTest {
    
    public EnricherTest() {
    }

    @Test
    public void testEnricher1() throws SIGException {
        Message m = newMessage(0, 0, "<pelicula><titulo>Crimen Ferpecto</titulo></pelicula>");
        Enricher enricher = new Enricher("<pelicula><precio>10.5</precio></pelicula>");
        Buffer in = new Buffer(null);
        enricher.addInput(in);
        Buffer out = new Buffer(null);
        enricher.addOutput(out);

        in.push(m);

        enricher.process();

        Message response = out.retrive();
        assertNotNull(response);
        assertTrue(response.evaluateXPathString("/pelicula/titulo").contains("Crimen Ferpecto"));
        assertTrue(response.evaluateXPathString("/pelicula/precio").contains("10.5"));
    }
    
}
