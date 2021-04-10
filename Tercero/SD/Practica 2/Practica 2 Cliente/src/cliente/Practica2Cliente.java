package cliente;

import comun.DatosAlmacen;
import comun.Fecha;
import comun.IGestionAlmacenes;
import comun.Producto;
import java.awt.event.WindowAdapter;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Arrays;
import javax.swing.JOptionPane;

public class Practica2Cliente {

    public static void main(String[] args) throws NotBoundException, MalformedURLException, RemoteException {
        int puerto;
        String host;
        /*
        Scanner Teclado = new Scanner(System.in);
        System.out.print("Introduce el nº de puerto para comunicarse: ");
        puerto = Teclado.nextInt();
        System.out.print("Introduce el nombre del host: ");
        host = Teclado.next();
         */
        puerto = 49591;
        host = "localhost";

        IGestionAlmacenes stub = (IGestionAlmacenes) Naming.lookup("rmi://" + host + ":" + puerto + "/GestionAlmacenes");

        MenuPrincipal menuPrincipal = new MenuPrincipal(stub);
        menuPrincipal.setVisible(true);

        menuPrincipal.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                DatosAlmacen actual = menuPrincipal.getActual();
                if (actual != null) {
                    try {
                        stub.guardarAlmacen(actual.getFichero());
                        stub.cerrarAlmacen(actual.getFichero());
                    } catch (RemoteException ex) {
                        JOptionPane.showMessageDialog(null, ex.getMessage(), "Error al guardar almacen", JOptionPane.ERROR_MESSAGE);
                    }
                }
                System.exit(0);
            }
        });

        /*
        stub.abrirAlmacen("almacen1.jsd");
        for(Producto p : stub.obtenerProductos("almacen1.jsd")){
            System.out.println(p.getNombreProducto());
        }
         */

 /*
        stub.crearAlmacen("Almacen 1", "Huelva", "almacen1.jsd");
        System.out.println(stub.almacenAbierto("almacen1.jsd"));
        stub.anadirProducto("almacen1.jsd", new Producto("C001", 5, "Aceite de oliva", 12.5f, "Oliva de la buena", new Fecha(12, 6, 2000)));
        stub.anadirProducto("almacen1.jsd", new Producto("C002", 1, "Gazpacho", 5.0f, "Carpachoso de tomates", new Fecha(14, 1, 2012)));
        stub.cerrarAlmacen("almacen1.jsd");
         */
    }

}
