package juegodados;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Juego {

    private int apuesta;
    private final Jugador jugador;
    private final DadoDoble dadoJugador, dadoBanca;
    private final VentanaPrincipal ventana;
    private static final int SHUFFLE_DELAY = 100;
    private static final int SHUFFLE_COUNT = 20;
    private static final int RETRY_DELAY = 1000;
    private final Runnable procesoTirarDados = new Runnable() {
        @Override
        public void run() {
            try {
                do {
                    for (int i = 0; i < SHUFFLE_COUNT; i++) {
                        dadoBanca.tirar();
                        dadoJugador.tirar();
                        ventana.actualizarDados();
                        Thread.sleep(SHUFFLE_DELAY);
                    }
                } while (resultado());
            } catch (InterruptedException ex) {
                Logger.getLogger(Juego.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    };

    public Juego(Jugador jugador, DadoDoble dadoJugador, DadoDoble dadoBanca, VentanaPrincipal ventana) {
        this.jugador = jugador;
        this.dadoJugador = dadoJugador;
        this.dadoBanca = dadoBanca;
        this.ventana = ventana;
    }

    public void apostar(int cantidad) {
        if (apuesta < 0) {
            ventana.actualizarEstado("No puedes apostar una cantiad negativa, listillo");
            return;
        }
        if (apuesta != 0) {
            ventana.actualizarEstado("No puedes volver a postar, tira los dados");
            return;
        }
        apuesta = cantidad;
        jugador.cambiarSaldo(-cantidad);
        ventana.actualizarEstado("Cantidad apostada, tire los dados");
    }

    public void tirarDaods() {
        if (apuesta == 0) {
            ventana.actualizarEstado("Tienes que apostar antes de tirar los dados");
            return;
        }
        Thread thread = new Thread(procesoTirarDados);
        thread.start();
    }

    public boolean resultado() throws InterruptedException {
        int modificacion = 0;
        boolean sigueJugando = false;
        if (dadoBanca.getDados() == 7 || dadoBanca.getDados() == 11) {
            sigueJugando = true;
        } else if (dadoJugador.getDados() == 7 || dadoJugador.getDados() == 11) {
            modificacion = apuesta * 2;
        } else if (dadoJugador.getDados() == 12) {
            sigueJugando = true;
        } else if (dadoJugador.getDados() > dadoBanca.getDados()) {
            modificacion = apuesta;
        }
        jugador.cambiarSaldo(modificacion);
        apuesta = 0;
        if (sigueJugando) {
            ventana.actualizarEstado("Se vuelve a tirar");
            Thread.sleep(RETRY_DELAY);
        } else {
            ventana.actualizarEstado("Tirada completada, te han reembolsado: " + modificacion);
        }
        return sigueJugando;
    }
}
