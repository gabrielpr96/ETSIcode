package servidor;

import comun.GestionAlmacenesIntf;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Practica2Servidor {

    public static void main(String[] args) throws RemoteException {
        try {
            GestionAlmacenesIntf stub = new GestionAlmacenes();
            int puerto;
            /*
            Scanner Teclado = new Scanner(System.in);
            System.out.print("Introduce el nº de puerto para comunicarse: ");
            Puerto = Teclado.nextInt();
            */
            puerto = 49591;
        
            Registry reg = LocateRegistry.createRegistry(puerto);
            reg.rebind("GestionAlmacenes", stub);

            System.out.println("Servidor Calculadora esperando peticiones ... ");
            System.out.println("Pulsa enter para cerrar...");
            System.in.read();
            reg.unbind("GestionAlmacenes");
            System.exit(0);
        } catch (Exception e) {
            System.out.println("Error en servidor Calculadora:" + e);
        }
    }
    
}
