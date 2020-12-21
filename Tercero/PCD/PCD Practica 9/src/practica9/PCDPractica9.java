package practica9;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

public class PCDPractica9 {

    private static String hash = "";

    public static void main(String[] args) throws InterruptedException {
        JFrame f = new JFrame();
        f.setTitle("Practica 9: Borja López");
        f.setResizable(false);
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        CanvasArea canvas = new CanvasArea();
        JButton btnAleatorio = new JButton("Aleatorio");
        JTextField inputNumero = new JTextField("");
        JButton btnHash = new JButton("SHA256");
        JSlider sliderHilos = new JSlider(1, 10);
        JButton btnRun = new JButton("RUN");
        JLabel labelHash = new JLabel(" ");

        inputNumero.setPreferredSize(new Dimension(80, 30));
        sliderHilos.setMajorTickSpacing(1);
        sliderHilos.setMinorTickSpacing(1);
        sliderHilos.setPaintTicks(true);
        sliderHilos.setPaintLabels(true);
        sliderHilos.setValue(2);
        canvas.setSize(500, 200);
        canvas.setBackground(Color.white);

        Random r = new Random();
        r.setSeed(System.nanoTime());

        btnAleatorio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String texto = Integer.toString(r.nextInt((int) 1e6));
                inputNumero.setText(texto);
                labelHash.setText("");
            }
        });

        btnHash.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                hash = hashString(inputNumero.getText());
                labelHash.setText(hash);
                //inputNumero.setText("");
            }
        });

        btnRun.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Supervisor(hash, sliderHilos.getValue(), canvas, new Thread() {
                    @Override
                    public void run() {
                        inputNumero.setEnabled(true);
                        sliderHilos.setEnabled(true);
                        btnAleatorio.setEnabled(true);
                        btnRun.setEnabled(true);
                        btnHash.setEnabled(true);
                    }
                }).start();
                inputNumero.setEnabled(false);
                sliderHilos.setEnabled(false);
                btnAleatorio.setEnabled(false);
                btnRun.setEnabled(false);
                btnHash.setEnabled(false);
            }
        });

        f.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(3, 3, 3, 3);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weighty = 0;
        gbc.weightx = 1;
        f.add(btnAleatorio, gbc);
        gbc.gridx = 1;
        f.add(inputNumero, gbc);
        gbc.gridx = 2;
        f.add(btnHash, gbc);
        gbc.gridx = 3;
        f.add(sliderHilos, gbc);
        gbc.gridx = 4;
        f.add(btnRun, gbc);
        gbc.gridx = 0;
        gbc.gridwidth = 5;
        gbc.gridy = 1;
        f.add(labelHash, gbc);
        gbc.gridy = 2;
        f.add(canvas, gbc);

        f.pack();
        labelHash.setText("");
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }

    public static String hashString(String text) {
        if (text.length() < 6) {
            StringBuilder sb = new StringBuilder();
            while (sb.length() < 6 - text.length()) {
                sb.append('0');
            }
            text = sb.append(text).toString();
        }
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(text.getBytes());
            StringBuilder hex = new StringBuilder();
            for (int i = 0; i < hash.length; i++) {
                if ((0xff & hash[i]) < 0x10) {
                    hex.append("0").append(Integer.toHexString((0xFF & hash[i])).toUpperCase());
                } else {
                    hex.append(Integer.toHexString(0xFF & hash[i]).toUpperCase());
                }
            }
            return hex.toString();
        } catch (NoSuchAlgorithmException ex) {
            return "";
        }
    }
}
