package patroniteratorpractica;

import java.util.AbstractMap;
import java.util.AbstractMap.SimpleEntry;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class ContenedorAsociativo implements IIterable {

    private final Map<Object, Object> datos;

    public ContenedorAsociativo() {
        datos = new HashMap<>();
    }

    public void agregar(Object clave, Object val) {
        datos.put(clave, val);
    }

    public void eliminar(Object clave) {
        datos.remove(clave);
    }

    public Object obtener(Object clave) {
        return datos.get(clave);
    }

    @Override
    public String toString() {
        return "Contenedor asociativo";
    }

    @Override
    public Object[] getDatos() {
        String[] listado = new String[datos.size()];
        int i = 0;
        for (Entry<Object, Object> entry : datos.entrySet()) {
            listado[i++] = entry.getKey() + " -> " + entry.getValue();
        }
        return listado;
    }
}
