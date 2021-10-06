package patronsingletronpractica;

public class Usuario {

    private final Avion avion;
    private final String dni;

    public Usuario(String dni) {
        avion = Avion.getAvion();
        this.dni = dni;
    }

    public void QuieroComprarBilletes(int billetes) throws ReservaExceotion {
        avion.vendeBilletes(billetes, this);
    }
    
    @Override
    public String toString(){
        return dni;
    }
}
