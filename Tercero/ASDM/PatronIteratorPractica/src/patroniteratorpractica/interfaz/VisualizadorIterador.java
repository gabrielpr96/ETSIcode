package patroniteratorpractica.interfaz;

import patroniteratorpractica.IIterador;
import patroniteratorpractica.OperacionNoSoportada;

public class VisualizadorIterador extends javax.swing.JFrame {

    private final IIterador iterador;
    
    public VisualizadorIterador(IIterador iterador) {
        this.iterador = iterador;
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnSiguiente = new javax.swing.JButton();
        btnHaySiguiente = new javax.swing.JButton();
        btnAtenrior = new javax.swing.JButton();
        inputActual = new javax.swing.JTextField();
        labelEstado = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Visualizador iterador");

        btnSiguiente.setText("Siguiente");
        btnSiguiente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSiguienteActionPerformed(evt);
            }
        });

        btnHaySiguiente.setText("Hay siguiente");
        btnHaySiguiente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHaySiguienteActionPerformed(evt);
            }
        });

        btnAtenrior.setText("Anterior");
        btnAtenrior.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAtenriorActionPerformed(evt);
            }
        });

        labelEstado.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        labelEstado.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelEstado.setText("<html>Iterador iniciado</html>");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Elemento actual");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnHaySiguiente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnAtenrior, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnSiguiente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelEstado, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(inputActual, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnSiguiente)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnHaySiguiente)
                    .addComponent(inputActual, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAtenrior)
                    .addComponent(labelEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSiguienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSiguienteActionPerformed
        try {
            inputActual.setText(iterador.siguiente().toString());
            labelEstado.setText("<html><p style='color:green'>Siguiente pbtenido</p></html>");
        } catch (OperacionNoSoportada ex) {
            labelEstado.setText("<html><p style='color:red'>Error: "+ex.getMessage()+"</p></html>");
        }
    }//GEN-LAST:event_btnSiguienteActionPerformed

    private void btnAtenriorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtenriorActionPerformed
        try {
            inputActual.setText(iterador.anterior().toString());
            labelEstado.setText("<html><p style='color:green'>Anterior obtenido</p></html>");
        } catch (OperacionNoSoportada ex) {
            labelEstado.setText("<html><p style='color:red'>Error: "+ex.getMessage()+"</p></html>");
        }
    }//GEN-LAST:event_btnAtenriorActionPerformed

    private void btnHaySiguienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHaySiguienteActionPerformed
        if(iterador.haySiguiente()){
            labelEstado.setText("<html><p style='color:green'>Hay siguiente</p></html>");
        }else{
            labelEstado.setText("<html><p style='color:red'>No hay siguiente</p></html>");
        }
    }//GEN-LAST:event_btnHaySiguienteActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAtenrior;
    private javax.swing.JButton btnHaySiguiente;
    private javax.swing.JButton btnSiguiente;
    private javax.swing.JTextField inputActual;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel labelEstado;
    // End of variables declaration//GEN-END:variables
}
