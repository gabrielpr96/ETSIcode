package libPruebas;

import libClases.*;

public class Prueba1 {
    public static void main(String[] args){
        Alumno a1 = new Alumno(1, "Pepe", "Ramirez", "Malaga");
        a1.addAsignatura(new Asignatura(100, 5.5f, true));
        a1.addAsignatura(new Asignatura(101, 3.1f, true));
        Alumno a2 = new Erasmus(2, "Antuan", "Puyol", "Barcelona", "Republica catalana");
        a2.addAsignatura(new Asignatura(101, 9.8f, true));
        Alumno a3 = new Standard(3, "Juan", "Isidoro", "de la Sierra", "Cuenca");
        a3.addAsignatura(new Asignatura(105, 10f, true));
        a3.addAsignatura(new Asignatura(101, 4.2f, true));
        a3.addAsignatura(new Asignatura(100, 6.8f, true));
        
        System.out.println("a1: "+a1);
        System.out.println("a2: "+a2);
        System.out.println("a3: "+a3);
        
        a1.addAlumno(a2);
        a1.addAlumno(a3);
        
        try {
            Alumno t = a1.buscarAlumno(0);
            System.out.println("El alumno 0 de a1:\n"+t);
        } catch (ExcepcionNoexiste e) {
            System.out.println("Excepcion: "+e);
        }
        
        try {
            Alumno t = a1.buscarAlumno(-1);
            System.out.println("El alumno -1 de a1:\n"+t);
        } catch (ExcepcionNoexiste e) {
            System.out.println("Excepcion: "+e);
        }
    }
}
