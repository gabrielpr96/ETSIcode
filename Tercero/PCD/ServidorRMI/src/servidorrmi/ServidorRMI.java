package servidorrmi;

import Remoto.Ejemplo;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ServidorRMI {

    public static void main(String[] args) throws RemoteException, IOException {
        Ejemplo ejem = new Ejemplo(0);
        
        Registry registro = LocateRegistry.createRegistry(2021);
        registro.rebind("ejemremoto", ejem);
        
        System.out.println("El contador vale "+ejem.incrementa("la main", 4));
        
        System.out.println("El servidor funciona. Intro para finalizar...");
        System.in.read();
        System.exit(0);
    }
    
}
