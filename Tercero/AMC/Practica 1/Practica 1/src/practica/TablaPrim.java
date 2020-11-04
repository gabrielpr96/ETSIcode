package practica;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.List;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TablaPrim {
    private final JFrame frame;
    private final JPanel pTabla, pMatriz;
    private final Map<Punto, Integer> posicion;
    private int paso;
    private final JLabel[][] matriz;
    private final DecimalFormat df2;
    
    public TablaPrim(Punto[] puntos){
        df2 = new DecimalFormat("#.##");
        posicion = new HashMap<>();
        for (int i = 0; i < puntos.length; i++) {
            posicion.put(puntos[i], i+1);
        }
        paso = 0;
        frame = new JFrame();
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5,5,5,5);
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
        matriz = new JLabel[puntos.length][puntos.length];
        for (int i = 0; i < puntos.length; i++) {
            gbc.gridx = i+1;
            gbc.gridy = 0;
            JLabel label = new JLabel(Integer.toString(i+1));
            label.setPreferredSize(new Dimension(50, 20));
            pMatriz.add(label, gbc);
        }
        for (int j = 0; j < puntos.length; j++) {
            gbc.gridx = 0;
            gbc.gridy = j+1;
            JLabel label = new JLabel(Integer.toString(j+1));
            label.setPreferredSize(new Dimension(30, 20));
            pMatriz.add(label, gbc);
        }
        for (int i = 0; i < puntos.length; i++) {
            for (int j = 0; j < puntos.length; j++) {
                gbc.gridy = i+1;
                gbc.gridx = j+1;
                matriz[i][j] = new JLabel("X");
                matriz[i][j].setPreferredSize(new Dimension(50, 20));
                pMatriz.add(matriz[i][j], gbc);
            }
        }
        pMatriz.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        gbc.gridx = 1;
        gbc.gridy = 0;
        panel.add(pMatriz, gbc);
        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
    }
    
    public void addPaso(Arista seleccion, Arista[] aristas){
        ArrayList<Punto> puntos = new ArrayList<>();
        for (Arista arista : aristas) {
            if(arista != null){
                if(!puntos.contains(arista.getVertice1()))
                    puntos.add(arista.getVertice1());
                if(!puntos.contains(arista.getVertice2()))
                    puntos.add(arista.getVertice2());
            }
        }
        addPaso(seleccion, puntos.toArray(new Punto[0]));
    }
    public void addPaso(Arista seleccion, Punto[] puntos){
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weighty = 0;
        gbc.weightx = 0;
        JPanel pComponentes = new JPanel();
        pComponentes.setLayout(new GridBagLayout());
        gbc.insets = new Insets(2,2,2,2);
        int j = 0;
        gbc.gridy = 0;
        for (int i = 0; i < puntos.length; i++) {
            gbc.gridx = i;
            pComponentes.add(new JLabel(Integer.toString(posicion.get(puntos[i]))), gbc);
        }
        pComponentes.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        gbc.insets = new Insets(5,5,5,5);
        gbc.gridy = paso++;
        gbc.gridx = 0;
        JLabel label = new JLabel(paso==0?"Inicial":Integer.toString(paso));
        //label.setPreferredSize(new Dimension(50, 50));
        pTabla.add(label, gbc);
        gbc.gridx = 1;
        label = new JLabel(seleccion==null?"-":("("+posicion.get(seleccion.getVertice1())+", "+posicion.get(seleccion.getVertice2())+")"));
        //label.setPreferredSize(new Dimension(50, 50));
        pTabla.add(label, gbc);
        gbc.gridx = 2;
        pTabla.add(pComponentes, gbc);
        frame.pack();
    }
    
    public void updateMatrix(Punto indice, Map<Punto, Arista> fila){
        int iFila = posicion.get(indice)-1;
        for (Map.Entry<Punto, Arista> celda : fila.entrySet()) {
            if(celda.getValue() != null)
                matriz[iFila][posicion.get(celda.getKey())-1].setText(df2.format(celda.getValue().coste()));
        }
    }

}
