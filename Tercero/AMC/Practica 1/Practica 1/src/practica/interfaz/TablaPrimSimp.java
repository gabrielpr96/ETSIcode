package practica.interfaz;

import practica.geometriz.Punto;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.text.DecimalFormat;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TablaPrimSimp {

    private final JFrame frame;
    private final JPanel pTabla, pMatriz;
    private int paso;
    private final DecimalFormat df2;

    public TablaPrimSimp(Punto[] puntos, double[][] grafo) {
        df2 = new DecimalFormat("#.##");
        paso = 0;
        frame = new JFrame();
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weighty = 0;
        gbc.weightx = 0;
        pTabla = new JPanel();
        pTabla.setLayout(new GridBagLayout());
        pTabla.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        panel.add(pTabla, gbc);
        pMatriz = new JPanel();
        pMatriz.setLayout(new GridBagLayout());
        JLabel[][] matriz = new JLabel[puntos.length][puntos.length];
        for (int i = 0; i < puntos.length; i++) {
            gbc.gridx = i + 1;
            gbc.gridy = 0;
            JLabel label = new JLabel(Integer.toString(i + 1));
            label.setPreferredSize(new Dimension(50, 20));
            pMatriz.add(label, gbc);
        }
        for (int j = 0; j < puntos.length; j++) {
            gbc.gridx = 0;
            gbc.gridy = j + 1;
            JLabel label = new JLabel(Integer.toString(j + 1));
            label.setPreferredSize(new Dimension(30, 20));
            pMatriz.add(label, gbc);
        }
        for (int i = 0; i < puntos.length; i++) {
            for (int j = 0; j < puntos.length; j++) {
                gbc.gridy = i + 1;
                gbc.gridx = j + 1;
                matriz[i][j] = new JLabel(df2.format(grafo[i][j]));
                matriz[i][j].setPreferredSize(new Dimension(50, 20));
                pMatriz.add(matriz[i][j], gbc);
            }
        }
        pMatriz.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        gbc.gridx = 1;
        gbc.gridy = 0;
        panel.add(pMatriz, gbc);
        frame.add(panel);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weighty = 0;
        gbc.weightx = 0;
        gbc.gridy = paso++;
        gbc.gridx = 0;
        JLabel label = new JLabel("Paso");
        pTabla.add(label, gbc);
        gbc.gridx = 1;
        label = new JLabel("T");
        pTabla.add(label, gbc);
        gbc.gridx = 2;
        label = new JLabel("K(min)");
        pTabla.add(label, gbc);
        gbc.gridx = 3;
        label = new JLabel("B");
        pTabla.add(label, gbc);
        gbc.gridx = 4;
        label = new JLabel("C");
        pTabla.add(label, gbc);

        frame.pack();
        frame.setVisible(true);
    }

    public void addPaso(int u, int v, double distancia, boolean[] conexos) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weighty = 0;
        gbc.weightx = 0;
        gbc.gridy = paso++;
        gbc.gridx = 0;
        JLabel label = new JLabel(paso == 0 ? "Inicial" : Integer.toString(paso));
        pTabla.add(label, gbc);
        gbc.gridx = 1;
        label = new JLabel(u == -1 ? "" : ("{" + u + ", " + v + "}"));
        pTabla.add(label, gbc);
        gbc.gridx = 2;
        label = new JLabel(v == -1 ? "" : (+v + "(" + distancia + ")"));
        pTabla.add(label, gbc);
        gbc.gridx = 3;
        StringBuilder text = new StringBuilder();
        text.append("{");
        for (int i = 0; i < conexos.length; i++) {
            if (conexos[i]) {
                text.append(Integer.toString(i));
                text.append(", ");
            }
        }
        if (text.length() > 1) {
            text.setLength(text.length() - 2);
        }
        text.append("}");
        label = new JLabel(text.toString());
        pTabla.add(label, gbc);
        gbc.gridx = 4;
        text = new StringBuilder();
        text.append("{");
        for (int i = 0; i < conexos.length; i++) {
            if (!conexos[i]) {
                text.append(Integer.toString(i));
                text.append(", ");
            }
        }
        if (text.length() > 1) {
            text.setLength(text.length() - 2);
        }
        text.append("}");
        label = new JLabel(text.toString());
        pTabla.add(label, gbc);
        frame.pack();
    }

}
