package patronadapter;

import java.util.Scanner;

public class PatronAdapter {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        
        IObjetivo adaptado = new Adaptador("Segundo");
        System.out.print("¿Cómo quiere mostrarlo? (1 mayusculas, 2 minusculas): ");
        int formato = s.nextInt();
        
        adaptado.mostrarNombre(formato);
    }

}
