package patronprototypemasfactory;

import java.util.Scanner;

public class PatronPrototypeMasFactory {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int opcion;
        Figura figura = null, clon = null;
        Creador fabrica = new CreadorConcreto();

        do {
            System.out.print("¿Qué tipo de figura quiere?\n"
                    + "1. Circulo\n"
                    + "2. Clon circulo\n"
                    + "3. Cuadrado\n"
                    + "4. Clon cuadrado\n"
                    + "5. Salir\n\n"
                    + "Opcion: ");
            opcion = s.nextInt();
            s.nextLine();
            switch (opcion) {
                case 1:
                case 3:
                    figura = fabrica.Factory_method(opcion);
                    if (figura != null) {
                        System.out.print("Indique el nombre de la figura: ");
                        figura.setNombre(s.nextLine());
                        System.out.print("Indique las coordenadas: ");
                        figura.mover(s.nextInt(), s.nextInt());
                    }
                    break;
                case 2:
                case 4:
                    clon = fabrica.Factory_method(opcion);
                    if (clon != null) {
                        clon.setNombre("Clon");
                        clon.mover(25, 75);
                    }
                    break;
            }
            System.out.println();
            if (figura != null) {
                System.out.println("Original: " + figura.getNombre());
                figura.getPosicion();
            }
            if (clon != null) {
                System.out.println("Clon: " + clon.getNombre());
                clon.getPosicion();
            }
            System.out.println();
        } while (opcion != 5);

    }

}
