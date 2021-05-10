using System;
using Practica_4_Cliente.GestionAlmacen;

namespace Practica_4_Cliente {
    class Program {

        private static String leerDefecto(String concepto, String defecto) {
            Console.Write("{0} [{1}]: ", concepto, defecto);
            String userInput = Console.ReadLine();
            if(userInput.Trim().Length != 0) {
                return userInput;
            }
            return defecto;
        }

        private static int leerDefectoInt(String concepto, int defecto) {
            try {
                return Int32.Parse(leerDefecto(concepto, defecto.ToString()));
            } catch(Exception e) {
                return leerDefectoInt(concepto, defecto);
            }
        }

        private static float leerDefectoFloat(String concepto, float defecto) {
            try {
                return Single.Parse(leerDefecto(concepto, defecto.ToString()));
            } catch(Exception e) {
                return leerDefectoFloat(concepto, defecto);
            }
        }

        private static String leer(String concepto) {
            Console.Write("{0}: ", concepto);
            return Console.ReadLine();
        }

        private static int leerInt(String concepto) {
            try {
                return Int32.Parse(leer(concepto));
            } catch(Exception e) {
                return leerInt(concepto);
            }
        }

        private static float leerFloat(String concepto) {
            try {
                return Single.Parse(leer(concepto));
            } catch(Exception e) {
                return leerFloat(concepto);
            }
        }

        private static int menu(String almacen) {
            Console.Clear();
            Console.Write("----- Menu Almacenes ----- {0}\n"
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
                    + "Opcion: ", almacen == null ? "" : almacen);
            return Int32.Parse(Console.ReadLine());
        }

        private static void gestionar(GestionAlmacenClient stub) {
            String nombre, direccion, fichero, codProducto, actualNombre = null;
            int actual = -1;
            int opt;
            do {
                opt = menu(actualNombre);
                switch(opt) {
                    case 1: //Crear almacen
                        if(actual != -1) {
                            stub.GuardarAlmacen(actual);
                            stub.CerrarAlmacen(actual);
                            actual = -1;
                            actualNombre = null;
                        }
                        nombre = leer("Nombre");
                        direccion = leer("Direccion");
                        fichero = leer("Fichero");
                        actual = stub.CrearAlmacen(nombre, direccion, fichero);
                        if(actual == -1) {
                            Console.WriteLine("Error en la operacion");
                        } else {
                            actualNombre = nombre;
                        }
                        break;
                    case 2: //Abrir fichero
                        if(actual != -1) {
                            stub.GuardarAlmacen(actual);
                            stub.CerrarAlmacen(actual);
                            actual = -1;
                            actualNombre = null;
                        }
                        fichero = leer("Fichero");
                        actual = stub.AbrirAlmacen(fichero);
                        if(actual == -1) {
                            Console.WriteLine("Error en la operacion");
                        } else {
                            TDatosAlmacen datos = stub.DatosAlmacen(actual);
                            if(datos == null) {
                                Console.WriteLine("Error en la operacion");
                            } else {
                                actualNombre = datos.Nombre;
                            }
                        }
                        break;
                    case 3: //Cerrar almacen
                        if(actual == -1) {
                            Console.WriteLine("No se ha habierto ningun almacen");
                        } else {
                            if(!stub.CerrarAlmacen(actual)) {
                                Console.WriteLine("Error en la operacion");
                            }
                            actual = -1;
                            actualNombre = null;
                        }
                        break;
                    case 4: //Guardar datos
                        if(actual == -1) {
                            Console.WriteLine("No se ha habierto ningun almacen");
                        } else {
                            if(!stub.GuardarAlmacen(actual)) {
                                Console.WriteLine("Error en la operacion");
                            }
                        }
                        break;
                    case 5: //Listar productos
                        if(actual == -1) {
                            Console.WriteLine("No se ha habierto ningun almacen");
                        } else {
                            int nProductos = stub.NProductos(actual);
                            if(nProductos == 0) {
                                Console.WriteLine("No hay productos en este almacen");
                            } else {
                                Console.WriteLine(String.Format("{0,10} {1,25} {2,10} {3,10} {4,16}", "CODIGO", "NOMBRE", "PRECIO", "CANTIDAD", "FECHA CADUCIDAD"));
                                for(int i = 0; i < nProductos; i++) {
                                    TProducto producto = stub.ObtenerProducto(actual, i);
                                    Console.WriteLine(String.Format("{0,10} {1,25} {2,10} {3,10} {4,16}", producto.CodProducto, producto.NombreProducto, producto.Precio, producto.Cantidad, String.Format("{0,2}/{1,2}/{2,4}", producto.Caducidad.Dia, producto.Caducidad.Mes, producto.Caducidad.Anyo)));
                                }
                            }
                        }
                        break;
                    case 6: //Añadir producto
                        if(actual == -1) {
                            Console.WriteLine("No se ha habierto ningun almacen");
                        } else {
                            TProducto nuevo = new TProducto();
                            TFecha caducidad = new TFecha();
                            nuevo.CodProducto = leer("Codigo");
                            nuevo.NombreProducto = leer("Nombre");
                            nuevo.Descripcion = leer("Descripcion");
                            nuevo.Precio = leerFloat("Precio");
                            nuevo.Cantidad = leerInt("Cantidad");
                            caducidad.Dia = leerInt("Caducidad dia");
                            caducidad.Mes = leerInt("Caducidad mes");
                            caducidad.Anyo = leerInt("Caducidad anyo");
                            nuevo.Caducidad = caducidad;
                            if(!stub.AnadirProducto(actual, nuevo)) {
                                Console.WriteLine("Error en la operacion");
                            }
                        }
                        break;
                    case 7: //Actualizar producto
                        if(actual == -1) {
                            Console.WriteLine("No se ha habierto ningun almacen");
                        } else {
                            codProducto = leer("Codigo");
                            TProducto producto = stub.ObtenerProducto(actual, stub.BuscaProducto(actual, codProducto));
                            if(producto == null) {
                                Console.WriteLine("No se ha encontrado el producto");
                            } else {
                                TProducto nuevo = new TProducto();
                                TFecha caducidad = new TFecha();
                                nuevo.CodProducto = codProducto;
                                nuevo.NombreProducto = leerDefecto("Nombre", producto.NombreProducto);
                                nuevo.Descripcion = leerDefecto("Descripcion", producto.Descripcion);
                                nuevo.Precio = leerDefectoFloat("Precio", producto.Precio);
                                nuevo.Cantidad = leerDefectoInt("Cantidad", producto.Cantidad);
                                caducidad.Dia = leerDefectoInt("Caducidad dia", producto.Caducidad.Dia);
                                caducidad.Mes = leerDefectoInt("Caducidad mes", producto.Caducidad.Mes);
                                caducidad.Anyo = leerDefectoInt("Caducidad anyo", producto.Caducidad.Anyo);
                                nuevo.Caducidad = caducidad;
                                if(!stub.ActualizarProducto(actual, nuevo)) {
                                    Console.WriteLine("Error en la operacion");
                                }
                            }
                        }
                        break;
                    case 8: //Consultar producto
                        if(actual == -1) {
                            Console.WriteLine("No se ha habierto ningun almacen");
                        } else {
                            codProducto = leer("Codigo");
                            TProducto producto = stub.ObtenerProducto(actual, stub.BuscaProducto(actual, codProducto));
                            if(producto == null) {
                                Console.WriteLine("Error en la operacion");
                            } else {
                                Console.WriteLine("Nombre: {0}\nDescripcion: {1}\nPrecio: {2}\nCantidad: {3}\nCaducidad: {4}/{5}/{6}\n", producto.NombreProducto, producto.Descripcion, producto.Precio, producto.Cantidad, producto.Caducidad.Dia, producto.Caducidad.Mes, producto.Caducidad.Anyo);
                            }
                        }
                        break;
                    case 9: //Eliminar producto
                        if(actual == -1) {
                            Console.WriteLine("No se ha habierto ningun almacen");
                        } else {
                            codProducto = leer("Codigo del producto a eliminar");
                            if(!stub.EliminarProducto(actual, codProducto)) {
                                Console.WriteLine("Error en la operacion");
                            }
                        }
                        break;
                }
                if(opt != 0) {
                    Console.WriteLine("\n\nPulsa enter para continuar.");
                    Console.ReadLine();
                }
            } while(opt != 0);

            if(actual != -1) {
                stub.GuardarAlmacen(actual);
                stub.CerrarAlmacen(actual);
            }
        }

        static void Main(string[] args) {
            GestionAlmacenClient client = new GestionAlmacenClient();

            gestionar(client);

            Console.WriteLine("Pulsa Enter para salir");
            Console.ReadLine();

            client.Close();
        }
    }
}
