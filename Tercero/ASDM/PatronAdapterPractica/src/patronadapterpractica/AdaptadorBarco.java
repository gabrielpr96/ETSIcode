package patronadapterpractica;

public class AdaptadorBarco extends Barco implements IMandos {

    @Override
    public void Acelerar() {
        if (super.isGrifo()) {
            super.MoverMasRapido();
        } else {
            super.Conectar();
            super.Activar();
        }
    }

    @Override
    public void Frenar() {
        super.Detener();
        if (velocidad == 0) {
            super.Desconectar();
        }
    }

    @Override
    public Estado getEstado() {
        return new Estado(velocidad * 100, velocidad, grifo, false);
    }

}
