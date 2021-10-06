package Servidor;

import Implementacion.CalculadoraImp;
import Interfaz.Calculadora;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

//–Djava.security.policy=fichero.permiso
public class CalculadoraServidor {

    public static void main(String[] args) {
        try {
            Calculadora calcStub = new CalculadoraImp();
            int Puerto = 0;
            Scanner Teclado = new Scanner(System.in);
            System.out.print("Introduce el nº de puerto para comunicarse: ");
            Puerto = Teclado.nextInt();
        
            Registry reg = LocateRegistry.createRegistry(Puerto);
            reg.rebind("Calculadora", calcStub);

            System.out.println("Servidor Calculadora esperando peticiones ... ");
            System.out.println("Pulsa enter para cerrar...");
            System.in.read();
            System.exit(0);
            //reg.unbind("Calculadora");
        } catch (Exception e) {
            System.out.println("Error en servidor Calculadora:" + e);
        }
    }

}
