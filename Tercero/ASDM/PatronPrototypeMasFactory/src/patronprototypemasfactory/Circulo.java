package patronprototypemasfactory;

public class Circulo implements Figura {

    private String nombre;
    private int x, y;

    public Circulo() {
        nombre = "Circulo";
        x = 0;
        y = 0;
    }

    @Override
    public void setNombre(String n) {
        nombre = n;
    }

    @Override
    public String getNombre() {
        return nombre;
    }

    @Override
    public void mover(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public void getPosicion() {
        System.out.println("El circulo se encuentra en (" + x + "," + y + ").");
    }

    @Override
    public Figura clonar() {
        Circulo clon = new Circulo();
        clon.setNombre(nombre);
        clon.mover(x, y);
        return clon;
    }

}
