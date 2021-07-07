package lopez_pineda_borja;

public class IteradorPacientes implements IIterador {

    private final Paciente[] pacientes;
    private int pos;

    public IteradorPacientes(Paciente[] pacientes) {
        this.pacientes = pacientes;
        pos = 0;
    }

    @Override
    public Paciente siguiente() {
        return haySiguiente() ? pacientes[pos++] : null;
    }

    @Override
    public Paciente anterior() {
        return hayAnterior() ? pacientes[--pos] : null;
    }

    @Override
    public boolean haySiguiente() {
        return pos < pacientes.length;
    }

    @Override
    public boolean hayAnterior() {
        return pos > 0;
    }

}
