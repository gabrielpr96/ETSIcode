package cliente;

import comun.GestionAlmacenesIntf;
import comun.TDatosAlmacen;
import comun.TFecha;
import comun.TProducto;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Practica2Cliente {

    private static String leerDefecto(String concepto,  String defecto) {
        try {
            BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
            System.out.print(concepto+" ["+defecto+"]: ");
            String userInput = input.readLine();
            if (!userInput.trim().isEmpty()) {
                return userInput;
            }
        } catch (IOException ex) {
            Logger.getLogger(Practica2Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        return defecto;
    }
    private static int leerDefectoInt(String concepto,  int defecto) {
        try {
            return Integer.parseInt(leerDefecto(concepto, Integer.toString(defecto)));
        } catch (NumberFormatException ex) {
            Logger.getLogger(Practica2Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        return defecto;
    }
    private static float leerDefectoFloat(String concepto,  float defecto) {
        try {
            return Float.parseFloat(leerDefecto(concepto, String.format("%s", defecto)));
        } catch (NumberFormatException ex) {
            Logger.getLogger(Practica2Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        return defecto;
    }

    private static int menu(Scanner s, String almacen) {
        System.out.print("----- Menu Almacenes ----- " + (almacen == null ? "" : almacen) + "\n"
                + "\n"
                + "1.- Crear un almacen vacio.\n"
                + "2.- Abrir un fichero de almacen.\n"
                + "3.- Cerrar un almacen.\n"
                + "4.- Guardar datos.\n"
                + "5.- Listar productos del almacen.\n"
                + "6.- Anadir un producto.\n"
                + "7.- Actualizr un producto.\n"
                + "8.- Consultar un producto.\n"
                + "9.- Eliminar un producto.\n"
                + "0.- Salir.\n"
                + "\n"
                + "Opcion: ");
        int opt = s.nextInt();
        s.nextLine();
        return opt;
    }

    public static void main(String[] args) {
        int puerto;
        String host;
        Scanner s = new Scanner(System.in);
        /*
        System.out.print("Introduce el nº de puerto para comunicarse: ");
        puerto = Teclado.nextInt();
        System.out.print("Introduce el nombre del host: ");
        host = Teclado.next();
         */
        puerto = 49591;
        host = "localhost";

        GestionAlmacenesIntf stub;
        try {
            stub = (GestionAlmacenesIntf) Naming.lookup("rmi://" + host + ":" + puerto + "/GestionAlmacenes");
            int actual = -1;
            String actualNombre = null;

            String nombre, direccion, fichero, descripcion, codProducto;
            int cantidad, dia, mes, anyo;
            float precio;
            int opt;
            do {
                opt = menu(s, actualNombre);
                switch (opt) {
                    case 1: //Crear almacen
                        if (actual != -1) {
                            stub.GuardarAlmacen(actual);
                            stub.CerrarAlmacen(actual);
                        }
                        System.out.print("Nombre: ");
                        nombre = s.nextLine();
                        System.out.print("Direccion: ");
                        direccion = s.nextLine();
                        System.out.print("Fichero: ");
                        fichero = s.nextLine();
                        actual = stub.CrearAlmacen(nombre, direccion, fichero);
                        if (actual == -1) {
                            System.out.println("Error en la operacion");
                        } else {
                            actualNombre = nombre;
                        }
                        break;
                    case 2: //Abrir fichero
                        if (actual != -1) {
                            stub.GuardarAlmacen(actual);
                            stub.CerrarAlmacen(actual);
                        }
                        System.out.print("Fichero: ");
                        fichero = s.nextLine();
                        actual = stub.AbrirAlmacen(fichero);
                        if (actual == -1) {
                            System.out.println("Error en la operacion");
                        } else {
                            TDatosAlmacen datos = stub.DatosAlmacen(actual);
                            if (datos == null) {
                                System.out.println("Error en la operacion");
                            } else {
                                actualNombre = datos.getNombre();
                            }
                        }
                        break;
                    case 3: //Cerrar almacen
                        if (actual == -1) {
                            System.out.println("No se ha habierto ningun almacen");
                        } else {
                            if (!stub.CerrarAlmacen(actual)) {
                                System.out.println("Error en la operacion");
                            }
                            actual = -1;
                            actualNombre = null;
                        }
                        break;
                    case 4: //Guardar datos
                        if (actual == -1) {
                            System.out.println("No se ha habierto ningun almacen");
                        } else {
                            if (!stub.GuardarAlmacen(actual)) {
                                System.out.println("Error en la operacion");
                            }
                        }
                        break;
                    case 5: //Listar productos
                        if (actual == -1) {
                            System.out.println("No se ha habierto ningun almacen");
                        } else {
                            int nProductos = stub.NProductos(actual);
                            if (nProductos == 0) {
                                System.out.println("No hay productos en este almacen");
                            } else {
                                System.out.println("CODIGO   NOMBRE                       PRECIO   CANTIDAD   FECHA CADUCIDAD");
                                for (int i = 0; i < nProductos; i++) {
                                    TProducto producto = stub.ObtenerProducto(actual, i);
                                    System.out.println(String.format("%-8s %-28s %-8.2f %-10d %-15s", producto.getCodProducto(), producto.getNombreProducto(), producto.getPrecio(), producto.getCantidad(), producto.getCaducidad()));
                                }
                            }
                        }
                        break;
                    case 6: //Añadir producto
                        if (actual == -1) {
                            System.out.println("No se ha habierto ningun almacen");
                        } else {
                            System.out.print("Codigo: ");
                            codProducto = s.nextLine();
                            System.out.print("Nombre: ");
                            nombre = s.nextLine();
                            System.out.print("Descripcion: ");
                            descripcion = s.nextLine();
                            System.out.print("Precio: ");
                            precio = s.nextFloat();
                            System.out.print("Cantidad: ");
                            cantidad = s.nextInt();
                            System.out.print("Caducidad dia: ");
                            dia = s.nextInt();
                            System.out.print("Caducidad mes: ");
                            mes = s.nextInt();
                            System.out.print("Caducidad anyo: ");
                            anyo = s.nextInt();
                            if (!stub.AnadirProducto(actual, new TProducto(codProducto, nombre, descripcion, precio, cantidad, new TFecha(dia, mes, anyo)))) {
                                System.out.println("Error en la operacion");
                            }
                        }
                        break;
                    case 7: //Actualizar producto
                        if (actual == -1) {
                            System.out.println("No se ha habierto ningun almacen");
                        } else {
                            System.out.print("Codigo: ");
                            codProducto = s.nextLine();
                            TProducto producto = stub.ObtenerProducto(actual, stub.BuscaProducto(actual, codProducto));
                            if (producto == null) {
                                System.out.print("No se ha encontrado el producto");
                            } else {
                                nombre = leerDefecto("Nombre", producto.getNombreProducto());
                                descripcion = leerDefecto("Descripcion", producto.getDescripcion());
                                precio = leerDefectoFloat("Precio", producto.getPrecio());
                                cantidad = leerDefectoInt("Cantidad", producto.getCantidad());
                                dia = leerDefectoInt("Caducidad dia", producto.getCaducidad().getDia());
                                mes = leerDefectoInt("Caducidad mes", producto.getCaducidad().getMes());
                                anyo = leerDefectoInt("Caducidad anyo", producto.getCaducidad().getAnyo());
                                if (!stub.ActualizarProducto(actual, new TProducto(codProducto, nombre, descripcion, precio, cantidad, new TFecha(dia, mes, anyo)))) {
                                    System.out.println("Error en la operacion");
                                }
                            }
                        }
                        break;
                    case 8: //Consultar producto
                        if (actual == -1) {
                            System.out.println("No se ha habierto ningun almacen");
                        } else {
                            System.out.print("Codigo: ");
                            codProducto = s.nextLine();
                            TProducto producto = stub.ObtenerProducto(actual, stub.BuscaProducto(actual, codProducto));
                            if (producto == null) {
                                System.out.print("No se ha encontrado el producto");
                            } else {
                                System.out.println("Nombre: " + producto.getNombreProducto() + "\n"
                                        + "Descripcion: " + producto.getDescripcion() + "\n"
                                        + "Precio: " + producto.getPrecio() + "\n"
                                        + "Cantidad: " + producto.getCantidad() + "\n"
                                        + "Caducidad: " + producto.getCaducidad() + "\n");
                            }
                        }
                        break;
                    case 9: //Eliminar producto
                        if (actual == -1) {
                            System.out.println("No se ha habierto ningun almacen");
                        } else {
                            System.out.print("Codigo del producto a eliminar: ");
                            codProducto = s.nextLine();
                            if (!stub.EliminarProducto(actual, codProducto)) {
                                System.out.println("Error en la operacion");
                            }
                        }
                        break;
                }
                if (opt != 0) {
                    System.out.print("\n\nPulsa enter para continuar.");
                    s.nextLine();
                }
            } while (opt != 0);

            if (actual != -1) {
                try {
                    stub.GuardarAlmacen(actual);
                    stub.CerrarAlmacen(actual);
                } catch (RemoteException ex) {
                    ex.printStackTrace();
                }
            }
        } catch (NotBoundException | MalformedURLException | RemoteException ex) {
            Logger.getLogger(Practica2Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }

        //System.exit(0);
    }

}
