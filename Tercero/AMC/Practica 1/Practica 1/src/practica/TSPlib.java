package practica;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class TSPlib {

    public static Punto[] parse(String archivo) {
        Punto[] puntos = null;
        int i = 0;
        try {
            BufferedReader in = new BufferedReader(new FileReader(archivo));
            String line;
            boolean cordSection = false;
            while ((line = in.readLine()) != null) {
                if (!line.equals("EOF") && !line.equals("")) {
                    if (cordSection) {
                        String[] parts = line.split(" ");
                        puntos[i++] = new Punto(Double.parseDouble(parts[1].trim()), Double.parseDouble(parts[2].trim()));
                    } else {
                        if (line.equals("NODE_COORD_SECTION")) {
                            cordSection = true;
                        } else if (line.contains("DIMENSION")) {
                            String[] parts = line.split(":");
                            puntos = new Punto[Integer.parseInt(parts[1].trim())];
                        }
                    }
                }
            }
            in.close();
            return puntos;
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return puntos;
    }

    public static void formar(String archivo, Arista[] aristas, Punto[] vertices) {
        try {
            Path path = Paths.get(archivo);
            BufferedWriter out = new BufferedWriter(new FileWriter(path.toFile()));
            out.write("NAME : " + path.getFileName() + "\n");
            out.write("TYPE : TOUR\n");
            out.write("DIMENSION : " + vertices.length + "\n");
            out.write("SOLUTION : " + Algoritmos.costeCamino(aristas) + "\n");
            out.write("TOUR_SECTION\n");
            Map<Punto, Integer> posicion = new HashMap<>();
            for (int i = 0; i < vertices.length; i++) {
                posicion.put(vertices[i], i + 1);
            }
            for (Arista arista : aristas) {
                out.write(posicion.get(arista.getVertice1()) + ", " + posicion.get(arista.getVertice2()) + "\n");
            }
            out.write("-1\nEOF\n");
            out.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
