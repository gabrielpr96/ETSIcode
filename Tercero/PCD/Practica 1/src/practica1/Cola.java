package practica1;

public class Cola implements ICola {

    private int head, tail, numelementos;
    private final int capacidad;
    private final Object[] datos;

    public Cola(int capacidad) {
        this.capacidad = capacidad;
        this.datos = new Object[capacidad];
        this.numelementos = this.head = this.tail = 0;

    }

    @Override
    public int getNum() {
        return numelementos;
    }

    @Override
    public void Acola(Object elemento) throws Exception {
        if (colallena()) {
            throw new Exception("No hay espacio en la cola para insertar el elemento");
        }
        datos[tail] = elemento;
        tail = (tail + 1) % capacidad;
        numelementos++;
    }

    @Override
    public Object Desacola() throws Exception {
        if (colavacia()) {
            throw new Exception("No hay elementos en la cola");
        }
        Object elemento = datos[head];
        datos[head] = null;
        head = (head + 1) % capacidad;
        numelementos--;
        return elemento;
    }

    @Override
    public Object Primero() throws Exception {
        if (colavacia()) {
            throw new Exception("No hay elementos en la cola");
        }
        return datos[head];
    }

    public boolean colavacia() {
        return numelementos == 0;
    }

    public boolean colallena() {
        return numelementos == capacidad;
    }
    
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < numelementos; i++) {
            sb.append(datos[(head+i)%capacidad]).append(" ");
        }
        return sb.toString();
    }

}
