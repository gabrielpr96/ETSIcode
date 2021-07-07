package lopez_pineda_borja;

import java.util.ArrayList;
import java.util.List;

public class ContenedorPacientes implements IIterable {

    private static final ContenedorPacientes contenedorPacientes = new ContenedorPacientes();
    private final List<Paciente> pacientes;

    private ContenedorPacientes() {
        pacientes = new ArrayList<>();
    }

    public void agregarPaciente(Paciente paciente) {
        pacientes.add(paciente);
    }

    public void eliminarPaciente(Paciente paciente) {
        pacientes.remove(paciente);
    }

    @Override
    public IIterador getIterador() {
        return new IteradorPacientes(pacientes.toArray(new Paciente[0]));
    }

    public static ContenedorPacientes getContenedorPacientes() {
        return contenedorPacientes;
    }

}
