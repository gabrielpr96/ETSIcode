package examen2015;

import java.util.ArrayList;
import java.util.List;

public class ColaPacientes implements IIterable {

    private static final ColaPacientes colaPacientes = new ColaPacientes();
    private final List<Paciente>[] pacientes;

    public static ColaPacientes getColaPacientes() {
        return colaPacientes;
    }

    private ColaPacientes() {
        pacientes = new ArrayList[4];
        for (int i = 0; i < pacientes.length; i++) {
            pacientes[i] = new ArrayList<Paciente>();
        }
    }

    public void agregarPaciente(Paciente paciente) throws ExcepcionGravedad {
        if (paciente.getGravedad() < 0 || paciente.getGravedad() > 3) {
            throw new ExcepcionGravedad(paciente.getGravedad());
        }
        pacientes[paciente.getGravedad()].add(paciente);
    }

    public void eliminarPaciente(Paciente paciente) {
        if (paciente.getGravedad() >= 0 && paciente.getGravedad() <= 3) {
            pacientes[paciente.getGravedad()].remove(paciente);
        }
    }

    @Override
    public IIterador getIterador() {
        List<Paciente> todos = new ArrayList<>();
        for (List<Paciente> cola : pacientes) {
            todos.addAll(cola);
        }
        return new IteradorPacientes(todos.toArray(new Paciente[0]));
    }
}
