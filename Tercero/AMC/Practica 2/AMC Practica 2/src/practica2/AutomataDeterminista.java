package practica2;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class AutomataDeterminista {

    private final Set<String> estados, estadosFinales, simbolos;
    private String estadoInicial;
    private final Map<String, String> transiciones;

    public AutomataDeterminista() {
        estados = new HashSet<>();
        estadosFinales = new HashSet<>();
        simbolos = new HashSet<>();
        estadoInicial = null;
        transiciones = new HashMap<>();
    }

    public void validar() throws Exception {
        if (estados.isEmpty()) {
            throw new Exception("No se han definido estados");
        }
        if (estadoInicial == null) {
            throw new Exception("Estado inicial no definido");
        }
        if (!estados.contains(estadoInicial)) {
            throw new Exception("El estado inicial no está incluido en la lista de estados");
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
        for (Map.Entry<String, String> transicion : transiciones.entrySet()) {
            String[] key = transicion.getKey().split("-");
            String value = transicion.getValue();
            if (!estados.contains(key[0])) {
                throw new Exception("Estado de partida de transicción no está incluido en la lista de estados");
            }
            if (!simbolos.contains(key[1])) {
                throw new Exception("Simbolo de transicción no está incluido en la lista de simbolos");
            }
            if (!estados.contains(value)) {
                throw new Exception("Estado resultado de transicción no está incluido en la lista de estados");
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

    public String getEstadoInicial() {
        return estadoInicial;
    }

    public Map<String, String> getTransiciones() {
        return transiciones;
    }

    public void setEstadoInicial(String estadoInicial) {
        this.estadoInicial = estadoInicial;
    }

    public void addEstados(String[] estados) {
        this.estados.addAll(Arrays.asList(estados));
    }

    public void addEstadosFinales(String[] estados) {
        estadosFinales.addAll(Arrays.asList(estados));
    }

    public void addTransicion(String partida, String simbolo, String resultado) {
        transiciones.put(formarCondicion(partida, simbolo), resultado);
        if (!simbolos.contains(simbolo)) {
            simbolos.add(simbolo);
        }
    }

    public static String formarCondicion(String partida, String simbolo) {
        return new StringBuilder().append(partida).append('-').append(simbolo).toString();
    }
}
