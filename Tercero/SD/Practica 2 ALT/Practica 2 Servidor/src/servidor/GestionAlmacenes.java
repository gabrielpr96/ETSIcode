package servidor;

import comun.GestionAlmacenesIntf;
import comun.TDatosAlmacen;
import comun.TFecha;
import comun.TProducto;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.IntStream;

final class TAlmacen {

    private final TDatosAlmacen Datos;
    private int nAbierto;
    private final List<TProducto> Productos;

    public TAlmacen(TDatosAlmacen Datos) {
        this.Datos = Datos;
        this.nAbierto = 1;
        this.Productos = new ArrayList<>();
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

public class GestionAlmacenes extends UnicastRemoteObject implements GestionAlmacenesIntf, Serializable {

    private final String DIR = System.getenv("APPDATA") + "\\sdp2\\";
    private final Map<Integer, TAlmacen> almacenes;
    private int contador;

    private boolean PosicionValida(int pAlmacen) {
        return almacenes.containsKey(pAlmacen) && almacenes.get(pAlmacen).getNAbierto() > 0;
    }

    private int BuscarAlmacenEnMemoria(String pFichero) {
        int pos = -1;
        Iterator it = almacenes.entrySet().iterator();
        while (it.hasNext() && pos == -1) {
            Map.Entry pair = (Map.Entry) it.next();
            if(((TAlmacen)pair.getValue()).getDatos().getFichero().equals(pFichero)){
                pos = (Integer) pair.getKey();
            }
        }
        return pos;
    }

    private int BuscarProductoEnAlmacen(int pAlmacen, String pCodProducto) {
        if (PosicionValida(pAlmacen)) {
            List<TProducto> productos = almacenes.get(pAlmacen).getProductos();
            return IntStream.range(0, productos.size())
                    .filter(i -> productos.get(i).getCodProducto().equals(pCodProducto))
                    .findFirst()
                    .orElse(-1);
        } else {
            return -1;
        }
    }

    public GestionAlmacenes() throws RemoteException {
        super();
        contador = 0;
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

    @Override
    public TDatosAlmacen DatosAlmacen(int pAlmacen) throws RemoteException {
        return PosicionValida(pAlmacen) ? almacenes.get(pAlmacen).getDatos() : null;
    }

    @Override
    public int NProductos(int pAlmacen) throws RemoteException {
        return PosicionValida(pAlmacen) ? almacenes.get(pAlmacen).getProductos().size() : -1;
    }

    @Override
    public int CrearAlmacen(String pNombre, String pDireccion, String pNomFichero) throws RemoteException {
        TAlmacen almacen = new TAlmacen(new TDatosAlmacen(pNombre, pDireccion, pNomFichero));
        int pos = contador++;
        almacenes.put(pos, almacen);
        GuardarAlmacen(pos);
        return pos;
    }

    @Override
    public int AbrirAlmacen(String pNomFichero) throws RemoteException {
        int pos = BuscarAlmacenEnMemoria(pNomFichero);
        if (pos != -1) {
            System.out.println("Reutilizo "+pos);
            almacenes.get(pos).setNAbierto(almacenes.get(pos).getNAbierto() + 1);
            return pos;
        }

        RandomAccessFile raf = null;

        try {
            raf = new RandomAccessFile(DIR + pNomFichero, "r");

            int nProductos = raf.readInt();
            TAlmacen almacen = new TAlmacen(new TDatosAlmacen(raf.readUTF(), raf.readUTF(), pNomFichero));

            for (int i = 0; i < nProductos; i++) {
                String codigo = raf.readUTF();
                int existencias = raf.readInt();
                String nombre = raf.readUTF();
                float precio = raf.readFloat();
                String descripcion = raf.readUTF();
                TFecha caducidad = new TFecha(raf.readInt(), raf.readInt(), raf.readInt());

                almacen.getProductos().add(new TProducto(codigo, nombre, descripcion, precio, existencias, caducidad));
            }

            pos = contador++;
            almacenes.put(pos, almacen);
            return pos;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (raf != null) {
                    raf.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(GestionAlmacenes.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return -1;
    }

    @Override
    public boolean GuardarAlmacen(int pAlmacen) throws RemoteException {
        RandomAccessFile ras = null;

        try {
            TAlmacen almacen = almacenes.get(pAlmacen);
            TDatosAlmacen datos = almacen.getDatos();

            File file = new File(DIR + datos.getFichero());
            if (file.exists()) {
                file.delete();
            }
            ras = new RandomAccessFile(file, "rw");

            ras.writeInt(almacen.getProductos().size());
            ras.writeUTF(datos.getNombre());
            ras.writeUTF(datos.getDireccion());

            for (TProducto producto : almacen.getProductos()) {
                ras.writeUTF(producto.getCodProducto());
                ras.writeInt(producto.getCantidad());
                ras.writeUTF(producto.getNombreProducto());
                ras.writeFloat(producto.getPrecio());
                ras.writeUTF(producto.getDescripcion());
                ras.writeInt(producto.getCaducidad().getDia());
                ras.writeInt(producto.getCaducidad().getMes());
                ras.writeInt(producto.getCaducidad().getAnyo());
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (ras != null) {
                    ras.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(GestionAlmacenes.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return true;
    }

    @Override
    public boolean CerrarAlmacen(int pAlmacen) throws RemoteException {
        if (PosicionValida(pAlmacen)) {
            GuardarAlmacen(pAlmacen);
            TAlmacen almacen = almacenes.get(pAlmacen);
            almacen.setNAbierto(almacen.getNAbierto() - 1);
            if (almacen.getNAbierto() == 0) {
                almacenes.remove(pAlmacen);
            }
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean AlmacenAbierto(int pAlmacen) throws RemoteException {
        return PosicionValida(pAlmacen);
    }

    @Override
    public int BuscaProducto(int pAlmacen, String pCodProducto) throws RemoteException {
        return BuscarProductoEnAlmacen(pAlmacen, pCodProducto);
    }

    @Override
    public TProducto ObtenerProducto(int pAlmacen, int pPosProducto) throws RemoteException {
        return PosicionValida(pAlmacen) ? almacenes.get(pAlmacen).getProductos().get(pPosProducto) : null;
    }

    @Override
    public boolean AnadirProducto(int pAlmacen, TProducto pProdNuevo) throws RemoteException {
        if (PosicionValida(pAlmacen)) {
            almacenes.get(pAlmacen).getProductos().add(pProdNuevo);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean ActualizarProducto(int pAlmacen, TProducto pProducto) throws RemoteException {
        int posProducto = BuscarProductoEnAlmacen(pAlmacen, pProducto.getCodProducto());
        if (posProducto == -1) {
            return false;
        } else {
            almacenes.get(pAlmacen).getProductos().set(posProducto, pProducto);
            return true;
        }
    }

    @Override
    public boolean EliminarProducto(int pAlmacen, String pCodProducto) throws RemoteException {
        int posProducto = BuscarProductoEnAlmacen(pAlmacen, pCodProducto);
        if (posProducto == -1) {
            return false;
        } else {
            almacenes.get(pAlmacen).getProductos().remove(posProducto);
            return true;
        }
    }

}
