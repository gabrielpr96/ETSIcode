package testutils.algoritmos;

public class BusquedaSecuencialOrdenada {
    public static int buscar(int[] v, int x){
        for (int i = 0; i < v.length; i++) {
            System.out.println("v["+i+"] = "+v[i]);
            if(v[i] == x)
                return i;
            else if(v[i] > x)
                return -1;
        }
        return -1;
    }
}
