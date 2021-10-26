package cafe;

import com.b0ve.solucionintegraciongenerica.adaptadores.AdaptadorPantalla;
import com.b0ve.solucionintegraciongenerica.adaptadores.AdaptadorTeclado;
import com.b0ve.solucionintegraciongenerica.puertos.Puerto;
import com.b0ve.solucionintegraciongenerica.tareas.Tarea;
import com.b0ve.solucionintegraciongenerica.utils.Proceso;

public class Cafe {

    public static void main(String[] args) throws Exception {
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
        
        p.validar();
        p.ejecutar();
        //p.terminar();
        p.esperar();
        //teclado.detener();
        System.out.println("Terminado");
    }
    
}
