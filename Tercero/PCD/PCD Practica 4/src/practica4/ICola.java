package practica4;

/**
 * Interfaz para estructuras de datos tipo cola
 * @author Borja López
 */
public interface ICola {

    /**
     * Devuelve la cantida de elementos que hay en la cola
     * @return La cantidad de elementos que hay en la cola
     */
    public int getNum();

    /**
     * Inserta un elemento en la cola
     * @param elemento Elemento a insertar
     * @throws Exception En caso de que la cola esté llena
     */
    public void Acola(Object elemento) throws Exception;

    /**
     * Extrae un elemento de la cola
     * @return El elemento que ha sido extraido
     * @throws Exception En caso de que la cola esté vacía
     */
    public Object Desacola() throws Exception;

    /**
     * Devuelve el primer elemento introducido en la cola, que será el siguiente en salir. Este método no modifica la estructura.
     * @return El primer element ode la cola
     * @throws Exception En caso de que la cola esté vacía
     */
    public Object Primero() throws Exception;
}
