package practica;

public class Arista implements Comparable{
    private final Punto vertice1, vertice2;
    private final Linea linea;
    
    public Arista(Punto v1, Punto v2){
        vertice1 = v1;
        vertice2 = v2;
        linea = new Linea(vertice1, vertice2);
    }
    
    public Punto getVertice1(){
        return vertice1;
    }
    public Punto getVertice2(){
        return vertice2;
    }
    public Linea getLinea(){
        return linea;
    }
    public double coste(){
        return linea.longitud();
    }

    @Override
    public boolean comparar(Object o, boolean sortType) {
        if(o == null || o.getClass() != Arista.class)
            return false;
        Arista a = (Arista)o;
        return coste() < a.coste();
    }
    
    @Override
    public String toString(){
        return vertice1+" "+vertice1.hashCode()+" <--("+coste()+")--> "+vertice2.hashCode()+" "+vertice2;
    }
            
}
