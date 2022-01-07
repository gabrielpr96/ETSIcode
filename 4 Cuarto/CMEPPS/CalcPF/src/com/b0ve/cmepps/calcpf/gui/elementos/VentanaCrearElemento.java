package com.b0ve.cmepps.calcpf.gui;

import com.b0ve.cmepps.calcpf.gui.models.ElementosTableModel;
import com.b0ve.cmepps.calcpf.enums.TipoElemento;
import com.b0ve.cmepps.calcpf.modelo.elementos.ElementoFuncional;
import com.b0ve.cmepps.calcpf.modelo.elementos.tipos.ConsultaExterna;
import com.b0ve.cmepps.calcpf.modelo.elementos.tipos.EntradaExterna;
import com.b0ve.cmepps.calcpf.modelo.elementos.tipos.FicheroLogicoExterno;
import com.b0ve.cmepps.calcpf.modelo.elementos.tipos.FicheroLogicoInterno;
import com.b0ve.cmepps.calcpf.modelo.elementos.tipos.SalidaExterna;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.Arrays;
import java.util.stream.Collectors;

public class VentanaCrearElemento extends javax.swing.JFrame {

    private final ElementosTableModel modelo;

    public VentanaCrearElemento(ElementosTableModel modelo) {
        this.modelo = modelo;
        initComponents();
        actualizarTipo();
        actualizarComplejidad();
        comboTipo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarComplejidad();
            }
        });
        FocusListener focusListener = new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                actualizarComplejidad();
            }

            @Override
            public void focusLost(FocusEvent e) {
                actualizarComplejidad();
            }
        };
        areaElementales1.addFocusListener(focusListener);
        areaReferencias1.addFocusListener(focusListener);
        areaElementales2.addFocusListener(focusListener);
        areaReferencias2.addFocusListener(focusListener);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnCrear = new javax.swing.JButton();
        comboTipo = new javax.swing.JComboBox<>();
        textNombre = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        labelReferencias1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        areaReferencias1 = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        areaElementales1 = new javax.swing.JTextArea();
        labelElementales1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        labelReferencias2 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        areaReferencias2 = new javax.swing.JTextArea();
        jScrollPane4 = new javax.swing.JScrollPane();
        areaElementales2 = new javax.swing.JTextArea();
        labelElementales2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        labelComplejidad = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Crear elemento");
        setAlwaysOnTop(true);
        setResizable(false);

        btnCrear.setText("Crear");
        btnCrear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrearActionPerformed(evt);
            }
        });

        comboTipo.setModel(new javax.swing.DefaultComboBoxModel<>(Arrays.stream(TipoElemento.values()).map(TipoElemento::getNombreCorto).collect(Collectors.toList()).toArray(new String[0])));
        comboTipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboTipoActionPerformed(evt);
            }
        });

        jLabel1.setText("Nombre");

        jLabel2.setText("Tipo");

        jPanel1.setBackground(new java.awt.Color(249, 249, 249));

        labelReferencias1.setText("Referencias");

        areaReferencias1.setColumns(20);
        areaReferencias1.setRows(5);
        jScrollPane1.setViewportView(areaReferencias1);

        areaElementales1.setColumns(20);
        areaElementales1.setRows(5);
        jScrollPane2.setViewportView(areaElementales1);

        labelElementales1.setText("Datos elementales");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelReferencias1)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelElementales1)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(labelElementales1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(labelReferencias1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(33, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(249, 249, 249));

        labelReferencias2.setText("Referencias");

        areaReferencias2.setColumns(20);
        areaReferencias2.setRows(5);
        jScrollPane3.setViewportView(areaReferencias2);

        areaElementales2.setColumns(20);
        areaElementales2.setRows(5);
        jScrollPane4.setViewportView(areaElementales2);

        labelElementales2.setText("Datos elementales");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelReferencias2)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelElementales2)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(labelElementales2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(labelReferencias2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel3.setText("Cada uno en una linea");

        labelComplejidad.setText("Complegidad:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(textNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(comboTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(labelComplejidad)
                                .addGap(18, 18, 18)
                                .addComponent(btnCrear))
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCrear)
                    .addComponent(jLabel3)
                    .addComponent(labelComplejidad))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCrearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrearActionPerformed
        modelo.add(generarElemento());
        dispose();
    }//GEN-LAST:event_btnCrearActionPerformed

    private void comboTipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboTipoActionPerformed
        actualizarTipo();
    }//GEN-LAST:event_comboTipoActionPerformed

    private void actualizarTipo() {
        String tipo = (String) comboTipo.getSelectedItem();
        switch (tipo) {
            case "EE":
            case "SE":
                jPanel1.setVisible(true);
                jPanel2.setVisible(false);
                labelReferencias1.setText("Ficheros referenciados");
                labelElementales1.setText("Tipos de datos elementales");
                break;
            case "CE":
                jPanel1.setVisible(true);
                jPanel2.setVisible(true);
                labelReferencias1.setText("Ficheros referenciados Entrada");
                labelElementales1.setText("Tipos de datos elementales Entrada");
                labelReferencias2.setText("Ficheros referenciados Salida");
                labelElementales2.setText("Tipos de datos elementales Salida");
                break;
            case "FLI":
            case "FLE":
                jPanel1.setVisible(true);
                jPanel2.setVisible(false);
                labelReferencias1.setText("Tipos de registros");
                labelElementales1.setText("Tipos de datos elementales");
                break;
        }
    }

    private ElementoFuncional generarElemento(){
        final ElementoFuncional elemento;
        String tipo = (String) comboTipo.getSelectedItem();
        String nombre = textNombre.getText().trim();
        switch (tipo) {
            case "EE":
                elemento = new EntradaExterna(nombre);
                break;
            case "SE":
                elemento = new SalidaExterna(nombre);
                break;
            case "CE":
                elemento = new ConsultaExterna(nombre);
                break;
            case "FLI":
                elemento = new FicheroLogicoInterno(nombre);
                break;
            case "FLE":
                elemento = new FicheroLogicoExterno(nombre);
                break;
            default:
                throw new IllegalStateException("Tipo no reconocido");
        }
        switch (tipo) {
            case "EE":
            case "SE":
            case "FLI":
            case "FLE":
                Arrays.stream(areaReferencias1.getText().split("\\r?\\n")).map(String::trim).forEach(s -> elemento.addReferencia(null, s));
                Arrays.stream(areaElementales1.getText().split("\\r?\\n")).map(String::trim).forEach(s -> elemento.addDatoElemental(null, s));
                break;
            case "CE":
                Arrays.stream(areaReferencias1.getText().split("\\r?\\n")).map(String::trim).forEach(s -> elemento.addReferencia("Entrada", s));
                Arrays.stream(areaElementales1.getText().split("\\r?\\n")).map(String::trim).forEach(s -> elemento.addDatoElemental("Entrada", s));
                Arrays.stream(areaReferencias2.getText().split("\\r?\\n")).map(String::trim).forEach(s -> elemento.addReferencia("Salida", s));
                Arrays.stream(areaElementales2.getText().split("\\r?\\n")).map(String::trim).forEach(s -> elemento.addDatoElemental("Salida", s));
                break;
        }
        return elemento;
    }
    
    private void actualizarComplejidad(){
        labelComplejidad.setText("Complejidad: "+generarElemento().getComplegidad().name());
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea areaElementales1;
    private javax.swing.JTextArea areaElementales2;
    private javax.swing.JTextArea areaReferencias1;
    private javax.swing.JTextArea areaReferencias2;
    private javax.swing.JButton btnCrear;
    private javax.swing.JComboBox<String> comboTipo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JLabel labelComplejidad;
    private javax.swing.JLabel labelElementales1;
    private javax.swing.JLabel labelElementales2;
    private javax.swing.JLabel labelReferencias1;
    private javax.swing.JLabel labelReferencias2;
    private javax.swing.JTextField textNombre;
    // End of variables declaration//GEN-END:variables
}
