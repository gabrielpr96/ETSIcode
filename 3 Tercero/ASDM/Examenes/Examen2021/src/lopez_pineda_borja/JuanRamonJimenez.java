package lopez_pineda_borja;

import java.util.ArrayList;
import java.util.List;

public class JuanRamonJimenez implements IObservable {

    private final ContenedorPacientes pacientes;
    private final List<IObservador> observadores;

    public JuanRamonJimenez() {
        pacientes = ContenedorPacientes.getContenedorPacientes();
        observadores = new ArrayList<>();
    }

    public boolean antenderPaciente(int consulta) {
        Paciente prioritario = null;

        IIterador iterador = pacientes.getIterador();
        while (iterador.haySiguiente()) {
            Paciente tmp = iterador.siguiente();
            if (prioritario == null || tmp.getGravedad() < prioritario.getGravedad()
                    || (tmp.getGravedad() == prioritario.getGravedad() && tmp.getHoraDeLlegada().anterior(prioritario.getHoraDeLlegada()))) {
                prioritario = tmp;
            }
        }

        if (prioritario != null) {
            pacientes.eliminarPaciente(prioritario);
            notificar(prioritario, consulta);
        }
        return prioritario != null;
    }

    @Override
    public void agregarObservador(IObservador observador) {
        observadores.add(observador);
    }

    @Override
    public void eliminarObservador(IObservador observador) {
        observadores.remove(observador);
    }

    @Override
    public void notificar(Paciente paciente, int sala) {
        for (IObservador observador : observadores) {
            observador.actualizar(paciente.getNombre(), sala);
        }
    }

}
