package patronobserver;

public interface ISensor {
    public void saltarAlarma(String tipo);
    public void agregarObservador(IObservador observador);
    public void eliminarObservador(IObservador observador);
    public void notificarObservadores();
}
