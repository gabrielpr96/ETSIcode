package com.b0ve.autosig.gui.models;

import com.b0ve.solucionintegraciongenerica.utils.exceptions.SIGException;
import java.util.ArrayList;
import javax.swing.AbstractListModel;

public class ExceptionListModel extends AbstractListModel{
    ArrayList<SIGException> items;
    
    public ExceptionListModel(){
        items = new ArrayList<>();
    }
    
    public void add(SIGException ex){
        items.add(ex);
        fireContentsChanged(this, items.size()-1, items.size()-1);
    }
    
    public SIGException get(int index){
        return items.get(index);
    }
    
    @Override
    public int getSize() {
        return items.size();
    }

    @Override
    public Object getElementAt(int index) {
        StringBuilder sb = new StringBuilder("<html><span style='color:red;'>");
        sb.append(items.get(index).getMessage());
        sb.append("</span></html>");
        return sb.toString();
    }
    
}
