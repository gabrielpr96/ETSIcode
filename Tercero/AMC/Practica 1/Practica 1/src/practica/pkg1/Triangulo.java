package practica.pkg1;

import java.util.ArrayList;

public class Triangulo {
    private final Punto p1, p2, p3;
    private Double area, perimetro;
    
    public Triangulo(Punto p1, Punto p2, Punto p3){
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
        this.area = null;
        this.perimetro = null;
    }
    
    public static double area(Triangulo t){
        return area(t.p1, t.p2, t.p3);
    }
    public static double area(Punto p1, Punto p2, Punto p3){
        return Math.abs((p1.getX()) * (p2.getY() - p3.getY()) + p2.getX() * (p3.getY() - p1.getY()) + p3.getX() * (p1.getY() - p2.getY())) / 2d;
    }
    public static double perimetro(Triangulo t){
        return perimetro(t.p1, t.p2, t.p3);
    }
    public static double perimetro(Punto p1, Punto p2, Punto p3){
        return p1.distancia(p2)+p2.distancia(p3)+p3.distancia(p1);
    }
    
    public double area(){
        if(this.area == null)
            this.area = Triangulo.area(this);
        return area;
    }
    public double perimetro(){
        if(this.perimetro == null)
            this.perimetro = Triangulo.perimetro(this);
        return perimetro;
    }
    
    @Override
    public String toString(){
        return "["+p1.toString()+", "+p2.toString()+", "+p3.toString()+"]";
    }
    
    @Override
    public boolean equals(Object obj){
        if(obj == null || obj.getClass() != Triangulo.class){
            return false;
        }else{
            ArrayList<Punto> puntos = new ArrayList<>();
            puntos.add(p1);
            puntos.add(p2);
            puntos.add(p3);
            Triangulo tObj = (Triangulo)obj;
            puntos.remove(tObj.p1);
            puntos.remove(tObj.p2);
            puntos.remove(tObj.p3);
            return puntos.isEmpty();
        }
    }
}
