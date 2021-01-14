package examen2018v2;

import java.awt.Canvas;
import java.util.ArrayList;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

public class CanvasMina extends Canvas {

    private int cantidad;
    private final int[] estado;
    private final int[] sacado;
    private int grandeLLenado;
    private final Font fTexto = new Font("Courier New", Font.PLAIN, 20), fNumero = new Font("Consolas", Font.BOLD, 30);

    public CanvasMina() {
        estado = new int[3];
        sacado = new int[3];
        cantidad = 0;
        grandeLLenado = 0;
    }

    public synchronized void cantidad(int tn) {
        cantidad = tn;
        repaint();
    }

    public synchronized void estado(int id, int e) {
        estado[id] = e;
        if (e == 0) {
            sacado[id]++;
        }
        repaint();
    }
    
    public synchronized void grandeLlenado(int llenado){
        grandeLLenado = llenado;
    }

    @Override
    public void update(Graphics g) {
        paint(g);
    }

    @Override
    public void paint(Graphics og) {
        Image img = createImage(getWidth(), getHeight());
        Graphics g = img.getGraphics();

        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(10, 10, 265, 130);
        g.setColor(Color.BLACK);
        g.setFont(fTexto);
        g.drawString("Cargadora Pequenya 1", 20, 40);
        g.setFont(fNumero);
        g.drawString(Integer.toString(sacado[0]), 130, 80);
        switch (estado[0]) {
            case 1:
                g.setColor(Color.GREEN);
                g.drawString("Esperando", 60, 120);
                break;
            case 0:
                g.setColor(Color.RED);
                g.drawString("Sacando", 80, 120);
                break;
            case 2:
                g.setColor(Color.YELLOW);
                g.drawString("Terminado", 60, 120);
                break;
        }

        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(10, 160, 265, 130);
        g.setColor(Color.BLACK);
        g.setFont(fTexto);
        g.drawString("Cargadora Pequenya 2", 20, 190);
        g.setFont(fNumero);
        g.drawString(Integer.toString(sacado[1]), 130, 230);
        switch (estado[1]) {
            case 1:
                g.setColor(Color.GREEN);
            g.drawString("Esperando", 60, 270);
                break;
            case 0:
                g.setColor(Color.RED);
            g.drawString("Sacando", 80, 270);
                break;
            case 2:
                g.setColor(Color.YELLOW);
            g.drawString("Terminado", 60, 270);
                break;
        }

        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(285, 10, 265, 130);
        g.setColor(Color.BLACK);
        g.setFont(fTexto);
        g.drawString("Cargadora Grande", 325, 40);
        g.setFont(fNumero);
        g.drawString(Integer.toString(sacado[2]), 405, 80);
        switch (estado[2]) {
            case 1:
                g.setColor(Color.GREEN);
            g.drawString("Esperando", 335, 120);
                break;
            case 0:
                g.setColor(Color.RED);
            g.drawString("Sacando", 355, 120);
                break;
            case 2:
                g.setColor(Color.YELLOW);
            g.drawString("Terminado", 335, 120);
                break;
        }
        g.setColor(Color.ORANGE);
        for (int i = 0; i < grandeLLenado; i++) {
            g.fillOval(500, 60+40*i, 25, 25);
        }

        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(285, 160, 265, 130);
        g.setColor(Color.BLACK);
        g.setFont(fTexto);
        g.drawString("Material", 375, 190);
        g.setFont(fNumero);
        g.drawString(Integer.toString(cantidad), 405, 230);

        og.drawImage(img, 0, 0, null);
    }
}
