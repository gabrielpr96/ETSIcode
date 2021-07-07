package examen2015;

import java.util.ArrayList;
import java.util.List;

public class Hospital implements IPublicador {

    private final ColaPacientes pacientes;
    private final List<IObservador> suscritos;

    public Hospital() {
        suscritos = new ArrayList<>();
        pacientes = ColaPacientes.getColaPacientes();
    }

    @Override
    public void agregarObservador(IObservador observador) {
        suscritos.add(observador);
    }

    @Override
    public void eliminarObservador(IObservador observador) {
        suscritos.remove(observador);
    }

    public void atenderPaciente(int consulta) {
        IIterador iterador = pacientes.getIterador();
        if (iterador.haySiguiente()) {
            Paciente paciente = iterador.siguiente();
            pacientes.eliminarPaciente(paciente);
            notificar(paciente, consulta);
        } else {
            System.out.println("No hay pacientes que atender");
        }
    }

    @Override
    public void notificar(Paciente paciente, int consulta) {
        for (IObservador suscrito : suscritos) {
            suscrito.actualizar(paciente, consulta);
        }
    }

}
