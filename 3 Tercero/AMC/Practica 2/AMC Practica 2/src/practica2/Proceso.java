package practica2;

public interface Proceso {

    public abstract boolean esFinal(String estado);

    public abstract boolean reconocer(String cadena) throws Exception;

    @Override
    public abstract String toString();
}
