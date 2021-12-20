package com.b0ve.cmepps.calcpf.gui.models;

import com.b0ve.cmepps.calcpf.modelo.influencias.TablaInfluencias;
import java.awt.Component;
import javax.swing.JSlider;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;

public class CaracteristicasModel extends AbstractTableModel {

    String headers[] = {"n", "Atributos", "Influencia"};

    private final TablaInfluencias influencias;

    public CaracteristicasModel(TablaInfluencias influencias) {
        this.influencias = influencias;
    }

    @Override
    public int getRowCount() {
        return TablaInfluencias.getConsideraciones().length+1;
    }

    @Override
    public int getColumnCount() {
        return 3;
    }


    @Override
    public String getColumnName(int c) {
        return headers[c];
    }

    @Override
    public boolean isCellEditable(int r, int c) {
        return true;
    }

    @Override
    public Object getValueAt(int r, int c) {
        switch(c){
            case 0: return r<14?r+1:"Total";
            case 1: return r<14?TablaInfluencias.getConsideraciones()[r]:"";
            case 2: return r<14?influencias.getValor(r):influencias.getSVA();
        }
        return "ERROR";
    }

    @Override
    public void setValueAt(Object value, int r, int c) {
        if(c == 2){
            influencias.setValor(r, Integer.parseInt((String) value));
            fireTableRowsUpdated(14, 14);
        }
    }

}