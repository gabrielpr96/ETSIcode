package controlador;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileNameExtensionFilter;
import modelo.*;
import vista.Lienzo;

public class Controlador {
	private Modelo modelo;
	private File fichero;
	private JTextArea infoArea;
	private JFrame mainFrame;
	private JLabel infoTitle;
	private Lienzo lienzo;
	private Grafo grafo;
	
	public Controlador(JFrame frame, JTextArea iArea, JLabel title, Lienzo lienz) {
		modelo = new Modelo();
		infoArea = iArea;
		infoTitle = title;
		mainFrame = frame;
		lienzo = lienz;
	}
	
	public void AbrirFichero() {
		JFileChooser fileChooser = new JFileChooser(); //Explorador de ficheros
		FileNameExtensionFilter filter = new FileNameExtensionFilter("TSP", "tsp"); //Extension filtro
		fileChooser.setFileFilter(filter); //Añadimos el filtro
		fileChooser.setCurrentDirectory(new File(System.getProperty("user.home"))); //Directorio base
		int result = fileChooser.showOpenDialog(null); //Abre el cuadro
		if (result == JFileChooser.APPROVE_OPTION) {
			try {
				File selectedFile = fileChooser.getSelectedFile(); //Fichero seleccionado
			    String[] str = selectedFile.getName().split("\\."); //Divisor de string(reg ex)
			    if(str[1].equals("tsp")) {	//Comparador de cadenas
			    	fichero = selectedFile;	//Guardado de fichero
			    	cls();
			    	setTitle(fichero.getName());
			    	if(modelo.getPuntos(fichero)) {
			    		writeLine("Datos cargados.");
			    		grafo = new Grafo(modelo.getPuntos());
			    	} else {
			    		error(mainFrame, "Error al cargar los datos.");
			    		writeLine("Error.");
			    	}
			    } else {
			    	error(mainFrame, "Formato no válido.");
			    }
			} catch (Exception e) {
				error(mainFrame, "Hubo un error con el fichero.");
				System.out.println ("Error: "+e.getMessage());
			}
		}
	}
	
	public void TExhaustivo(JFrame frame) {
		cls();
		lienzo.cls();
		writeLine("Ejecutando algoritmo Exhaustivo...");
		if(aviso(modelo.getPuntos().length)) {
			Triangulo triangulo = modelo.getTrianguloExh();
			writeLine("Reaultado: "+triangulo);
			writeLine("A:"+triangulo.Area()+"  P:"+triangulo.Perimetro());
			lienzo.setPuntos(modelo.getPuntos());
			lienzo.setTriangulo(triangulo);
			frame.repaint();
		} else {
			writeLine("Algoritmo no ejecutado.");
		}
	}
	
	public void TDivideYVenceras(JFrame frame) {
		cls();
		lienzo.cls();
		writeLine("Ejecutando algoritmo Divide y Vencerás...");
		if(aviso(modelo.getPuntos().length)) {
			Triangulo triangulo = modelo.getTrianguloDyV();
			writeLine("Reaultado: "+triangulo);
			writeLine("A:"+triangulo.Area()+"  P:"+triangulo.Perimetro());
			lienzo.setPuntos(modelo.getPuntos());
			lienzo.setTriangulo(triangulo);
			frame.repaint();
		} else {
			writeLine("Algoritmo no ejecutado.");
		}
	}
	
	public void TPrim(JFrame frame) {
		cls();
		lienzo.cls();
		writeLine("Ejecutando algoritmo Prim...");
		if(aviso(modelo.getPuntos().length)) {
			lienzo.setPuntos(modelo.getPuntos());
			String[] nodos = Alg_Prim.Prim(grafo);
			lienzo.setListaNodos(nodos);
			writeLine("Coste minimo: "+nodos[0].split("#")[1]);
			frame.repaint();
			GuardarFichero("prim", null);
		} else {
			writeLine("Algoritmo no ejecutado.");
		}
	}
	
	public void TKruskal(JFrame frame) {
		cls();
		lienzo.cls();
		writeLine("Ejecutando algoritmo Kruskal...");
		if(aviso(modelo.getPuntos().length)) {
			lienzo.setPuntos(modelo.getPuntos());
			String[] nodos = Alg_Kruskal.Kruskal(grafo);
			lienzo.setBilistanodos(nodos);
			writeLine("Coste minimo: "+nodos[0].split("#")[2]);
			frame.repaint();
			GuardarFichero("kruskal", null);
		} else {
			writeLine("Algoritmo no ejecutado.");
		}
	}
	
	public void MostrarOcultarPuntos(JFrame frame, Lienzo lienzo) {
		if(lienzo.getMuestraPuntos()) {
			lienzo.setMuestraPuntos(false);
		} else {
			lienzo.setMuestraPuntos(true);
		}
		frame.repaint();
	}
	
	
	public void GenerarDatos(int values) {
		cls();
		writeLine("Datos generados: "+values);
		modelo.GenerarDatos(values);
		grafo = new Grafo(modelo.getPuntos());
	}
	
	public void GuardarFichero(String filename, String comment) {
		if(comment!=null) {	//TSP
			JFrame parentFrame = new JFrame();
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setDialogTitle("Guardar fichero TSP");
			FileNameExtensionFilter filter = new FileNameExtensionFilter("tsp", "tsp");
			fileChooser.setFileFilter(filter);
			fileChooser.setSelectedFile(new File(filename));
			fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
			int userSelection = fileChooser.showSaveDialog(parentFrame);
			if (userSelection == JFileChooser.APPROVE_OPTION) {
			    File fileToSave = fileChooser.getSelectedFile();
			    creaTSP(fileToSave, comment);
			}
		} else { //TOUR
			JFrame parentFrame = new JFrame();
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setDialogTitle("Guardar fichero TOUR");
			FileNameExtensionFilter filter = new FileNameExtensionFilter("tour", "tour");
			fileChooser.setFileFilter(filter);
			fileChooser.setSelectedFile(new File(filename));
			fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
			int userSelection = fileChooser.showSaveDialog(parentFrame);
			if (userSelection == JFileChooser.APPROVE_OPTION) {
			    File fileToSave = fileChooser.getSelectedFile();
			    creaTOUR(fileToSave);
			}
		}
	}
	
	public void creaTSP(File file, String comment) {
		try {
			File newfile = new File(file.getAbsolutePath()+".tsp");
			file = newfile;
			if (file.createNewFile()) {
				Punto[] puntos = modelo.getPuntos();
				FileWriter writer = new FileWriter(file);
				writer.write("NAME: "+file.getName()+"\n");
				writer.write("TYPE: TSP"+"\n");
				writer.write("COMMENT: "+comment+"\n");
				writer.write("DIMENSION: "+puntos.length+"\n");
				writer.write("EDGE_WEIGHT_TYPE: EUC_2D"+"\n");
				writer.write("NODE_COORD_SECTION"+"\n");
				for(int i=0;i<puntos.length;i++) {
					writer.write(puntos[i].getN()+" "+puntos[i].getX()+" "+puntos[i].getY()+"\n");
				}
				writer.write("EOF"+"\n");
				writer.close();
			} else {
				error(mainFrame, "ERROR: No se pudo crear el archivo.");
			}
	    } catch (IOException e) {
	    	error(mainFrame, "ERROR: No se pudo crear el archivo.");
	    }
	}
	
	@SuppressWarnings("unused")
	public void creaTOUR(File file) {
		try {
			File newfile = new File(file.getAbsolutePath()+".tour");
			file = newfile;
			if (file.createNewFile()) {
				String[] listaNodos = lienzo.getListaNodos();
				String[] bilistanodos = lienzo.getBilistanodos();
				FileWriter writer = new FileWriter(file);
				writer.write("NAME: "+file.getName()+"\n");
				writer.write("TYPE: TOUR"+"\n");
				writer.write("DIMENSION: "+(listaNodos!=null?listaNodos.length:bilistanodos.length)+"\n");
				writer.write("SOLUTION: "+(listaNodos!=null?listaNodos[0].split("#")[1]:bilistanodos[0].split("#")[2])+"\n");
				writer.write("TOUR_SECTION"+"\n");
				if(listaNodos!=null) {
					for(int j=0;j<listaNodos.length;j++) {
						String[] nums = listaNodos[j].split("#");
						writer.write((Integer.parseInt(nums[0])+1)+","+(j+1)+"\n");
					}
				} else {
					for(int k=0;k<bilistanodos.length-1;k++) {
						String[] nums = bilistanodos[k].split("#");
						writer.write((Integer.parseInt(nums[0])+1)+","+(Integer.parseInt(nums[1])+1)+"\n");
					}
				}
				writer.write("-1"+"\n");
				writer.write("EOF"+"\n");
				writer.close();
			} else {
				error(mainFrame, "ERROR: No se pudo crear el archivo.");
			}
	    } catch (IOException e) {
	    	error(mainFrame, e.getMessage());
	    }
	}
	
	public boolean aviso(int num) {
		boolean resp = false;
		if(num>=5000) {
			int dialogButton = JOptionPane.YES_NO_OPTION;
			int dialogResult = JOptionPane.showConfirmDialog (null, "AVISO: hay 5000 o más puntos, la ejecución podría durár más de 10min.\n¿Está seguro que desea ejecutar el algoritmo?",null, dialogButton);
			if(dialogResult == JOptionPane.YES_OPTION){
				resp = true;
			}
		} else if(num>=1000) {
			int dialogButton = JOptionPane.YES_NO_OPTION;
			int dialogResult = JOptionPane.showConfirmDialog (null, "AVISO: hay 1000 o más puntos, se tardará unos minutos en la ejecución.\n¿Está seguro que desea ejecutar el algoritmo?",null, dialogButton);
			if(dialogResult == JOptionPane.YES_OPTION){
				resp = true;
			}
		} else {
			resp = true;
		}
		return resp;
	}
	
	public void writeLine(String str) {
		infoArea.append(str+"\n");
	}
	
	public void write(String str) {
		infoArea.append(str);
	}
	
	public void cls() {
		infoArea.setText(null);
	}
	
	public void error(JFrame frame, String text) {
		JOptionPane.showMessageDialog(frame, text);
	}
	
	public void salir() {
		System.exit(0);
	}
	
	public void setTitle(String title) {
		infoTitle.setText("Info: "+title);
	}
}
