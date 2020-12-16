package practica2;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class AFND {

    private final AutomataNoDeterminista automata;

    public AFND(AutomataNoDeterminista automata) {
        this.automata = automata;
    }

    public boolean reconocer(String[] cadena) throws Exception {
        automata.validar();
        Set<String> macroestado = new HashSet<>(automata.getEstadosIniciales());
        for (String simbolo : cadena) {
            lambdaClausura(macroestado, automata);
            ejecutar(macroestado, simbolo, automata);
            if(macroestado.isEmpty())
                throw new Exception("El macroestado se ha quedado vacio");
        }
        return automata.getEstadosFinales().containsAll(macroestado);
    }
    
    public static void ejecutar(Set<String> macroestado, String simbolo, AutomataNoDeterminista automata){
        Set<String> siguienteMacroestado = new HashSet<>();
        for (String estado : macroestado) {
            String[] siguientes = automata.getTransiciones().get(AutomataDeterminista.formarCondicion(estado, simbolo));
            if(siguientes != null){
                siguienteMacroestado.addAll(Arrays.asList(siguientes));
            }
        }
        macroestado.clear();
        macroestado.addAll(siguienteMacroestado);
    }
    
    public static void lambdaClausura(Set<String> macroestado, AutomataNoDeterminista automata){
        Set<String> nuevos = new HashSet<>();
        for (String estado : macroestado) {
            nuevos.addAll(lambdaClausura(estado, automata));
        }
        macroestado.addAll(nuevos);
    }
    public static Set<String> lambdaClausura(String estado, AutomataNoDeterminista automata){
        Set<String> nuevos = new HashSet<>();
        String[] resultados = automata.getTransiciones().get(estado);
        if(resultados != null){
            for (String resultado : resultados) {
                nuevos.add(resultado);
                nuevos.addAll(lambdaClausura(resultado, automata));
            }
        }
        return nuevos;
    }
}
