package practica8;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

/**
 * *
 * id: Identificador del hilo tipo: true si es un ClienteRegistro, false si es
 * un ClienteNota atendidoRegistro: true si el cliente está siendo atendido por
 * un registrador atendidoOficial: true si el cliente está siendo atendido por
 * un oficial
 *
 * @author borja
 */
class Cliente {

    private final long id;
    private final boolean tipo;

    //Almacenan por quien está siendo atendido
    private boolean atendidoRegistrador, atendidoOficial;

    /**
     * *
     * Representacion de un cliente esperando en la cola
     *
     * @param id
     * @param tipo True es tipo registro, False es tipo nota simple
     */
    public Cliente(long id, boolean tipo) {
        this.id = id;
        this.tipo = tipo;
        atendidoRegistrador = false;
        atendidoOficial = false;
    }

    public void atendidoRegistrador() {
        atendidoRegistrador = true;
    }

    public void atendidoOficinal() {
        atendidoOficial = true;
    }

    public boolean esAtendidoRegistrador() {
        return atendidoRegistrador;
    }

    public boolean esAtendidoOficinal() {
        return atendidoOficial;
    }

    public long getId() {
        return id;
    }

    public boolean getTipo() {
        return tipo;
    }
}

public class CanvasRegistro extends Canvas {

    private final ArrayList<Cliente> cola;
    private int libresRegistrador, libresOficinal;

    private final static Font fTrabajador = new Font("Courier New", Font.BOLD, 22);
    private final static Font fNumero = new Font("Consolas", Font.BOLD, 30);

    private final static Color clienteRegistro = Color.magenta;
    private final static Color clienteNota = Color.orange;
    private final static Color registrador = Color.red;
    private final static Color oficial = Color.blue;

    public CanvasRegistro(int nOficiales, int nRegistradores) {
        cola = new ArrayList<>();
        libresRegistrador = nRegistradores;
        libresOficinal = nOficiales;
    }

    /**
     * Agrega el clinte tipo registro a la cola. El identificador del cliente es
     * la ID del hilo que llama al metodo.
     */
    public void entraClienteRegistro() {
        cola.add(new Cliente(Thread.currentThread().getId(), true));
        repaint();
    }

    /**
     * Agrega el clinte nota simple registro a la cola. El identificador del
     * cliente es la ID del hilo que llama al metodo.
     */
    public void entraClienteNota() {
        cola.add(new Cliente(Thread.currentThread().getId(), false));
        repaint();
    }

    /**
     * Saca al cliente de la cola y libera los a los empleados que le estaban
     * antendiendo
     */
    public void saleCliente() {
        long id = Thread.currentThread().getId();
        Cliente c = cola.stream().filter(cliente -> cliente.getId() == id).findFirst().orElse(null);
        if (c.esAtendidoRegistrador()) {
            libresRegistrador++;
        }
        if (c.esAtendidoOficinal()) {
            libresOficinal++;
        }
        cola.remove(c);
        repaint();
    }

    /**
     * Modifica a un cliente ya en la cola para indicar que está siendo atendido
     * por un registrador y consume al empleado
     */
    public void atendidoPorRegistrador() {
        long id = Thread.currentThread().getId();
        Cliente c = cola.stream().filter(cliente -> cliente.getId() == id).findFirst().orElse(null);
        c.atendidoRegistrador();
        libresRegistrador--;
        repaint();
    }

    /**
     * Modifica a un cliente ya en la cola para indicar que está siendo atendido
     * por un oficial y consume al empleado
     */
    public void atendidoPorOficial() {
        long id = Thread.currentThread().getId();
        Cliente c = cola.stream().filter(cliente -> cliente.getId() == id).findFirst().orElse(null);
        c.atendidoOficinal();
        libresOficinal--;
        repaint();
    }

    /**
     * Comprueba si todos los registradores están ocupados
     *
     * @return Verdadero si no hay registradores libres
     */
    public boolean registradoresOcupados() {
        return libresRegistrador == 0;
    }

    /**
     * Comprueba si todos los oficiales están ocupados
     *
     * @return Verdadero si no hay oficiales libres
     */
    public boolean oficialesOcupados() {
        return libresOficinal == 0;
    }

    @Override
    public void paint(Graphics og) {
        Image oi = createImage(getWidth(), getHeight());
        Graphics g = oi.getGraphics();

        g.setColor(Color.lightGray);
        g.fillRect(10, 10, 180, 95);

        g.setFont(fTrabajador);
        g.setColor(Color.black);
        g.drawString("Oficiales", 40, 35);
        g.drawString("libres", 60, 60);

        g.setColor(oficial);
        for (int i = 0; i < libresOficinal; i++) {
            g.fillOval(35 + i * 50, 70, 25, 25);
        }

        g.setColor(Color.lightGray);
        g.fillRect(10, 130, 180, 95);

        g.setFont(fTrabajador);
        g.setColor(Color.black);
        g.drawString("Registradores", 15, 155);
        g.drawString("libres", 60, 180);

        g.setColor(registrador);
        for (int i = 0; i < libresRegistrador; i++) {
            g.fillOval(35 + i * 50, 190, 25, 25);
        }

        g.setFont(fTrabajador);
        g.setColor(Color.black);
        g.drawString("Clientes", 240, 30);

        g.setFont(fNumero);
        for (int i = 0; i < cola.size(); i++) {
            Cliente c = cola.get(i);
            g.setColor(c.getTipo() ? clienteRegistro : clienteNota);
            g.drawString(Long.toString(c.getId()), 280, 80 + i * 50);
            if (c.esAtendidoOficinal()) {
                g.setColor(oficial);
                g.fillOval(240, 60 + i * 50, 25, 25);
            }
            if (c.esAtendidoRegistrador()) {
                g.setColor(registrador);
                g.fillOval(330, 60 + i * 50, 25, 25);
            }
        }

        og.drawImage(oi, 0, 0, null);
    }

    @Override
    public void update(Graphics g) {
        paint(g);
    }
}
