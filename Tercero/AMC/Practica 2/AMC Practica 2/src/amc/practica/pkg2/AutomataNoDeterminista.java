package amc.practica.pkg2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class AutomataNoDeterminista {

    private final Set<String> estados, estadosFinales, estadosIniciales, simbolos;
    private final Map<String, String[]> transiciones;

    public AutomataNoDeterminista() {
        estados = new HashSet<>();
        estadosFinales = new HashSet<>();
        simbolos = new HashSet<>();
        estadosIniciales = new HashSet<>();
        transiciones = new HashMap<>();
    }

    public void validar() throws Exception {
        if (estados.isEmpty()) {
            throw new Exception("No se han definido estados");
        }
        if (estadosIniciales.isEmpty()) {
            throw new Exception("Estados iniciales no definido");
        }
        if (!estados.containsAll(estadosIniciales)) {
            throw new Exception("Los estados iniciales no está incluido en la lista de estados");
        }
        if (estadosFinales.isEmpty()) {
            throw new Exception("No se han definido estados finales");
        }
        if (!estados.containsAll(estadosFinales)) {
            throw new Exception("Los estaos finales no está incluido en la lista de estados");
        }
        if (simbolos.isEmpty()) {
            throw new Exception("No se han definido simbolos");
        }
        for (Map.Entry<String, String[]> transicion : transiciones.entrySet()) {
            String[] value = transicion.getValue();
            if (!estados.containsAll(Arrays.asList(value))) {
                throw new Exception("Estados resultado de transicción no está incluido en la lista de estados");
            }
            if (transicion.getKey().contains("-")) {
                String[] key = transicion.getKey().split("-");
                if (!estados.contains(key[0])) {
                    throw new Exception("Estado de partida de transicción no está incluido en la lista de estados");
                }
                if (!simbolos.contains(key[1])) {
                    throw new Exception("Simbolo de transicción no está incluido en la lista de simbolos");
                }
            } else {
                String key = transicion.getKey();
                if (!estados.contains(key)) {
                    throw new Exception("Estado de partida de transicción no está incluido en la lista de estados");
                }
            }
        }
    }

    public Set<String> getEstados() {
        return estados;
    }

    public Set<String> getEstadosFinales() {
        return estadosFinales;
    }

    public Set<String> getSimbolos() {
        return simbolos;
    }

    public Set<String> getEstadosIniciales() {
        return estadosIniciales;
    }

    public Map<String, String[]> getTransiciones() {
        return transiciones;
    }

    public void addEstados(String[] estados) {
        this.estados.addAll(Arrays.asList(estados));
    }

    public void addEstadosIniciales(String[] estados) {
        this.estadosIniciales.addAll(Arrays.asList(estados));
    }

    public void addEstadosFinales(String[] estados) {
        estadosFinales.addAll(Arrays.asList(estados));
    }

    public void addTransicion(String partida, String simbolo, String[] resultado) {
        transiciones.put(formarCondicion(partida, simbolo), resultado);
        if (!simbolos.contains(simbolo)) {
            simbolos.add(simbolo);
        }
    }

    public static String formarCondicion(String partida, String simbolo) {
        return simbolo == null ? partida : new StringBuilder().append(partida).append('-').append(simbolo).toString();
    }
}
