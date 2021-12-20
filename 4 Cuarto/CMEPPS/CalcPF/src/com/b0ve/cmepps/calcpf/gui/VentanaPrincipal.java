package com.b0ve.cmepps.calcpf.gui;

import com.b0ve.cmepps.calcpf.modelo.Actualizable;
import com.b0ve.cmepps.calcpf.helpers.PFHelper;
import static com.b0ve.cmepps.calcpf.helpers.PFHelper.openFrame;
import com.b0ve.cmepps.calcpf.modelo.Estimacion;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public final class VentanaPrincipal extends javax.swing.JFrame implements Actualizable {

    private Estimacion estimacion;

    public VentanaPrincipal() {
        estimacion = new Estimacion();
        estimacion.setObserver(this);
        initComponents();
        actualizar();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        textNombre = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        labelEstadistica = new javax.swing.JLabel();
        menubar = new javax.swing.JMenuBar();
        menuArchivo = new javax.swing.JMenu();
        menuItemAbrir = new javax.swing.JMenuItem();
        menuItemGuardar = new javax.swing.JMenuItem();
        menuItemCerrar = new javax.swing.JMenuItem();
        menuItemSalir = new javax.swing.JMenuItem();
        menuElementosFuncionales = new javax.swing.JMenu();
        menuItemListaElementos = new javax.swing.JMenuItem();
        menuItemPFNA = new javax.swing.JMenuItem();
        menuFactorCorreccion = new javax.swing.JMenu();
        menuItemCaracteristicas = new javax.swing.JMenuItem();
        menuItemPFA = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("CalcPF");
        setAlwaysOnTop(true);
        setResizable(false);

        jLabel1.setText("Nombre del proyecto");

        textNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textNombreActionPerformed(evt);
            }
        });

        jLabel2.setText("Estadistica:");

        jLabel5.setText("CalcPF CMEPPS UHU 2022");

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/b0ve/cmepps/calcpf/src/bg.jpg"))); // NOI18N

        labelEstadistica.setText("jLabel8");

        menuArchivo.setText("Archivo");

        menuItemAbrir.setText("Abrir");
        menuItemAbrir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemAbrirActionPerformed(evt);
            }
        });
        menuArchivo.add(menuItemAbrir);

        menuItemGuardar.setText("Guardar");
        menuItemGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemGuardarActionPerformed(evt);
            }
        });
        menuArchivo.add(menuItemGuardar);

        menuItemCerrar.setText("Cerrar");
        menuItemCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemCerrarActionPerformed(evt);
            }
        });
        menuArchivo.add(menuItemCerrar);

        menuItemSalir.setText("Salir");
        menuItemSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemSalirActionPerformed(evt);
            }
        });
        menuArchivo.add(menuItemSalir);

        menubar.add(menuArchivo);

        menuElementosFuncionales.setText("Elementos Funcionales");

        menuItemListaElementos.setText("Lista de elementos");
        menuItemListaElementos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemListaElementosActionPerformed(evt);
            }
        });
        menuElementosFuncionales.add(menuItemListaElementos);

        menuItemPFNA.setText("PFNA");
        menuItemPFNA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemPFNAActionPerformed(evt);
            }
        });
        menuElementosFuncionales.add(menuItemPFNA);

        menubar.add(menuElementosFuncionales);

        menuFactorCorreccion.setText("Factor de corrección");

        menuItemCaracteristicas.setText("Características");
        menuItemCaracteristicas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemCaracteristicasActionPerformed(evt);
            }
        });
        menuFactorCorreccion.add(menuItemCaracteristicas);

        menuItemPFA.setText("PFA");
        menuItemPFA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemPFAActionPerformed(evt);
            }
        });
        menuFactorCorreccion.add(menuItemPFA);

        menubar.add(menuFactorCorreccion);

        setJMenuBar(menubar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel5))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(textNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2))
                                .addGap(0, 1, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(labelEstadistica, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel7)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labelEstadistica, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jLabel7))
                .addGap(28, 28, 28)
                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void menuItemAbrirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemAbrirActionPerformed
        try {
            JFileChooser fileChooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter("CalcPF", "cfp");
            fileChooser.setFileFilter(filter);
            fileChooser.setDialogType(JFileChooser.OPEN_DIALOG);
            fileChooser.showOpenDialog(this);
            File file = fileChooser.getSelectedFile();
            if (file != null) {
                estimacion = PFHelper.loadEstimacion(file);
                estimacion.setObserver(this);
                textNombre.setText(estimacion.getNombre());
                actualizar();
            }
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(VentanaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_menuItemAbrirActionPerformed

    private void menuItemGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemGuardarActionPerformed
        try {
            JFileChooser fileChooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter("CalcPF", "cfp");
            fileChooser.setFileFilter(filter);
            fileChooser.setDialogType(JFileChooser.SAVE_DIALOG);
            fileChooser.showOpenDialog(this);
            File file = fileChooser.getSelectedFile();
            if (file != null) {
                if (!file.getName().endsWith("cfp")) {
                    file = new File(file.toString() + ".cfp");
                }
                PFHelper.saveEstimacion(estimacion, file);
            }
        } catch (IOException ex) {
            Logger.getLogger(VentanaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_menuItemGuardarActionPerformed

    private void menuItemCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemCerrarActionPerformed
        estimacion = new Estimacion();
        estimacion.setObserver(this);
        actualizar();
    }//GEN-LAST:event_menuItemCerrarActionPerformed

    private void menuItemSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemSalirActionPerformed
        System.exit(0);
    }//GEN-LAST:event_menuItemSalirActionPerformed

    private void menuItemListaElementosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemListaElementosActionPerformed
        openFrame(new VentanaListaElementos(estimacion.getElementosFuncionales()));
    }//GEN-LAST:event_menuItemListaElementosActionPerformed

    private void menuItemPFNAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemPFNAActionPerformed
        openFrame(new VentanaPFNA(estimacion.getElementosFuncionales()));
    }//GEN-LAST:event_menuItemPFNAActionPerformed

    private void menuItemCaracteristicasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemCaracteristicasActionPerformed
        openFrame(new VentanaCaracteristicas(estimacion.getTablaInfluencias()));
    }//GEN-LAST:event_menuItemCaracteristicasActionPerformed

    private void menuItemPFAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemPFAActionPerformed
        openFrame(new VentanaPFA(estimacion));
    }//GEN-LAST:event_menuItemPFAActionPerformed

    private void textNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textNombreActionPerformed
        estimacion.setNombre(textNombre.getText());
        actualizar();
    }//GEN-LAST:event_textNombreActionPerformed

    @Override
    public void actualizar() {
        labelEstadistica.setText(estimacion.toString());
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel labelEstadistica;
    private javax.swing.JMenu menuArchivo;
    private javax.swing.JMenu menuElementosFuncionales;
    private javax.swing.JMenu menuFactorCorreccion;
    private javax.swing.JMenuItem menuItemAbrir;
    private javax.swing.JMenuItem menuItemCaracteristicas;
    private javax.swing.JMenuItem menuItemCerrar;
    private javax.swing.JMenuItem menuItemGuardar;
    private javax.swing.JMenuItem menuItemListaElementos;
    private javax.swing.JMenuItem menuItemPFA;
    private javax.swing.JMenuItem menuItemPFNA;
    private javax.swing.JMenuItem menuItemSalir;
    private javax.swing.JMenuBar menubar;
    private javax.swing.JTextField textNombre;
    // End of variables declaration//GEN-END:variables
}
