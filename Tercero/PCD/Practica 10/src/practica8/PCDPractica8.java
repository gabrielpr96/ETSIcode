package practica8;

import registro.Registro;
import java.awt.Color;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class PCDPractica8 {
    
    private static final int MAX_CLIENTES = 25;
    
    public static void main(String[] args) throws InterruptedException, IOException {
        CanvasRegistro canvas = new CanvasRegistro(3, 1);
        
        JFrame f = new JFrame();
        canvas.setSize(390, 700);
        canvas.setBackground(new Color(205, 250, 212));
        f.add(canvas);
        f.setTitle("Practica 8: Borja López");
        f.setResizable(false);
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        f.pack();
        f.setLocationRelativeTo(null);
        f.setVisible(true);
        
        Registro registro = new Registro(canvas);
        
        Registry reg = LocateRegistry.createRegistry(5144);
        reg.rebind("registroremoto", registro);
        
        System.out.println("Pulsa enter para cerrar...");
        System.in.read();
        System.exit(0);
    }
    
}
