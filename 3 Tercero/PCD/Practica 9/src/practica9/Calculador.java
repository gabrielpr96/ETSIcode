package practica9;

import java.util.concurrent.Callable;
import static practica9.PCDPractica9.hashString;

/**
 * Revisa un rango definido de valores y busca coincidencias con el hash.
 * Si lo encuentra, devuelve el número. En caso contrario devuelve null.
 * @author borja
 */
public class Calculador implements Callable<Integer>{

    private final String hash;
    private final int inicio, fin, id;
    private final CanvasArea canvas;
    
    /**
     * 
     * @param hash Hash a romper
     * @param inicio Valor inicial (incluido) para empezar a buscar
     * @param fin Valor final (incluido) para terminar de buscar
     * @param id ID del Calculador, usado para pintar en su hueco del Canvas
     * @param canvas Canvas sobre el que pintar el progreso
     */
    public Calculador(String hash, int inicio, int fin, int id, CanvasArea canvas){
        this.hash = hash;
        this.inicio = inicio;
        this.fin = fin;
        this.id = id;
        this.canvas = canvas;
    }
    
    @Override
    public Integer call() throws Exception {
        int i = inicio;
        int resultado = -1;
        while(i <= fin && resultado == -1 && !Thread.interrupted()){
            canvas.actualizarNumero(id, i);
            if(hashString(Integer.toString(i)).equals(hash))
                resultado = i;
            else
                i++;
        }
        return resultado;
    }
    
}
