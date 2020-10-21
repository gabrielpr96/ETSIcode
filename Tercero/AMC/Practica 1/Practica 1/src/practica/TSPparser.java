package practica;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class TSPparser {
    public static Punto[] parse(String archivo){
        Punto[] puntos = null;
        int i = 0;
        try {
            BufferedReader in = new BufferedReader(new FileReader(archivo));
            String line;
            boolean cordSection = false;
            while( (line = in.readLine()) != null ) {
                if( !line.equals("EOF") && !line.equals("") ) {
                    if(cordSection){
                        String[] parts = line.split(" ");
                        puntos[i++] = new Punto(Double.parseDouble(parts[1].trim()), Double.parseDouble(parts[2].trim()));
                    }else{
                        if(line.equals("NODE_COORD_SECTION")){
                            cordSection = true;
                        }else if(line.contains("DIMENSION")){
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
}
