package patronadapter;

public class Adaptador extends Adaptado implements IObjetivo {

    public Adaptador(String nombre) {
        super(nombre);
    }

    @Override
    public void mostrarNombre(int formato) {
        mostrarNombre(formato == 1 ? "mayusculas" : "minusculas");
    }

}
