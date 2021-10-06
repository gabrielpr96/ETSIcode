package practica2;

public class AFD implements Proceso, Cloneable {

    private AutomataDeterminista automata;

    public AFD(AutomataDeterminista automata) {
        this.automata = automata;
    }

    @Override
    public boolean reconocer(String cadena) throws Exception {
        char[] simbolos = cadena.toCharArray();
        automata.validar();
        String estado = automata.getEstadoInicial();
        for (char simbolo : simbolos) {
            if (!automata.getSimbolos().contains(simbolo)) {
                throw new Exception("Simbolo en cadena no reconocido");
            }
            estado = automata.getTransiciones().get(AutomataDeterminista.formarCondicion(estado, simbolo));
            if (estado == null) {
                throw new Exception("Transicion no incluida en la lista de transiciones");
            }
        }
        return automata.getEstadosFinales().contains(estado);
    }

    @Override
    public boolean esFinal(String estado) {
        return automata.getEstadosFinales().contains(estado);
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        AFD obj = null;
        try {
            obj = (AFD) super.clone();
            obj.automata = (AutomataDeterminista) automata.clone();
        } catch (CloneNotSupportedException ex) {
        }
        return obj;
    }

    @Override
    public String toString() {
        return automata.toString();
    }
}
