/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

/**
 *
 * @author jorgel
 */
public class P1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Punto p1 = new Punto(7,5);
        Punto p2 =new Punto(3,2);
        Punto p3=new Punto(1,2);
        Triangulo t= new Triangulo(p1,p2,p3);
        System.out.println(t.area());
        System.out.println(t.perimetro());
    


        
    }
    
}
