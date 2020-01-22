package libPruebas;

import libClases.*;

public class Prueba1 {
    public static void main(String[] args) {
        System.out.println( "BIBLIOTECA\n");
        Biblioteca uhu = new Biblioteca();
        Libro lib1=new Libro("LA COLMENA", "Cela", 1001, 1, 276);
        Libro lib2=new Libro("PLATERO Y YO", "Jimenez", 1002, 2, 330);
        Libro lib3=new Libro("EL HOBBIT", "Tolkien", 1003, 2, 330);
        Revista rev2=new Revista("MUY INTERESANTE", "Godoy", 2001, 45);
        Revista rev3=new Revista("NATIONAL GEOGRAPHICS", "Gil", 2002, 60);
        uhu.alta(rev2);
        uhu.alta(rev3);
        uhu.alta(lib2);
        uhu.alta(lib3);
        uhu.alta(lib1);
        uhu.alta(rev3); //No debe darlo de alta porque ya existe un volumen con ese ISBN
        uhu.alta(lib3); //No debe darlo de alta porque ya existe un volumen con ese ISBN

        System.out.print("\nListado de Libros y Revistas\n\n");
        uhu.listar();

        int nej=uhu.buscar(2002); //busca el libro o revista con ISBN 2002
        if (nej != -1) { //devuelve -1 si no esta en la biblioteca
        Revista r=(Revista)uhu.getVolumen(nej); //devuelve el libro o revista que esta en la pos nej
        r.agregarNumero(61); //agrega el numero 61 a la revista
        r.agregarNumero(); //agrega el ultimo numero a la revista
        r.agregarNumero(70); //agrega el numero 70 a la revista
        }

        ((Libro)uhu.getVolumen(uhu.buscar(1001))).agregarEjemplares(5); //agregar 5 ejemplares al libro
        //con ISBN 1001
        uhu.agregarNumero(); //agrega a todas las revistas de la biblioteca su ultimo numero
        System.out.print("\nListado de Revistas\n\n");
        uhu.listarRevistas();
        System.out.print("\nListado de Libros\n\n");
        uhu.listarLibros();
    }
}
