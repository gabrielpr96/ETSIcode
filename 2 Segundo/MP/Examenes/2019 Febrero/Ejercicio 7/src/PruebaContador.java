public class PruebaContador {
    private Contador c;
    
    public void comenzar(){
        c = new Contador(1);
        this.setContador(c);
    }
    
    public void setContador(Contador c){
        c = null;
        c = new Contador(3);
    }
    
    
    //Prueba
    public void verContador(){
        System.out.println("Valor del contador: "+c);
    }
    public static void main(String[] args){
        PruebaContador pc = new PruebaContador();
        pc.comenzar();
        pc.verContador();
        //Logicamente es el resultado es 1. Porque setContador recive una referencia al propio atributo c de la clase. Lo primero que hace es igualarla a null, ya c no hace referencia al atributo de la clase. Pero eso no modifica en absoluto el atributo. Luego crea un nuevo contador con otro valor que es referenciado por la misma variable c, igualmente no tiene nada que ver con el atributo c.
    }
}
