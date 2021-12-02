package juegoahorcado;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import static juegoahorcado.ControladorJuego.ESTADOS.*;

public class ControladorJuego implements KeyListener {

    enum ESTADOS {
        ESPERANDO_INICIO, //Esperando a que el juegador pulse enter para comnenzar la partida
        INTRODUCIENDO_NOMBRE_JUGADOR_1, //Se esta tecleando el nombre del jugador 1 hasta que se pulse enter
        INTRODUCIENDO_NOMBRE_JUGADOR_2, //Se esta tecleando el nombre del jugador 2 hasta que se pulse enter
        INTRODUCIENDO_PALABRA, //Se esta tecleando a que el jugador
        ADIVINANDO_PALABRA, //Se estan introduciendo letras para adivinar la palabra
        PARTIDA_TERMINADA, //La partida ha terminado, esperando enter para regresar a introduciendo palabra
        JUEGO_TERMINADO //Han terminado todas las partidas, esperando enter para volver a introduciendo nombre jugador 1
    }
    private static final int PARTIDAS_POR_JUEGO = 5;

    private final CanvasAhorcado canvas;
    private int nJuego;
    private ESTADOS estado;
    private StringBuilder palabraTMP;

    public ControladorJuego(CanvasAhorcado canvas) {
        this.canvas = canvas;
        this.palabraTMP = new StringBuilder();
        comenzarJuego();
    }

    private void comenzarJuego() {
        nJuego = 0;
        estado = ESPERANDO_INICIO;
        canvas.reset();
        canvas.setMsg("Pulse Enter para comenzar la partida.");
    }

    @Override
    public void keyTyped(KeyEvent e) {
        switch (estado) {
            case ESPERANDO_INICIO:
                break;
            case INTRODUCIENDO_NOMBRE_JUGADOR_1:
            case INTRODUCIENDO_NOMBRE_JUGADOR_2:
            case INTRODUCIENDO_PALABRA:
                procesarLetra(e.getKeyChar());
                break;
            case ADIVINANDO_PALABRA:
                adivinarLetra(e.getKeyChar());
                break;
        }
    }

    private void procesarLetra(char letra) {
        if (letra == '\b') {
            palabraTMP.setLength(palabraTMP.length() - 1);
        } else {
            palabraTMP.append(letra);
        }
        canvas.setPalabra(palabraTMP.toString());
    }

    private void adivinarLetra(char letra) {
        if (letra != '\b' && letra != ' ' && letra != '\r' && letra != '\n') {
            if (!canvas.addLetraProbada(letra)) {
                canvas.addLinea();
            }
            if (canvas.isAdivinada() || canvas.getLineas() >= 11) {
                estado = PARTIDA_TERMINADA;
                if ((canvas.isAdivinada() ? (nJuego % 2 == 0 ? 1 : 2) : (nJuego % 2 != 0 ? 2 : 1)) == 1) {
                    canvas.setMsg("Gana " + canvas.getJugador1());
                    canvas.addPunto1();
                } else {
                    canvas.setMsg("Gana " + canvas.getJugador2());
                    canvas.addPunto2();
                }
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            switch (estado) {
                case ESPERANDO_INICIO:
                    transicionIntroducirNombre1();
                    break;
                case INTRODUCIENDO_NOMBRE_JUGADOR_1:
                    canvas.setNombreJugador1(palabraTMP.toString());
                    transicionIntroducirNombre2();
                    break;
                case INTRODUCIENDO_NOMBRE_JUGADOR_2:
                    canvas.setNombreJugador2(palabraTMP.toString());
                    transicionIntroduciendoPalabra();
                    break;
                case INTRODUCIENDO_PALABRA:
                    transicionAdivinarPalabra();
                    break;
                case PARTIDA_TERMINADA:
                    nJuego++;
                    if (nJuego == 5) {
                        transicionNuevoJuego();
                    } else {
                        transicionIntroduciendoPalabra();
                    }
                    break;
                case JUEGO_TERMINADO:
                    comenzarJuego();
                    break;
            }
        }
    }

    private void transicionIntroducirNombre1() {
        System.out.println("INICIAR");
        estado = INTRODUCIENDO_NOMBRE_JUGADOR_1;
        canvas.setPalabra("");
        palabraTMP.setLength(0);
        canvas.setOcultarPalabra(false);
        canvas.setMsg("Introduzca el nombre del primer jugador.");
    }

    private void transicionIntroducirNombre2() {
        estado = INTRODUCIENDO_NOMBRE_JUGADOR_2;
        canvas.setPalabra("");
        palabraTMP.setLength(0);
        canvas.setMsg("Introduzca el nombre del segundo jugador.");
    }

    public void transicionIntroduciendoPalabra() {
        estado = INTRODUCIENDO_PALABRA;
        canvas.setPalabra("");
        canvas.resetLetrasProbadas();
        canvas.resetLineas();
        canvas.setOcultarPalabra(false);
        palabraTMP.setLength(0);
        if (nJuego % 2 == 0) {
            canvas.setMsg(canvas.getJugador1() + " introduzca una palabra.");
        } else {
            canvas.setMsg(canvas.getJugador2() + " introduzca una palabra.");
        }
    }

    public void transicionAdivinarPalabra() {
        estado = ADIVINANDO_PALABRA;
        canvas.setOcultarPalabra(true);
        canvas.resetLineas();
        if (nJuego % 2 == 1) {
            canvas.setMsg(canvas.getJugador1() + " pruebe con una letra.");
        } else {
            canvas.setMsg(canvas.getJugador2() + " pruebe con una letra.");
        }
    }

    public void transicionNuevoJuego() {
        estado = JUEGO_TERMINADO;
        if (canvas.getPuntos1() > canvas.getPuntos2()) {
            canvas.setMsg("Terminado, gana " + canvas.getJugador1());
        } else if (canvas.getPuntos2() > canvas.getPuntos1()) {
            canvas.setMsg("Terminado, gana " + canvas.getJugador2());
        } else {
            canvas.setMsg("Terminado, emnpate.");
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        //No utilizado
    }
}
