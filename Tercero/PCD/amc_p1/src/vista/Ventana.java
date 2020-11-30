package vista;

import java.awt.BorderLayout;
import javax.swing.*;
import controlador.Controlador;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.FlowLayout;

public class Ventana {
	private Controlador controlador;
	
	/* ITEMS */
	// Menu 1
	JMenu m1;		// Menú
	JMenuItem m11; 	// Cargar archivo
	JMenuItem m12; 	// Generar datos...
	JMenuItem m13;	// Guardar datos...
	JMenuItem m14;	// Salir
	// Menu 2
	JMenu m2;		// Algoritmos
	JMenuItem m21;	// Exhaustivo
	JMenuItem m22;	// Divide y Vencerás
	JMenuItem m23;	// Prim
	JMenuItem m24;	// Kruskal
	// Menu 3
	JMenu m3; 		//  Cjto. de Datos
	JMenuItem m31;
	
	Lienzo lienzo;
	JLabel lb_info;
	
	
	public Ventana() {
		JFrame frame = new JFrame("Práctica 1");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setResizable(false);
		JMenuBar mb = new JMenuBar();
		JMenu m1 = new JMenu("Archivo");
		mb.add(m1);
		m11 = new JMenuItem("Cargar archivo");
		m11.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controlador.AbrirFichero();
				enableMenu();
			}
		});
		m1.add(m11);
		m12 = new JMenuItem("Generar datos...");
		m12.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				generadatos(frame);
				frame.setEnabled(false);
			}
		});
		m1.add(m12);
		m13 = new JMenuItem("Guardar datos...");
		m13.setEnabled(false);
		m13.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				guardarfichero(frame);
				frame.setEnabled(false);
			}
		});
		m1.add(m13);
		m14 = new JMenuItem("Salir");
		m14.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controlador.salir();
			}
		});
		m1.add(m14);
		JPanel panel = new JPanel();
		panel.setBackground(Color.BLACK);
		frame.getContentPane().add(BorderLayout.NORTH, mb);
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		lienzo = new Lienzo(null);
		panel.add(lienzo);
		lienzo.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		m2 = new JMenu("Algoritmos");
		m2.setEnabled(false);
		mb.add(m2);
		
		m21 = new JMenuItem("Exhaustivo");
		m21.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controlador.TExhaustivo(frame);
			}
		});
		m21.setEnabled(false);
		m2.add(m21);
		
		m22 = new JMenuItem("Divide y Vencer\u00E1s");
		m22.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controlador.TDivideYVenceras(frame);
			}
		});
		m22.setEnabled(false);
		m2.add(m22);
		
		m23 = new JMenuItem("Prim");
		m23.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controlador.TPrim(frame);
			}
		});
		m23.setEnabled(false);
		m2.add(m23);
		
		m24 = new JMenuItem("Kruskal");
		m24.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controlador.TKruskal(frame);
			}
		});
		m24.setEnabled(false);
		m2.add(m24);
		
		m3 = new JMenu("Cjto. de Datos");
		m3.setEnabled(false);
		mb.add(m3);
		
		m31 = new JMenuItem("Mostrar/Ocultar puntos");
		m31.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controlador.MostrarOcultarPuntos(frame, lienzo);
			}
		});
		m31.setEnabled(false);
		m3.add(m31);
		frame.getContentPane().add(BorderLayout.CENTER, panel);
	    
		JPanel info = new JPanel();
		frame.getContentPane().add(BorderLayout.SOUTH,info);
		info.setLayout(new BoxLayout(info, BoxLayout.Y_AXIS));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		info.add(scrollPane);
		
		JTextArea infoArea = new JTextArea();
		infoArea.setFont(new Font("Verdana", Font.PLAIN, 14));
		scrollPane.setViewportView(infoArea);
		infoArea.setLineWrap(true);
		infoArea.setWrapStyleWord(true);
		infoArea.setEditable(false);
		infoArea.setRows(6);
		lb_info = new JLabel("Info:");
		scrollPane.setColumnHeaderView(lb_info);
		lb_info.setFont(new Font("Verdana", Font.BOLD, 14));
		lb_info.setHorizontalAlignment(SwingConstants.CENTER);
		
		
		controlador = new Controlador(frame, infoArea, lb_info, lienzo);
		
	    
		frame.setVisible(true);
		controlador.imprimeAlgoritmo();
	}
	
	
	public void enableMenu() {
		m13.setEnabled(true);
		m2.setEnabled(true);
		m21.setEnabled(true);
		m22.setEnabled(true);
		m23.setEnabled(true);
		m24.setEnabled(true);
		m3.setEnabled(true);
		m31.setEnabled(true);
	}
	
	public void generadatos(JFrame frame) {
		new GenerarDatos(frame, controlador, this);
	}
	
	public void guardarfichero(JFrame frame) {
		new GuardarFichero(frame, controlador, this);
	}
}
