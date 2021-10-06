package patronprototype;

public class PrototipoConcreto1 implements Prototipo {

    private String nombre;

    public PrototipoConcreto1() {
        nombre = "Prototipo1";
    }

    @Override
    public String getNombre() {
        return nombre;
    }

    @Override
    public void setNombre(String n) {
        nombre = n;
    }

    @Override
    public Prototipo clonar() {
        PrototipoConcreto1 prototipo = new PrototipoConcreto1();
        prototipo.setNombre(nombre);
        return prototipo;
    }

}
