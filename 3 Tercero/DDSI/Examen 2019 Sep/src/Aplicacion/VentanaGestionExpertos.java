/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aplicacion;

import Persistencia.ConexionOracle;
import Persistencia.Experto;
import Persistencia.ManejaExperto;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author borja
 */
public class VentanaGestionExpertos extends java.awt.Frame {

    private final ConexionOracle conexion;
    private final DefaultTableModel mExpertos;

    /**
     * Creates new form ventanaExpetos
     *
     * @param conexion
     */
    public VentanaGestionExpertos(ConexionOracle conexion) {
        initComponents();
        setLocationRelativeTo(getParent());
        setTitle("Gestión de Expertos");
        setResizable(false);

        this.conexion = conexion;

        mExpertos = new DefaultTableModel();
        jTableExpertos.setModel(mExpertos);
        String[] nombreColumnas = {"Código", "Nombre", "Pais", "Sexo", "Especialidad"};
        mExpertos.setColumnIdentifiers(nombreColumnas);
        jTableExpertos.getTableHeader().setResizingAllowed(false);
        jTableExpertos.getColumnModel().getColumn(0).setPreferredWidth(50);
        jTableExpertos.getColumnModel().getColumn(1).setPreferredWidth(200);
        jTableExpertos.getColumnModel().getColumn(2).setPreferredWidth(150);
        jTableExpertos.getColumnModel().getColumn(3).setPreferredWidth(50);
        jTableExpertos.getColumnModel().getColumn(4).setPreferredWidth(200);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTableExpertos = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jTextFieldPais = new javax.swing.JTextField();
        jButtonFiltrar = new javax.swing.JButton();
        jButtonTodos = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jTextFieldSexo = new javax.swing.JTextField();
        jButtonContar = new javax.swing.JButton();
        jLabelContado = new javax.swing.JLabel();
        btnFiltro = new javax.swing.JButton();

        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                exitForm(evt);
            }
        });

        jTableExpertos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTableExpertos);

        jLabel1.setText("País:");

        jButtonFiltrar.setText("Filtrar por país");
        jButtonFiltrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonFiltrarActionPerformed(evt);
            }
        });

        jButtonTodos.setText("Listar todos");
        jButtonTodos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonTodosActionPerformed(evt);
            }
        });

        jLabel2.setText("Sexo:");

        jButtonContar.setText("Contar");
        jButtonContar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonContarActionPerformed(evt);
            }
        });

        btnFiltro.setText("Ordenar por sexo");
        btnFiltro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFiltroActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 680, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldSexo, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButtonContar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelContado))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldPais, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonFiltrar)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonTodos)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnFiltro)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextFieldPais, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonFiltrar)
                    .addComponent(jButtonTodos)
                    .addComponent(btnFiltro))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextFieldSexo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonContar)
                    .addComponent(jLabelContado))
                .addContainerGap())
        );

        jButtonFiltrar.getAccessibleContext().setAccessibleName("Filtrar por pais");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Exit the Application
     */
    private void exitForm(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_exitForm
        dispose();
    }//GEN-LAST:event_exitForm

    /**
     * Muestra en la tabla los expertos del pais introducido
     *
     * @param evt
     */
    private void jButtonFiltrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonFiltrarActionPerformed
        //Filtrar por pais
        limpiarTabla();
        ManejaExperto me = new ManejaExperto(conexion);
        try {
            ArrayList<Experto> expertos = me.listaExpertosPorPais(jTextFieldPais.getText());
            for (Experto experto : expertos) {
                mExpertos.addRow(new String[]{experto.getCodExperto(), experto.getNombre(), experto.getPais(), experto.getSexo(), experto.getEspecialidad()});
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error al listar expertos", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButtonFiltrarActionPerformed

    /**
     * Muestra en la tabla todos los expertos sin filtrar
     *
     * @param evt
     */
    private void jButtonTodosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonTodosActionPerformed
        //Sin filtrado
        limpiarTabla();
        ManejaExperto me = new ManejaExperto(conexion);
        try {
            ArrayList<Experto> expertos = me.listaExpertos();
            for (Experto experto : expertos) {
                mExpertos.addRow(new String[]{experto.getCodExperto(), experto.getNombre(), experto.getPais(), experto.getSexo(), experto.getEspecialidad()});
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error al listar expertos", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButtonTodosActionPerformed

    /**
     * Muestra cuentos especialistas hay del sexo introducido
     *
     * @param evt
     */
    private void jButtonContarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonContarActionPerformed
        ManejaExperto me = new ManejaExperto(conexion);
        try {
            String sexo = jTextFieldSexo.getText();
            jLabelContado.setText("Hay " + me.sexoExperto(sexo) + " " + (sexo.equals("F") ? "mujeres" : "hombres") + " en la base de datos");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error al contar expertos", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButtonContarActionPerformed

    private void btnFiltroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFiltroActionPerformed
        //Sin filtrado
        limpiarTabla();
        ManejaExperto me = new ManejaExperto(conexion);
        try {
            ArrayList<Experto> expertos = me.listaExpertosPorSexo();
            for (Experto experto : expertos) {
                mExpertos.addRow(new String[]{experto.getCodExperto(), experto.getNombre(), experto.getPais(), experto.getSexo(), experto.getEspecialidad()});
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error al listar expertos", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnFiltroActionPerformed

    /**
     * Vacía la tabla de expertos
     */
    private void limpiarTabla() {
        while (mExpertos.getRowCount() > 0) {
            mExpertos.removeRow(0);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnFiltro;
    private javax.swing.JButton jButtonContar;
    private javax.swing.JButton jButtonFiltrar;
    private javax.swing.JButton jButtonTodos;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabelContado;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableExpertos;
    private javax.swing.JTextField jTextFieldPais;
    private javax.swing.JTextField jTextFieldSexo;
    // End of variables declaration//GEN-END:variables
}
