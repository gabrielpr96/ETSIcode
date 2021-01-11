package registro;

import interfaz.IRegistro;
import java.rmi.RemoteException;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.rmi.server.UnicastRemoteObject;
import practica8.CanvasRegistro;

public class Registro extends UnicastRemoteObject implements IRegistro{

    private final CanvasRegistro canvas;
    private final ReentrantLock l;
    private final Condition cRegistro, cNota;
    private volatile int esperandoRegistro, esperandoNota;

    /**
     * El recurso compartido
     *
     * @param canvas
     * @throws java.rmi.RemoteException
     */
    public Registro(CanvasRegistro canvas) throws RemoteException {
        super();
        this.canvas = canvas;
        l = new ReentrantLock(true);
        cRegistro = l.newCondition();
        cNota = l.newCondition();
        esperandoRegistro = 0;
        esperandoNota = 0;
    }

    /**
     * Protocolo de entrada para clientes tipo registro
     * @param id
     * @throws java.rmi.RemoteException
     */
    @Override
    public void entraRegistro(int id) throws RemoteException {
        l.lock();
        try {
            esperandoRegistro++;
            canvas.entraClienteRegistro(id);
            if (canvas.oficialesOcupados() || canvas.registradoresOcupados()) {
                cRegistro.await();
            }
            esperandoRegistro--;
            canvas.atendidoPorOficial(id);
            canvas.atendidoPorRegistrador(id);
        } catch (InterruptedException ex) {
            Logger.getLogger(Registro.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            l.unlock();
        }
    }

    /**
     * Protocolo de entrada para clientes tipo nota simple
     * @param id
     * @throws java.rmi.RemoteException
     */
    @Override
    public void entraNota(int id) throws RemoteException {
        l.lock();
        try {
            esperandoNota++;
            canvas.entraClienteNota(id);
            if ((canvas.oficialesOcupados() && canvas.registradoresOcupados()) || (esperandoRegistro > 0 && canvas.oficialesOcupados())) {
                cNota.await();
            }
            esperandoNota--;
            if (!canvas.oficialesOcupados()) {
                canvas.atendidoPorOficial(id);
            } else if (!canvas.registradoresOcupados()) {
                canvas.atendidoPorRegistrador(id);
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(Registro.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            l.unlock();
        }
    }

    /**
     * Procolo de salida, el mismo para los dos tipos de cliente
     * @param id
     * @throws java.rmi.RemoteException
     */
    @Override
    public void sale(int id) throws RemoteException {
        l.lock();
        try {
            canvas.saleCliente(id);
            if (!canvas.oficialesOcupados() && !canvas.registradoresOcupados()) {
                //Hay empleados libres de los dos tipos
                //Pueden entrar clientes registro si los hay, si no puedo despertar a dos clientes nota
                if (esperandoRegistro > 0) {
                    cRegistro.signal();
                } else {
                    cNota.signal();
                    cNota.signal();
                }
            } else {
                //Solo hay empleados de un tipo libre
                //Solo puede entrar un cliente nota, si no hay clientes registro esperasndo, porque esos tienen prioridad. Excepto si solo quedan oficiales, entonces sí
                if (esperandoRegistro == 0 || !canvas.oficialesOcupados()) {
                    cNota.signal();
                }
            }
        } finally {
            l.unlock();
        }
    }
}
