package patroniterator;

public class ListaNumerosIterador implements IIterador{
    private int[] numeros;
    private int posicion;
    
    public ListaNumerosIterador(int num[]){
        numeros = num;
        posicion = 0;
    }

    @Override
    public Object siguiente() {
        return numeros[posicion++];
    }

    @Override
    public boolean tieneSiguiente() {
        return posicion < numeros.length && numeros[posicion] != 0;
    }

    @Override
    public Object anterior() {
        return posicion > 0 ? numeros[--posicion] : null;
    }
    
}
