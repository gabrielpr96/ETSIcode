package patronobserver;

public class Policia implements IObservador{

    @Override
    public void actualizar() {
        System.out.println("La policia ha recibido la alarma");
    }
    
}
