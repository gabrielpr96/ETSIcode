package patroniterator;

public class PatronIterator {

    public static void main(String[] args) {
        IListaNumeros ln = new ListaNumeros();
        System.out.println("Voy a agregar los numeros: 5, 1, 16, 89, 2, 3, 8, 23");
        ln.agregar(5);
        ln.agregar(1);
        ln.agregar(16);
        ln.agregar(89);
        ln.agregar(2);
        ln.agregar(3);
        ln.agregar(8);
        ln.agregar(23);
        IIterador it1 = ln.crearIterador();
        while(it1.tieneSiguiente())
            System.out.println(it1.siguiente());
        
        IListaPalabras lp = new ListaPalabras();
        System.out.println("Voy a agregar los numeros: Casa, Bosque, Teclado, Raspberry PI, Durgod");
        lp.agregar("Casa");
        lp.agregar("Bosque");
        lp.agregar("Teclado");
        lp.agregar("Raspberry PI");
        lp.agregar("Durgod");
        IIterador it2 = lp.crearIterador();
        while(it2.tieneSiguiente())
            System.out.println(it2.siguiente());
        
    }
    
}
