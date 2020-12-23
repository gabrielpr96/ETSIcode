package practica2;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class AutomataDeterminista implements Cloneable{

    private Set<String> estados, estadosFinales;
    private Set<Character> simbolos;
    private String estadoInicial;
    private Map<String, String> transiciones;

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
            if(key[1].length() != 1){
                throw new Exception("Simbolo no es un solo caracter");
            }else if (!simbolos.contains(key[1].charAt(0))) {
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

    public Set<Character> getSimbolos() {
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

    public void addTransicion(String partida, char simbolo, String resultado) {
        transiciones.put(formarCondicion(partida, simbolo), resultado);
        if (!simbolos.contains(simbolo)) {
            simbolos.add(simbolo);
        }
    }

    public static String formarCondicion(String partida, char simbolo) {
        return new StringBuilder().append(partida).append('-').append(simbolo).toString();
    }
    
    @Override
    public Object clone() throws CloneNotSupportedException {
        AutomataDeterminista obj = null;
        try {
            obj = (AutomataDeterminista) super.clone();
            obj.estados = new HashSet<>(estados);
            obj.estadosFinales = new HashSet<>(estadosFinales);
            obj.simbolos = new HashSet<>(simbolos);
            obj.transiciones = new HashMap<>(transiciones);
        } catch (CloneNotSupportedException ex) {
        }
        return obj;
    }
    
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("ESTADOS: ").append(estados.toString()).append("\n")
                .append("INICIAL: ").append(estadoInicial).append("\n")
                .append("FINALES: ").append(estadosFinales).append("\n")
                .append("TRANSICIONES: ").append("\n");
        for (Map.Entry<String, String> transicion : transiciones.entrySet()) {
            String[] key = transicion.getKey().split("-");
            String value = transicion.getValue();
            sb.append("\t").append(key[0]).append(" '").append(key[1]).append("' ").append(value).append("\n");
        }
        sb.append("FIN");
        return sb.toString();
    }
}
