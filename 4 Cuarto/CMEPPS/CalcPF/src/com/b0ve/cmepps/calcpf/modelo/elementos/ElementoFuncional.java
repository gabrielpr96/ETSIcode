package com.b0ve.cmepps.calcpf.modelo.elementos;

import com.b0ve.cmepps.calcpf.enums.Complejidad;
import static com.b0ve.cmepps.calcpf.enums.Complejidad.COMPLEJA;
import static com.b0ve.cmepps.calcpf.enums.Complejidad.MEDIA;
import static com.b0ve.cmepps.calcpf.enums.Complejidad.SIMPLE;
import com.b0ve.cmepps.calcpf.enums.TipoElemento;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author borja
 */
public abstract class ElementoFuncional implements Serializable{
    protected static final Complejidad[][] tabla = {{SIMPLE, SIMPLE, MEDIA}, {SIMPLE, MEDIA, COMPLEJA}, {MEDIA, COMPLEJA, COMPLEJA}};
    
    private final String nombre;
    protected final TipoElemento tipo;
    protected final Map<String, List<String>> referencias;
    protected final Map<String, List<String>> datosElementales;
    
    public ElementoFuncional(String nombre, TipoElemento tipo){
        this.nombre = nombre;
        this.tipo = tipo;
        
        referencias = new HashMap<>();
        datosElementales = new HashMap<>();
    }

    public String getNombre() {
        return nombre;
    }
    
    public void addReferencia(String fila, String nombre){
        addToListIntMap(referencias, fila, nombre);
    }
    
    public void addDatoElemental(String fila, String nombre){
        addToListIntMap(datosElementales, fila, nombre);
    }
    
    private static void addToListIntMap(Map<String, List<String>> mapa, String clave, String nombre){
       List<String> lista = mapa.get(clave);
       if(lista == null){
           lista = new ArrayList<>();
           mapa.put(clave, lista);
       }
       lista.add(nombre);
    }

    public TipoElemento getTipo() {
        return tipo;
    }

    public Map<String, List<String>> getReferencias() {
        return referencias;
    }

    public Map<String, List<String>> getDatosElementales() {
        return datosElementales;
    }
    
    public String getListaReferencias(String clave){
        return String.join(", ", referencias.get(clave));
    }
    
    public String getListaDatosElementales(String clave){
        return String.join(", ", datosElementales.get(clave));
    }
    
    protected Complejidad consultarTabla(int limiteFila1, int limiteFila2, int limiteColumna1, int limiteColumna2, String clave){
        int fila, columna;
        
        int fr = referencias.get(clave).size();
        if(fr <= limiteFila1)
            fila = 0;
        else if(fr <= limiteFila2)
            fila = 1;
        else
            fila = 2;
        
        int de = datosElementales.get(clave).size();
        if(de <= limiteColumna1)
            columna = 0;
        else if(de <= limiteColumna2)
            columna = 1;
        else
            columna = 2;
        
        return tabla[fila][columna];
    } 
    
    public abstract String getNombreReferencias();
    public abstract String getNombreElementales();
    
    public abstract Complejidad getComplegidad();
}
