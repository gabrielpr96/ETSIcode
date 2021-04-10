package comun;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IGestionAlmacenes extends Remote {
    
    /* Devuelve la lista de archivos de almacenes */
    String[] obtenerAlmacenesFicheros() throws RemoteException;

    /*Dado una posición del almacén, devuelve sus datos */
    DatosAlmacen datosAlmacen(String fichero) throws RemoteException;

    /*Dado una posición de almacén, devuelve el número de productos o -1 si no existe.*/
    int nProductos(String fichero) throws RemoteException;

    /*Crea un almacén vacío de productos y devuelve la posición donde se encuentra. Si previamente ya
    estaba creado y cargado en memoria, devolverá la posición donde se encuentra en memoria. */
    void crearAlmacen(String nombre, String direccion, String fichero) throws RemoteException;

    /*Abre un fichero de almacén y lo carga en memoria y devuelve su posición. Si previamente ya estaba
    cargado devuelve la posición donde se encuentra. */
    void abrirAlmacen(String fichero) throws RemoteException;

    /*Dado la posición de un almacén vuelca sus datos al fichero. Devuelve true si ha podido guardarlo.*/
    boolean guardarAlmacen(String fichero) throws RemoteException;

    /*Dado la posición de un almacén, vuelca sus datos al fichero y elimina la memoria dinámica asociada.
    Si el Almacén es compartido, solo el último cliente es el que eliminará la memoria dinámica.*/
    boolean cerrarAlmacen(String fichero) throws RemoteException;

    /* Dado la posición del almacén devuelve true si el almacén está cargado en memoria. */
    boolean almacenAbierto(String fichero) throws RemoteException;

    /*Dado los códigos del producto del producto y el fichero del almacen, lo devuelve.*/
    Producto obtenerProducto(String fichero, String codProducto) throws RemoteException;

    /*Dado lel fichero del almacen, devuelve todos sus productos.*/
    Producto[] obtenerProductos(String fichero) throws RemoteException;

    /*Dado la posición de un almacén y un producto lo añadirá a la memoria dinámica del almacén y devuelve
    true. No puede existir dos productos con el mismo código en el almacén. */
    boolean anadirProducto(String fichero, Producto prodNuevo) throws RemoteException;

    /*Dado la posición de un almacén y un producto lo actualizará en la memoria dinámica del almacén. */
    boolean actualizarProducto(String fichero, Producto prodNuevo) throws RemoteException;

    /*Dado la posición del almacén y un código de producto, lo elimina de la memoria dinámica y devuelve
    true.*/
    boolean eliminarProducto(String fichero, String codProducto) throws RemoteException;
}
