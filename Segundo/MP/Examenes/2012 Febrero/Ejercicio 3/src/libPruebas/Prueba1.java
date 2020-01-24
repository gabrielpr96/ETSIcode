package libPruebas;

import libClases.*;

public class Prueba1 {
    public static void main(String[] args){
        Tienda pizzeria = new Tienda("Panuchi", "88787654", 1000); // nombre , Nif, capacidad almacen
        pizzeria.addProducto(new Producto("01", "Cerveza ", 3, 10, 2, .5f));
        pizzeria.addProducto(new Producto("02", "Cocacola", 3, 10, 1, .5f));
        pizzeria.addProducto(new Producto("03", "Pizza   ", 5, 100, 7, 6));
        // crea los productos con cantidad = stock Máximo
        pizzeria.addCuenta(1, 5); //Mesa 1 con 5 Items
        pizzeria.addItem(1, "01", 10); //añade a mesa 10 una Cervezas buscándolo en Almacén
        pizzeria.addItem(1, "02", 8); //añade a mesa 8 una coca colas buscándolo en Almacén
        pizzeria.addItem(1, "03", 1); //añade a mesa 1 una Pizza buscándolo en Almacén
        pizzeria.addItem(1, new Propina()); // Añade a mesa 1 el item propina pasado por parámetro
        pizzeria.addItem(1, new Cubiertos(4)); // Añade a mesa 1 el item cubiertos 4 personas
        System.out.println("La tienda ha ganado " + pizzeria.calcularGanancia() + "€");
        System.out.println("Pedido de articulos " + pizzeria.hacerPedido()); 
    }
}
