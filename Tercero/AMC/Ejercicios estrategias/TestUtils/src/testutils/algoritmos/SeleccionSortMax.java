package testutils.algoritmos;

import java.util.Arrays;

public class SeleccionSortMax {

    public static void ordenar(int[] v) {
        int i, j, maxNum, max, temp = 0;
        for (i = v.length - 1; i >= 0; i--) {
            maxNum = v[i];
            max = i;
            for (j = 0; j < i; j++) {
                if (v[j] > maxNum) {
                    maxNum = v[j];
                    max = j;
                }
            }
            temp = v[i];
            v[i] = v[max];
            v[max] = temp;
            System.out.println("Pasada " + (v.length - i - 1) + ": " + Arrays.toString(v));
        }
    }
}
