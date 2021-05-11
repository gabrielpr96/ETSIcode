package comun;

import java.io.Serializable;

public class TFecha implements Serializable {

    private final int Dia, Mes, Anyo;

    public TFecha(int Dia, int Mes, int Anyo) {
        this.Dia = Dia;
        this.Mes = Mes;
        this.Anyo = Anyo;
    }

    public int getDia() {
        return Dia;
    }

    public int getMes() {
        return Mes;
    }

    public int getAnyo() {
        return Anyo;
    }

    @Override
    public String toString() {
        return (Dia<10?("0"+Dia):Dia) + "/" + (Mes<10?("0"+Mes):Mes) + "/" + Anyo;
    }
}
