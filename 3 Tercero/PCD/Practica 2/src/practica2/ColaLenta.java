package practica2;

public class ColaLenta implements ICola {

    private int head, tail, numelementos;
    private final int capacidad;
    private final Object[] datos;

    /**
     * Crea una estrucura de tipo de tipo cola
     * @param capacidad Número de elementos máximos que puede contener la cola
     */
    public ColaLenta(int capacidad) {
        this.capacidad = capacidad;
        this.datos = new Object[capacidad];
        this.numelementos = this.head = this.tail = 0;

    }

    @Override
    public int getNum() {
        return numelementos;
    }

    @Override
    public /*synchronized*/ void Acola(Object elemento) throws Exception {
        if (colallena()) {
            throw new Exception("No hay espacio en la cola para insertar el elemento");
        }
        datos[tail] = elemento;
        Thread.sleep(100);
        tail = (tail + 1) % capacidad;
        Thread.sleep(100);
        numelementos++;
    }

    @Override
    public /*synchronized*/ Object Desacola() throws Exception {
        if (colavacia()) {
            throw new Exception("No hay elementos en la cola");
        }
        Object elemento = datos[head];
        //datos[head] = null;
        Thread.sleep(100);
        head = (head + 1) % capacidad;
        Thread.sleep(100);
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
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < numelementos; i++) {
            sb.append(datos[(head+i)%capacidad]).append(" ");
        }
        return sb.toString();
    }

}
