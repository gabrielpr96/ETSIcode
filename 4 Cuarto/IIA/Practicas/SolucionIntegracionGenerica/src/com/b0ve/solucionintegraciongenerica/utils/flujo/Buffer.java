package com.b0ve.solucionintegraciongenerica.utils.flujo;

import com.b0ve.solucionintegraciongenerica.tareas.Avisable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public final class Buffer {

    private final Queue<Mensaje> mensajes;
    private Queue<Mensaje> reserva;
    private final Avisable salida;

    public Buffer(Avisable salida) {
        this.reserva = null;
        this.mensajes = new LinkedList<>();
        this.salida = salida;
    }

    public synchronized boolean empty() {
        return this.mensajes.isEmpty();
    }

    public synchronized Mensaje retrive() {
        return mensajes.poll();
    }

    public synchronized void push(Mensaje mensaje) {
        if (reserva == null) {
            mensajes.add(mensaje);
            if (salida != null) {
                salida.signalInput();
            }
        } else {
            reserva.add(mensaje);
        }
    }

    public synchronized Iterator<Mensaje> getIterator() {
        return mensajes.iterator();
    }

    public synchronized void deleteMessage(Mensaje mensaje) {
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
}
