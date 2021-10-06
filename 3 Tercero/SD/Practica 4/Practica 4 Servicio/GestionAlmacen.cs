using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.ServiceModel;
using System.Threading;


namespace Practica_4_Servicio {
    [ServiceBehavior(ConcurrencyMode = ConcurrencyMode.Single, InstanceContextMode = InstanceContextMode.Single)]
    public class GestionAlmacen : IGestionAlmacen {
        private class TAlmacen {
            private readonly TDatosAlmacen Datos;
            private int nAbierto;
            private readonly List<TProducto> Productos;

            public TAlmacen(TDatosAlmacen Datos) {
                this.Datos = Datos;
                this.nAbierto = 1;
                this.Productos = new List<TProducto>();
            }

            public TDatosAlmacen getDatos() {
                return Datos;
            }

            public List<TProducto> getProductos() {
                return Productos;
            }

            public int getNAbierto() {
                return nAbierto;
            }

            public void setNAbierto(int pNAbierto) {
                nAbierto = pNAbierto;
            }
        }

        private readonly String DIR = Environment.GetFolderPath(Environment.SpecialFolder.ApplicationData) + "\\sdp2\\";
        private readonly Dictionary<int, TAlmacen> almacenes;
        private int contador;

        private Boolean PosicionValida(int pAlmacen) {
            return almacenes.ContainsKey(pAlmacen) && almacenes[pAlmacen].getNAbierto() > 0;
        }

        private int BuscarAlmacenEnMemoria(String pFichero) {
            try {
                return almacenes
                    .Where(kvp => kvp.Value.getDatos().Fichero.Equals(pFichero))
                    .Select(kvp => kvp.Key)
                    .First();
            } catch(Exception e) {
                return -1;
            }
        }

        private int BuscarProductoEnAlmacen(int pAlmacen, String pCodProducto) {
            if(PosicionValida(pAlmacen)) {
                try {
                    return almacenes[pAlmacen].getProductos()
                        .Select((prod, index) => new { prod, index })
                        .First(p => p.prod.CodProducto.Equals(pCodProducto))
                        .index;
                } catch(Exception e) {
                    return -1;
                }
            } else {
                return -1;
            }
        }

        public GestionAlmacen() {
            contador = 0;
            almacenes = new Dictionary<int, TAlmacen>();
            try {
                Directory.CreateDirectory(DIR);
                Console.WriteLine("Directorio de almacenes: {0}", DIR);
            } catch(IOException e) {
                Console.WriteLine(e.Source);
            }

            Console.WriteLine("Activo :-)");
        }
        ~GestionAlmacen() {
            Console.WriteLine("Descativado :-(");
            Thread.Sleep(2000);
        }

        /*Dado una posición del almacén, devuelve sus datos */
        public TDatosAlmacen DatosAlmacen(int pAlmacen) {
            return PosicionValida(pAlmacen) ? almacenes[pAlmacen].getDatos() : null;
        }

        /*Dado una posición de almacén, devuelve el número de productos o -1 si no existe.*/
        public int NProductos(int pAlmacen) {
            return PosicionValida(pAlmacen) ? almacenes[pAlmacen].getProductos().Count() : -1;
        }

        /*Crea un almacén vacío de productos y devuelve la posición donde se encuentra. Si previamente ya
        estaba creado y cargado en memoria, devolverá la posición donde se encuentra en memoria. */
        public int CrearAlmacen(String pNombre, String pDireccion, String pNomFichero) {
            TAlmacen almacen = new TAlmacen(new TDatosAlmacen { Nombre = pNombre, Direccion = pDireccion, Fichero = pNomFichero });
            int pos = contador++;
            almacenes[pos] = almacen;
            GuardarAlmacen(pos);
            return pos;
        }

        /*Abre un fichero de almacén y lo carga en memoria y devuelve su posición. Si previamente ya estaba
        cargado devuelve la posición donde se encuentra. */
        public int AbrirAlmacen(String pNomFichero) {
            int pos = BuscarAlmacenEnMemoria(pNomFichero);
            if(pos != -1) {
                almacenes[pos].setNAbierto(almacenes[pos].getNAbierto() + 1);
                return pos;
            }

            try {
                using(BinaryReader reader = new BinaryReader(File.Open(DIR + pNomFichero, FileMode.Open))) {
                    int nProductos = reader.ReadInt32();
                    TAlmacen almacen = new TAlmacen(new TDatosAlmacen { Nombre = reader.ReadString(), Direccion = reader.ReadString(), Fichero = pNomFichero });

                    for(int i = 0; i < nProductos; i++) {
                        String codigo = reader.ReadString();
                        int existencias = reader.ReadInt32();
                        String nombre = reader.ReadString();
                        float precio = reader.ReadSingle();
                        String descripcion = reader.ReadString();
                        TFecha caducidad = new TFecha { Dia = reader.ReadInt32(), Mes = reader.ReadInt32(), Anyo = reader.ReadInt32() };

                        almacen.getProductos().Add(new TProducto { CodProducto = codigo, NombreProducto = nombre, Descripcion = descripcion, Precio = precio, Cantidad = existencias, Caducidad = caducidad });
                    }

                    pos = contador++;
                    almacenes[pos] = almacen;
                    return pos;
                }
            } catch(Exception e) {
                return -1;
            }
        }

        /*Dado la posición de un almacén vuelca sus datos al fichero. Devuelve true si ha podido guardarlo.*/
        public Boolean GuardarAlmacen(int pAlmacen) {
            if(!PosicionValida(pAlmacen)) return false;

            TAlmacen almacen = almacenes[pAlmacen];
            TDatosAlmacen datos = almacen.getDatos();

            try {
                using(BinaryWriter writer = new BinaryWriter(File.Open(DIR + datos.Fichero, FileMode.Create))) {
                    writer.Write(almacen.getProductos().Count());
                    writer.Write(datos.Nombre);
                    writer.Write(datos.Direccion);

                    foreach(TProducto producto in almacen.getProductos()) {
                        writer.Write(producto.CodProducto);
                        writer.Write(producto.Cantidad);
                        writer.Write(producto.NombreProducto);
                        writer.Write(producto.Precio);
                        writer.Write(producto.Descripcion);
                        writer.Write(producto.Caducidad.Dia);
                        writer.Write(producto.Caducidad.Mes);
                        writer.Write(producto.Caducidad.Anyo);
                    }
                }
                return true;
            } catch(Exception e) {
                return false;
            }

        }

        /*Dado la posición de un almacén, vuelca sus datos al fichero y elimina la memoria dinámica asociada.
        Si el Almacén es compartido, solo el último cliente es el que eliminará la memoria dinámica.*/
        public Boolean CerrarAlmacen(int pAlmacen) {
            if(PosicionValida(pAlmacen)) {
                GuardarAlmacen(pAlmacen);
                TAlmacen almacen = almacenes[pAlmacen];
                almacen.setNAbierto(almacen.getNAbierto() - 1);
                if(almacen.getNAbierto() == 0) {
                    almacenes.Remove(pAlmacen);
                }
                return true;
            } else {
                return false;
            }
        }

        /* Dado la posición del almacén devuelve true si el almacén está cargado en memoria. */
        public Boolean AlmacenAbierto(int pAlmacen) {
            return PosicionValida(pAlmacen);
        }

        /*Dado la posición del almacén y un código de producto, devuelve la posición dentro del vector de
        dinámico donde se encuentra el producto. Si no lo encuentra devuelve -1. */
        public int BuscaProducto(int pAlmacen, String pCodProducto) {
            return BuscarProductoEnAlmacen(pAlmacen, pCodProducto);
        }

        /*Dado la posición del almacén y la del producto, lo devuelve.*/
        public TProducto ObtenerProducto(int pAlmacen, int pPosProducto) {
            return PosicionValida(pAlmacen) ? almacenes[pAlmacen].getProductos()[pPosProducto] : null;
        }

        /*Dado la posición de un almacén y un producto lo añadirá a la memoria dinámica del almacén y devuelve
        true. No puede existir dos productos con el mismo código en el almacén. */
        public Boolean AnadirProducto(int pAlmacen, TProducto pProdNuevo) {
            if(PosicionValida(pAlmacen)) {
                almacenes[pAlmacen].getProductos().Add(pProdNuevo);
                return true;
            } else {
                return false;
            }
        }

        /*Dado la posición de un almacén y un producto lo actualizará en la memoria dinámica del almacén. */
        public Boolean ActualizarProducto(int pAlmacen, TProducto pProducto) {
            int posProducto = BuscarProductoEnAlmacen(pAlmacen, pProducto.CodProducto);
            if(posProducto == -1) {
                return false;
            } else {
                almacenes[pAlmacen].getProductos()[posProducto] = pProducto;
                return true;
            }
        }

        /*Dado la posición del almacén y un código de producto, lo elimina de la memoria dinámica y devuelve
        true.*/
        public Boolean EliminarProducto(int pAlmacen, String pCodProducto) {
            int posProducto = BuscarProductoEnAlmacen(pAlmacen, pCodProducto);
            if(posProducto == -1) {
                return false;
            } else {
                almacenes[pAlmacen].getProductos().RemoveAt(posProducto);
                return true;
            }
        }
    }
}
