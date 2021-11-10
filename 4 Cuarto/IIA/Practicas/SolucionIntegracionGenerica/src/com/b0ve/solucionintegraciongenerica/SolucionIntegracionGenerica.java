package com.b0ve.solucionintegraciongenerica;

import com.b0ve.solucionintegraciongenerica.utils.excepciones.ConfigurationException;
import com.b0ve.solucionintegraciongenerica.adaptadores.*;
import com.b0ve.solucionintegraciongenerica.puertos.Puerto;
import com.b0ve.solucionintegraciongenerica.tareas.Tarea;
import com.b0ve.solucionintegraciongenerica.utils.flujo.Mensaje;
import com.b0ve.solucionintegraciongenerica.utils.Proceso;
import static com.b0ve.solucionintegraciongenerica.utils.Proceso.TipoTarea.*;
import com.b0ve.solucionintegraciongenerica.utils.condiciones.FilterConditionEquals;
import com.b0ve.solucionintegraciongenerica.utils.flujo.Buffer;
import java.util.ArrayList;
import java.util.Iterator;
import org.w3c.dom.NodeList;

public class SolucionIntegracionGenerica {

    /*
        TODO List:
            ContextSlimmer.slim
    */
    
    public static void main(String[] args) throws Exception {
        /*
        Proceso pross = new Proceso();
        AdaptadorFicheroWhatcher adapterFichero = new AdaptadorFicheroWhatcher("C:\\Users\\borja\\Downloads\\watch", "C:\\Users\\borja\\Downloads\\salida");
        Puerto pFile = pross.crearPuerto(adapterFichero);
        AdaptadorTeclado adapterConsola = new AdaptadorTeclado();
        Puerto pSalida = pross.crearPuerto(adapterConsola);
        pross.encadenar(pFile, pSalida);
        pross.encadenar(pSalida, pFile);
        
        pross.ejecutar();
        pross.esperar();
        
        Proceso pross = new Proceso();
        AdaptadorMySQL adapterMysql = new AdaptadorMySQL("localhost", 3306, "cafe", "root", "");
        Puerto pMysql = pross.crearPuerto(adapterMysql);
        AdaptadorTeclado adapterConsola = new AdaptadorTeclado();
        Puerto pSalida = pross.crearPuerto(adapterConsola);
        pross.encadenar(pMysql, pSalida);
        pross.encadenar(pSalida, pMysql);
        
        pross.ejecutar();
        pross.esperar();
        
        System.exit(0);
        
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
        //p.terminar();
        p.esperar();
        //teclado.detener();
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
