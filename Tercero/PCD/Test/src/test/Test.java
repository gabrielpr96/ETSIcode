
/*
 * Fichero: EjemploJTextPane.java
 * Autor: Chuidiang
 * Fecha: 27/03/07 19:29
 */
package test;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.WindowConstants;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

/**
 * Ejemplo de un JTextPane
 *
 * @author Chuidiang
 *
 */
public class Test {

    /**
     * Crea un nuevo objeto EjemploJTextPane.
     */
    public Test() {
        try {
            // Creación de la ventana de ejemplo
            JFrame v = new JFrame("JTextPane");
            JTextPane editor = new JTextPane();
            JScrollPane scroll = new JScrollPane(editor);
            v.getContentPane().add(scroll);
            v.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

            // Atributos para cada uno de las frases.
            SimpleAttributeSet attrs = new SimpleAttributeSet();

            // En negrita
            StyleConstants.setBold(attrs, true);
            editor.getStyledDocument()
                    .insertString(
                            editor.getStyledDocument().getLength(), "Negrita", attrs);
            insertaNuevaLinea(editor);

            // En cursiva
            StyleConstants.setItalic(attrs, true);
            StyleConstants.setBold(attrs, false);
            editor.getStyledDocument()
                    .insertString(
                            editor.getStyledDocument().getLength(), "cursiva", attrs);
            insertaNuevaLinea(editor);

            // Ponemos el cursor al final del texto
            editor.setCaretPosition(editor.getStyledDocument().getLength());

            // Un icono
            ImageIcon icono = new ImageIcon("d:/viejo.gif");
            editor.insertIcon(icono);
            insertaNuevaLinea(editor);

            // Ponemos el cursor al final del texto
            editor.setCaretPosition(editor.getStyledDocument().getLength());

            // insertamos un JButton
            JButton boton = new JButton("Pulsame");
            editor.insertComponent(boton);

            // Visualizamos la ventana.
            v.pack();
            v.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * main de la clase
     *
     * @param args se ignoran
     */
    public static void main(String[] args) throws URISyntaxException, IOException {
        URL resource = Test.class.getResource("/res/data");
        File f = Paths.get(resource.toURI()).toFile();
        String t = Files.readAllLines(f.toPath()).toString();
        System.out.println(t);
        new Test();
    }

    /**
     * Inserta un fin de linea en el editor
     *
     * @param editor Editor en el que poner el fin de línea
     *
     * @throws BadLocationException Si se intenta insertar fuera del texto.
     */
    private void insertaNuevaLinea(JTextPane editor)
            throws BadLocationException {
        // Atributos null
        editor.getStyledDocument()
                .insertString(
                        editor.getStyledDocument().getLength(),
                        System.getProperty("line.separator"), null);
    }
}
