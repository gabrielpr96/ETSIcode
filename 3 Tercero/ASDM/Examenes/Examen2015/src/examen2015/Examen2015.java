package examen2015;

import java.util.Scanner;

public class Examen2015 {

    private static final Scanner s = new Scanner(System.in);

    public static int menu() {
        System.out.print("\nElige una opcion:\n"
                + "1. Introducir paciente\n"
                + "2. Atender paciente\n"
                + "3. Salir\n"
                + "Opcion: ");
        int n = s.nextInt();
        s.nextLine();
        System.out.println();
        return n;
    }

    public static void main(String[] args) {
        Hospital hospital = new Hospital();
        ColaPacientes pacientes = ColaPacientes.getColaPacientes();

        for (int i = 1; i <= 3; i++) {
            Cartel cartel = new Cartel(i);
            hospital.agregarObservador(cartel);
            cartel.setLocation(300 * i, 300);
            cartel.setVisible(true);
        }

        while (true) {
            switch (menu()) {
                case 1:
                    System.out.print("Nombre: ");
                    String nombre = s.nextLine();
                    System.out.print("Gravedad [0, 3]: ");
                    int gravedad = s.nextInt();
                    s.nextLine();
                    Paciente paciente = new Paciente(nombre, gravedad);
                    try {
                        pacientes.agregarPaciente(paciente);
                    } catch (ExcepcionGravedad ex) {
                        System.out.println(ex.getMessage());
                    }
                    break;
                case 2:
                    System.out.print("Consulta: ");
                    int consulta = s.nextInt();
                    s.nextLine();
                    hospital.atenderPaciente(consulta);
                    break;
                case 3:
                    System.exit(0);
            }
        }

    }

}
