package libClases;

public class Biblioteca {
    
    private int nVolumenes;
    private Volumen[] lista;
    
    public Biblioteca(){
        nVolumenes = 0;
        lista = new Volumen[1];
    }
    
    public void alta(Volumen v){
        if(buscar(v.getISBN()) == -1){
            if(nVolumenes == lista.length){
                Volumen[] aux = new Volumen[lista.length*2];
                for(int i = 0; i < nVolumenes; i++)
                    aux[i] = lista[i];
                lista = aux;
            }
            lista[nVolumenes] = v;
            nVolumenes++;
        }
    }
    
    public int buscar(long ISBN){
        int i = 0, pos = -1;
        while(i < nVolumenes && pos == -1)
            if(lista[i].getISBN() == ISBN)
                pos = i;
            else i++;
        return pos;
    }
    
    public Volumen getVolumen(int n){
        return lista[n];
    }
    
    public void agregarNumero(){
        for(int i = 0; i < nVolumenes; i++)
            if(lista[i].getClass().equals(Revista.class))
                ((Revista)lista[i]).agregarNumero();
    }
    
    public void listar(){
        for(int i = 0; i < nVolumenes; i++)
            System.out.println((i+1)+" "+lista[i]);
    }
    public void listarRevistas(){
        int n = 1;
        for(int i = 0; i < nVolumenes; i++)
            if(lista[i].getClass().equals(Revista.class))
                System.out.println((n++)+" "+lista[i]);
    }
    public void listarLibros(){
        int n = 1;
        for(int i = 0; i < nVolumenes; i++)
            if(lista[i].getClass().equals(Libro.class))
                System.out.println((n++)+" "+lista[i]);
    }
}
