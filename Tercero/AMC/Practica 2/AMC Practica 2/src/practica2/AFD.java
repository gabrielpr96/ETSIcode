package practica2;

public class AFD {

    private final AutomataDeterminista automata;

    public AFD(AutomataDeterminista automata) {
        this.automata = automata;
    }

    public boolean reconocer(String[] cadena) throws Exception {
        automata.validar();
        String estado = automata.getEstadoInicial();
        for (String simbolo : cadena) {
            estado = ejecutar(estado, simbolo, automata);
        }
        return automata.getEstadosFinales().contains(estado);
    }
    
    public static String ejecutar(String estado, String simbolo, AutomataDeterminista automata) throws Exception{
        if (!automata.getSimbolos().contains(simbolo)) {
                throw new Exception("Simbolo en cadena no reconocido");
            }
            String siguiente = automata.getTransiciones().get(AutomataDeterminista.formarCondicion(estado, simbolo));
            if (siguiente == null) {
                throw new Exception("Transicion no incluida en la lista de transiciones");
            }
            return siguiente;
    }
}
