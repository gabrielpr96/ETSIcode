/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.b0ve.autosig.gui;

import com.b0ve.autosig.AutoSIG;
import static com.b0ve.autosig.AutoSIG.prettyPrintMessage;
import com.b0ve.sig.flow.Message;
import com.b0ve.sig.tasks.TaskFlowInterrupter;
import javax.swing.DefaultListModel;
import org.w3c.dom.Document;

/**
 *
 * @author borja
 */
public class FlowInterrupterWindow extends javax.swing.JFrame {

    private final TaskFlowInterrupter task;

    public FlowInterrupterWindow(TaskFlowInterrupter task) {
        this.task = task;
        initComponents();
        act();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        bodyTest = new javax.swing.JTextArea();
        actBtn = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        deleteBtn = new javax.swing.JButton();
        sendBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Input Stub");

        bodyTest.setColumns(20);
        bodyTest.setRows(5);
        jScrollPane1.setViewportView(bodyTest);

        actBtn.setText("Actualizar Mensaje");
        actBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                actBtnActionPerformed(evt);
            }
        });

        jLabel2.setText("Mensaje");

        deleteBtn.setForeground(new java.awt.Color(204, 0, 51));
        deleteBtn.setText("Eliminar");
        deleteBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteBtnActionPerformed(evt);
            }
        });

        sendBtn.setForeground(new java.awt.Color(0, 102, 0));
        sendBtn.setText("Reenviar");
        sendBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sendBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 490, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(deleteBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(sendBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(actBtn))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(0, 450, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 292, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(actBtn)
                    .addComponent(deleteBtn)
                    .addComponent(sendBtn))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void actBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_actBtnActionPerformed
        act();
    }//GEN-LAST:event_actBtnActionPerformed

    private void deleteBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteBtnActionPerformed
        task.drop();
        act();
    }//GEN-LAST:event_deleteBtnActionPerformed

    private void sendBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sendBtnActionPerformed
        task.forward();
        act();
    }//GEN-LAST:event_sendBtnActionPerformed

    private void act() {
        Message m = task.peek();
        if(m == null){
            bodyTest.setText("");
        }else{
            bodyTest.setText(prettyPrintMessage(m));
        }
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton actBtn;
    private javax.swing.JTextArea bodyTest;
    private javax.swing.JButton deleteBtn;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton sendBtn;
    // End of variables declaration//GEN-END:variables
}
