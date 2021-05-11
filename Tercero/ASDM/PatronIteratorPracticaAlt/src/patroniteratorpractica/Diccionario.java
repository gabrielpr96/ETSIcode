package patroniteratorpractica;

import java.util.AbstractMap;
import java.util.AbstractMap.SimpleEntry;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Diccionario implements IIterable {

    private final Map<Object, Object> datos;

    public Diccionario() {
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
    public ListaIterador getIterador() {
        String[] listado = new String[datos.size()];
        int i = 0;
        for (Entry<Object, Object> entry : datos.entrySet()) {
            listado[i++] = entry.getKey() + " -> " + entry.getValue();
        }
        return new ListaIterador(listado);
    }

    @Override
    public String toString() {
        return "Diccionario";
    }
}
