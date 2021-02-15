package testutils.algoritmos;

public class BusquedaBinaria {
    public static int buscar(int[] v, int x) {
        return buscar(v, x, 0, v.length - 1);
    }

    public static int buscar(int[] v, int x, int p, int q) {
        if (p > q) {
            return -1;
        }
        int m = Math.round((p + q) / 2.0f);
        //int m = (p + q) / 2;
        System.out.println("v["+m+"] = "+v[m]);
        if (v[m] == x) {
            return m;
        } else if (v[m] > x) {
            return buscar(v, x, p, m - 1);
        } else {
            return buscar(v, x, m + 1, q);
        }
    }
}
