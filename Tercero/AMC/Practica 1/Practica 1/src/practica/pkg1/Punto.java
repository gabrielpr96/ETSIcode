package practica.pkg1;

public class Punto {
    private double x, y;
    
    public Punto(int x, int y){
        this.x = x;
        this.y = y;
    }
    
    public double getX(){
        return x;
    }
    public double getY(){
        return y;
    }
    
    @Override
    public String toString(){
        return "("+x+", "+y+")";
    }
}
