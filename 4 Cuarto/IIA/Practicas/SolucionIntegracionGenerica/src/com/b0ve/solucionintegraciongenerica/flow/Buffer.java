package com.b0ve.solucionintegraciongenerica.flow;

import com.b0ve.solucionintegraciongenerica.tasks.Notifiable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public final class Buffer {

    private final Queue<Message> mensajes;
    private Queue<Message> reserva;
    private final Notifiable salida;

    public Buffer(Notifiable salida) {
        this.reserva = null;
        this.mensajes = new LinkedList<>();
        this.salida = salida;
    }

    public synchronized boolean empty() {
        return this.mensajes.isEmpty();
    }

    /**
     * Returns the element that has been waiting the most in the queue
     * @return 
     */
    public synchronized Message retrive() {
        return mensajes.poll();
    }

    /**
     * Inserts a message in the queue.
     * @param mensaje 
     */
    public synchronized void push(Message mensaje) {
        if (reserva == null) {
            mensajes.add(mensaje);
            if (salida != null) {
                salida.signalInput();
            }
        } else {
            reserva.add(mensaje);
        }
    }

    /**
     * Returns an Iterator to walk over all the messages in the queue
     * @return 
     */
    public synchronized Iterator<Message> getIterator() {
        return mensajes.iterator();
    }

    /**
     * Deletes a message from the queue
     * @param mensaje 
     */
    public synchronized void deleteMessage(Message mensaje) {
        mensajes.remove(mensaje);
    }

    /**
     * Blocks new messages from entering in the queue.
     * They are stored in a temporary space for later.
     * Use before iterating over the queue
     */
    public synchronized void lockPushes() {
        reserva = new LinkedList<>();
    }

    /**
     * Unlocks the queue, new messages can be added.
     * Messages in the temporary space are added in.
     * Use after iterating over the queue
     */
    public synchronized void unlockPushes() {
        boolean hayCambios = !reserva.isEmpty();
        while (!reserva.isEmpty()) {
            mensajes.add(reserva.poll());
        }
        reserva = null;
        if (hayCambios && salida != null) {
            salida.signalInput();
        }
    }

    @Override
    public String toString() {
        return "Buffer{" + "mensajes=" + mensajes + ", reserva=" + reserva + '}';
    }
}
