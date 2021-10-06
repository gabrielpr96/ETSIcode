package lopez_pineda_borja;

public interface IIterador {

    Paciente siguiente();

    Paciente anterior();

    boolean haySiguiente();

    boolean hayAnterior();
}
