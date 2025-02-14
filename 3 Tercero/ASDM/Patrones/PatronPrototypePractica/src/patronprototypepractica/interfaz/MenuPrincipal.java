package patronprototypepractica.interfaz;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import patronprototypepractica.Mascota;
import patronprototypepractica.SerVivo;
import patronprototypepractica.Persona;

public class MenuPrincipal extends javax.swing.JFrame {

    private final List<SerVivo> seresVivos;

    public MenuPrincipal() {
        seresVivos = new ArrayList<>();
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaSeresVivos = new javax.swing.JTable();
        btnNuevaPersona = new javax.swing.JButton();
        btnNuevaMascota = new javax.swing.JButton();
        btnClone = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Patron Prototype Borja Lopez");

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Seres vivos");

        tablaSeresVivos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Cabeza de familia", "Nombre", "Direccion"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaSeresVivos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tablaSeresVivosMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(tablaSeresVivos);

        btnNuevaPersona.setText("Crear nueva persona");
        btnNuevaPersona.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevaPersonaActionPerformed(evt);
            }
        });

        btnNuevaMascota.setText("Crear nueva mascota");
        btnNuevaMascota.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevaMascotaActionPerformed(evt);
            }
        });

        btnClone.setText("Clonar");
        btnClone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCloneActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jLabel1)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(btnNuevaPersona)
                        .addGap(18, 18, 18)
                        .addComponent(btnNuevaMascota)
                        .addGap(18, 18, 18)
                        .addComponent(btnClone)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNuevaPersona)
                    .addComponent(btnNuevaMascota)
                    .addComponent(btnClone))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnNuevaPersonaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevaPersonaActionPerformed
        NuevaPersona nuevaPersona = new NuevaPersona(this);
        nuevaPersona.setLocationRelativeTo(this);
        nuevaPersona.setVisible(true);
    }//GEN-LAST:event_btnNuevaPersonaActionPerformed

    private void btnNuevaMascotaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevaMascotaActionPerformed
        NuevaMascota nuevaMasciota = new NuevaMascota(this);
        nuevaMasciota.setLocationRelativeTo(this);
        nuevaMasciota.setVisible(true);
    }//GEN-LAST:event_btnNuevaMascotaActionPerformed

    private void btnCloneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCloneActionPerformed
        int row = tablaSeresVivos.getSelectedRow();
        if (row != -1) {
            SerVivo serVivo = seresVivos.get(row);
            JFrame ventanaClonar;
            if (serVivo.getClass() == Persona.class) {
                ventanaClonar = new ClonarPersona(this, (Persona) serVivo);
            } else {
                ventanaClonar = new ClonarMascota(this, (Mascota) serVivo);
            }
            ventanaClonar.setLocationRelativeTo(this);
            ventanaClonar.setVisible(true);
        }
    }//GEN-LAST:event_btnCloneActionPerformed

    private void tablaSeresVivosMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaSeresVivosMousePressed
        JTable table = (JTable) evt.getSource();
        Point point = evt.getPoint();
        int row = table.rowAtPoint(point);
        if (evt.getClickCount() == 2 && table.getSelectedRow() != -1) {
            MostrarSerVivo mostrarSerVivo = new MostrarSerVivo(seresVivos.get(row));
            mostrarSerVivo.setLocationRelativeTo(null);
            mostrarSerVivo.setVisible(true);
        }
    }//GEN-LAST:event_tablaSeresVivosMousePressed

    public void addSerVivo(SerVivo serVivo) {
        seresVivos.add(serVivo);
        actualizarTabla();
    }

    public List<Persona> getPersonas() {
        return seresVivos.stream().filter(serVivo -> serVivo.getClass() == Persona.class).map(serVivo -> (Persona) serVivo).collect(Collectors.toList());
    }

    private void actualizarTabla() {
        DefaultTableModel model = (DefaultTableModel) tablaSeresVivos.getModel();
        while (model.getRowCount() > 0) {
            model.removeRow(0);
        }
        for (SerVivo serVivo : seresVivos) {
            model.addRow(new String[]{serVivo.getCabezaDeFamilia().getNombre(), serVivo.getNombre(), serVivo.getDireccion()});
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClone;
    private javax.swing.JButton btnNuevaMascota;
    private javax.swing.JButton btnNuevaPersona;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tablaSeresVivos;
    // End of variables declaration//GEN-END:variables
}
