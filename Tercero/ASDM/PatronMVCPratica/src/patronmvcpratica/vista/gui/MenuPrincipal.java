package patronmvcpratica.vista.gui;

import patronmvcpratica.vista.InterfazVista;

public class MenuPrincipal extends javax.swing.JFrame {

    private final InterfazGraficoConversor interfaz;

    public MenuPrincipal(InterfazGraficoConversor interfaz) {
        this.interfaz = interfaz;
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        listaOperaciones = new javax.swing.JList<>();
        jLabel1 = new javax.swing.JLabel();
        btnSeleccionar = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        btnGUI = new javax.swing.JButton();
        btnCLI = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("MVC Borja López");

        listaOperaciones.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        listaOperaciones.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Pesetas a Euros", "Euros a Pesetas", "Libras a Euros", "Euros a Libras", "Dolares a Euros", "Euros a Dolares" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(listaOperaciones);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Seleccione una operación");

        btnSeleccionar.setText("Seleccionar operacion");
        btnSeleccionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSeleccionarActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Cambiar interfaz");

        btnGUI.setText("GUI");
        btnGUI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGUIActionPerformed(evt);
            }
        });

        btnCLI.setText("CLI");
        btnCLI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCLIActionPerformed(evt);
            }
        });

        jButton1.setText("Salir");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane1)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                            .addComponent(btnSeleccionar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(36, 36, 36)
                                .addComponent(btnGUI)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnCLI))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(72, 72, 72)
                        .addComponent(jButton1)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSeleccionar)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGUI)
                    .addComponent(btnCLI))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSeleccionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSeleccionarActionPerformed
        switch (listaOperaciones.getSelectedValue().toLowerCase()) {
            case "pesetas a euros":
                interfaz.procesaOperacion(1, InterfazVista.PESETAS_A_EUROS);
                break;
            case "euros a pesetas":
                interfaz.procesaOperacion(1, InterfazVista.EUROS_A_PESETAS);
                break;
            case "libras a euros":
                interfaz.procesaOperacion(1, InterfazVista.LIBRAS_A_EUROS);
                break;
            case "euros a libras":
                interfaz.procesaOperacion(1, InterfazVista.EUROS_A_LIBRAS);
                break;
            case "dolares a euros":
                interfaz.procesaOperacion(1, InterfazVista.DOLARES_A_EUROS);
                break;
            case "euros a dolares":
                interfaz.procesaOperacion(1, InterfazVista.EUROS_A_DOLARES);
                break;
        }
    }//GEN-LAST:event_btnSeleccionarActionPerformed

    private void btnGUIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGUIActionPerformed
        interfaz.procesaOperacion(1, InterfazVista.CAMBIO_INTERFAZ_GUI);
    }//GEN-LAST:event_btnGUIActionPerformed

    private void btnCLIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCLIActionPerformed
        interfaz.procesaOperacion(1, InterfazVista.CAMBIO_INTERFAZ_CLI);
    }//GEN-LAST:event_btnCLIActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        interfaz.procesaOperacion(1, InterfazVista.SALIR);
    }//GEN-LAST:event_jButton1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCLI;
    private javax.swing.JButton btnGUI;
    private javax.swing.JButton btnSeleccionar;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JList<String> listaOperaciones;
    // End of variables declaration//GEN-END:variables
}
