package patronadapterpractica.interfaces;

import patronadapterpractica.CocheAutomatico;
import patronadapterpractica.CocheManual;
import patronadapterpractica.Estado;

public class ConducirCocheManual extends javax.swing.JFrame {

    private final CocheManual coche;

    public ConducirCocheManual() {
        coche = new CocheManual();
        initComponents();
        actualizarSalpicadero();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        labelVelocidad = new javax.swing.JLabel();
        labelRevoluciones = new javax.swing.JLabel();
        labelEncendido = new javax.swing.JLabel();
        labelAveria = new javax.swing.JLabel();
        labelMarcha = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        btnEncender = new javax.swing.JButton();
        btnApagar = new javax.swing.JButton();
        btnSubirMarcha = new javax.swing.JButton();
        btnBajarMarcha = new javax.swing.JButton();
        btnReparar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Coche manual");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        labelVelocidad.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        labelVelocidad.setText("Velocidad");

        labelRevoluciones.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        labelRevoluciones.setText("Revoluciones");

        labelEncendido.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        labelEncendido.setText("Encendido");

        labelAveria.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        labelAveria.setText("Averia");

        labelMarcha.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        labelMarcha.setText("Marcha");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelVelocidad, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelRevoluciones, javax.swing.GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE)
                    .addComponent(labelEncendido, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelAveria, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelMarcha, javax.swing.GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelVelocidad)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(labelRevoluciones)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(labelMarcha)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(labelEncendido)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(labelAveria)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Conducir un coche manual");

        jButton1.setText("Acelerar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Frenar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        btnEncender.setText("Encender");
        btnEncender.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEncenderActionPerformed(evt);
            }
        });

        btnApagar.setText("Apagar");
        btnApagar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnApagarActionPerformed(evt);
            }
        });

        btnSubirMarcha.setText("Subir marcha");
        btnSubirMarcha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSubirMarchaActionPerformed(evt);
            }
        });

        btnBajarMarcha.setText("Bajar marcha");
        btnBajarMarcha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBajarMarchaActionPerformed(evt);
            }
        });

        btnReparar.setText("Reparar");
        btnReparar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRepararActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnReparar))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btnEncender, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnApagar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnSubirMarcha)
                                    .addComponent(btnBajarMarcha))))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnReparar))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnEncender)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnApagar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2)
                        .addGap(18, 18, 18)
                        .addComponent(btnSubirMarcha)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnBajarMarcha)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        coche.Acelerar();
        actualizarSalpicadero();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        coche.Frenar();
        actualizarSalpicadero();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void btnEncenderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEncenderActionPerformed
        coche.Encender();
        actualizarSalpicadero();
    }//GEN-LAST:event_btnEncenderActionPerformed

    private void btnApagarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnApagarActionPerformed
        coche.Apagar();
        actualizarSalpicadero();
    }//GEN-LAST:event_btnApagarActionPerformed

    private void btnSubirMarchaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSubirMarchaActionPerformed
        coche.SubirMarcha();
        actualizarSalpicadero();
    }//GEN-LAST:event_btnSubirMarchaActionPerformed

    private void btnBajarMarchaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBajarMarchaActionPerformed
        coche.BajarMarcha();
        actualizarSalpicadero();
    }//GEN-LAST:event_btnBajarMarchaActionPerformed

    private void btnRepararActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRepararActionPerformed
        coche.Reparar();
        actualizarSalpicadero();
    }//GEN-LAST:event_btnRepararActionPerformed

    private void actualizarSalpicadero() {
        Estado estado = coche.getEstado();
        labelVelocidad.setText("<html><span style=\"font-weight:bold;\">Velocidad: </span><span>" + estado.getVelocidad() + " km/h</span></html>");
        labelRevoluciones.setText("<html><span style=\"font-weight:bold;\">Revoluciones: </span><span>" + estado.getRevoluciones() + " rpm</span></html>");
        labelMarcha.setText("<html><span style=\"font-weight:bold;\">Revoluciones: </span><span>" + coche.getMarcha() + "ª </span></html>");
        labelEncendido.setText("<html><span style=\"font-weight:bold;\">Motor: </span>" + (estado.isEncendido() ? "<span style=\"color:green;\">Encendido</span>" : "<span>Apagado</span>") + "</html>");
        labelAveria.setText("<html><span style=\"font-weight:bold;\">Estado: </span><span>" + (estado.isAveria() ? "<span style=\"color:red;\">Averiado</span>" : "<span style=\"color:green;\">Bueno</span>") + "</span></html>");
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnApagar;
    private javax.swing.JButton btnBajarMarcha;
    private javax.swing.JButton btnEncender;
    private javax.swing.JButton btnReparar;
    private javax.swing.JButton btnSubirMarcha;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel labelAveria;
    private javax.swing.JLabel labelEncendido;
    private javax.swing.JLabel labelMarcha;
    private javax.swing.JLabel labelRevoluciones;
    private javax.swing.JLabel labelVelocidad;
    // End of variables declaration//GEN-END:variables
}
