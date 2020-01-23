package libClases;

public abstract class Volumen {
    private final String titulo, autor;
    private final long ISBN;
    
    public Volumen(String titulo, String autor, long ISBN){
        this.titulo = titulo;
        this.autor = autor;
        this.ISBN = ISBN;
    }
    
    public long getISBN(){
        return ISBN;
    }
    
    @Override
    public String toString(){
        return titulo+" ("+autor+") "+ISBN;
    }
}
