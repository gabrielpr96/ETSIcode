package patroniterator;

public class ListaNumeros implements IListaNumeros{
    private int[] numeros;
    private int posicion;
    
    public ListaNumeros(){
        numeros = new int[10];
        posicion = 0;
    }

    @Override
    public void agregar(int num) {
        numeros[posicion++] = num;
    }

    @Override
    public ListaNumerosIterador crearIterador() {
        return new ListaNumerosIterador(numeros);
    }
}
