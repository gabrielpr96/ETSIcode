package practica;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.HashMap;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TablaKruskal {

    private final JFrame frame;
    private final JPanel panel;
    private final Map<Punto, Integer> posicion;
    private final Punto[] puntos;
    private int paso;

    public TablaKruskal(Punto[] puntos) {
        this.puntos = puntos;
        posicion = new HashMap<>();
        for (int i = 0; i < puntos.length; i++) {
            posicion.put(puntos[i], i + 1);
        }
        paso = 0;
        frame = new JFrame();
        panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        frame.add(panel);
        frame.setVisible(true);
    }

    public void addPaso(Arista seleArista, Conjunto[] conjuntos, boolean verdadero) {
        Map<Punto, Integer> mapa = new HashMap<>();
        for (int i = 0; i < puntos.length; i++) {
            mapa.put(puntos[i], Conjunto.buscar(conjuntos, i));
        }
        addPaso(seleArista, mapa, verdadero);
    }

    public void addPaso(Arista seleccion, Map<Punto, Integer> conjuntos, boolean verdadero) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weighty = 0;
        gbc.weightx = 0;
        JPanel pComponentes = new JPanel();
        pComponentes.setLayout(new GridBagLayout());
        int j = 0;
        gbc.gridy = 0;
        for (int k = 0; k < posicion.size(); k++) {
            gbc.insets = new Insets(2, 2, 2, 2);
            int i = 0;
            boolean tiene = false;
            JPanel pConjunto = new JPanel();
            pConjunto.setLayout(new GridBagLayout());
            for (Map.Entry<Punto, Integer> vertice : conjuntos.entrySet()) {
                if (vertice.getValue() == k) {
                    gbc.gridx = i++;
                    pConjunto.add(new JLabel(Integer.toString(posicion.get(vertice.getKey()))), gbc);
                    tiene = true;
                }
            }
            if (tiene) {
                gbc.insets = new Insets(0, 0, 0, 0);
                gbc.gridx = j++;
                pConjunto.setBorder(BorderFactory.createLineBorder(Color.black, 1));
                pComponentes.add(pConjunto, gbc);
            }
        }
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridy = paso++;
        gbc.gridx = 0;
        JLabel label = new JLabel(paso == 0 ? "Inicial" : Integer.toString(paso));
        //label.setPreferredSize(new Dimension(50, 50));
        panel.add(label, gbc);
        gbc.gridx = 1;
        label = new JLabel(seleccion == null ? "-" : ("(" + posicion.get(seleccion.getVertice1()) + ", " + posicion.get(seleccion.getVertice2()) + ")" + (verdadero ? "" : " X")));
        //label.setPreferredSize(new Dimension(50, 50));
        panel.add(label, gbc);
        gbc.gridx = 2;
        panel.add(pComponentes, gbc);
        frame.pack();
    }
}
