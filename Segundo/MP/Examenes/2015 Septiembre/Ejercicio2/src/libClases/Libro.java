package libClases;

public class Libro extends Volumen{
    
    private int cantidad;
    private final int paginas;
    
    public Libro(String titulo, String autor, long ISBN, int cantidad, int paginas) {
        super(titulo, autor, ISBN);
        this.cantidad = cantidad;
        this.paginas = paginas;
    }
    
    public void agregarEjemplares(int n){
        cantidad += n;
    }
    
    @Override
    public String toString(){
        return super.toString()+" "+cantidad+" ej. ("+paginas+" pag.)";
    }
    
}
