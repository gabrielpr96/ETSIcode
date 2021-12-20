/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.b0ve.autosig.gui;

import com.b0ve.autosig.AutoExceptionHandle;
import com.b0ve.autosig.AutoProcess;
import static com.b0ve.autosig.PrettyPrinting.prettyPrintMessage;
import com.b0ve.sig.connectors.Connector;
import com.b0ve.sig.connectors.test.StubInput;
import com.b0ve.sig.connectors.test.StubOutput;
import com.b0ve.sig.flow.Slot;
import com.b0ve.sig.flow.Message;
import com.b0ve.sig.ports.Port;
import com.b0ve.sig.tasks.Task;
import com.b0ve.sig.tasks.TaskFlowInterrupter;
import com.b0ve.sig.utils.exceptions.SIGException;
import com.b0ve.sig.utils.exceptions.LogSink;
import java.awt.event.MouseEvent;
import java.io.File;
import java.nio.file.Path;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JList;

/**
 *
 * @author borja
 */
public class MainWindow extends javax.swing.JFrame {

    private AutoProcess p;
    private final AutoExceptionHandle exceptionHandle;
    private final LogSink logSink;

    public MainWindow() {
        initComponents();
        exceptionHandle = new AutoExceptionHandle(this);
        logSink = logListModel::addElement;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tabbedPanelOutput = new javax.swing.JTabbedPane();
        panelOutputExceptions = new javax.swing.JPanel();
        exceptionListScroll = new javax.swing.JScrollPane();
        exceptionList = new javax.swing.JList<>();
        panelOutputLog = new javax.swing.JPanel();
        logListScroll = new javax.swing.JScrollPane();
        logList = new javax.swing.JList<>();
        panelMessage = new javax.swing.JPanel();
        messageTextScroll = new javax.swing.JScrollPane();
        messageText = new javax.swing.JTextArea();
        jPanel1 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        statusLabel = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        nHilosLabel = new javax.swing.JLabel();
        actStatusBtn = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        nMensajesLabel = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        taskList = new javax.swing.JList<>();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        adapterList = new javax.swing.JList<>();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        portList = new javax.swing.JList<>();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        bufferList = new javax.swing.JList<>();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        messageList = new javax.swing.JList<>();
        actBtn = new javax.swing.JButton();
        menuBar = new javax.swing.JMenuBar();
        menuFile = new javax.swing.JMenu();
        menuItemLoad = new javax.swing.JMenuItem();
        menuItemExit = new javax.swing.JMenuItem();
        menuExecute = new javax.swing.JMenu();
        menuItemStart = new javax.swing.JMenuItem();
        menuItemStop = new javax.swing.JMenuItem();
        menuItemValidate = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("AutoSIG");

        exceptionListModel = new DefaultListModel();
        exceptionList.setModel(exceptionListModel);
        exceptionList.setForeground(new java.awt.Color(255, 0, 51));
        exceptionList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                exceptionListMouseClicked(evt);
            }
        });
        exceptionListScroll.setViewportView(exceptionList);

        javax.swing.GroupLayout panelOutputExceptionsLayout = new javax.swing.GroupLayout(panelOutputExceptions);
        panelOutputExceptions.setLayout(panelOutputExceptionsLayout);
        panelOutputExceptionsLayout.setHorizontalGroup(
            panelOutputExceptionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(exceptionListScroll)
        );
        panelOutputExceptionsLayout.setVerticalGroup(
            panelOutputExceptionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(exceptionListScroll, javax.swing.GroupLayout.DEFAULT_SIZE, 189, Short.MAX_VALUE)
        );

        tabbedPanelOutput.addTab("Excepciones", panelOutputExceptions);

        logListModel = new DefaultListModel();
        logList.setModel(logListModel);
        logList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                logListMouseClicked(evt);
            }
        });
        logListScroll.setViewportView(logList);

        javax.swing.GroupLayout panelOutputLogLayout = new javax.swing.GroupLayout(panelOutputLog);
        panelOutputLog.setLayout(panelOutputLogLayout);
        panelOutputLogLayout.setHorizontalGroup(
            panelOutputLogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(logListScroll, javax.swing.GroupLayout.DEFAULT_SIZE, 700, Short.MAX_VALUE)
        );
        panelOutputLogLayout.setVerticalGroup(
            panelOutputLogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(logListScroll, javax.swing.GroupLayout.DEFAULT_SIZE, 189, Short.MAX_VALUE)
        );

        tabbedPanelOutput.addTab("Debugging Log", panelOutputLog);

        messageText.setColumns(20);
        messageText.setRows(5);
        messageTextScroll.setViewportView(messageText);

        javax.swing.GroupLayout panelMessageLayout = new javax.swing.GroupLayout(panelMessage);
        panelMessage.setLayout(panelMessageLayout);
        panelMessageLayout.setHorizontalGroup(
            panelMessageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(messageTextScroll, javax.swing.GroupLayout.DEFAULT_SIZE, 700, Short.MAX_VALUE)
        );
        panelMessageLayout.setVerticalGroup(
            panelMessageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(messageTextScroll, javax.swing.GroupLayout.DEFAULT_SIZE, 189, Short.MAX_VALUE)
        );

        tabbedPanelOutput.addTab("Mensaje", panelMessage);

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel6.setText("Estado:");

        statusLabel.setText("No cargado");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel8.setText("Hilos:");

        nHilosLabel.setText("0");

        actStatusBtn.setText("Actualizar estadisticas");
        actStatusBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                actStatusBtnActionPerformed(evt);
            }
        });

        jLabel10.setText("Estadisticas");

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel11.setText("Mensajes en buffers: ");

        nMensajesLabel.setText("0");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 541, Short.MAX_VALUE)
                        .addComponent(actStatusBtn))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusLabel))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(nHilosLabel))
                            .addComponent(jLabel10)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(nMensajesLabel)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(statusLabel))
                .addGap(18, 18, 18)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(nHilosLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(nMensajesLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 58, Short.MAX_VALUE)
                .addComponent(actStatusBtn)
                .addContainerGap())
        );

        tabbedPanelOutput.addTab("Estado", jPanel1);

        jLabel1.setText("Tareas");

        taskListModel = new DefaultListModel();
        taskList.setModel(taskListModel);
        taskList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                taskListMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(taskList);

        jLabel2.setText("Adaptadores");

        adapterListModel = new DefaultListModel();
        adapterList.setModel(adapterListModel);
        adapterList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                adapterListMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(adapterList);

        jLabel3.setText("Puertos");

        portListModel = new DefaultListModel();
        portList.setModel(portListModel);
        portList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                portListMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(portList);

        jLabel4.setText("Buffers");

        bufferListModel = new DefaultListModel();
        bufferList.setModel(bufferListModel);
        bufferList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bufferListMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(bufferList);

        jLabel5.setText("Mensajes");

        messageListModel = new DefaultListModel();
        messageList.setModel(messageListModel);
        messageList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                messageListMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(messageList);

        actBtn.setText("Actualizar");
        actBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                actBtnActionPerformed(evt);
            }
        });

        menuFile.setText("Archivo");

        menuItemLoad.setText("Cargar");
        menuItemLoad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemLoadActionPerformed(evt);
            }
        });
        menuFile.add(menuItemLoad);

        menuItemExit.setText("Salir");
        menuItemExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemExitActionPerformed(evt);
            }
        });
        menuFile.add(menuItemExit);

        menuBar.add(menuFile);

        menuExecute.setText("Editar");

        menuItemStart.setText("Iniciar");
        menuItemStart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemStartActionPerformed(evt);
            }
        });
        menuExecute.add(menuItemStart);

        menuItemStop.setText("Detener");
        menuItemStop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemStopActionPerformed(evt);
            }
        });
        menuExecute.add(menuItemStop);

        menuItemValidate.setText("Validar");
        menuItemValidate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemValidateActionPerformed(evt);
            }
        });
        menuExecute.add(menuItemValidate);

        menuBar.add(menuExecute);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tabbedPanelOutput)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane4)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jScrollPane5)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(actBtn)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane4)
                            .addComponent(jScrollPane2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(actBtn)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel5)
                                .addComponent(jLabel3)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(tabbedPanelOutput)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void menuItemLoadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemLoadActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.showOpenDialog(this);
        File file = fileChooser.getSelectedFile();
        if (file != null) {
            load(file.toPath());
        }
    }//GEN-LAST:event_menuItemLoadActionPerformed

    private void menuItemStartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemStartActionPerformed
        if (p != null) {
            p.start();
            statusLabel.setText("Iniciado");
        }
    }//GEN-LAST:event_menuItemStartActionPerformed

    private void menuItemStopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemStopActionPerformed
        if (p != null) {
            new Thread(() -> {
                try {
                    p.stop();
                    statusLabel.setText("Detenido");
                } catch (InterruptedException ex) {
                } catch (SIGException ex) {
                    p.handleException(ex);
                }
            }).start();
        }
    }//GEN-LAST:event_menuItemStopActionPerformed

    private void menuItemValidateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemValidateActionPerformed
        if (p != null) {
            p.validate();
        }
    }//GEN-LAST:event_menuItemValidateActionPerformed

    /**
     * Doble click en una excepción abre la ventana de vista detallada
     *
     * @param evt
     */
    private void exceptionListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exceptionListMouseClicked
        if (evt.getClickCount() == 2) {
            ExceptionWindow window = new ExceptionWindow(exceptionList.getSelectedValue());
            window.setLocationRelativeTo(this);
            window.setVisible(true);
        }
    }//GEN-LAST:event_exceptionListMouseClicked

    /**
     * Al seleccionar una tarea, se muestran sus buffers
     *
     * @param evt
     */
    private void taskListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_taskListMouseClicked
        showBuffers(taskList, evt);
        if(evt.getClickCount() == 2){
            String taskName = (String) taskList.getModel().getElementAt(taskList.locationToIndex(evt.getPoint()));
            Task task = (Task) p.getByName(taskName);
            if(task instanceof TaskFlowInterrupter){
                new FlowInterrupterWindow((TaskFlowInterrupter) task).setVisible(true);
            }
        }
    }//GEN-LAST:event_taskListMouseClicked

    /**
     * Abrir la ventana con ajustes del adaptador si se puede modificar
     * manualmente
     *
     * @param evt
     */
    private void adapterListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_adapterListMouseClicked
        if (evt.getClickCount() == 2) {
            String name = adapterList.getModel().getElementAt(adapterList.locationToIndex(evt.getPoint()));
            Connector adapter = (Connector) p.getByName(name);
            JFrame window = null;
            if (adapter instanceof StubInput) {
                window = new InputStubWindow(p, (StubInput) adapter);
            } else if (adapter instanceof StubOutput) {
                window = new OutputStubWindow((StubOutput) adapter);
            }
            if (window != null) {
                window.setLocationRelativeTo(this);
                window.setVisible(true);
            }
        }
    }//GEN-LAST:event_adapterListMouseClicked

    /**
     * Mostrar los buffers del puerto
     *
     * @param evt
     */
    private void portListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_portListMouseClicked
        showBuffers(portList, evt);
    }//GEN-LAST:event_portListMouseClicked

    /**
     * Seleccionar el buffer.
     *
     * @param evt
     */
    private void bufferListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bufferListMouseClicked
        showBufferContent(bufferList.getModel().getElementAt(bufferList.locationToIndex(evt.getPoint())).getBuffer());
    }//GEN-LAST:event_bufferListMouseClicked

    /**
     * Actualizar los mensajes del buffer seleccionado
     *
     * @param evt
     */
    private void actBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_actBtnActionPerformed
        BufferListItem item = bufferList.getSelectedValue();
        if (item != null) {
            showBufferContent(item.getBuffer());
        }
    }//GEN-LAST:event_actBtnActionPerformed

    /**
     * Mostrar el mensaje en la pestaña del mensaje.
     *
     * @param evt
     */
    private void messageListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_messageListMouseClicked
        Message message = messageList.getModel().getElementAt(messageList.locationToIndex(evt.getPoint()));
        messageText.setText(prettyPrintMessage(message));
    }//GEN-LAST:event_messageListMouseClicked

    /**
     * Actualizar el numero de hilos y mensajes.
     *
     * @param evt
     */
    private void actStatusBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_actStatusBtnActionPerformed
        nHilosLabel.setText(Integer.toString(Thread.activeCount()));
        nMensajesLabel.setText(Integer.toString(p.messageCount()));
    }//GEN-LAST:event_actStatusBtnActionPerformed

    private void logListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logListMouseClicked
        String message = logList.getModel().getElementAt(logList.locationToIndex(evt.getPoint()));
        System.out.println(message);
    }//GEN-LAST:event_logListMouseClicked

    private void menuItemExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemExitActionPerformed
        System.exit(0);
    }//GEN-LAST:event_menuItemExitActionPerformed

    public void load(Path path) {
        p = new AutoProcess(exceptionHandle);
        try {
            p.build(path);
            p.setLogSink(logSink);

            taskListModel.clear();
            for (Iterator<Entry<String, Task>> iterator = p.getTasks(); iterator.hasNext();) {
                taskListModel.addElement(iterator.next().getKey());
            }
            adapterListModel.clear();
            for (Iterator<Entry<String, Connector>> iterator = p.getAdapters(); iterator.hasNext();) {
                adapterListModel.addElement(iterator.next().getKey());
            }
            portListModel.clear();
            for (Iterator<Entry<String, Port>> iterator = p.getPorts(); iterator.hasNext();) {
                portListModel.addElement(iterator.next().getKey());
            }

            setTitle("AutoSIG " + p.getName());
            statusLabel.setText("Cargado");
        } catch (SIGException ex) {
            exceptionHandle.handleException(ex);
            p = null;
        }
    }

    private void showBuffers(JList<String> list, MouseEvent evt) {
        if (p != null) {
            bufferListModel.clear();
            String taskName = (String) list.getModel().getElementAt(list.locationToIndex(evt.getPoint()));
            Task task = (Task) p.getByName(taskName);
            if (task != null) {
                for (ListIterator<Slot> iter = task.inputs(); iter.hasNext();) {
                    Slot input = iter.next();
                    String in = p.findName(input.getIn());
                    if (in != null) {
                        bufferListModel.addElement(new BufferListItem(input, in, taskName));
                    }
                }

                for (ListIterator<Slot> iter = task.outputs(); iter.hasNext();) {
                    Slot output = iter.next();
                    String out = p.findName(output.getOut());
                    if (out != null) {
                        bufferListModel.addElement(new BufferListItem(output, taskName, out));
                    }
                }
            }
        }
    }

    private void showBufferContent(Slot buffer) {
        try {
            buffer.lockPushes();
            messageListModel.clear();
            try {
                for (Iterator<Message> iter = buffer.getIterator(); iter.hasNext();) {
                    messageListModel.addElement(iter.next());
                }
            } catch (Exception ex) {
            } finally {
                buffer.unlockPushes();
            }
        } catch (InterruptedException ex) {
        }

    }
    //Acceso publico para el ExceptionHandler

    public void addException(SIGException ex) {
        System.out.println("Add exception");
        exceptionListModel.addElement(ex);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton actBtn;
    private javax.swing.JButton actStatusBtn;
    private javax.swing.JList<String> adapterList;
    private DefaultListModel adapterListModel;
    private javax.swing.JList<BufferListItem> bufferList;
    private DefaultListModel bufferListModel;
    private javax.swing.JList<SIGException> exceptionList;
    private DefaultListModel exceptionListModel;
    private javax.swing.JScrollPane exceptionListScroll;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JList<String> logList;
    private DefaultListModel logListModel;
    private javax.swing.JScrollPane logListScroll;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenu menuExecute;
    private javax.swing.JMenu menuFile;
    private javax.swing.JMenuItem menuItemExit;
    private javax.swing.JMenuItem menuItemLoad;
    private javax.swing.JMenuItem menuItemStart;
    private javax.swing.JMenuItem menuItemStop;
    private javax.swing.JMenuItem menuItemValidate;
    private javax.swing.JList<Message> messageList;
    private DefaultListModel messageListModel;
    private javax.swing.JTextArea messageText;
    private javax.swing.JScrollPane messageTextScroll;
    private javax.swing.JLabel nHilosLabel;
    private javax.swing.JLabel nMensajesLabel;
    private javax.swing.JPanel panelMessage;
    private javax.swing.JPanel panelOutputExceptions;
    private javax.swing.JPanel panelOutputLog;
    private javax.swing.JList<String> portList;
    private DefaultListModel portListModel;
    private javax.swing.JLabel statusLabel;
    private javax.swing.JTabbedPane tabbedPanelOutput;
    private javax.swing.JList<String> taskList;
    private DefaultListModel taskListModel;
    // End of variables declaration//GEN-END:variables
}

class BufferListItem {

    private final Slot buffer;
    private final String name;

    public BufferListItem(Slot buffer, String task1, String task2) {
        this.buffer = buffer;
        this.name = task1 + " -> " + task2;
    }

    public Slot getBuffer() {
        return buffer;
    }

    @Override
    public String toString() {
        return name;
    }

}
