package implementacion;

import comun.DatosAlmacen;
import comun.IGestionAlmacenes;
import comun.Producto;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;

public class GestionAlmacenes extends UnicastRemoteObject implements IGestionAlmacenes, Serializable {

    private final String DIR = System.getenv("APPDATA") + "\\sdp2\\";
    private final Map<String, Almacen> almacenes;

    public GestionAlmacenes() throws RemoteException {
        super();
        almacenes = new HashMap<>();
        try {
            Path pDIR = Paths.get(DIR);
            if (!Files.exists(pDIR)) {
                Files.createDirectories(Paths.get(DIR));
            }
            System.out.println("Directorio de almacenes: " + DIR);
        } catch (IOException ex) {
            Logger.getLogger(GestionAlmacenes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Devuelve una el nombre de los ficheros de almacenes
     *
     * @return Una lista con los nombrs de ficheros en la carpeta de almacenes
     * @throws RemoteException
     */
    @Override
    public String[] obtenerAlmacenesFicheros() throws RemoteException {
        ArrayList<String> ficheros = new ArrayList<>();
        try {
            Files.newDirectoryStream(Paths.get(DIR), path
                    -> Files.isRegularFile(path) && path.toString().endsWith(".jsd")
            ).forEach((fichero) -> {
                ficheros.add(fichero.toFile().getName());
            });
        } catch (IOException ex) {
            Logger.getLogger(GestionAlmacenes.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ficheros.toArray(new String[0]);
    }

    /**
     * Busca y devuelve los datos de un almacen en memoria
     *
     * @param fichero Identificador del almacen
     * @return Los datos de un almacen en memoria
     * @throws RemoteException
     */
    @Override
    public DatosAlmacen datosAlmacen(String fichero) throws RemoteException {
        return almacenes.get(fichero).getDatos();
    }

    /**
     * Busca y devuelve el número de productos que tiene un almacen en memoria
     *
     * @param fichero Identificador del almacen
     * @return La cantidad de productos de un almacen en memoria
     * @throws RemoteException
     */
    @Override
    public int nProductos(String fichero) throws RemoteException {
        return almacenes.get(fichero).getProductos().size();
    }

    /**
     * Crea un almacen cargado en memoria y lo guarda en el disco
     *
     * @param nombre Nombre a mostrar
     * @param direccion Dirección del almacen
     * @param fichero Dirección y nombre del fichero donde se guardará
     * @throws RemoteException
     */
    @Override
    public void crearAlmacen(String nombre, String direccion, String fichero) throws RemoteException {
        Almacen almacen = almacenes.get(fichero);
        if (almacen == null) {
            almacenes.put(fichero, new Almacen(1, new DatosAlmacen(nombre, direccion, fichero)));
            guardarAlmacen(fichero);
        }else{
            almacen.abrir();
        }
    }

    /**
     * Carga en memoria un almacen si no está ya abierto
     *
     * @param fichero Identificador del almacen
     * @throws RemoteException
     */
    @Override
    public void abrirAlmacen(String fichero) throws RemoteException {
        Almacen almacen = almacenes.get(fichero);
        if (almacen == null) {
            FileInputStream fis = null;
            ObjectInputStream ois = null;
            try {
                fis = new FileInputStream(DIR + fichero);
                ois = new ObjectInputStream(fis);
                almacen = (Almacen) ois.readObject();
                almacen.resetAbiertos();
                almacenes.put(fichero, almacen);
            } catch (IOException | ClassNotFoundException ex) {
                ex.printStackTrace();
            } finally {
                try {
                    if (fis != null) {
                        fis.close();
                    }
                    if (ois != null) {
                        ois.close();
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }else{
            almacen.abrir();
        }
    }

    /**
     *
     * @param fichero Identificador del almacen
     * @return
     * @throws RemoteException
     */
    @Override
    public boolean guardarAlmacen(String fichero) throws RemoteException {
        Almacen almacen = almacenes.get(fichero);
        if (almacen != null) {
            File f = new File(DIR + fichero);
            if (f.exists()) {
                f.delete();
            }
            FileOutputStream fos = null;
            ObjectOutputStream oos = null;
            try {
                fos = new FileOutputStream(DIR + fichero);
                oos = new ObjectOutputStream(fos);
                oos.writeObject(almacen);
                oos.flush();
            } catch (IOException ex) {
                ex.printStackTrace();
                return false;
            } finally {
                try {
                    if (fos != null) {
                        fos.close();
                    }
                    if (oos != null) {
                        oos.close();
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Cierra un almacen. Si ningun usuario lo está usando, lo guarda y elimina
     * definitivamente.
     *
     * @param fichero Identificador del almacen
     * @return Verdadero si el se ha podido cerrar el almacen
     * @throws RemoteException
     */
    @Override
    public boolean cerrarAlmacen(String fichero) throws RemoteException {
        Almacen almacen = almacenes.get(fichero);
        if (almacen == null) {
            return false;
        }
        almacen.cerrar();
        if (!almacen.abierto()) {
            System.out.println("CIERRO DE VERDAD EL ALMACEN "+fichero);
            guardarAlmacen(fichero);
            almacenes.remove(fichero);
        }
        return true;
    }

    /**
     * Comprueba si un almacen está abierto buscandolo por su fichero
     *
     * @param fichero Identificador del almacen
     * @return Verdadero si el almacen está abierto
     * @throws RemoteException
     */
    @Override
    public boolean almacenAbierto(String fichero) throws RemoteException {
        return almacenes.get(fichero) != null;
    }

    /**
     * Devuelve un producto localizado por el fichero en el que se guarda el
     * almacen y el código del producto
     *
     * @param fichero Dirección del fichero del almacen
     * @param codProducto Codigo del producto
     * @return El producto buscado
     * @throws RemoteException
     */
    @Override
    public Producto obtenerProducto(String fichero, String codProducto) throws RemoteException {
        return almacenes.get(fichero).getProductos().get(codProducto);
    }

    /**
     * Devuelve todos los productos en un almacen
     *
     * @param fichero Identificador del almacen
     * @return Array de productos en el almacen
     * @throws RemoteException
     */
    @Override
    public Producto[] obtenerProductos(String fichero) throws RemoteException {
        return almacenes.get(fichero).getProductos().values().toArray(new Producto[0]);
    }

    /**
     * Comprueba si se puede agregar el producto y lo añade
     *
     * @param fichero Identificador del almacen
     * @param prodNuevo Nuevo producto a agregar
     * @return Falso si no se ha podido agregar el producto
     * @throws RemoteException
     */
    @Override
    public boolean anadirProducto(String fichero, Producto prodNuevo) throws RemoteException {
        Almacen almacen = almacenes.get(fichero);
        if (almacen == null || almacen.getProductos().containsKey(prodNuevo.getCodProducto())) {
            return false;
        }
        almacen.getProductos().put(prodNuevo.getCodProducto(), prodNuevo);
        return true;
    }

    /**
     * Comprueba si el producto existe en el almacen y lo actualiza
     *
     * @param fichero Identificador del almacen
     * @param prodNuevo Nuevo producto a agregar
     * @return Falso si el producto no ha podido ser actualizado
     * @throws RemoteException
     */
    @Override
    public boolean actualizarProducto(String fichero, Producto prodNuevo) throws RemoteException {
        Almacen almacen = almacenes.get(fichero);
        if (almacen == null || !almacen.getProductos().containsKey(prodNuevo.getCodProducto())) {
            return false;
        }
        almacen.getProductos().put(prodNuevo.getCodProducto(), prodNuevo);
        return true;
    }

    /**
     * Comprueba si se puede eliminar un producto de un almacen
     *
     * @param fichero Identificador del almacen
     * @param codProducto Codigo del producto a eliminar
     * @return Falso si el producto no existe y no se ha podido eliminar
     * @throws RemoteException
     */
    @Override
    public boolean eliminarProducto(String fichero, String codProducto) throws RemoteException {
        Almacen almacen = almacenes.get(fichero);
        if (almacen == null || !almacen.getProductos().containsKey(codProducto)) {
            return false;
        }
        almacen.getProductos().remove(codProducto);
        return true;
    }
}
