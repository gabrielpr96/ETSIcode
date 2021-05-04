package patronobserver;

public class Bombero implements IObservador{

    @Override
    public void actualizar() {
        System.out.println("Los bomberos han recibido la alarma");
    }
    
}
