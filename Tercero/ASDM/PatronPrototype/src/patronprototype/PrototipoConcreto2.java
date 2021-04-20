package patronprototype;

public class PrototipoConcreto2 implements Prototipo {

    private String nombre;

    public PrototipoConcreto2() {
        nombre = "PrototipoConcreto2";
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
        PrototipoConcreto2 prototipo = new PrototipoConcreto2();
        prototipo.setNombre(nombre);
        return prototipo;
    }

}
