package testutils.algoritmos;

import java.util.Arrays;

public class QuickSort {

    public static void ordenar(int[] v) {
        ordenar(v, 0, v.length - 1, 0);
        System.out.println("Final -> " + Arrays.toString(v));
    }

    public static void ordenar(int[] v, int p, int q, int level) {
        if(p >= q) return;
        int m = partition(v, p, q);

        System.out.println("Nivel: " + level + " -> " + Arrays.toString(v));
        
        ordenar(v, p, m - 1, level + 1);
        ordenar(v, m + 1, q, level + 1);
        
    }

    public static int partition(int[] v, int p, int q) {
        int pivot = v[p];
        
        int l = p+1;
        int r = q;
        
        boolean hecho = false;
        while(!hecho){
            while(l <= r && v[l] <= pivot)
                l++;
            while(v[r] >= pivot && r >= l)
                r--;
            if(r < l)
                hecho = true;
            else{
                int tmp = v[l];
                v[l] = v[r];
                v[r] = tmp;
            }
        }
        
        int tmp = v[p];
        v[p] = v[r];
        v[r] = tmp;
        
        return r;
    }
}
