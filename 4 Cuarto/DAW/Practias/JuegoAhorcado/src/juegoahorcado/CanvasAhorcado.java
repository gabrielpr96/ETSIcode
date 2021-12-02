package juegoahorcado;

import java.awt.BasicStroke;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

public class CanvasAhorcado extends Canvas {

    private static final Color COLOR_PREVIO = Color.LIGHT_GRAY;
    private static final Color COLOR_DEFINITIVO = Color.black;
    private static final Font FUENTE_TEXTO = new Font("Consolas", Font.PLAIN, 22);

    private int lineas;
    private String palabra;
    private boolean ocultarPalabra;
    private List<Character> probadas;
    private String msg;
    private String jugador1;
    private String jugador2;
    private int puntos1;
    private int puntos2;

    public CanvasAhorcado() {
        reset();
    }

    @Override
    public void paint(Graphics og) {
        Image oi = createImage(getWidth(), getHeight());
        Graphics2D g = (Graphics2D) oi.getGraphics();

        g.setColor(Color.black);
        g.setStroke(new BasicStroke(3));

        //Cabeza
        g.setColor(lineas > 0 ? COLOR_DEFINITIVO : COLOR_PREVIO);
        g.drawOval(210, 80, 60, 60);

        //Cuerpo
        g.setColor(lineas > 1 ? COLOR_DEFINITIVO : COLOR_PREVIO);
        g.drawOval(200, 140, 80, 140);

        //Brazo izquierdo
        g.setColor(lineas > 2 ? COLOR_DEFINITIVO : COLOR_PREVIO);
        g.drawLine(215, 155, 150, 100);

        //Brazo derecho
        g.setColor(lineas > 3 ? COLOR_DEFINITIVO : COLOR_PREVIO);
        g.drawLine(265, 155, 330, 100);

        //Pierza izquierda
        g.setColor(lineas > 4 ? COLOR_DEFINITIVO : COLOR_PREVIO);
        g.drawLine(215, 265, 170, 350);

        //Pierna derecha
        g.setColor(lineas > 5 ? COLOR_DEFINITIVO : COLOR_PREVIO);
        g.drawLine(265, 265, 310, 350);

        //Soga
        g.setColor(lineas > 6 ? COLOR_DEFINITIVO : COLOR_PREVIO);
        g.drawLine(240, 40, 240, 80);

        //Palo
        g.setColor(lineas > 7 ? COLOR_DEFINITIVO : COLOR_PREVIO);
        g.drawLine(70, 40, 240, 40);

        //Mastil
        g.setColor(lineas > 8 ? COLOR_DEFINITIVO : COLOR_PREVIO);
        g.drawLine(70, 40, 70, 400);

        //Escuadra
        g.setColor(lineas > 9 ? COLOR_DEFINITIVO : COLOR_PREVIO);
        g.drawLine(70, 80, 130, 40);

        //Base
        g.setColor(lineas > 10 ? COLOR_DEFINITIVO : COLOR_PREVIO);
        g.drawLine(20, 400, 120, 400);

        //Palabra
        g.setFont(FUENTE_TEXTO);
        g.setColor(Color.BLACK);
        if (ocultarPalabra) {
            StringBuilder guiones = new StringBuilder();
            for (int i = 0; i < palabra.length(); i++) {
                char cChar = palabra.charAt(i);
                if (probadas.contains(cChar)) {
                    guiones.append(cChar);
                } else {
                    guiones.append('_');
                }
                guiones.append(' ');
            }
            g.drawString(guiones.toString(), 340, 150);
        } else {
            g.drawString(palabra, 340, 150);
        }
        
        //Palabras probadas
        if(!probadas.isEmpty()){
            StringBuilder listado = new StringBuilder();
            for (Character probada : probadas) {
                listado.append(probada).append(", ");
            }
            listado.setLength(listado.length()-2);
            g.drawString(listado.toString(), 340, 190);
        }

        //Mensaje
        g.drawString(msg, 20, 430);

        //Jugadores y sus puntos
        if (!jugador1.isEmpty()) {
            g.drawString(jugador1 + ": " + puntos1, 40, 460);
        }
        if (!jugador2.isEmpty()) {
            g.drawString(jugador2 + ": " + puntos1, 300, 460);
        }

        og.drawImage(oi, 0, 0, null);
    }

    @Override
    public void update(Graphics g) {
        paint(g);
    }
    
    public void reset(){
        this.lineas = 0;
        this.palabra = "";
        this.ocultarPalabra = false;
        this.probadas = new ArrayList<>();
        this.msg = "";
        this.jugador1 = "";
        this.jugador2 = "";
        this.puntos1 = 0;
        this.puntos2 = 0;
        repaint();
    }

    public void resetLineas() {
        this.lineas = 0;
        repaint();
    }

    public void addLinea() {
        this.lineas++;
        repaint();
    }

    public void resetLetrasProbadas() {
        this.probadas.clear();
        repaint();
    }

    public boolean addLetraProbada(Character c) {
        boolean yaIntroducida = probadas.contains(c);
        boolean existe = palabra.indexOf(c) != -1;
        if(!yaIntroducida){
            this.probadas.add(c);
        }
        repaint();
        return  existe && !yaIntroducida;
    }

    public void setPalabra(String palabra) {
        this.palabra = palabra.trim();
        repaint();
    }

    public void setMsg(String msg) {
        this.msg = msg;
        repaint();
    }

    public void setNombreJugador1(String jugador1) {
        this.jugador1 = jugador1;
        repaint();
    }

    public void setNombreJugador2(String jugador2) {
        this.jugador2 = jugador2;
        repaint();
    }

    public void addPunto1() {
        this.puntos1++;
        repaint();
    }

    public void addPunto2() {
        this.puntos2++;
        repaint();
    }

    public void setOcultarPalabra(boolean ocultarPalabra) {
        this.ocultarPalabra = ocultarPalabra;
    }

    public String getJugador1() {
        return jugador1;
    }

    public String getJugador2() {
        return jugador2;
    }

    public int getLineas() {
        return lineas;
    }
    
    public boolean isAdivinada(){
        for (int i = 0; i < palabra.length(); i++) {
            if(!probadas.contains(palabra.charAt(i))) return false;
        }
        return true;
    }

    public int getPuntos1() {
        return puntos1;
    }

    public int getPuntos2() {
        return puntos2;
    }
    
    

}
