package com.b0ve.cmepps.calcpf.gui;

import static com.b0ve.cmepps.calcpf.enums.Complejidad.*;
import com.b0ve.cmepps.calcpf.enums.TipoElemento;
import com.b0ve.cmepps.calcpf.modelo.Estimacion;
import com.b0ve.cmepps.calcpf.modelo.elementos.ElementoFuncional;
import java.util.List;
import javax.swing.table.DefaultTableModel;

public class VentanaPFNA extends javax.swing.JFrame {
    
    private final List<ElementoFuncional> elementos;
    
    public VentanaPFNA(List<ElementoFuncional> elementos) {
        this.elementos = elementos;
        initComponents();
        
        long pfna = 0;
        DefaultTableModel model = (DefaultTableModel) tabla.getModel();
        for (TipoElemento tipo : TipoElemento.values()) {
            long nSimple = elementos.stream().filter(e -> e.getTipo() == tipo).filter(e -> e.getComplegidad() == SIMPLE).count();
            long nMedia = elementos.stream().filter(e -> e.getTipo() == tipo).filter(e -> e.getComplegidad() == MEDIA).count();
            long nCompleja = elementos.stream().filter(e -> e.getTipo() == tipo).filter(e -> e.getComplegidad() == COMPLEJA).count();
            int pSimple = Estimacion.getPonderacion().get(tipo).get(SIMPLE);
            int pMedia = Estimacion.getPonderacion().get(tipo).get(SIMPLE);
            int pCompleja = Estimacion.getPonderacion().get(tipo).get(SIMPLE);
            long total = nSimple*pSimple + nMedia*pMedia + nCompleja*pCompleja;
            model.addRow(new String[]{"No. "+tipo.name(), nSimple+" x "+pSimple, nMedia+" x "+pMedia, nCompleja+" x "+pCompleja, Long.toString(total)});
            pfna += total;
        }
        model.addRow(new String[]{"", "", "", "", Long.toString(pfna)});
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("PFNA");
        setAlwaysOnTop(true);
        setResizable(false);

        tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
            },
            new String [] {
                "Descripción", "Sencilla", "Media", "Compleja", "Total PF"
            }
        ));
        jScrollPane1.setViewportView(tabla);

        jLabel1.setText("Total de puntos función no ajustaos (PFNA)");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 239, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 246, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabla;
    // End of variables declaration//GEN-END:variables
}
