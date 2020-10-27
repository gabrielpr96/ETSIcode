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
public class Triangulo {

    private Punto p[] = new Punto[3];
    private Double area, perimetro;


    public Triangulo() {
    }

    ;
    public Triangulo(Punto p1, Punto p2, Punto p3) {
        this.p[0] = p1;
        this.p[1] = p2;
        this.p[2] = p3;
    }

    ;
    public Triangulo(Triangulo t) {
        this.p[0] = t.getp()[0];
        this.p[1] = t.getp()[1];
        this.p[2] = t.getp()[2];
    }

    ;
    public double area() {
        
        this.area = Math.abs((p[0].getx()) * (p[1].gety() - p[2].gety()) + p[1].getx() * (p[2].gety() - p[0].gety()) + p[2].getx() * (p[0].gety() - p[1].gety())) / 2d;
        return area;
    }

    public double perimetro() {
        
        double da = Math.sqrt(Math.abs(Math.pow((p[1].getx() - p[0].getx()),2) + Math.pow(p[1].gety() - p[0].gety(),2)));
        double db = Math.sqrt(Math.abs(Math.pow(p[2].getx() - p[0].getx(),2) + Math.pow(p[2].gety() - p[0].gety(),2)));
        double dc = Math.sqrt(Math.abs(Math.pow(p[2].getx() - p[1].getx(),2) + Math.pow(p[2].gety() - p[1].gety(),2)));
        perimetro = da + db + dc;

        return perimetro;
    }

    ;

    ;
    public Punto[] getp() {
        return p;
    }
;
}
