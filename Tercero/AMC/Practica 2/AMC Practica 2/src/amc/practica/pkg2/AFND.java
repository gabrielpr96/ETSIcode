package amc.practica.pkg2;

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
            ejecutar(macroestado, simbolo);
            if(macroestado.isEmpty())
                throw new Exception("El macroestado se ha quedado vacio");
        }
        return automata.getEstadosFinales().containsAll(macroestado);
    }
    
    public void ejecutar(Set<String> macroestado, String simbolo){
        lambdaClausura(macroestado);
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
    
    public void lambdaClausura(Set<String> macroestado){
        Set<String> nuevos = new HashSet<>();
        for (String estado : macroestado) {
            nuevos.addAll(lambdaClausura(estado));
        }
        macroestado.addAll(nuevos);
    }
    public Set<String> lambdaClausura(String estado){
        Set<String> nuevos = new HashSet<>();
        String[] resultados = automata.getTransiciones().get(estado);
        if(resultados != null){
            for (String resultado : resultados) {
                nuevos.add(resultado);
                nuevos.addAll(lambdaClausura(resultado));
            }
        }
        return nuevos;
    }
}
