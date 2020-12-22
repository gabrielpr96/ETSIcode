package practica2;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class AFND implements Proceso, Cloneable {

    private final AutomataNoDeterminista automata;

    public AFND(AutomataNoDeterminista automata) {
        this.automata = automata;
    }

    @Override
    public boolean reconocer(String cadena) throws Exception {
        char[] simbolos = cadena.toCharArray();
        automata.validar();
        Set<String> macroestado = new HashSet<>(automata.getEstadosIniciales());
        Set<String> nuevos = new HashSet<>();
        for (char simbolo : simbolos) {
            for (String estado : macroestado) {
                nuevos.addAll(lambdaClausura(estado));
            }
            macroestado.addAll(nuevos);

            nuevos.clear();
            for (String estado : macroestado) {
                String[] siguientes = automata.getTransiciones().get(AutomataDeterminista.formarCondicion(estado, simbolo));
                if (siguientes != null) {
                    nuevos.addAll(Arrays.asList(siguientes));
                }
            }
            macroestado.clear();
            macroestado.addAll(nuevos);
            nuevos.clear();

            if (macroestado.isEmpty()) {
                throw new Exception("El macroestado se ha quedado vacio");
            }
        }
        return automata.getEstadosFinales().containsAll(macroestado);
    }

    public Set<String> lambdaClausura(String estado) {
        Set<String> nuevos = new HashSet<>();
        String[] resultados = automata.getTransiciones().get(estado);
        if (resultados != null) {
            for (String resultado : resultados) {
                nuevos.add(resultado);
                nuevos.addAll(lambdaClausura(resultado));
            }
        }
        return nuevos;
    }

    @Override
    public boolean esFinal(String estado) {
        return automata.getEstadosFinales().contains(estado);
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return (AFND) super.clone();
    }

    @Override
    public String toString() {
        return automata.toString();
    }
}
