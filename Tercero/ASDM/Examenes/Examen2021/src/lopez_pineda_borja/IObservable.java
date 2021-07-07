package lopez_pineda_borja;

public interface IObservable {

    void agregarObservador(IObservador observador);

    void eliminarObservador(IObservador observador);

    void notificar(Paciente paciente, int sala);
}
