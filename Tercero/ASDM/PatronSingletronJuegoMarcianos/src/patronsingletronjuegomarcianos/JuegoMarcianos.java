package patronsingletronjuegomarcianos;

import java.util.Scanner;

public class JuegoMarcianos {

    public static void main(String[] args) {
        int disparos;

        Ordenador ordenador = new Ordenador();
        Jugador jugador = new Jugador();
        Marcianos marcianos = Marcianos.obtenerMarcianos();

        Scanner s = new Scanner(System.in);
        do {
            System.out.print("¿Cuantos disparos quieres realizar? ");
            disparos = s.nextInt();
            jugador.destruirMarcianos(disparos);
            ordenador.nuevosMarcianos();
        } while (disparos != 0 && marcianos.cuantosMarcianosQuedan() != 0);
    }

}
