package practica;

import java.awt.Dimension;
import java.awt.FileDialog;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import static practica.Algoritmos.randomMap;

public class Interfaz extends JPanel {

    private static final String[] ALGORITMOS = {"Exaustivo lineas", "DyV lineas", "Exaustivo triangulos", "DyV triangulos"};
    
    private static int ancho, alto;
    private final Lienzo lienzo;

    private final JButton btnAleatorio, btnCargar, btnComenzar, btnDetener;
    private final JSlider sliEspera;
    private final JComboBox cboAlgoritmo;
    private final JTextField txtTaya;
    private final JTextArea txtMensaje;
    private Thread trabajador;
    
    private Punto[] puntos;

    /**
     * Crea el panel de la interfaz
     */
    public Interfaz() {
        lienzo = new Lienzo();
        btnAleatorio = new JButton("Generar aleatoriamente");
        btnCargar = new JButton("Cargar archivo");
        btnComenzar = new JButton("COMENZAR");
        btnDetener = new JButton("DETENER");
        sliEspera = new JSlider();
        cboAlgoritmo = new JComboBox(ALGORITMOS);
        txtTaya = new JTextField();
        txtMensaje = new JTextArea();

        componentes();
    }

    /**
     * Monta los componentes en el manel
     */
    private void componentes() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(3,3,3,3);
        
        BufferedImage myPicture;
        try {
            myPicture = ImageIO.read(getClass().getResource("/res/logo.png"));
            JLabel picLabel = new JLabel(new ImageIcon(myPicture));
            gbc.gridx = 0;
            gbc.gridy = 0; 
            gbc.gridwidth = 1;
            gbc.gridheight = 1;
            gbc.weighty = 0;
            gbc.weightx = 0;
            add(picLabel, gbc);
        } catch (IOException ex) {
            Logger.getLogger(Interfaz.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        JPanel menu = new JPanel();
        menu.setLayout(new GridBagLayout());
        
        JPanel algoritmo = new JPanel();
        algoritmo.setLayout(new GridBagLayout());
        gbc.gridx = 1;
        gbc.gridy = 0; 
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weighty = 0;
        gbc.weightx = 0;
        algoritmo.add(cboAlgoritmo, gbc);
        gbc.gridx = 0;
        gbc.gridy = 0; 
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weighty = 0;
        gbc.weightx = 0;
        algoritmo.add(new JLabel("Algoritmo:  "), gbc);
        gbc.gridx = 0;
        gbc.gridy = 0; 
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weighty = 1;
        gbc.weightx = 0;
        menu.add(algoritmo, gbc);
        
        JPanel ejecutar = new JPanel();
        ejecutar.setLayout(new GridBagLayout());
        
        JPanel taya = new JPanel();
        taya.setLayout(new GridBagLayout());
        
        gbc.gridx = 0;
        gbc.gridy = 0; 
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weighty = 0;
        gbc.weightx = 0;
        taya.add(new JLabel("Taya:  "), gbc);
        gbc.gridx = 1;
        gbc.gridy = 0; 
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weighty = 0;
        gbc.weightx = 0;
        taya.add(txtTaya, gbc);
        gbc.gridx = 0;
        gbc.gridy = 0; 
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weighty = 0;
        gbc.weightx = 0;
        ejecutar.add(taya, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 1; 
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weighty = 0;
        gbc.weightx = 0;
        ejecutar.add(btnAleatorio, gbc);
        gbc.gridx = 1;
        gbc.gridy = 1; 
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weighty = 0;
        gbc.weightx = 0;
        ejecutar.add(btnCargar, gbc);
        gbc.gridx = 0;
        gbc.gridy = 2; 
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weighty = 0;
        gbc.weightx = 0;
        ejecutar.add(btnComenzar, gbc);
        gbc.gridx = 1;
        gbc.gridy = 2; 
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weighty = 0;
        gbc.weightx = 0;
        ejecutar.add(btnDetener, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 1; 
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weighty = 1;
        gbc.weightx = 0;
        menu.add(ejecutar, gbc);
        
        
        JPanel velocidad = new JPanel();
        velocidad.setLayout(new GridBagLayout());
        gbc.gridx = 0;
        gbc.gridy = 0; 
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weighty = 0;
        gbc.weightx = 0;
        velocidad.add(new JLabel("Velocidad de ejecución"), gbc);
        gbc.gridx = 0;
        gbc.gridy = 1; 
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weighty = 0;
        gbc.weightx = 0;
        velocidad.add(sliEspera, gbc);
        gbc.gridx = 0;
        gbc.gridy = 2; 
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weighty = 1;
        gbc.weightx = 0;
        menu.add(velocidad, gbc);
        
        JPanel resultado = new JPanel();
        resultado.setLayout(new GridBagLayout());
        
        gbc.gridx = 0;
        gbc.gridy = 0; 
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weighty = 0;
        gbc.weightx = 0;
        resultado.add(new JLabel("Resulado:"), gbc);
        gbc.gridx = 0;
        gbc.gridy = 1; 
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weighty = 0;
        gbc.weightx = 0;
        resultado.add(txtMensaje, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 3; 
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weighty = 1;
        gbc.weightx = 0;
        menu.add(resultado, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 1; 
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weighty = 1;
        gbc.weightx = 0;
        add(menu, gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 0; 
        gbc.gridwidth = 1;
        gbc.gridheight = 2;
        gbc.weighty = 0;
        gbc.weightx = 0;
        add(lienzo, gbc);
        
        
        sliEspera.setMinimum(0);
        sliEspera.setMaximum(1000);
        txtTaya.setText("10");
        txtTaya.setPreferredSize(new Dimension(100, 30));
        txtMensaje.setPreferredSize(new Dimension(500, 50));
        txtMensaje.setLineWrap(true);

        btnAleatorio.addActionListener((ActionEvent e) -> {
            puntos = randomMap(Integer.parseInt(txtTaya.getText()), -100, 100, -100, 100);
            lienzo.drawMap(puntos);
            detenerAlgoritmo();
            
        });
        btnCargar.addActionListener((ActionEvent e) -> {
            FileDialog dialog = new FileDialog((Frame)null, "Select File to Open");
            dialog.setMode(FileDialog.LOAD);
            dialog.setVisible(true);
            String file = dialog.getFile();
            if(file != null){
                puntos = TSPparser.parse(dialog.getDirectory() + dialog.getFile());
                lienzo.drawMap(puntos);
                detenerAlgoritmo();
            }
        });
        btnComenzar.addActionListener((ActionEvent e) -> {
            txtMensaje.setText("Ejecutando algoritmo");
            iniciarAlgoritmo();
        });
        btnDetener.addActionListener((ActionEvent e) -> {
            detenerAlgoritmo();
        });
        sliEspera.addChangeListener((ChangeEvent e) -> {
            Algoritmos.setEsperaDraw(1000-sliEspera.getValue());
        });
    }
    
    /**
     * Detiene el trabajdor actual si lo hubiera, esperando a que termine, y comienza uno nuevo con el algoritmo seleccionado y los puntos cargados
     */
    private void iniciarAlgoritmo(){
        if(trabajador != null){
            trabajador.interrupt();
            while(trabajador.isAlive()){
                try {
                    Thread.sleep(10);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Interfaz.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        trabajador = new Thread() {
            @Override
            public void run() {
                switch(cboAlgoritmo.getSelectedIndex()){
                    case 0:
                        Algoritmos.DrawExaustivoLinea(puntos, lienzo, txtMensaje);
                        break;
                    case 1:
                        Algoritmos.DrawDyVlinea(puntos, lienzo, txtMensaje);
                        break;
                    case 2:
                        Algoritmos.DrawExaustivo(puntos, lienzo, txtMensaje);
                        break;
                    case 3:
                        Algoritmos.DrawDyV(puntos, lienzo, txtMensaje);
                        break;
                }
            }
        };
        trabajador.start();
        btnDetener.setEnabled(true);
    }
    
    /**
     * Detiene el trabajdor actual si lo hubiera, no espera a que termine.
     */
    private void detenerAlgoritmo(){
        if(trabajador != null && trabajador.isAlive()){
            if(sliEspera.getValue() == 1000)
                Algoritmos.setEsperaDraw(1);
            trabajador.interrupt();
            trabajador = null;
        }
    }
    
    
    /**
     * Establece el tamaño del panel
     * @param w Ancho
     * @param h Alto
     */
    @Override
    public void setSize(int w, int h){
        ancho = w;
        alto = h;
        lienzo.setSize(alto, alto);
        lienzo.setLado(alto);
        super.setSize(w, h);
    }
}
