package practica2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.Arrays;

public class Parser {

    public static AutomataDeterminista parseTextAFD(String text) throws IOException {
        StringReader sr = new StringReader(text);
        BufferedReader br = new BufferedReader(sr);
        AutomataDeterminista automata = parseAFD(br);
        br.close();
        sr.close();
        return automata;
    }

    public static AutomataDeterminista parseFileAFD(String dir) throws IOException {
        FileReader fr = new FileReader(new File(dir));
        BufferedReader br = new BufferedReader(fr);
        AutomataDeterminista automata = parseAFD(br);
        br.close();
        fr.close();
        return automata;
    }

    private static AutomataDeterminista parseAFD(BufferedReader file) throws IOException {
        AutomataDeterminista automata = new AutomataDeterminista();

        String line;
        boolean modoTransiciones = false;
        while ((line = file.readLine()) != null) {
            if(line.trim().equals("FIN")){
                modoTransiciones = false;
            }else if (modoTransiciones) {
                String[] partes = line.trim().split("'");
                automata.addTransicion(partes[0].trim(), partes[1], partes[2].trim());
            } else {
                if (line.startsWith("ESTADOS:")) {
                    automata.addEstados(line.substring(8).trim().split(" "));
                } else if (line.startsWith("INICIAL:")) {
                    automata.setEstadoInicial(line.substring(8).trim());
                } else if (line.startsWith("FINALES:")) {
                    automata.addEstadosFinales(line.substring(8).trim().split(" "));
                } else if (line.startsWith("TRANSICIONES:")) {
                    modoTransiciones = true;
                }
            }
        }

        return automata;
    }

    public static AutomataNoDeterminista parseTextAFND(String text) throws IOException {
        StringReader sr = new StringReader(text);
        BufferedReader br = new BufferedReader(sr);
        AutomataNoDeterminista automata = parseAFND(br);
        br.close();
        sr.close();
        return automata;
    }

    public static AutomataNoDeterminista parseFileAFND(String dir) throws IOException {
        FileReader fr = new FileReader(new File(dir));
        BufferedReader br = new BufferedReader(fr);
        AutomataNoDeterminista automata = parseAFND(br);
        br.close();
        fr.close();
        return automata;
    }

    private static AutomataNoDeterminista parseAFND(BufferedReader file) throws IOException {
        AutomataNoDeterminista automata = new AutomataNoDeterminista();

        String line;
        boolean modoTransiciones = false, modoTransicionesLambda = false;
        while ((line = file.readLine()) != null) {
            if(line.trim().equals("FIN")){
                modoTransiciones = false;
                modoTransicionesLambda = false;
            }else if (modoTransiciones) {
                String[] partes = line.trim().split("'");
                automata.addTransicion(partes[0].trim(), partes[1], partes[2].trim().split(" "));
            } else if (modoTransicionesLambda) {
                String[] partes = line.trim().split(" ");
                automata.addTransicion(partes[0].trim(), null, Arrays.copyOfRange(partes, 1, partes.length));
            } else {
                if (line.startsWith("ESTADOS:")) {
                    automata.addEstados(line.substring(8).trim().split(" "));
                } else if (line.startsWith("INICIAL:")) {
                    automata.addEstadosIniciales(line.substring(8).trim().split(" "));
                } else if (line.startsWith("FINALES:")) {
                    automata.addEstadosFinales(line.substring(8).trim().split(" "));
                } else if (line.startsWith("TRANSICIONES:")) {
                    modoTransiciones = true;
                } else if (line.startsWith("TRANSICIONES LAMBDA:")) {
                    modoTransicionesLambda = true;
                }
            }
        }

        return automata;
    }
}
