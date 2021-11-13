/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.b0ve.solucionintegraciongenerica.utils.flujo;

import com.b0ve.solucionintegraciongenerica.flow.FragmentInfo;
import com.b0ve.solucionintegraciongenerica.flow.Message;
import com.b0ve.solucionintegraciongenerica.utils.exceptions.ParseException;
import java.io.IOException;
import java.util.Iterator;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import org.junit.Test;
import static org.junit.Assert.*;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author borja
 */
public class MensajeTest {

    @Test
    public void testFragmentInfo() throws ParseException {
        //Se cumple el orden de inserción y extracción
        Message m1 = new Message("<n>1</n>");
        m1.addFragmentInfo(new FragmentInfo(1, 0));
        m1.addFragmentInfo(new FragmentInfo(2, 0));
        assertEquals(m1.removeFragmentInfo().getFragmentID(), 2);
        assertEquals(m1.getFragmentInfo().getFragmentID(), 1);
        m1.removeFragmentInfo();
        assertNull(m1.getFragmentInfo());

        //Agregar la pila fragmentos de un mensaje a otro
        m1.addFragmentInfo(new FragmentInfo(1, 0));
        m1.addFragmentInfo(new FragmentInfo(2, 0));
        Message m2 = new Message("<n>2</n>");
        m2.addFragmentInfo(m1.getFragmentInfoStack());
        m2.addFragmentInfo(new FragmentInfo(3, 0));
        assertEquals(m2.removeFragmentInfo().getFragmentID(), 3);
        assertEquals(m2.removeFragmentInfo().getFragmentID(), 2);
        assertEquals(m2.removeFragmentInfo().getFragmentID(), 1);
        assertNull(m2.getFragmentInfo());
    }

}
