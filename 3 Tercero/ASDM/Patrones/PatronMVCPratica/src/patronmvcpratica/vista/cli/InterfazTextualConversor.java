package patronmvcpratica.vista.cli;

import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import patronmvcpratica.controlador.ControlConversor;
import patronmvcpratica.vista.InterfazVista;

public class InterfazTextualConversor implements InterfazVista {

    private ControlConversor controlador;
    private int nroOperacion;
    private final BufferedReader in;

    public InterfazTextualConversor() {
        nroOperacion = 0;
        in = new BufferedReader(new InputStreamReader(System.in));
    }

    private int leerOpcion() {
        try {
            return Integer.parseInt(in.readLine());
        } catch (IOException | NumberFormatException e) {
            OperacionIncorrecta();
            return 0;
        }
    }

    private double leerCantidad() {
        try {
            return Double.parseDouble(in.readLine());
        } catch (Exception e) {
            OperacionIncorrecta();
            System.out.println("El formato debe ser 0.0");
            leerCantidad();
            return 0;
        }
    }

    private void solicitaOperacion() {
        System.out.print("\n---- MENU DE CONVERSION ----\n"
                + "1. - Pesetas a Euros\n"
                + "2. - Euros a Pesetas\n"
                + "3. - Libras a Euros\n"
                + "4. - Euros a Libras\n"
                + "5. - Dolares a Euros\n"
                + "6. - Euros a Dolares\n"
                + "7. - Cambiar vista CLI\n"
                + "8. - Cambiar vista GUI\n"
                + "0. - Salir\n"
                + "Indique la operacion elegida: ");
    }

    private void OperacionIncorrecta() {
        System.out.println("Operacion incorrecta, intentelo de nuevo");
        procesaNuevaOperacion();
    }

    private void procesaNuevaOperacion() {
        solicitaOperacion();
        nroOperacion = leerOpcion();
        switch (nroOperacion) {
            case 0:
                controlador.actionPerformed(new ActionEvent(this, nroOperacion, SALIR));
                break;
            case 1:
                controlador.actionPerformed(new ActionEvent(this, nroOperacion, PESETAS_A_EUROS));
                break;
            case 2:
                controlador.actionPerformed(new ActionEvent(this, nroOperacion, EUROS_A_PESETAS));
                break;
            case 3:
                controlador.actionPerformed(new ActionEvent(this, nroOperacion, LIBRAS_A_EUROS));
                break;
            case 4:
                controlador.actionPerformed(new ActionEvent(this, nroOperacion, EUROS_A_LIBRAS));
                break;
            case 5:
                controlador.actionPerformed(new ActionEvent(this, nroOperacion, DOLARES_A_EUROS));
                break;
            case 6:
                controlador.actionPerformed(new ActionEvent(this, nroOperacion, EUROS_A_DOLARES));
                break;
            case 7:
                controlador.actionPerformed(new ActionEvent(this, nroOperacion, CAMBIO_INTERFAZ_CLI));
                break;
            case 8:
                controlador.actionPerformed(new ActionEvent(this, nroOperacion, CAMBIO_INTERFAZ_GUI));
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

    @Override
    public void detener() {
        System.out.println("\n\nAdios");
    }

}
