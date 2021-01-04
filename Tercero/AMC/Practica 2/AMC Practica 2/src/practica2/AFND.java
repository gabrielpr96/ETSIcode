package practica2;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class AFND implements Proceso, Cloneable {

    private AutomataNoDeterminista automata;

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
                lambdaClausura(estado, nuevos);
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
        macroestado.retainAll(automata.getEstadosFinales());
        return !macroestado.isEmpty();
    }

    public void lambdaClausura(String estado, Set<String> nuevos) {
        String[] resultados = automata.getTransiciones().get(estado);
        if (resultados != null) {
            for (String resultado : resultados) {
                if(!nuevos.contains(resultado)){
                    nuevos.add(resultado);
                    lambdaClausura(resultado, nuevos);
                }
            }
        }
    }

    @Override
    public boolean esFinal(String estado) {
        return automata.getEstadosFinales().contains(estado);
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        AFND obj = null;
        try {
            obj = (AFND) super.clone();
            obj.automata = (AutomataNoDeterminista) automata.clone();
        } catch (CloneNotSupportedException ex) {
        }
        return obj;
    }

    @Override
    public String toString() {
        return automata.toString();
    }
}
