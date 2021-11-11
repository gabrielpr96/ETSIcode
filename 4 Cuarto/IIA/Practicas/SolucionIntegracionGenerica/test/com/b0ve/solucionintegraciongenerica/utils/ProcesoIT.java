/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.b0ve.solucionintegraciongenerica.utils;

import com.b0ve.solucionintegraciongenerica.adaptadores.Adaptador;
import com.b0ve.solucionintegraciongenerica.puertos.Puerto;
import com.b0ve.solucionintegraciongenerica.tareas.Tarea;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author borja
 */
public class ProcesoIT {

    @Test
    public void testPrueba1() {
        System.out.println("debugLog");
        String log = "";
        Proceso instance = new Proceso();
        instance.debugLog(log);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
