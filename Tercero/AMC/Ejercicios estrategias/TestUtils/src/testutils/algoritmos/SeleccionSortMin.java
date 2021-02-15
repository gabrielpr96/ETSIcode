package testutils.algoritmos;

import java.util.Arrays;

public class SeleccionSortMin {

    public static void ordenar(int[] v) {
        int n = v.length;
        for (int i = 0; i < n - 1; i++) {
            int min = i;
            for (int j = i + 1; j < n; j++) {
                if (v[j] < v[min]) {
                    min = j;
                }
            }
            int temp = v[min];
            v[min] = v[i];
            v[i] = temp;
            
            System.out.println("Pasada "+(i+1)+": "+Arrays.toString(v));
        }
    }
}
