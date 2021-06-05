package patroniterator;

public class ListaPalabras implements IListaPalabras{
    private String[] palabras;
    private int posicion;
    
    public ListaPalabras(){
        palabras = new String[10];
        posicion = 0;
    }

    @Override
    public void agregar(String p) {
        palabras[posicion++] = p;
    }

    @Override
    public ListaPalabrasIterador crearIterador() {
        return new ListaPalabrasIterador(palabras);
    }
}
