package testutils.algoritmos;

import java.util.Arrays;

public class InsercionSort {

    public static void ordenar(int[] v) {
        int n = v.length; 
        for (int i = 1; i < n; ++i) { 
            int key = v[i]; 
            int j = i - 1; 
  
            while (j >= 0 && v[j] > key) { 
                v[j + 1] = v[j]; 
                j = j - 1; 
            } 
            v[j + 1] = key; 
            
            System.out.println("Pasada "+(i)+": "+Arrays.toString(v));
        }
    }
}
