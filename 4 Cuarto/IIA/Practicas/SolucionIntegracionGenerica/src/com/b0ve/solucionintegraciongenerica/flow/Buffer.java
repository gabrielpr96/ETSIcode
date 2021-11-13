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

    public synchronized Message retrive() {
        return mensajes.poll();
    }

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

    public synchronized Iterator<Message> getIterator() {
        return mensajes.iterator();
    }

    public synchronized void deleteMessage(Message mensaje) {
        mensajes.remove(mensaje);
    }

    public synchronized void lockPushes() {
        reserva = new LinkedList<>();
    }

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
