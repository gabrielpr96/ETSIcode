package patronobserver;

public class ProteccionCivil implements IObservador{

    @Override
    public void actualizar() {
        System.out.println("Proteccion civil ha recibido la alarma");
    }
    
}
