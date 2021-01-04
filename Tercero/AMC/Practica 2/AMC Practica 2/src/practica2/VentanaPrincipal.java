/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica2;

import java.awt.Color;
import java.awt.FileDialog;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Shape;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter.DefaultHighlightPainter;
import javax.swing.text.Document;
import javax.swing.text.Highlighter;
import javax.swing.text.Highlighter.HighlightPainter;
import javax.swing.text.JTextComponent;
import javax.swing.text.PlainDocument;
import static practica2.AutomataDrawer.*;

/**
 *
 * @author borja
 */
public class VentanaPrincipal extends javax.swing.JFrame {

    /**
     * Creates new form VentanaPrincipal
     */
    public VentanaPrincipal() {
        initComponents();
        setLocationRelativeTo(null);
        setTitle("AMC Practica 2: Miguel y Borja");

        textPane.setFont(new Font("Courier New", Font.PLAIN, 12));
        textPane.getDocument().putProperty(PlainDocument.tabSizeAttribute, 4);
        textPane.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                aplicarEstilo();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                aplicarEstilo();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                aplicarEstilo();
            }
        });
    }

    private static final HighlightPainter palabraClave = new HighlightPainter() {
        @Override
        public void paint(Graphics g, int p0, int p1, Shape bounds, JTextComponent c) {
            Rectangle r0 = null, r1 = null, rbounds = bounds.getBounds();
            try { // convert positions to pixel coordinates
                r0 = (Rectangle) c.modelToView2D(p0);
                r1 = (Rectangle) c.modelToView2D(p1);
            } catch (BadLocationException ex) {
                return;
            }
            g.setColor(Color.darkGray);
            g.fillRect(r0.x, r0.y + r0.height - 3, r1.x - r0.x, 2);
        }
    };

    private void aplicarEstilo() {
        try {
            Highlighter hilite = textPane.getHighlighter();
            Highlighter.Highlight[] hilites = hilite.getHighlights();

            for (int i = 0; i < hilites.length; i++) {
                if (hilites[i].getPainter() instanceof HighlightPainter) {
                    hilite.removeHighlight(hilites[i]);
                }
            }
            highlightKeyword("ESTADOS:");
            highlightKeyword("INICIAL:");
            highlightKeyword("FINALES:");
            highlightKeyword("TRANSICIONES:");
            highlightKeyword("TRANSICIONES LAMBDA:");
            highlightKeyword("FIN");
            highlightString("'");
            highlightComentario("#");
        } catch (Exception ex) {
            Logger.getLogger(VentanaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void highlightKeyword(String pattern) throws Exception {
        Highlighter hilite = textPane.getHighlighter();
        Document doc = textPane.getDocument();
        String text = doc.getText(0, doc.getLength());
        int pos = 0;

        while ((pos = text.indexOf(pattern, pos)) >= 0) {
            hilite.addHighlight(pos, pos + pattern.length(), palabraClave);
            pos += pattern.length();
        }
    }

    private final static DefaultHighlightPainter stringEntrecomillado = new DefaultHighlightPainter(Color.pink);

    public void highlightString(String pattern) throws Exception {
        Highlighter hilite = textPane.getHighlighter();
        Document doc = textPane.getDocument();
        String text = doc.getText(0, doc.getLength());
        int pos = 0;

        while ((pos = text.indexOf(pattern, pos)) >= 0) {
            int pos1 = pos;
            pos += pattern.length();
            pos = text.indexOf(pattern, pos);
            if (pos > 0) {
                hilite.addHighlight(pos1, pos + pattern.length(), stringEntrecomillado);
            } else {
                pos = pos1;
            }
            pos += pattern.length();
        }
    }

    private final static DefaultHighlightPainter comentarioVerde = new DefaultHighlightPainter(Color.green);

    public void highlightComentario(String pattern) throws Exception {
        Highlighter hilite = textPane.getHighlighter();
        Document doc = textPane.getDocument();
        String text = doc.getText(0, doc.getLength());
        int pos = 0;

        while ((pos = text.indexOf(pattern, pos)) >= 0) {
            int pos1 = pos;
            pos += pattern.length();
            pos = text.indexOf("\n", pos);
            if (pos < 0) {
                pos = text.length();
            }
            hilite.addHighlight(pos1, pos, comentarioVerde);
            pos += pattern.length();
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        GrupotipoAutomata = new javax.swing.ButtonGroup();
        botonCargar = new javax.swing.JButton();
        botonGuardar = new javax.swing.JButton();
        botonLimpiar = new javax.swing.JButton();
        textEstado = new javax.swing.JTextField();
        labelEstado = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        textCadena = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        textSimbolo = new javax.swing.JTextField();
        botonPaso = new javax.swing.JButton();
        botonEjecutar = new javax.swing.JButton();
        botonReiniciar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        textPane = new javax.swing.JTextPane();
        botonAFD = new javax.swing.JRadioButton();
        botonAFND = new javax.swing.JRadioButton();
        jLabel5 = new javax.swing.JLabel();
        labelReconocido = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setIconImages(null);

        botonCargar.setText("Cargar archivo");
        botonCargar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonCargarActionPerformed(evt);
            }
        });

        botonGuardar.setText("Guardar archivo");
        botonGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonGuardarActionPerformed(evt);
            }
        });

        botonLimpiar.setText("Limpiar");
        botonLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonLimpiarActionPerformed(evt);
            }
        });

        textEstado.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        labelEstado.setText("Estado");

        jLabel3.setText("Cadena");

        textCadena.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        jLabel4.setText("Simbolo");

        textSimbolo.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        botonPaso.setText("Paso");
        botonPaso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonPasoActionPerformed(evt);
            }
        });

        botonEjecutar.setText("Ejecutar");
        botonEjecutar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonEjecutarActionPerformed(evt);
            }
        });

        botonReiniciar.setText("Reiniciar");
        botonReiniciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonReiniciarActionPerformed(evt);
            }
        });

        jScrollPane2.setViewportView(textPane);

        GrupotipoAutomata.add(botonAFD);
        botonAFD.setSelected(true);
        botonAFD.setText("AFD");

        GrupotipoAutomata.add(botonAFND);
        botonAFND.setText("AFND");

        jLabel5.setText("Tipo de automata");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(botonCargar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(botonGuardar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(botonLimpiar))
                            .addComponent(textEstado)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addGap(0, 230, Short.MAX_VALUE))
                                    .addComponent(textCadena))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(textSimbolo, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(labelEstado)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(botonAFD)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(botonAFND)))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(botonEjecutar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(botonPaso)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(botonReiniciar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(labelReconocido)))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botonCargar)
                    .addComponent(botonGuardar)
                    .addComponent(botonLimpiar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 272, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botonAFD)
                    .addComponent(botonAFND))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(labelEstado)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(textEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(textSimbolo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textCadena, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botonEjecutar)
                    .addComponent(botonPaso)
                    .addComponent(botonReiniciar)
                    .addComponent(labelReconocido))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botonCargarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonCargarActionPerformed
        FileDialog dialog = new FileDialog((Frame) null, "Selecciona un archivo para abrir");
        dialog.setMode(FileDialog.LOAD);
        dialog.setVisible(true);
        String file = dialog.getFile();
        if (file != null) {
            if (file.endsWith("afnd")) {
                botonAFND.setSelected(true);
            } else {
                botonAFD.setSelected(true);
            }
            try {
                textPane.setText(Files.readString(new File(dialog.getDirectory() + file).toPath()));
                reiniciar();
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage());
            }
        }
    }//GEN-LAST:event_botonCargarActionPerformed

    private void botonGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonGuardarActionPerformed
        FileDialog dialog = new FileDialog((Frame) null, "Guardar el archivo");
        dialog.setMode(FileDialog.SAVE);
        dialog.setVisible(true);
        String file = dialog.getFile();
        if (file != null) {
            if (botonAFND.isSelected()) {
                if (!file.endsWith(".afnd")) {
                    file += ".afnd";
                }
            } else {
                if (!file.endsWith(".afd")) {
                    file += ".afd";
                }
            }
            try {
                Files.writeString(new File(dialog.getDirectory() + file).toPath(), textPane.getText());
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage());
            }
        }
    }//GEN-LAST:event_botonGuardarActionPerformed

    private void botonLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonLimpiarActionPerformed
        if (botonAFD.isSelected()) {
            textPane.setText("#Automata Finito Determinista\n"
                    + "ESTADOS: \n"
                    + "INICIAL: \n"
                    + "FINALES: \n"
                    + "TRANSICIONES: \n"
                    + "\t\n"
                    + "FIN\n"
                    + "");
        } else {
            textPane.setText("#Automata Finito No Determinista\n"
                    + "ESTADOS: \n"
                    + "INICIAL: \n"
                    + "FINALES: \n"
                    + "TRANSICIONES: \n"
                    + "\t\n"
                    + "FIN\n"
                    + "TRANSICIONES LAMBDA: \n"
                    + "\t\n"
                    + "FIN\n"
                    + "");
        }
        AutomataDrawer.hideGraph();
    }//GEN-LAST:event_botonLimpiarActionPerformed

    private void botonEjecutarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonEjecutarActionPerformed
        try {
            iniciar();
            while (!nuevoMacroestado.isEmpty() || !textCadena.getText().trim().isEmpty()) {
                paso();
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
            reiniciar();
        }
    }//GEN-LAST:event_botonEjecutarActionPerformed

    private void botonPasoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonPasoActionPerformed
        try {
            paso();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
            ex.printStackTrace();
            reiniciar();
        }
    }//GEN-LAST:event_botonPasoActionPerformed

    private void botonReiniciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonReiniciarActionPerformed
        reiniciar();
        hideGraph();
    }//GEN-LAST:event_botonReiniciarActionPerformed

    private String estado;
    private Set<String> macroestado;
    private AutomataDeterminista afd;
    private AutomataNoDeterminista afnd;
    private boolean afndPaso, afndTerminado;
    private List<String[]> nuevoMacroestado;
    private Set<String> antiguosEstados;

    private void iniciar() throws IOException, Exception {
        if (botonAFD.isSelected()) {
            afd = Parser.parseTextAFD(textPane.getText());
            afd.validar();
            estado = afd.getEstadoInicial();
            textEstado.setText(estado);
            afnd = null;
            labelEstado.setText("Estado");
            setGraph(createGraphAFD(afd, null, estado));
        } else {
            afnd = Parser.parseTextAFND(textPane.getText());
            afnd.validar();
            macroestado = afnd.getEstadosIniciales();
            textEstado.setText(macroestado.toString());
            afd = null;
            labelEstado.setText("Macroestado");
            afndPaso = false;
            afndTerminado = false;
            setGraph(createGraphAFND(afnd, null, macroestado, null));
        }
        nuevoMacroestado = new ArrayList<>();
        showGraph();
        updateReconocido();
    }

    private void paso() throws Exception {
        if ((botonAFD.isSelected() && afd == null) || (!botonAFD.isSelected() && afnd == null)) {
            iniciar();
        } else {
            String texto = textCadena.getText().trim();
            if (!nuevoMacroestado.isEmpty()) {
                String[] siguienteEstado = nuevoMacroestado.iterator().next();
                nuevoMacroestado.remove(siguienteEstado);
                if (siguienteEstado.length == 2) {
                    macroestado.add(siguienteEstado[1]);
                    setGraph(createGraphAFND(afnd, new String[]{siguienteEstado[0], null, siguienteEstado[1]}, macroestado, antiguosEstados));
                } else {
                    macroestado.add(siguienteEstado[2]);
                    setGraph(createGraphAFND(afnd, new String[]{siguienteEstado[0], siguienteEstado[1], siguienteEstado[2]}, macroestado, antiguosEstados));
                }
                boolean esta = false;
                int i = 0;
                while (i < nuevoMacroestado.size() && !esta) {
                    if (nuevoMacroestado.get(i)[0].equals(siguienteEstado[0])) {
                        esta = true;
                    } else {
                        i++;
                    }
                }
                if (!esta) {
                    antiguosEstados.remove(siguienteEstado[0]);
                }
                textEstado.setText(macroestado.toString());
                if (nuevoMacroestado.isEmpty()) {
                    afndPaso = !afndPaso;
                }

                if (textCadena.getText().isEmpty() && !afndTerminado) {
                    afndTerminado = true;
                    //La última lambdaclausura
                    for (String subestado : macroestado) {
                        lambdaClausura(subestado, nuevoMacroestado);
                    }
                }
            } else if (!texto.isEmpty()) {
                char[] simbolos = textCadena.getText().trim().toCharArray();
                char simbolo = simbolos[0];
                textSimbolo.setText(Character.toString(simbolo));
                boolean consumir = true;
                if (botonAFD.isSelected()) {
                    if (!afd.getSimbolos().contains(simbolo)) {
                        throw new Exception("Simbolo en cadena no reconocido");
                    }
                    String preEstado = estado;
                    String condicion = AutomataDeterminista.formarCondicion(estado, simbolo);
                    estado = afd.getTransiciones().get(condicion);
                    if (estado == null) {
                        throw new Exception("Transicion no incluida en la lista de transiciones");
                    }
                    setGraph(createGraphAFD(afd, new String[]{preEstado, Character.toString(simbolo), estado}, estado));
                    textEstado.setText(estado);
                } else {
                    if (afndPaso) {
                        for (String estado : macroestado) {
                            String[] siguientes = afnd.getTransiciones().get(AutomataDeterminista.formarCondicion(estado, simbolo));
                            if (siguientes != null) {
                                for (String siguiente : siguientes) {
                                    nuevoMacroestado.add(new String[]{estado, Character.toString(simbolo), siguiente});
                                }
                            }
                        }
                        antiguosEstados = new HashSet<>(macroestado);
                        macroestado.clear();
                        if (nuevoMacroestado.isEmpty()) {
                            throw new Exception("El macroestado se ha quedado vacio");
                        }
                        if (nuevoMacroestado.isEmpty()) {
                            afndPaso = !afndPaso;
                        } else {
                            paso();
                        }
                    } else {
                        for (String subestado : macroestado) {
                            lambdaClausura(subestado, nuevoMacroestado);
                        }
                        antiguosEstados = new HashSet<>(macroestado);
                        consumir = false;
                        if (nuevoMacroestado.isEmpty()) {
                            afndPaso = !afndPaso;
                        } else {
                            paso();
                        }
                    }
                }
                if (consumir) {
                    textCadena.setText(textCadena.getText().substring(1));
                }
            } else {
                //No hay simbolos
                if (!botonAFD.isSelected()) {
                }

            }
            updateReconocido();
        }
    }

    public void lambdaClausura(String estado, List<String[]> nuevos) {
        String[] resultados = afnd.getTransiciones().get(estado);
        if (resultados != null) {
            for (String resultado : resultados) {
                boolean dentro = false;
                int i = 0;
                while (i < nuevos.size() && !dentro) {
                    if (nuevos.get(i)[0].equals(estado) && nuevos.get(i)[1].equals(resultado)) {
                        dentro = true;
                    } else {
                        i++;
                    }
                }
                if (!dentro) {
                    nuevos.add(new String[]{estado, resultado});
                    lambdaClausura(resultado, nuevos);
                }
            }
        }
    }

    private void updateReconocido() {
        boolean reconocido;
        if (botonAFD.isSelected()) {
            reconocido = afd.getEstadosFinales().contains(estado);
        } else {
            //reconocido = afnd.getEstadosFinales().containsAll(macroestado);
            HashSet<String> tmp = new HashSet<>();
            tmp.addAll(macroestado);
            tmp.retainAll(afnd.getEstadosFinales());
            reconocido = !tmp.isEmpty();
        }
        if (reconocido) {
            labelReconocido.setText("Reconocido");
            labelReconocido.setForeground(Color.BLUE);
        } else {
            labelReconocido.setText("No reconocido");
            labelReconocido.setForeground(Color.red);
        }
    }

    private void reiniciar() {
        afd = null;
        afnd = null;
        textCadena.setText("");
        textEstado.setText("");
        textSimbolo.setText("");
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup GrupotipoAutomata;
    private javax.swing.JRadioButton botonAFD;
    private javax.swing.JRadioButton botonAFND;
    private javax.swing.JButton botonCargar;
    private javax.swing.JButton botonEjecutar;
    private javax.swing.JButton botonGuardar;
    private javax.swing.JButton botonLimpiar;
    private javax.swing.JButton botonPaso;
    private javax.swing.JButton botonReiniciar;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel labelEstado;
    private javax.swing.JLabel labelReconocido;
    private javax.swing.JTextField textCadena;
    private javax.swing.JTextField textEstado;
    private javax.swing.JTextPane textPane;
    private javax.swing.JTextField textSimbolo;
    // End of variables declaration//GEN-END:variables
}
