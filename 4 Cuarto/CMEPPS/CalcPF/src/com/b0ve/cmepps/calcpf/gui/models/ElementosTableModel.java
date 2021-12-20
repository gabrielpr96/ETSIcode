package com.b0ve.cmepps.calcpf.gui.models;

import com.b0ve.cmepps.calcpf.enums.TipoElemento;
import com.b0ve.cmepps.calcpf.modelo.elementos.ElementoFuncional;
import java.util.ArrayList;
import java.util.List;
import javafx.util.Pair;
import javax.swing.table.AbstractTableModel;

public class ElementosTableModel extends AbstractTableModel {

    private final List<ElementoFuncional> elementos;

    public ElementosTableModel(List<ElementoFuncional> elementos) {
        this.elementos = elementos;
    }
    
    public void add(ElementoFuncional elemento){
        int first = getRowCount();
        elementos.add(elemento);
        int last = getRowCount()-1;
        fireTableRowsInserted(first, last);
    }
    
    public void remove(int i){
        Pair<ElementoFuncional, Pair<Integer, Integer>> pos = findByRow(i);
        elementos.remove(pos.getKey());
        fireTableRowsDeleted(pos.getValue().getValue(), pos.getValue().getValue());
    }

    @Override
    public int getRowCount() {
        return elementos.stream().mapToInt(e -> e.getTipo() == TipoElemento.CE ? 3 : 1).sum();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Pair<ElementoFuncional, Pair<Integer, Integer>> pos = findByRow(rowIndex);
        ElementoFuncional elemento = pos.getKey();
        int diff = pos.getValue().getKey();
        int i = pos.getValue().getValue();
        if (elemento == null) {
            return "";
        }
        if (elemento.getTipo() == TipoElemento.CE) {
            switch (diff) {
                case 0:
                    switch (columnIndex) {
                        case 0:
                            return i + 1;
                        case 1:
                            return elemento.getNombre();
                        case 2:
                            return elemento.getTipo().getNombreCorto();
                        case 3:
                            return "";
                        case 4:
                            return "";
                    }
                case 1:
                    switch (columnIndex) {
                        case 0:
                            return "";
                        case 1:
                            return "Lado entrada";
                        case 2:
                            return "";
                        case 3:
                            return elemento.getListaReferencias("Entrada");
                        case 4:
                            return elemento.getListaDatosElementales("Entrada");
                    }
                case 2:
                    switch (columnIndex) {
                        case 0:
                            return "";
                        case 1:
                            return "Lado salida";
                        case 2:
                            return "";
                        case 3:
                            return elemento.getListaReferencias("Salida");
                        case 4:
                            return elemento.getListaDatosElementales("Salida");
                    }
            }
        } else {
            switch (columnIndex) {
                case 0:
                    return i + 1;
                case 1:
                    return elemento.getNombre();
                case 2:
                    return elemento.getTipo().getNombreCorto();
                case 3:
                    return elemento.getListaReferencias(null);
                case 4:
                    return elemento.getListaDatosElementales(null);
            }
        }
        return "ERROR";
    }

    @Override
    public String getColumnName(int column) {
        switch(column){
            case 0: return "No.";
            case 1: return "Nombre";
            case 2: return "Tipo";
            case 3: return "Referencias";
            case 4: return "Datos elementales";
        }
        return "ERROR";
    }
    
    private Pair<ElementoFuncional, Pair<Integer, Integer>> findByRow(int rowIndex){
        ElementoFuncional elemento = null;
        int i = 0, pos = 0, diff = 0;
        while (i < elementos.size() && elemento == null) {
            ElementoFuncional tmp = elementos.get(i);
            if (tmp.getTipo() == TipoElemento.CE) {
                diff = rowIndex - pos;
                if (diff <= 2) {
                    elemento = tmp;
                }
                pos += 3;
            } else {
                if (pos == rowIndex) {
                    elemento = tmp;
                }
                pos++;
            }
            i++;
        }
        return new Pair(elemento, new Pair(diff, i));
    }

}
