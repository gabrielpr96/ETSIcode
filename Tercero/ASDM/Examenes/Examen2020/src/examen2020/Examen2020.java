package examen2020;

import java.io.RandomAccessFile;
import java.util.Scanner;

public class Examen2020 {

    public static void main(String[] args) {
        Tren tren = Tren.getTren();
        FabricaEstaciones fabrica = new FabricaEstaciones();
        Estacion[] estaciones = new Estacion[5];
        for (int i = 0; i <= 4; i++) {
            estaciones[i] = fabrica.crearEstacion(i);
            tren.agregarObservador(estaciones[i]);
        }
        
        boolean salir = false;
        Scanner s = new Scanner(System.in);
        while(!salir){
            System.out.print("Menu \n1. Actualizar pasajeros\n2. Salir\nOpcion: ");
            switch(s.nextInt()){
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
