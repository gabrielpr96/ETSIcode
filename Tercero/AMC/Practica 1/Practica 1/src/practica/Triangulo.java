package practica;

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
    
    public double area(){
        if(this.area == null)
            this.area = Math.abs((p1.getX()) * (p2.getY() - p3.getY()) + p2.getX() * (p3.getY() - p1.getY()) + p3.getX() * (p1.getY() - p2.getY())) / 2d;
        return area;
    }
    public double perimetro(){
        if(this.perimetro == null)
            this.perimetro = p1.distancia(p2)+p2.distancia(p3)+p3.distancia(p1);
        return perimetro;
    }
    
    public Punto getP1(){
        return p1;
    }
    public Punto getP2(){
        return p2;
    }
    public Punto getP3(){
        return p3;
    }
    
    /**
     * Compara un triangulo con sigo mismo.
     * @param t
     * @return Verdadero si su perimetro es menor al del pasado por parámetro. A igual perímetro, compara si su area es mayor.
     */
    public boolean comparar(Triangulo t){
        return perimetro() < t.perimetro() || (perimetro() == t.perimetro() && area() > t.area());
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
