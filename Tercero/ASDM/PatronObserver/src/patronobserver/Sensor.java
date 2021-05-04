package patronobserver;

import java.util.ArrayList;

public class Sensor implements ISensor{

    private final ArrayList<IObservador> observadores;
    
    public Sensor(){
        observadores = new ArrayList<>();
    }
    
    @Override
    public void saltarAlarma(String tipo) {
        System.out.println("Hay una alarma de "+tipo);
        notificarObservadores();
    }

    @Override
    public void agregarObservador(IObservador observador) {
        observadores.add(observador);
    }

    @Override
    public void eliminarObservador(IObservador observador) {
        observadores.remove(observador);
    }

    @Override
    public void notificarObservadores() {
        observadores.stream().forEach(observador -> observador.actualizar());
    }
    
}
