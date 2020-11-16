package practica2;

public class ColaLenta implements ICola {

    private int head, tail, numelementos;
    private final int capacidad;
    private final Object[] datos;
    private final CanvasCola canvas;

    /**
     * Crea una estrucura de tipo de tipo cola
     *
     * @param capacidad Número de elementos máximos que puede contener la cola
     */
    public ColaLenta(int capacidad, CanvasCola canvas) {
        this.capacidad = capacidad;
        this.datos = new Object[capacidad];
        this.numelementos = this.head = this.tail = 0;
        this.canvas = canvas;

    }

    @Override
    public int getNum() {
        return numelementos;
    }

    @Override
    public synchronized void Acola(Object elemento) throws Exception {
        int intento = 0;
        while (colallena()) {
            if (intento >= 3) {
                canvas.avisa("COLA LLENA");
                throw new Exception("No hay espacio en la cola para insertar el elemento");
            }
            System.out.println("Hilo "+Thread.currentThread().getId()+" espera para insertar");
            intento++;
            wait();
        }
        datos[tail] = elemento;
        Thread.sleep(100);
        tail = (tail + 1) % capacidad;
        Thread.sleep(100);
        numelementos++;
        canvas.representa(datos, head, tail, numelementos);
        notifyAll();
    }

    @Override
    public synchronized Object Desacola() throws Exception {
        int intento = 0;
        while (colavacia()) {
            if (intento >= 3) {
                canvas.avisa("COLA VACIA");
                throw new Exception("No hay elementos en la cola");
            }
            System.out.println("Hilo "+Thread.currentThread().getId()+" espera para extraer");
            intento++;
            wait();
        }
        Object elemento = datos[head];
        datos[head] = null;
        Thread.sleep(100);
        head = (head + 1) % capacidad;
        Thread.sleep(100);
        numelementos--;
        canvas.representa(datos, head, tail, numelementos);
        notifyAll();
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
     *
     * @return True si la cola está vacía, False en caso contrario
     */
    private boolean colavacia() {
        return numelementos == 0;
    }

    /**
     * Comprueba si la cola está llena
     *
     * @return True si al cola está vacía, False en caso contrario
     */
    private boolean colallena() {
        return numelementos == capacidad;
    }

    /**
     * La representación en cadena de la cola consiste en una lista de elementos
     * separados por espacios, comenzando por el primer elemento insertado
     *
     * @return Un String conteniendo la representación serializada de la cola
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < numelementos; i++) {
            sb.append(datos[(head + i) % capacidad]).append(" ");
        }
        return sb.toString();
    }

}
