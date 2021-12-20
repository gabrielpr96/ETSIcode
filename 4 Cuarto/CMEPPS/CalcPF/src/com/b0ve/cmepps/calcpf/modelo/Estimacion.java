package com.b0ve.cmepps.calcpf.modelo;

import com.b0ve.cmepps.calcpf.enums.Complejidad;
import static com.b0ve.cmepps.calcpf.enums.Complejidad.*;
import com.b0ve.cmepps.calcpf.enums.TipoElemento;
import static com.b0ve.cmepps.calcpf.enums.TipoElemento.*;
import com.b0ve.cmepps.calcpf.modelo.elementos.ElementoFuncional;
import com.b0ve.cmepps.calcpf.modelo.influencias.TablaInfluencias;
import com.sun.javafx.collections.ObservableListWrapper;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javafx.collections.ListChangeListener;

public class Estimacion implements Serializable{

    private static final Map<TipoElemento, Map<Complejidad, Integer>> ponderacion = Stream.of(new Object[][]{
        {EE, Stream.of(new Object[][]{{SIMPLE, 3}, {MEDIA, 4}, {COMPLEJA, 6}}).collect(Collectors.toMap(data -> (Complejidad) data[0], data -> (Integer) data[1]))},
        {SE, Stream.of(new Object[][]{{SIMPLE, 4}, {MEDIA, 5}, {COMPLEJA, 7}}).collect(Collectors.toMap(data -> (Complejidad) data[0], data -> (Integer) data[1]))},
        {CE, Stream.of(new Object[][]{{SIMPLE, 3}, {MEDIA, 4}, {COMPLEJA, 6}}).collect(Collectors.toMap(data -> (Complejidad) data[0], data -> (Integer) data[1]))},
        {FLI, Stream.of(new Object[][]{{SIMPLE, 7}, {MEDIA, 10}, {COMPLEJA, 15}}).collect(Collectors.toMap(data -> (Complejidad) data[0], data -> (Integer) data[1]))},
        {FLE, Stream.of(new Object[][]{{SIMPLE, 5}, {MEDIA, 7}, {COMPLEJA, 10}}).collect(Collectors.toMap(data -> (Complejidad) data[0], data -> (Integer) data[1]))}
    }).collect(Collectors.toMap(data -> (TipoElemento) data[0], data -> (Map<Complejidad, Integer>) data[1]));

    private String nombre;
    private final SerializableObservableListWrapper<ElementoFuncional> elementosFuncionales;
    private final TablaInfluencias tablaInfluencias;

    public Estimacion() {
        nombre = "Sin titulo";
        elementosFuncionales = new SerializableObservableListWrapper<>();
        tablaInfluencias = new TablaInfluencias();
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public List<ElementoFuncional> getElementosFuncionales() {
        return elementosFuncionales;
    }

    public TablaInfluencias getTablaInfluencias() {
        return tablaInfluencias;
    }

    public int getPFNA() {
        return elementosFuncionales.stream().mapToInt(e -> ponderacion.get(e.getTipo()).get(e.getComplegidad())).sum();
    }
    
    public double getFA(){
        return tablaInfluencias.getFA();
    }

    public double getPFA() {
        return getPFNA() * tablaInfluencias.getFA();
    }

    public static Map<TipoElemento, Map<Complejidad, Integer>> getPonderacion() {
        return ponderacion;
    }
    
    public void setObserver(Actualizable obs){
        elementosFuncionales.setObserver(obs);
        tablaInfluencias.setObserver(obs);
    }

    @Override
    public String toString() {
        return "<html><h2>"+nombre+"</h2><h3>"+getPFNA()+" PFNA</h3><h3>"+getFA()+" FA</h3><h3>"+getPFA()+" PFA</h3></html>";
    }
    
    

}
