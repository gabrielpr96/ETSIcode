package juegodados;

public class Jugador {
    private int saldo;
    
    public Jugador(int saldo){
        this.saldo = saldo;
    }

    public int getSaldo() {
        return saldo;
    }
    
    public void cambiarSaldo(int delta){
        saldo += delta;
    }
    
    
}
