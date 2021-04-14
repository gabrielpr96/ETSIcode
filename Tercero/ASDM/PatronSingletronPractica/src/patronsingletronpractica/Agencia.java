package patronsingletronpractica;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Agencia {

    private final Avion avion;
    private final String nombre;

    public Agencia(String nombre) {
        avion = Avion.getAvion();
        this.nombre = nombre;
    }

    public void QuieroDevolverBilletes(int billetes, Usuario usuario) throws ReservaExceotion {
        avion.devuelveBilletes(billetes, usuario);
    }
    
    @Override
    public String toString(){
        return nombre;
    }
}
