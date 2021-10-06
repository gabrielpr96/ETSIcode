package patronprototype;

public class PatronPrototype {

    public static void main(String[] args) {
        Prototipo p1 = new PrototipoConcreto1();
        Prototipo p2 = new PrototipoConcreto2();
        Prototipo c1, c2;
        
        c1 = p1.clonar();
        System.out.println("Nombre prototipo 1: "+p1.getNombre());
        System.out.println("Nombre  clonado  1: "+c1.getNombre());
        
        c1.setNombre("Don josé");
        System.out.println("Nombre prototipo 1: "+p1.getNombre());
        System.out.println("Nombre  clonado  1: "+c1.getNombre());
    }

}
