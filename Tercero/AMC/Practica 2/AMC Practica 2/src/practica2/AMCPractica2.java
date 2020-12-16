package practica2;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AMCPractica2 {

    public static void main(String[] args) {
        VentanaPrincipal frame = new VentanaPrincipal();
        frame.setVisible(true);
        test();
    }
    
    private static void test(){
        String baseDir = "C:\\PROJECTOS\\ETSIcode\\Tercero\\AMC\\Practica 2\\Datasets\\";
        try {
            AutomataDeterminista automata = Parser.parseFileAFD(baseDir+"Test1.afd");
            AFD afd = new AFD(automata);
            try {
                System.out.println("Entrada 01101 en automata 1: "+(afd.reconocer(new String[]{"0", "1", "1", "0", "1"})?"Valida":"No valida"));
            } catch (Exception e) {
                System.out.println("Excepcion: "+e.getMessage());
            }
            try {
                System.out.println("Entrada 0110 en automata 1: "+(afd.reconocer(new String[]{"0", "1", "1", "0"})?"Valida":"No valida"));
            } catch (Exception e) {
                System.out.println("Excepcion: "+e.getMessage());
            }
            
            automata = Parser.parseFileAFD(baseDir+"Test2.afd");
            afd = new AFD(automata);
            try {
                System.out.println("Entrada 112312211 en automata 2: "+(afd.reconocer(new String[]{"1", "1", "2", "3", "1", "2", "2", "1", "1"})?"Valida":"No valida"));
            } catch (Exception e) {
                System.out.println("Excepcion: "+e.getMessage());
            }
            try {
                System.out.println("Entrada 1123122 en automata 2: "+(afd.reconocer(new String[]{"1", "1", "2", "3", "1", "2", "2"})?"Valida":"No valida"));
            } catch (Exception e) {
                System.out.println("Excepcion: "+e.getMessage());
            }
            try {
                System.out.println("Entrada 122312 en automata 2: "+(afd.reconocer(new String[]{"1", "2", "2", "3", "1", "2"})?"Valida":"No valida"));
            } catch (Exception e) {
                System.out.println("Excepcion: "+e.getMessage());
            }
        } catch (IOException ex) {
            Logger.getLogger(AMCPractica2.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            AutomataNoDeterminista automata = Parser.parseFileAFND(baseDir+"Test3.afnd");
            AFND afnd = new AFND(automata);
            try {
                System.out.println("Entrada 112 en automata 3: "+(afnd.reconocer(new String[]{"1", "1", "2"})?"Valida":"No valida"));
            } catch (Exception e) {
                System.out.println("Excepcion: "+e.getMessage());
            }
            try {
                System.out.println("Entrada 1123 en automata 3: "+(afnd.reconocer(new String[]{"1", "1", "2", "3"})?"Valida":"No valida"));
            } catch (Exception e) {
                System.out.println("Excepcion: "+e.getMessage());
            }
            try {
                System.out.println("Entrada 112321 en automata 3: "+(afnd.reconocer(new String[]{"1", "1", "2", "3", "2", "1"})?"Valida":"No valida"));
            } catch (Exception e) {
                System.out.println("Excepcion: "+e.getMessage());
            }
        } catch (IOException ex) {
            Logger.getLogger(AMCPractica2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
