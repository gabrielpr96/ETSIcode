public class Contador {
    private int valor;
    
    public Contador(int v){
        valor = v;
    }
    
    
    //Prueba
    @Override
    public String toString(){
        return Integer.toString(valor);
    }
}
