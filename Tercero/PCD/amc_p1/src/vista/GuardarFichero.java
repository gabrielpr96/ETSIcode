package vista;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import controlador.Controlador;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GuardarFichero {
	JFrame framePadre;
	Controlador controlador;
	private JTextField tf2;
	private JTextField tf1;
	private JTextField tf3;
	
	public GuardarFichero(JFrame frameP, Controlador control, Ventana ventana) {
		framePadre = frameP;
		controlador = control;
		
		JFrame frame = new JFrame("Guardar fichero");
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				frameP.setEnabled(true);
			}
		});
		frame.setResizable(false);
		frame.setSize(new Dimension(356, 160));
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);
		frame.getContentPane().setLayout(null);
		
		tf2 = new JTextField();
		tf2.setBounds(151, 57, 158, 20);
		frame.getContentPane().add(tf2);
		tf2.setColumns(10);
		
		JLabel lb1 = new JLabel("Nombre del fichero:");
		lb1.setBounds(23, 26, 126, 20);
		frame.getContentPane().add(lb1);
		
		JLabel lb2 = new JLabel("Comentario(Opcional):");
		lb2.setBounds(23, 57, 127, 20);
		frame.getContentPane().add(lb2);
		
		tf1 = new JTextField();
		tf1.setHorizontalAlignment(SwingConstants.RIGHT);
		tf1.setText("ejemplo");
		tf1.setColumns(10);
		tf1.setBounds(151, 26, 121, 20);
		frame.getContentPane().add(tf1);
		
		tf3 = new JTextField();
		tf3.setBackground(Color.WHITE);
		tf3.setEditable(false);
		tf3.setHorizontalAlignment(SwingConstants.CENTER);
		tf3.setText(".tsp");
		tf3.setColumns(10);
		tf3.setBounds(276, 26, 33, 20);
		frame.getContentPane().add(tf3);
		
		JButton btn1 = new JButton("Guardar");
		btn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String filename = tf1.getText().trim();
				String comment = tf2.getText().trim();
				controlador.GuardarFichero(filename, comment);
				ventana.enableMenu();
				frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
			}
		});
		btn1.setBounds(126, 88, 89, 23);
		frame.getContentPane().add(btn1);
		frame.setVisible(true);
	}
}
