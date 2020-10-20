package practica.pkg1;

public class Linea {
    private final Punto p1, p2;
    private Double longitud;
    
    public Linea(Punto p1,Punto p2){
        this.p1 = p1;
        this.p2 = p2;
        this.longitud = null;
    }
    
    public static double longitud(Linea l){
        return Punto.distancia(l.p1, l.p2);
    }
    
    public double longitud(){
        if(this.longitud == null)
            this.longitud = Linea.longitud(this);
        return longitud;
    }
    
    @Override
    public boolean equals(Object obj){
        if(obj == null || obj.getClass() != Linea.class){
            return false;
        }else{
            Linea lObj = (Linea)obj;
            return (p1 == lObj.p1 && p2 == lObj.p2) || (p1 == lObj.p2 && p2 == lObj.p1);
        }
    }
    
    @Override
    public String toString(){
        return "["+p1.toString()+", "+p2.toString()+"]";
    }
}
