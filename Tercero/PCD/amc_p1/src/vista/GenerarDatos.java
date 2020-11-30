package vista;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JComponent;
import javax.swing.JFrame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JPanel;
import controlador.Controlador;
import javax.swing.JComboBox;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class GenerarDatos {
	JFrame framePadre;
	Controlador controlador;
	JRadioButton rbtn1;
	JRadioButton rbtn2;
	JSpinner spinner;
	
	public GenerarDatos(JFrame frameP, Controlador control, Ventana ventana) {
		framePadre = frameP;
		controlador = control;
		
		JFrame frame = new JFrame("Generar Datos");
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				frameP.setEnabled(true);
			}
		});
		frame.setResizable(false);
		frame.setSize(new Dimension(265, 140));
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);
		
		JComponent panel1 = new JPanel();
		panel1.setLayout(null);
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"200", "500", "1500", "5000"}));
		comboBox.setSelectedIndex(0);
		comboBox.setBounds(142, 11, 86, 22);
		panel1.add(comboBox);
		JButton btnNewButton = new JButton("Generar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(rbtn1.isSelected()) {
					controlador.GenerarDatos(Integer.parseInt((String)comboBox.getSelectedItem()));
				} else {
					controlador.GenerarDatos(Integer.parseInt(spinner.getValue().toString()));
				}
				ventana.enableMenu();
				frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
			}
		});
		btnNewButton.setBounds(92, 70, 84, 23);
		panel1.add(btnNewButton);
		frame.getContentPane().add(panel1);
		
		rbtn1 = new JRadioButton("Grupos de puntos:");
		rbtn1.setSelected(true);
		rbtn1.setBounds(21, 11, 115, 23);
		panel1.add(rbtn1);
		
		rbtn2 = new JRadioButton("Introducir valores:");
		rbtn2.setBounds(21, 40, 115, 23);
		panel1.add(rbtn2);
		
		ButtonGroup group = new ButtonGroup();
	    group.add(rbtn1);
	    group.add(rbtn2);
		
		spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(4, 4, 10000, 1));
		spinner.setBounds(142, 39, 86, 20);
		panel1.add(spinner);
		frame.setVisible(true);
	}
}
