package practica2;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AMCPractica2 {

    public static void main(String[] args) throws IOException, InterruptedException {
        VentanaPrincipal frame = new VentanaPrincipal();
        frame.setVisible(true);
        
        AutomataDrawer.setVentanaPrincipal(frame);
        test();
    }
    
    private static void test(){
        String baseDir = "C:\\PROJECTOS\\ETSIcode\\Tercero\\AMC\\Practica 2\\Datasets\\";
        try {
            AutomataDeterminista automata = Parser.parseFileAFD(baseDir+"Test1.afd");
            AFD afd = new AFD(automata);
            try {
                System.out.println("Entrada 01101 en automata 1: "+(afd.reconocer("01101")?"Valida":"No valida"));
            } catch (Exception e) {
                System.out.println("Excepcion: "+e.getMessage());
            }
            try {
                System.out.println("Entrada 0110 en automata 1: "+(afd.reconocer("0110")?"Valida":"No valida"));
            } catch (Exception e) {
                System.out.println("Excepcion: "+e.getMessage());
            }
            
            automata = Parser.parseFileAFD(baseDir+"Test2.afd");
            afd = new AFD(automata);
            try {
                System.out.println("Entrada 112312211 en automata 2: "+(afd.reconocer("112312211")?"Valida":"No valida"));
            } catch (Exception e) {
                System.out.println("Excepcion: "+e.getMessage());
            }
            try {
                System.out.println("Entrada 1123122 en automata 2: "+(afd.reconocer("1123122")?"Valida":"No valida"));
            } catch (Exception e) {
                System.out.println("Excepcion: "+e.getMessage());
            }
            try {
                System.out.println("Entrada 122312 en automata 2: "+(afd.reconocer("122312")?"Valida":"No valida"));
            } catch (Exception e) {
                System.out.println("Excepcion: "+e.getMessage());
            }
        } catch (IOException ex) {
            Logger.getLogger(AMCPractica2.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            AutomataNoDeterminista automata = Parser.parseFileAFND(baseDir+"Test3.afnd");
            System.out.println(automata);
            AFND afnd = new AFND(automata);
            try {
                System.out.println("Entrada 112 en automata 3: "+(afnd.reconocer("112")?"Valida":"No valida"));
            } catch (Exception e) {
                System.out.println("Excepcion: "+e.getMessage());
            }
            try {
                System.out.println("Entrada 1123 en automata 3: "+(afnd.reconocer("1123")?"Valida":"No valida"));
            } catch (Exception e) {
                System.out.println("Excepcion: "+e.getMessage());
            }
            try {
                System.out.println("Entrada 112321 en automata 3: "+(afnd.reconocer("112321")?"Valida":"No valida"));
            } catch (Exception e) {
                System.out.println("Excepcion: "+e.getMessage());
            }
        } catch (IOException ex) {
            Logger.getLogger(AMCPractica2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
