package com.b0ve.cmepps.calcpf.gui;

import com.b0ve.cmepps.calcpf.gui.models.ElementosTableModel;
import com.b0ve.cmepps.calcpf.helpers.PFHelper;
import com.b0ve.cmepps.calcpf.modelo.elementos.ElementoFuncional;
import com.b0ve.cmepps.calcpf.modelo.elementos.tipos.EntradaExterna;
import java.util.List;

public class VentanaListaElementos extends javax.swing.JFrame {

    private final List<ElementoFuncional> elementosFuncionales;
    
    public VentanaListaElementos(List<ElementoFuncional> elementosFuncionales) {
        this.elementosFuncionales = elementosFuncionales;
        initComponents();
        
        /*
        ElementosTableModel modelo = (ElementosTableModel) tabla.getModel();
        EntradaExterna ee = new EntradaExterna("Hola");
        ee.addDatoElemental(null, "Datazo");
        ee.addReferencia(null, "Referenciazo");
        ee.getReferencias().get(null);
        modelo.add(ee);
        */
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        ElementosTableModel modelo = new ElementosTableModel(elementosFuncionales);
        tabla = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Elementos funcionales");
        setAlwaysOnTop(true);
        setResizable(false);

        tabla.setModel(modelo);
        tabla.setToolTipText("");
        jScrollPane1.setViewportView(tabla);

        jButton1.setText("Nuevo elemento funcional");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Eliminar seleccionado");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 705, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton2)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 410, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        PFHelper.openFrame(new VentanaCrearElemento((ElementosTableModel) tabla.getModel()));
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        int selected = tabla.getSelectedRow();
        if(selected != -1){
            ((ElementosTableModel)tabla.getModel()).remove(selected);
        }
    }//GEN-LAST:event_jButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabla;
    // End of variables declaration//GEN-END:variables
}
