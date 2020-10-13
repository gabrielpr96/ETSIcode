package practica.pkg1;

public class Triangulo {
    private final Punto p1, p2, p3;
    private Double area, perimetro;
    
    public Triangulo(Punto p1,Punto p2,Punto p3){
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
        this.area = null;
        this.perimetro = null;
    }
    
    public static double area(Punto p1, Punto p2, Punto p3){
        return 0;
    }
    public static double perimetro(Punto p1, Punto p2, Punto p3){
        return 0;
    }
    
    public double area(){
        if(this.area == null)
            this.area = Triangulo.area(p1, p2, p3);
        return area;
    }
    public double perimetro(){
        if(this.perimetro == null)
            this.perimetro = Triangulo.perimetro(p1, p2, p3);
        return perimetro;
    }
    
    @Override
    public String toString(){
        return "["+p1.toString()+", "+p2.toString()+", "+p3.toString()+"]";
    }
}
