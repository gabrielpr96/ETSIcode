package examen2020;

import java.util.Scanner;

public class Examen2020 {

    public static void main(String[] args) {
        Tren tren = Tren.getTren();

        Estaciones estaciones = new Estaciones();
        Iterador iterador = estaciones.getIterador();
        while (iterador.haySiguiente()) {
            tren.agregarObservador(iterador.siguiente());
        }

        boolean salir = false;
        Scanner s = new Scanner(System.in);
        while (!salir) {
            System.out.print("Menu \n1. Actualizar pasajeros\n2. Salir\nOpcion: ");
            switch (s.nextInt()) {
                case 1:
                    System.out.print("Personas que suben: ");
                    int suben = s.nextInt();
                    System.out.print("Personas que bajan: ");
                    int bajan = s.nextInt();
                    System.out.println("\n\n--------------------------\nActualizando pasajeros\n--------------------------\n\n");
                    tren.setPlazasDisponibles(suben, bajan);
                    System.out.println("\n\n--------------------------\nMoviendo tren\n--------------------------\n\n");
                    tren.moverse();
                    break;
                default:
                    salir = true;
            }
        }
    }

}
