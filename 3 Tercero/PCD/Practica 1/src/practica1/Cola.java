package practica1;

public class Cola implements ICola {

    private int head, tail, numelementos;
    private final int capacidad;
    private final Object[] datos;

    /**
     * Crea una estrucura de tipo de tipo cola
     * @param capacidad Número de elementos máximos que puede contener la cola
     */
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

    /**
     * Comprueba si la cola está vacía
     * @return True si la cola está vacía, False en caso contrario
     */
    public boolean colavacia() {
        return numelementos == 0;
    }

    /**
     * Comprueba si la cola está llena
     * @return True si al cola está vacía, False en caso contrario
     */
    public boolean colallena() {
        return numelementos == capacidad;
    }
    
    /**
     * La representación en cadena de la cola consiste en una lista de elementos separados por espacios, comenzando por el primer elemento insertado
     * @return Un String conteniendo la representación serializada de la cola
     */
    @Override
    public String toString(){
        if(numelementos == 0)
            return "";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < numelementos; i++) {
            sb.append(datos[(head+i)%capacidad]).append(" ");
        }
        sb.setLength(sb.length()-1);
        return sb.toString();
    }

}
