package libPruebas;

import libClases.*;

public class Prueba1 {
    public static void main(String[] args){
        Alumno a1 = null, a2 = null, a3 = null;
        try {
            a1 = new Local("001", "Pepe", "Fernandez", "Ortega", "C/ Perez Cubillas", 5);
            a2 = new Erasmus("002", "Pedro", "Cajal", "Italia", "C/ Fabrica fundidores", 10);
            a1.addAlumno(new Local("003", "Isidoro", "Tejero", "Montrial", "Avda. Guatemala", 2));
            a3 = new Erasmus("004", "Iñaqui", "Puyol", "Republica catalana", "C/ Prat de lobregat", 5);
        } catch (ExceptionFormatoInvalido e) {
            System.out.println("Error: "+e);
        }
        a1.addAsignatura(101);
        a2.addAsignatura(102);
        a1.buscarAlumno(0).addAsignatura(103);
        a1.buscarAlumno(0).addAsignatura(104);
        
        System.out.println("a1: "+a1);
        System.out.println("a2: "+a2);
        System.out.println("a1 0: "+a1.buscarAlumno(0));
        System.out.println("a1 1: "+a1.buscarAlumno(1));
        
    }
}
