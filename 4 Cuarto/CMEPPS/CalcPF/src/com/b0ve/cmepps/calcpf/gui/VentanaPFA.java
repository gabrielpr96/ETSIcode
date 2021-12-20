package com.b0ve.cmepps.calcpf.gui;

import com.b0ve.cmepps.calcpf.modelo.Estimacion;

public class VentanaPFA extends javax.swing.JFrame {

    public VentanaPFA(Estimacion estimacion) {
        initComponents();
        labelPFNA.setText("PFNA = "+estimacion.getPFNA());
        labelFA.setText("FA = 0.65 + (0.01 * "+estimacion.getTablaInfluencias().getSVA()+") = "+estimacion.getTablaInfluencias().getFA());
        labelPFA.setText("PFA = "+estimacion.getPFNA()+" * "+estimacion.getTablaInfluencias().getFA()+" = "+estimacion.getPFA());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        labelFA = new javax.swing.JLabel();
        labelPFA = new javax.swing.JLabel();
        labelPFNA = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("PFA");
        setAlwaysOnTop(true);
        setResizable(false);

        labelFA.setText("FA");

        labelPFA.setText("PFA");

        labelPFNA.setText("PFNA");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelPFNA, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
                    .addComponent(labelFA, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelPFA, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelPFNA)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(labelFA)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(labelPFA)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel labelFA;
    private javax.swing.JLabel labelPFA;
    private javax.swing.JLabel labelPFNA;
    // End of variables declaration//GEN-END:variables
}
