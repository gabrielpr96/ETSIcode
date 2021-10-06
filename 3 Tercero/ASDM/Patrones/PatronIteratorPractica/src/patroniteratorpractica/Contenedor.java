package patroniteratorpractica;

import java.util.ArrayList;
import java.util.List;

public class Contenedor implements IIterable {

    private final List<Object> datos;

    public Contenedor() {
        datos = new ArrayList<>();
    }

    public void agregar(int pos, Object val) {
        datos.add(pos, val);
    }
    
    public void agregar(Object val) {
        datos.add(val);
    }

    public void eliminar(int pos) {
        datos.remove(pos);
    }

    public Object obtener(int pos) {
        return datos.get(pos);
    }

    @Override
    public String toString() {
        return "Contenedor";
    }

    @Override
    public Object[] getDatos() {
        return datos.toArray(new Object[0]);
    }

}
