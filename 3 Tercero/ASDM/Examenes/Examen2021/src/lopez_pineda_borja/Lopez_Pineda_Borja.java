package lopez_pineda_borja;

import java.util.Scanner;

public class Lopez_Pineda_Borja {

    private static int menu(Scanner s) {
        System.out.print("Elige una opción:\n"
                + "\t1. Introducir paciente\n"
                + "\t2. Atender paciente\n"
                + "\t3. Salir\n"
                + "Opcion: ");
        int n = s.nextInt();
        s.nextLine();
        return n;
    }

    public static void main(String[] args) {
        JuanRamonJimenez hospital = new JuanRamonJimenez();
        ContenedorPacientes pacientes = ContenedorPacientes.getContenedorPacientes();

        Panel[] paneles = new Panel[3];
        for (int i = 0; i < 3; i++) {
            paneles[i] = new Panel(i + 1);
            hospital.agregarObservador(paneles[i]);

            paneles[i].setLocation(100 + i * 300, 300);
            paneles[i].setVisible(true);
        }

        Scanner s = new Scanner(System.in);
        boolean continuar = true;
        while (continuar) {
            switch (menu(s)) {
                case 1:
                    System.out.println("\nIntroducir paciente ---------------");
                    System.out.print("Nombre: ");
                    String nombre = s.nextLine();
                    System.out.print("Gravedad [0, 3]: ");
                    int gravedad = s.nextInt();
                    if (gravedad >= 0 && gravedad <= 3) {
                        System.out.print("Hora de llegada (hora): ");
                        int horaLlegada = s.nextInt();
                        System.out.print("Hora de llegada (minutos): ");
                        int minutosLlegada = s.nextInt();
                        pacientes.agregarPaciente(new Paciente(nombre, gravedad, new Fecha(horaLlegada, minutosLlegada)));
                        System.out.println("Paciente agregado correctamente.\n");
                    } else {
                        System.out.println("La gravedad no está entre 0 y 3 inclusive.\n");
                    }
                    break;
                case 2:
                    System.out.println("\nAtender paciente ---------------");
                    System.out.print("Consulta: ");
                    int consulta = s.nextInt();
                    System.out.println(hospital.antenderPaciente(consulta) ? "Paciente antendido correctamente.\n" : "No hay pacientes que antender.\n");
                    break;
                case 3:
                    continuar = false;
            }
        }
        for (Panel panel : paneles) {
            panel.dispose();
        }
    }

}
