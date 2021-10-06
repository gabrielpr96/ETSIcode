package examen2015;

public interface IPublicador {

    public void agregarObservador(IObservador observador);

    public void eliminarObservador(IObservador observador);

    public void notificar(Paciente paciente, int consulta);
}
