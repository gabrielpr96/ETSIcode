package patronadapterpractica;

public abstract class Coche{
    protected int velocidad, revoluciones;
    protected boolean encendido, averia;
    
    public Coche(){
        velocidad = revoluciones = 0;
        encendido = averia = false;
    }
    
    public Estado getEstado(){
        return new Estado(velocidad, revoluciones, encendido, averia);
    }
}
