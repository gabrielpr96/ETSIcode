package patroniteratorpractica;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class Pila implements IIterable {

    private final Stack<Object> datos;

    public Pila() {
        datos = new Stack<>();
    }

    public void agregar(Object val) {
        datos.push(val);
    }

    public Object obtener() {
        return datos.peek();
    }

    public Object sacar() {
        return datos.pop();
    }

    @Override
    public ListaIterador getIterador() {
        List<Object> elementos = Arrays.asList(datos.toArray(new Object[0]));
        Collections.reverse(elementos);
        return new ListaIterador(elementos.toArray(new Object[0]));
    }

    @Override
    public String toString() {
        return "Pila";
    }
}
