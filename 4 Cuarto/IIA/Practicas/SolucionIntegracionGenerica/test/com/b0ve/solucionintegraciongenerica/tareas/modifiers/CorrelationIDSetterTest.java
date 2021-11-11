/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.b0ve.solucionintegraciongenerica.tareas.modifiers;

import com.b0ve.solucionintegraciongenerica.utils.flujo.Buffer;
import com.b0ve.solucionintegraciongenerica.utils.flujo.Mensaje;
import static com.b0ve.solucionintegraciongenerica.utils.flujo.Mensaje.newMensaje;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author borja
 */
public class CorrelationIDSetterTest {

    @Test
    public void testCorrelationIDSetter1() {
        Mensaje m1 = newMensaje(0, 0, "<m>1</m>"),
                m2 = newMensaje(1, 0, "<m>2</m>"),
                m3 = newMensaje(2, 0, "<m>3</m>");
        CorrelationIDSetter cidSetter = new CorrelationIDSetter();
        Buffer in = new Buffer(null);
        cidSetter.addEntrada(in);
        Buffer out = new Buffer(null);
        cidSetter.addSalida(out);

        in.push(m1);
        in.push(m2);
        in.push(m3);

        cidSetter.procesar();

        assertEquals(out.retrive().getCorrelationID(), 0);
        assertEquals(out.retrive().getCorrelationID(), 1);
        assertEquals(out.retrive().getCorrelationID(), 2);
    }

}
