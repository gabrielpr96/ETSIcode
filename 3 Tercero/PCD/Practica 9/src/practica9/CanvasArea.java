package practica9;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.util.Arrays;

public class CanvasArea extends Canvas{
    private final Integer[] numeros;
    private int coloreado;
    private final Font  fNumero = new Font("Courier New", Font.BOLD, 20);
    
    public CanvasArea(){
        numeros = new Integer[10];
        reiniciar();
    }
    
    /**
     * Pone el valor actual de cada Calculador y borra el color.
     */
    public void reiniciar(){
        Arrays.fill(numeros, 0);
        coloreado = -1;
    }
    
    /**
     * Cambia el valor actual de un Calculador
     * @param id ID del Calculador
     * @param n Nuevo valor
     */
    public void actualizarNumero(int id, int n){
        numeros[id] = n;
        repaint();
    }
    
    /**
     * Colorea un valor actual, remplaza al ya existente
     * Si el valor no cincide con ningun valor actual, ninguno será pintado
     * @param n Valor actual del calculador que se quiere colorear
     */
    public void colorearNumero(int n){
        coloreado = Arrays.asList(numeros).indexOf(n);
        repaint();
    }
    
    @Override
    public void paint(Graphics og) {
        Image oi = createImage(getWidth(), getHeight());
        Graphics g = oi.getGraphics();

        g.setFont(fNumero);
        FontMetrics fm = g.getFontMetrics(fNumero);
        for (int i = 0; i < 10; i++) {
            g.setColor(coloreado == i?Color.red:Color.blue);
            String texto = Integer.toString(numeros[i]);
            g.drawString(texto, 50-(fm.stringWidth(texto)/2)+100*(i%5), i<5?80:160);
        }
        
        og.drawImage(oi, 0, 0, null);
    }
    
    @Override
    public void update(Graphics g) {
        paint(g);
    }
}
