/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package patronadapterpractica.interfaces;

import javax.swing.JFrame;
import patronadapterpractica.AdaptadorBarco;
import patronadapterpractica.AdaptadorCocheManual;
import patronadapterpractica.Barco;
import patronadapterpractica.CocheAutomatico;
import patronadapterpractica.CocheManual;

public class MenuPrincipal extends javax.swing.JFrame {

    public MenuPrincipal() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnAutomatico = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        btnManual = new javax.swing.JButton();
        btnBarco = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        btnAdaptada = new javax.swing.JButton();
        comboClase = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Adapter Borja López");

        btnAutomatico.setText("Coche automatico");
        btnAutomatico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAutomaticoActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Conducción nativa");

        btnManual.setText("Coche manual");
        btnManual.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnManualActionPerformed(evt);
            }
        });

        btnBarco.setText("Barco");
        btnBarco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBarcoActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Conducción adaptada");

        btnAdaptada.setText("Adaptada");
        btnAdaptada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdaptadaActionPerformed(evt);
            }
        });

        comboClase.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Coche automatico", "Coche manual", "Barco" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1)
                    .addComponent(btnManual, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnAutomatico, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnBarco, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(56, 56, 56)
                        .addComponent(btnAdaptada))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(comboClase, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAutomatico)
                    .addComponent(comboClase, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAdaptada)
                    .addComponent(btnManual))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnBarco)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAutomaticoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAutomaticoActionPerformed
        navegar(new ConducirCocheAutomatico());
    }//GEN-LAST:event_btnAutomaticoActionPerformed

    private void btnManualActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnManualActionPerformed
        navegar(new ConducirCocheManual());
    }//GEN-LAST:event_btnManualActionPerformed

    private void btnBarcoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBarcoActionPerformed
        navegar(new ConducirBarco());
    }//GEN-LAST:event_btnBarcoActionPerformed

    private void btnAdaptadaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdaptadaActionPerformed
        Class tipo;
        String nombre = (String) comboClase.getSelectedItem();
        //, Coche manual, Barco
        if (comboClase.getSelectedItem().equals("Coche automatico")) {
            tipo = CocheAutomatico.class;
        } else if (comboClase.getSelectedItem().equals("Coche manual")) {
            tipo = AdaptadorCocheManual.class;
        } else {
            tipo = AdaptadorBarco.class;
        }
        navegar(new ConducirAdaptado(tipo, nombre));
    }//GEN-LAST:event_btnAdaptadaActionPerformed

    private void navegar(JFrame f) {
        f.pack();
        f.setLocationRelativeTo(this);
        f.setVisible(true);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdaptada;
    private javax.swing.JButton btnAutomatico;
    private javax.swing.JButton btnBarco;
    private javax.swing.JButton btnManual;
    private javax.swing.JComboBox<String> comboClase;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    // End of variables declaration//GEN-END:variables
}
