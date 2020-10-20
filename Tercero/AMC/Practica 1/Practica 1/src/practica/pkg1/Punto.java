package practica.pkg1;

public class Punto {
    private final double x, y;
    
    public Punto(double x, double y){
        this.x = x;
        this.y = y;
    }
    
    public double getX(){
        return x;
    }
    public double getY(){
        return y;
    }
    
    public double distancia(Punto p){
        return Punto.distancia(this, p);
    }
    
    @Override
    public String toString(){
        return "("+x+", "+y+")";
    }
    
    @Override
    public boolean equals(Object obj){
        if(obj == null || obj.getClass() != Punto.class){
            return false;
        }else{
            Punto pObj = (Punto)obj;
            return Math.abs(x-pObj.x) <= 0.000001 && Math.abs(y-pObj.y) <= 0.000001;
        }
    }
    
    public static double distancia(Punto p1, Punto p2){
        return Math.sqrt(Math.pow(p1.x-p2.x, 2)+Math.pow(p1.y-p2.y, 2));
    }
}
