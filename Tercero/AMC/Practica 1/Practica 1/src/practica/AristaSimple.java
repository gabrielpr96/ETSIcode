package practica;

public class AristaSimple implements Comparable{
    public int v1, v2;
    public double coste;

    public AristaSimple(int v1, int v2, double coste) {
        this.v1 = v1;
        this.v2 = v2;
        this.coste = coste;
    }

    @Override
    public boolean comparar(Object o, boolean sortType) {
        if(o == null || o.getClass() != AristaSimple.class)
            return false;
        else{
            AristaSimple obj = (AristaSimple)o;
            return coste < obj.coste;
        }
    }
}
