package patronfactory;

import java.util.Scanner;

public class PatronFactory {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.print("¿Qué tipo de conexión quieres? ");
        String tipo = s.nextLine();

        Creador fabrica = new CreadorConcreto(tipo);
        Conexion conexion = fabrica.creaConexion();

        System.out.println("Estas conectado a una base de datos " + conexion.getDescripcion());
    }

}
