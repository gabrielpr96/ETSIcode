/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.b0ve.solucionintegraciongenerica.tareas.routers;

import com.b0ve.solucionintegraciongenerica.utils.flujo.Buffer;
import com.b0ve.solucionintegraciongenerica.utils.flujo.Mensaje;
import static com.b0ve.solucionintegraciongenerica.utils.flujo.Mensaje.newMensaje;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author borja
 */
public class CorrelatorTest {

    @Test
    public void testCorrelator1() throws Exception {
        Mensaje m11 = newMensaje(0, 0, "<m>11</m>"),
                m12 = newMensaje(1, 1, "<m>12</m>"),
                m13 = newMensaje(2, 2, "<m>13</m>"),
                m21 = newMensaje(3, 2, "<m>21</m>"),
                m22 = newMensaje(4, 1, "<m>22</m>"),
                m23 = newMensaje(5, 0, "<m>23</m>");
        Correlator correlator = new Correlator();
        Buffer in1 = new Buffer(null);
        Buffer in2 = new Buffer(null);
        correlator.addEntrada(in1);
        correlator.addEntrada(in2);
        Buffer out1 = new Buffer(null);
        Buffer out2 = new Buffer(null);
        correlator.addSalida(out1);
        correlator.addSalida(out2);

        in1.push(m11);
        in1.push(m12);
        in1.push(m13);
        in2.push(m21);
        in2.push(m22);
        in2.push(m23);

        correlator.procesar();

        assertEquals(out1.retrive().getCorrelationID(), out2.retrive().getCorrelationID());
        assertEquals(out1.retrive().getCorrelationID(), out2.retrive().getCorrelationID());
        assertEquals(out1.retrive().getCorrelationID(), out2.retrive().getCorrelationID());
    }

    @Test
    public void testCorrelator2() throws Exception {
        Correlator correlator = new Correlator("/cid");

        Mensaje m11 = newMensaje(0, 0, "<cid>0</cid>"),
                m12 = newMensaje(1, 1, "<cid>1</cid>"),
                m13 = newMensaje(2, 2, "<cid>2</cid>"),
                m21 = newMensaje(3, 3, "<cid>2</cid>"),
                m22 = newMensaje(4, 4, "<cid>1</cid>"),
                m23 = newMensaje(5, 5, "<cid>0</cid>");

        Buffer in1 = new Buffer(null);
        Buffer in2 = new Buffer(null);
        correlator.addEntrada(in1);
        correlator.addEntrada(in2);
        Buffer out1 = new Buffer(null);
        Buffer out2 = new Buffer(null);
        correlator.addSalida(out1);
        correlator.addSalida(out2);

        in1.push(m11);
        in1.push(m12);
        in1.push(m13);
        in2.push(m21);
        in2.push(m22);
        in2.push(m23);

        correlator.procesar();

        assertEquals(out1.retrive().evaluateXPathString("/cid"), out2.retrive().evaluateXPathString("/cid"));
        assertEquals(out1.retrive().evaluateXPathString("/cid"), out2.retrive().evaluateXPathString("/cid"));
        assertEquals(out1.retrive().evaluateXPathString("/cid"), out2.retrive().evaluateXPathString("/cid"));
    }

}
