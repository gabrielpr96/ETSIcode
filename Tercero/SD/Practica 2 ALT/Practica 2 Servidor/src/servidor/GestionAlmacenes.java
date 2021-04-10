package servidor;

import comun.GestionAlmacenesIntf;
import comun.TDatosAlmacen;
import comun.TFecha;
import comun.TProducto;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
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
    private final List<TAlmacen> almacenes;

    private boolean PosicionValida(int pAlmacen) {
        return pAlmacen < almacenes.size() && pAlmacen >= 0 && almacenes.get(pAlmacen).getNAbierto() > 0;
    }

    private int BuscarAlmacenEnMemoria(String pFichero) {
        return IntStream.range(0, almacenes.size())
                .filter(i -> almacenes.get(i).getDatos().getFichero().equals(pFichero))
                .findFirst()
                .orElse(-1);
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
        almacenes = new ArrayList<>();
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
        almacenes.add(almacen);
        int pos = almacenes.size()-1;
        GuardarAlmacen(pos);
        return pos;
    }

    @Override
    public int AbrirAlmacen(String pNomFichero) throws RemoteException {
        int pos = BuscarAlmacenEnMemoria(pNomFichero);
        if (pos != -1) {
            return pos;
        }

        FileInputStream fis = null;
        DataInputStream dis = null;

        try {
            fis = new FileInputStream(DIR + pNomFichero);
            dis = new DataInputStream(fis);

            TAlmacen almacen = new TAlmacen(new TDatosAlmacen(dis.readUTF(), dis.readUTF(), dis.readUTF()));

            while(dis.available() > 0) {
                almacen.getProductos().add(new TProducto(dis.readUTF(), dis.readUTF(), dis.readUTF(), Float.parseFloat(dis.readUTF()), Integer.parseInt(dis.readUTF()), new TFecha(Integer.parseInt(dis.readUTF()), Integer.parseInt(dis.readUTF()), Integer.parseInt(dis.readUTF()))));
            }
            
            almacenes.add(almacen);
            return almacenes.size()-1;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (fis != null) {
                    fis.close();
                }
                if (dis != null) {
                    dis.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(GestionAlmacenes.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return -1;
    }

    @Override
    public boolean GuardarAlmacen(int pAlmacen) throws RemoteException {
        FileOutputStream fos = null;
        DataOutputStream dos = null;

        try {
            TAlmacen almacen = almacenes.get(pAlmacen);
            TDatosAlmacen datos = almacen.getDatos();
            fos = new FileOutputStream(DIR + datos.getFichero());
            dos = new DataOutputStream(fos);

            dos.writeUTF(datos.getNombre());
            dos.writeUTF(datos.getDireccion());
            dos.writeUTF(datos.getFichero());

            for (TProducto producto : almacen.getProductos()) {
                dos.writeUTF(producto.getCodProducto());
                dos.writeUTF(producto.getNombreProducto());
                dos.writeUTF(producto.getDescripcion());
                dos.writeUTF(Float.toString(producto.getPrecio()));
                dos.writeUTF(Integer.toString(producto.getCantidad()));
                dos.writeUTF(Integer.toString(producto.getCaducidad().getDia()));
                dos.writeUTF(Integer.toString(producto.getCaducidad().getMes()));
                dos.writeUTF(Integer.toString(producto.getCaducidad().getAnyo()));
            }

            dos.flush();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (fos != null) {
                    fos.close();
                }
                if (dos != null) {
                    dos.close();
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
