package testutils;

import java.util.Arrays;
import testutils.algoritmos.*;

public class TestUtils {

    public static void main(String[] args) {
        //BusquedaBinaria.buscar(new int[]{3, 5, 6, 8, 11, 12, 14, 15, 17, 18}, 16);
        //Mergesort.ordena(new int[]{21, 1, 26, 45, 29, 28, 2, 9, 16, 49, 39, 27, 43, 34, 46, 40});
        //BusquedaSecuencial.buscar(new int[]{15, 18, 2, 19, 18, 0, 8, 14, 19, 14}, 18);
        //SeleccionSortMax.ordenar(new int[]{11, 7, 12, 14, 19, 1, 6, 18, 8, 20});
        //InsercionSort.ordenar(new int[]{15, 5, 4, 18, 12, 19, 14, 10, 8, 20});
        //BusquedaSecuencialOrdenada.buscar(new int[]{3, 5, 6, 8, 11, 12, 14, 15, 17, 18}, 13);
        //QuickSort.ordenar(new int[]{14, 17, 13, 15, 19, 10, 3, 16, 9, 12});
        //BusquedaBinaria.buscar(new int[]{3, 5, 6, 8, 11, 12, 14, 15, 17, 18}, 9);
        ShellSort.ordenar(new int[]{5, 16, 20, 12, 3, 8, 9, 17, 19, 7}, 3);
    }

}
