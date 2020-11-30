package modelo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Random;

public class Modelo {
	Punto[] puntos;
	public Modelo() {
	}
	
	public boolean getPuntos(File fichero) {
		try {
			FileReader fr = new FileReader(fichero);
			BufferedReader br = new BufferedReader(fr);
			
			String currentLine;
			int i = 0, subi = 0, dimension = 0;
			puntos = new Punto[1];
			while ((currentLine = br.readLine ()) != null) {
				if (i == 3) {
					dimension = Integer.parseInt(currentLine.split(": ")[1]);
					puntos = new Punto[dimension];
				}
				if (i>5 && !currentLine.equals("EOF")) {
					String[] parts = currentLine.split(" ");
					puntos[subi] = new Punto(Integer.parseInt(parts[0]),Double.parseDouble(parts[1]),Double.parseDouble(parts[2]));
					subi++;
				}
				if (currentLine.equals("EOF")) {
					break;
				}
				i++;
			}
			br.close();
			fr.close();
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public void GenerarDatos(int values) {
		puntos = new Punto[values];
		for(int i=0;i<values;i++) {
			Random r = new Random(System.nanoTime());
			double x = 2000 * r.nextDouble();
			double y = 2000 * r.nextDouble();
			Punto punto = new Punto(i+1, x, y);
			puntos[i] = punto;
		}
	}
	
	public Triangulo getTrianguloExh() {
		return Alg_Exhaustivo.Buscar(puntos);
	}
	
	public Triangulo getTrianguloDyV() {
		return Alg_DyV.Buscar(puntos);
	}
	
	public Punto[] getPuntos() {
		return puntos;
	}

	public void setPuntos(Punto[] puntos) {
		this.puntos = puntos;
	}
}
