package practica;

public interface Comparable {
    /**
     * Comparar un objeto con sigo mismo.
     * A.comparar(B) = A less than B
     * La respesta por defecto es false, en caso de fallo en la comparacion. EJ: o es null
     * @param o El objeto con el que se compara
     * @param sortType Parametro que indica al algoritmo como decidir.
     * @return Verdadero si es menor que el objeto pasado por parametro.
     */
    public boolean comparar(Object o, boolean sortType);
}
