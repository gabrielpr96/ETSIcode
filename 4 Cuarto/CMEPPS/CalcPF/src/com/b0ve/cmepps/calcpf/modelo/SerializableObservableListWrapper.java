package com.b0ve.cmepps.calcpf.modelo;

import java.io.Serializable;
import java.util.ArrayList;

public class SerializableObservableListWrapper<T> extends ArrayList<T> implements Serializable{
    
    private transient Actualizable observer;
    
    public void setObserver(Actualizable obs){
        observer = obs;
    }

    @Override
    public boolean remove(Object o) {
        boolean res = super.remove(o);
        if(observer != null) observer.actualizar();
        return res;
    }

    @Override
    public boolean add(T e) {
        boolean res = super.add(e);
        if(observer != null) observer.actualizar();
        return res;
    }
    
    
    
}
