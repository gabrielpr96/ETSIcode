package testutils.algoritmos;

import java.util.Arrays;

public class ShellSort {
    public static void ordenar(int[] v){
        ordenar(v, v.length);
    }
    
    public static void ordenar(int[] v, int contadorSublistas){
        while(contadorSublistas > 0){
            for (int posicionInicio = 0; posicionInicio < contadorSublistas; posicionInicio++) {
                brechaOrdenamientoPorInsercion(v, posicionInicio, contadorSublistas);
            }
            
            System.out.println("Tamano: "+contadorSublistas+": "+Arrays.toString(v));
            contadorSublistas -= 1;
        }
    }
    
    public static void brechaOrdenamientoPorInsercion(int[] v, int inicio, int brecha){
        for (int i = inicio+brecha; i < v.length ; i += brecha) {
            int valorActual = v[i];
            int posicion = i;
            while(posicion >= brecha && v[posicion-brecha] > valorActual){
                v[posicion] = v[posicion-brecha];
                posicion = posicion-brecha;
            }
            v[posicion] = valorActual;
        }
    }
}
