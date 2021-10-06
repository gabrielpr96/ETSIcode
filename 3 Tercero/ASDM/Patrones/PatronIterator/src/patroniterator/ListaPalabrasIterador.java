package patroniterator;

public class ListaPalabrasIterador implements IIterador{
    private String[] palabras;
    private int posicion;
    
    public ListaPalabrasIterador(String pals[]){
        palabras = pals;
        posicion = 0;
    }

    @Override
    public Object siguiente() {
        return palabras[posicion++];
    }

    @Override
    public boolean tieneSiguiente() {
        return posicion < palabras.length && palabras[posicion] != null;
    }

    @Override
    public Object anterior() {
        return posicion > 0 ? palabras[--posicion] : null;
    }
    
}
