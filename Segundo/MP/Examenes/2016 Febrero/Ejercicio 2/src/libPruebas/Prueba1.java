package libPruebas;

import libProfesor.*;

public class Prueba1 {
    public static void main(String[] args) {
    ClaseA a=new ClaseA(5), b=new ClaseA(), c=new ClaseA();//a con los 5 1º nº impares, b y c vacios
    ClaseA x=new ClaseB("BBVA"); //x vacio y cadena BBVA
    ClaseB y=new ClaseB("AVE", 2), z, j=new ClaseB(y); //y,j con los 2 1º nº impares y cadena AVE
    c=a;
    z=y;
    ((ClaseB)x).setCadena("OHL"); //x con la cadena OHL
    if (ClaseA.mayor(a,b)) //true si n de a es mayor que n de b
    System.out.println("a:" + a);
    System.out.println("a:" + a + "\nb:" + b + "\nc:" + c);
    System.out.println(c.get(4) + "," + j.get(0));
    System.out.println("x contiene la cadena:" + ((ClaseB)x).getCadena());
    }
}
