package com.b0ve.solucionintegraciongenerica;

import com.b0ve.solucionintegraciongenerica.utils.Constructor;
import com.b0ve.solucionintegraciongenerica.utils.ConfigurationException;
import com.b0ve.solucionintegraciongenerica.adaptadores.AdaptadorPantalla;
import com.b0ve.solucionintegraciongenerica.adaptadores.AdaptadorTeclado;
import com.b0ve.solucionintegraciongenerica.puertos.Puerto;
import com.b0ve.solucionintegraciongenerica.tareas.Replicator;
import com.b0ve.solucionintegraciongenerica.tareas.Tarea;
import com.b0ve.solucionintegraciongenerica.utils.Proceso;
import java.util.ArrayList;

public class SolucionIntegracionGenerica {

    public static void main(String[] args) throws ConfigurationException, InterruptedException {
        //Sintaxis V2
        Proceso p = new Proceso();
        AdaptadorTeclado teclado = new AdaptadorTeclado();
        Puerto pConsola = p.crearPuerto(teclado);
        Tarea replicator = p.crearTarea(Proceso.TipoTarea.REPLICATOR);
        for (int i = 0; i < 3; i++) {
            AdaptadorPantalla pantalla = new AdaptadorPantalla();
            Puerto pPantalla = p.crearPuerto(pantalla);
            p.encadenar(replicator, pPantalla);
        }
        p.encadenar(pConsola, replicator);
        p.encadenar(replicator, pConsola);
        
        p.ejecutar();
        p.terminar();
        p.esperar();
        teclado.detener();
        System.out.println("Terminado");
        
        
        /* Sintaxis V1
        ArrayList<Tarea> tareas = new ArrayList<>();
        AdaptadorTeclado teclado = new AdaptadorTeclado();
        Puerto pEntradaTeclado = new Puerto(teclado);
        tareas.add(pEntradaTeclado);
        Replicator replicator = new Replicator();
        tareas.add(replicator);
        for (int i = 0; i < 3; i++) {
            AdaptadorPantalla pantalla = new AdaptadorPantalla();
            Puerto pSalidaPantalla = new Puerto(pantalla);
            tareas.add(pSalidaPantalla);
            Constructor.encadenar(replicator, pSalidaPantalla);
        }
        Constructor.encadenar(replicator, pEntradaTeclado);
        Constructor.encadenar(pEntradaTeclado, replicator);
        Constructor.iniciarHilos(tareas);
        */
    }
    
}
