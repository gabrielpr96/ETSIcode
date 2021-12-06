package juegoahorcado;

import java.awt.Color;
import javax.swing.JFrame;

public class JuegoAhorcado {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Juego Ahorcado");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        CanvasAhorcado canvas = new CanvasAhorcado();
        canvas.setBackground(Color.white);
        frame.add(canvas);
        canvas.setSize(720, 480);
        frame.pack();
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        
        ControladorJuego controlador = new ControladorJuego(canvas);
        canvas.addKeyListener(controlador);
        frame.addKeyListener(controlador);
    }
    
}
