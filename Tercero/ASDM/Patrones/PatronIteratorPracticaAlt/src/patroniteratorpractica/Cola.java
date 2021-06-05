package patroniteratorpractica;

import java.util.ArrayDeque;
import java.util.Queue;

public class Cola implements IIterable {

    private final Queue<Object> datos;

    public Cola() {
        datos = new ArrayDeque<>();
    }

    public void agregar(Object val) {
        datos.add(val);
    }

    public Object obtener() {
        return datos.peek();
    }

    public Object sacar() {
        return datos.poll();
    }

    @Override
    public ListaIterador getIterador() {
        return new ListaIterador(datos.toArray(new Object[0]));
    }

    @Override
    public String toString() {
        return "Cola";
    }
}
