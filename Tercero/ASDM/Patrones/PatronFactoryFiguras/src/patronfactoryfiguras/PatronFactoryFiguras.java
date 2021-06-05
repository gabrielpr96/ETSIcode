package patronfactoryfiguras;

import java.util.Scanner;

public class PatronFactoryFiguras {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.print("¿Qué tipo de figura quieres? ");
        String tipo = s.nextLine();

        Factoria fabrica = new FactoriaFiguras(tipo);
        Figura figura = fabrica.creaFigura();

        System.out.println("Estas conectado a una figura de tipo " + figura.tipo());
    }
    
}
