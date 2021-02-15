package testutils.algoritmos;

public class BusquedaSecuencial {
    public static int buscar(int[] v, int x){
        for (int i = 0; i < v.length; i++) {
            System.out.println("v["+i+"] = "+v[i]);
            if(v[i] == x)
                return i;
        }
        return -1;
    }
}
