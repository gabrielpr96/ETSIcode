package patronobserver;

public class PatronObserver {

    public static void main(String[] args) {
        ISensor s = new Sensor();
        Policia p1 = new Policia();
        Bombero b1 = new Bombero();
        ProteccionCivil pc1 = new ProteccionCivil();
        s.agregarObservador(p1);
        s.agregarObservador(b1);
        s.agregarObservador(pc1);
        s.saltarAlarma("Fuego");
    }
    
}
