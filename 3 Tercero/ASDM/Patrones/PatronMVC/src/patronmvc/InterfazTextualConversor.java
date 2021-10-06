package patronmvc;

import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class InterfazTextualConversor implements InterfazVista{

    private ControlConversor controlador;
    private int nroOperacion;
    private final BufferedReader in;
    
    public InterfazTextualConversor(){
        nroOperacion = 0;
        in = new BufferedReader(new InputStreamReader(System.in));
    }
    
    private int leerOpcion(){
        try{
            return Integer.parseInt(in.readLine());
        }catch(Exception e){
            OperacionIncorrecta();
            return 0;
        }
    }
    
    private double leerCantidad(){
        try{
            return Double.parseDouble(in.readLine());
        }catch(Exception e){
            OperacionIncorrecta();
            System.out.println("El formato debe ser 0.0");
            leerCantidad();
            return 0;
        }
    }
    
    private void solicitaOperacion(){
        System.out.print("\n\n---- MENU DE CONVERSION ----\n"
                + "1. - Pesetas a Euros\n"
                + "2. - Euros a Pesetas\n"
                + "0. - Salir\n"
                + "Indique la operacion elegida: ");
    }
    
    private void OperacionIncorrecta(){
        System.out.println("Operacion incorrecta, intentelo de nuevo");
        procesaNuevaOperacion();
    }
    
    private void procesaNuevaOperacion(){
        solicitaOperacion();
        nroOperacion = leerOpcion();
        switch(nroOperacion){
            case 0:
                System.out.println("Adios");
                System.exit(0);
                break;
            case 1:
                controlador.actionPerformed(new ActionEvent(this, nroOperacion, AEUROS));
                break;
            case 2:
                controlador.actionPerformed(new ActionEvent(this, nroOperacion, APESETAS));
                break;
            default:
                OperacionIncorrecta();
        }
    }
    
    @Override
    public void setControlador(ControlConversor c) {
        controlador = c;
    }

    @Override
    public void arranca() {
        procesaNuevaOperacion();
    }

    @Override
    public double getCantidad() {
        System.out.print("Cantidad a convertir [0.0]: ");
        return leerCantidad();
    }

    @Override
    public void escribeCambio(String s) {
        System.out.println(s);
        procesaNuevaOperacion();
    }
    
}
