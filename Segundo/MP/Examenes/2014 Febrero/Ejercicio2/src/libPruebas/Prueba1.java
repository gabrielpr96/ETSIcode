package libPruebas;

import libClases.*;

public class Prueba1 {
    public static void main(String[] args) {
        Persona a=new Persona("juan", 23, 1), b=new Persona(), c=new Persona(b);
        Persona x=new Alumno("UHU", "pepe", 23, 2);
        Alumno y=new Alumno ("UCO"), z, j=new Alumno("UHU", a);
        c=a;
        z=y;
        ((Alumno) x).cambiarUniversidad("UAM"); //el alumno x se cambia a la universidad UAM
        if (Persona.mayor(b,a)) //true si b tiene mas años que a
        System.out.println("b:" + b);
        System.out.println(z);
    }
}
