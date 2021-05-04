package com.b0ve.patronobserverpractica.interfaz;

import com.b0ve.patronobserverpractica.Noticia;
import javax.swing.JLabel;

public class PantallaNoticia extends javax.swing.JFrame {

    public PantallaNoticia(Noticia noticia) {
        initComponents();
        labelTitulo.setText("<html>" + noticia.getTitulo() + "</html>");
        labelCuerpo.setText("<html>" + noticia.getCuerpo() + "</html>");
        labelCuerpo.setVerticalAlignment(JLabel.TOP);
        labelCuerpo.setVerticalTextPosition(JLabel.TOP);
        labelCuerpo.setAlignmentY(TOP_ALIGNMENT);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        labelTitulo = new javax.swing.JLabel();
        labelCuerpo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Nueva noticia");
        setAlwaysOnTop(true);

        labelTitulo.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        labelTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelTitulo.setText("jLabel1");

        labelCuerpo.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        labelCuerpo.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelCuerpo.setText("jLabel1");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(labelCuerpo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(labelCuerpo, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel labelCuerpo;
    private javax.swing.JLabel labelTitulo;
    // End of variables declaration//GEN-END:variables
}
