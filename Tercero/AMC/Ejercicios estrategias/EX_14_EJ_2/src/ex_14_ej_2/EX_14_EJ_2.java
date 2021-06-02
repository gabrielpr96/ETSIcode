package ex_14_ej_2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class EX_14_EJ_2 {

    private static final double INF = Double.POSITIVE_INFINITY;

    public static void main(String[] args) {
       /*
        double[][] matriz = {
            {0, 9, INF, INF, 8, 1},
            {9, 0, 3, INF, INF, 2},
            {INF, 3, 0, 5, INF, 4},
            {INF, INF, 5, 0, 7, INF},
            {8, INF, INF, 7, 0, 6},
            {1, 2, 4, INF, 6, 0},
        };
        Arista[] res = prim(matriz);
        int total = 0;
        for (Arista re : res) {
            System.out.println((re.v1+1) + " --> " + (re.v2+1)+"  ("+re.etiqueta+")");
            total += re.etiqueta;
        }
        System.out.println("Coste: "+total);
         */
 /*
        double[][] matriz = {
            {0, 4, INF, INF, INF, INF, INF, 8, INF},
            {4, 0, 8, INF, INF, INF, INF, 11, INF},
            {INF, 8, 0, 7, INF, 4, INF, INF, 2},
            {INF, INF, 7, 0, 9, 14, INF, INF, INF},
            {INF, INF, INF, 9, 0, 10, INF, INF, INF},
            {INF, INF, 4, 14, 10, 0, 2, INF, INF},
            {INF, INF, INF, INF, INF, 2, 0, 1, 6},
            {8, 11, INF, INF, INF, INF, 1, 0, 7},
            {INF, INF, 2, INF, INF, INF, 6, 7, 0}
        };
        Arista[] res = prim(matriz);
        int total = 0;
        for (Arista re : res) {
            System.out.println(re.v1 + " --> " + re.v2+"  ("+re.etiqueta+")");
            total += re.etiqueta;
        }
        System.out.println("Coste: "+total);
         */

        Arista[] aristas = new Arista[]{
            new Arista(0, 1, 4),
            new Arista(0, 7, 8),
            new Arista(1, 7, 11),
            new Arista(1, 2, 8),
            new Arista(7, 6, 1),
            new Arista(7, 8, 7),
            new Arista(2, 8, 2),
            new Arista(6, 8, 6),
            new Arista(2, 3, 7),
            new Arista(6, 5, 2),
            new Arista(2, 5, 4),
            new Arista(3, 4, 9),
            new Arista(5, 4, 10),
            new Arista(3, 5, 14)
        };
        Arista[] res = kruskal(aristas, 9);
        int total = 0;
        System.out.println();
        for (Arista re : res) {
            System.out.println(re);
            total += re.etiqueta;
        }
        System.out.println("Coste: " + total);

        /*
        System.out.println(fDyV(4));
        System.out.println(fDinamica(4));
        System.out.println(fDirecta(4));
         */
 /*
        double[][] R = {
            {0, 20, 75, 60, 0, 0},
            {0, 0, 2, 30, 25, 0},
            {0, 100, 0, 0, 200, 0},
            {0, 0, 0, 0, 0, 20},
            {0, 0, 40, 3, 0, 25},
            {0, 0, 0, 0, 0, 0}
        };
        Integer[] camino = dijkstra(R, 6, 1, 6);
        for (Integer v : camino) {
            System.out.print("V"+(v+1)+" <- ");
        }
        System.out.println();
         */
 /*
        int n = 5;
        int M = 13;
        int[] t = new int[]{6, 4, 2, 4, 5};
        int[] x = aprobarBacktracking(n, t, M);
        //int[] x = aprobarDinamica(n, t, M);
        //int[] x = aprobarVoraz(n, t, M);
        for (int j = 0; j < 3; j++) {
            int total = 0;
            System.out.print("M" + (j + 1) + ": ");
            for (int i = 0; i < n; i++) {
                if (x[i] == j + 1) {
                    System.out.print(i + "(" + t[i] + ") ");
                    total += t[i];
                }
            }
            System.out.println(" = " + total);
        }
         */
        //System.out.println("Existe algun algun fixed point: "+(busquedaPuntoFijo(new int[]{-3, -2, 0, 2, 3, 5, 7, 9, 12})?"Si":"No"));

        /*
        int n = 3, M = 5;
        int[] p = {1, 1, 4};
        int[] b = {2, 3, 6};
        //boolean[] xMochila = mochila(n, M, p, b);
        //System.out.println(Arrays.toString(xMochila));
        boolean[] xDinamica = dinamica(n, M, p, b);
        System.out.println(Arrays.toString(xDinamica));
        boolean[] xBacktracking = backtracking(n, M, p, b);
        System.out.println(Arrays.toString(xBacktracking));
        */
/*
        int t = 0;
        for (int i = 0; i < n; i++) {
            if (xMochila[i]) {
                t += b[i];
                System.out.print(b[i] + "(" + p[i] + ") ");
            }
        }
        System.out.println(" = " + t);*/
/*
        int t = 0;
        for (int i = 0; i < n; i++) {
            if (xDinamica[i]) {
                t += b[i];
                System.out.print(b[i] + "(" + p[i] + ") ");
            }
        }
        System.out.println(" = " + t);
        t = 0;
        for (int i = 0; i < n; i++) {
            if (xBacktracking[i]) {
                t += b[i];
                System.out.print(b[i] + "(" + p[i] + ") ");
            }
        }
        System.out.println(" = " + t);
        
                /*
        int n = 5;
        double[][] matriz = {
            {0, 3, 10, INF, INF},
            {3, 0, INF, 5, INF},
            {10, INF, 0, 6, 15},
            {INF, 5, 6, 0, 4},
            {INF, INF, 15, 4, 0},};
         */
 /*
        int n = 5;
        double[][] matriz = {
            {0, 3, 10, INF, INF},
            {3, 0, INF, 5, INF},
            {10, INF, 0, 6, 15},
            {INF, 5, 6, 0, 4},
            {INF, INF, INF, 4, 0}
        };
        double[][] C = new double[n][n];
        int[][] D = new int[n][n];
        Floyd(n, matriz, C, D);
        System.out.println(floydDistancia(1, 5, C));
        System.out.println(floydCamino(1, 5, D));
         */
 /*
        int n = 3, M = 6;
        int[] p = {5, 3, 3};
        int[] b = {11, 6, 6};
        double[] xMochilaFraccionada = mochilaFraccionada(n, M, p, b);
        System.out.println(Arrays.toString(xMochilaFraccionada));

        int t = 0;
        for (int i = 0; i < n; i++) {
            t += b[i] * xMochilaFraccionada[i];
            System.out.print(b[i] + "(" + xMochilaFraccionada[i] + ") ");
        }
        System.out.println(" = " + t);
         */
    }

    public static void Floyd(int n, double matriz[][], double C[][], int[][] D) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                C[i][j] = matriz[i][j];
            }
            C[i][i] = 0;
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (matriz[i][j] == INF) {
                    D[i][j] = -1;
                } else {
                    D[i][j] = i;
                }
            }
            D[i][i] = 0;
        }
        System.out.println("1 C");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    System.out.print("-\t");
                } else {
                    System.out.print(C[i][j] + "\t");
                }
            }
            System.out.println();
        }
        System.out.println("1 D");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    System.out.print("- ");
                } else if (D[i][j] == -1) {
                    System.out.print("  ");
                } else {
                    System.out.print((D[i][j] + 1) + " ");
                }
            }
            System.out.println();
        }
        System.out.println();
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (i != k && j != k && i != j && C[i][k] + C[k][j] < C[i][j]) {
                        C[i][j] = C[i][k] + C[k][j];
                        D[i][j] = D[k][j];
                    }
                }
            }

            System.out.println((k + 2) + " C");
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (i == j) {
                        System.out.print("-\t");
                    } else {
                        System.out.print(C[i][j] + "\t");
                    }
                }
                System.out.println();
            }
            System.out.println((k + 2) + " D");
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (i == j) {
                        System.out.print("- ");
                    } else if (D[i][j] == -1) {
                        System.out.print("  ");
                    } else {
                        System.out.print((D[i][j] + 1) + " ");
                    }
                }
                System.out.println();
            }
            System.out.println();
        }
    }

    public static double floydDistancia(int i, int j, double[][] C) {
        return C[i - 1][j - 1];
    }

    public static ArrayList<Integer> floydCamino(int i, int j, int[][] D) {
        i--;
        j--;
        ArrayList<Integer> camino = new ArrayList<>();
        int x = j;
        camino.add(x + 1);
        while (x != i) {
            x = D[i][x];
            camino.add(x + 1);
        }
        return camino;
    }

    public static int[] aprobarVoraz(int n, int[] t, int M) {
        int[] x = new int[n];
        int c = 1, m = M;
        for (int i = 0; i < n; i++) {
            if (t[i] > m) {
                c++;
                m = M;
            }
            x[i] = c;
            m -= t[i];
        }
        return x;
    }

    static class ClaveDinamicaAprobar {

        private final int n;
        private final int t[];
        private final int M;
        private final int[] x;
        private final int p;

        public ClaveDinamicaAprobar(int n, int[] t, int M, int[] x, int p) {
            this.n = n;
            this.M = M;
            this.p = p;
            this.t = new int[t.length];
            System.arraycopy(t, 0, this.t, 0, n);
            this.x = new int[x.length];
            System.arraycopy(x, 0, this.x, 0, n);
        }

        @Override
        public boolean equals(Object obj) {
            if (obj.getClass() == ClaveDinamicaAprobar.class) {
                ClaveDinamicaAprobar o = (ClaveDinamicaAprobar) obj;
                if (!(n == o.n && M == o.M && p == o.p)) {
                    return false;
                }
                for (int i = 0; i < n; i++) {
                    if (!(t[i] == o.t[i] && x[i] == o.x[i])) {
                        return false;
                    }
                }
                return true;
            } else {
                return false;
            }
        }

        @Override
        public int hashCode() {
            int hash = n + p + M;
            return hash;
        }

    }

    public static int[] aprobarDinamica(int n, int t[], int M) {
        int[] x = new int[n];
        HashMap<ClaveDinamicaAprobar, int[]> V = new HashMap();
        aprobarDinamica(n, t, M, x, 0, V);
        return x;
    }

    public static void aprobarDinamica(int n, int t[], int M, int x[], int p, HashMap<ClaveDinamicaAprobar, int[]> V) {
        if (p >= n) {
            return;
        }
        ClaveDinamicaAprobar clave = new ClaveDinamicaAprobar(n, t, M, x, p);
        int[] xGuardado = V.get(clave);
        if (xGuardado == null) {
            int[] xMejor = new int[n];
            boolean primera = true;

            if (aprobarContar(n, t, x, 1) + t[p] <= M) {
                x[p] = 1;
                aprobarDinamica(n, t, M, x, p + 1, V);
                if (primera || aprobarComparar(n, t, new int[][]{x, xMejor}) == 0) {
                    System.arraycopy(x, 0, xMejor, 0, n);
                    primera = false;
                }
            }

            if (aprobarContar(n, t, x, 2) + t[p] <= M) {
                x[p] = 2;
                aprobarDinamica(n, t, M, x, p + 1, V);
                if (primera || aprobarComparar(n, t, new int[][]{x, xMejor}) == 0) {
                    System.arraycopy(x, 0, xMejor, 0, n);
                    primera = false;
                }
            }

            if (aprobarContar(n, t, x, 3) + t[p] <= M) {
                x[p] = 3;
                aprobarDinamica(n, t, M, x, p + 1, V);
                if (primera || aprobarComparar(n, t, new int[][]{x, xMejor}) == 0) {
                    System.arraycopy(x, 0, xMejor, 0, n);
                    primera = false;
                }
            }

            System.arraycopy(xMejor, 0, x, 0, n);
            V.put(clave, xMejor);
        } else {
            System.arraycopy(xGuardado, 0, x, 0, n);
        }
    }

    public static int[] aprobarBacktracking(int n, int t[], int M) {
        int[] x = new int[n];
        aprobarBacktracking(n, t, M, x, 0);
        return x;
    }

    public static void aprobarBacktracking(int n, int t[], int M, int x[], int p) {
        if (p >= n) {
            return;
        }
        int[] xMejor = new int[n];
        boolean primera = true;

        if (aprobarContar(n, t, x, 1) + t[p] <= M) {
            x[p] = 1;
            aprobarBacktracking(n, t, M, x, p + 1);
            if (primera || aprobarComparar(n, t, new int[][]{x, xMejor}) == 0) {
                System.arraycopy(x, 0, xMejor, 0, n);
                primera = false;
            }
        }

        if (aprobarContar(n, t, x, 2) + t[p] <= M) {
            x[p] = 2;
            aprobarBacktracking(n, t, M, x, p + 1);
            if (primera || aprobarComparar(n, t, new int[][]{x, xMejor}) == 0) {
                System.arraycopy(x, 0, xMejor, 0, n);
                primera = false;
            }
        }

        if (aprobarContar(n, t, x, 3) + t[p] <= M) {
            x[p] = 3;
            aprobarBacktracking(n, t, M, x, p + 1);
            if (primera || aprobarComparar(n, t, new int[][]{x, xMejor}) == 0) {
                System.arraycopy(x, 0, xMejor, 0, n);
                primera = false;
            }
        }

        System.arraycopy(xMejor, 0, x, 0, n);
    }

    public static int aprobarComparar(int n, int t[], int[][] x) {
        int[][] w = new int[3][x.length];
        for (int j = 0; j < x.length; j++) {
            for (int i = 0; i < n; i++) {
                if (x[j][i] != 0) {
                    w[x[j][i] - 1][j] += t[i];
                }
            }
        }
        int min = 0;
        for (int i = 1; i < x.length; i++) {
            if (w[2][i] < w[2][min] || (w[2][i] == w[2][min] && w[1][i] < w[1][min])) {
                min = i;
            }
        }
        return min;
    }

    public static int aprobarContar(int n, int[] t, int[] x, int m) {
        int total = 0;
        for (int i = 0; i < n; i++) {
            if (x[i] == m) {
                total += t[i];
            }
        }
        return total;
    }

    public static double[] mochilaFraccionada(int n, int M, int[] p, int[] b) {
        double[] x = new double[n];
        for (int i = 0; i < n; i++) {
            x[i] = 0;
        }

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < n - i; j++) {
                if (b[j] / (float) p[j] < b[j + 1] / (float) p[j + 1]) {
                    int tmp = b[j];
                    b[j] = b[j + 1];
                    b[j + 1] = tmp;
                    tmp = p[j];
                    p[j] = p[j + 1];
                    p[j + 1] = tmp;
                }
            }
        }

        int i = 0;
        while (i < n && M >= p[i]) {
            x[i] = 1;
            M -= p[i];
            i++;
        }
        if (i < n) {
            x[i] = (M) / (double) p[i];
            i++;
            while (i < n) {
                x[i] = 0;
                i++;
            }
        }

        return x;
    }

    public static boolean[] mochila(int n, int M, int[] p, int[] b) {
        boolean[] x = new boolean[n];
        for (int i = 0; i < n; i++) {
            x[i] = false;
        }

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < n - i; j++) {
                if (b[j] / (float) p[j] < b[j + 1] / (float) p[j + 1]) {
                    int tmp = b[j];
                    b[j] = b[j + 1];
                    b[j + 1] = tmp;
                    tmp = p[j];
                    p[j] = p[j + 1];
                    p[j + 1] = tmp;
                }
            }
        }

        for (int i = 0; i < n; i++) {
            if (M >= p[i]) {
                x[i] = true;
                M -= p[i];
            }
            //System.out.println(b[i] + " / " + p[i] + " = " + (b[i] / (float) p[i]) + " --> " + (x[i] ? "Si" : "No"));
        }

        return x;
    }

    public static boolean[] dinamica(int n, int M, int[] p, int[] b) {
        int[][] V = new int[n + 1][M + 1];
        for (int i = 0; i <= n; i++) {
            V[i][0] = 0;
        }
        for (int i = 0; i <= M; i++) {
            V[0][i] = 0;
        }

        for (int k = 1; k <= n; k++) {
            for (int m = 1; m <= M; m++) {
                int optDejar = V[k - 1][m];
                if (m - p[k - 1] < 0) {
                    V[k][m] = optDejar;
                } else {
                    int optCoger = V[k - 1][m - p[k - 1]] + b[k - 1];
                    if (optCoger > optDejar) {
                        V[k][m] = optCoger;
                    } else {
                        V[k][m] = optDejar;
                    }
                }
            }
        }

        for (int k = 0; k <= n; k++) {
            for (int m = 0; m <= M; m++) {
                System.out.print(V[k][m] + "\t");
            }
            System.out.println();
        }

        int m = M;
        boolean[] x = new boolean[n];
        for (int i = n - 1; i >= 0; i--) {
            if (V[i][m] != V[i + 1][m]) {
                x[i] = true;
                m -= p[i];
            }
        }
        return x;
    }

    public static boolean[] backtracking(int n, int M, int[] p, int[] b) {
        boolean[] x = new boolean[n];
        backtracking(n, M, p, b, x, 0, 0);
        return x;
    }

    public static int backtracking(int n, int M, int[] p, int[] b, boolean[] x, int c, int B) {
        if (M < 0) {
            return -1;
        } else if (c >= n) {
            return B;
        }

        int bCoger = backtracking(n, M - p[c], p, b, x, c + 1, B + b[c]);
        int bDejar = backtracking(n, M, p, b, x, c + 1, B);

        if (bCoger > bDejar) {
            x[c] = true;
            return bCoger;
        } else {
            x[c] = false;
            return bDejar;
        }
    }

    public static Arista[] prim(double[][] matriz) {
        Arista[] solucion = new Arista[matriz.length - 1];

        int[] padre = new int[matriz.length];
        double distancia[] = new double[matriz.length];
        Boolean conexo[] = new Boolean[matriz.length];

        for (int i = 0; i < matriz.length; i++) {
            distancia[i] = Double.POSITIVE_INFINITY;
            conexo[i] = false;
        }

        distancia[0] = 0;
        padre[0] = -1;

        for (int n = 0; n < matriz.length - 1; n++) {
            int u = -1;
            double uValor = Double.POSITIVE_INFINITY;
            for (int i = 0; i < matriz.length; i++) {
                if (conexo[i] == false && distancia[i] < uValor) {
                    uValor = distancia[i];
                    u = i;
                }
            }

            conexo[u] = true;

            for (int v = 0; v < matriz.length; v++) {
                if (matriz[u][v] != 0 && conexo[v] == false && matriz[u][v] < distancia[v]) {
                    padre[v] = u;
                    distancia[v] = matriz[u][v];
                }
            }
        }

        for (int i = 1; i < matriz.length; i++) {
            solucion[i - 1] = new Arista(i, padre[i], distancia[i]);
        }

        return solucion;
    }

    public static Arista[] kruskal(Arista[] aristas, int nVertices) {
        Arrays.sort(aristas);
        ConjuntosDisjuntos conjuntos = new ConjuntosDisjuntos(nVertices);
        List<Arista> solucion = new ArrayList<>();
        for (Arista arista : aristas) {
            int cI = conjuntos.buscar(arista.v1), cJ = conjuntos.buscar(arista.v2);
            if (cI != cJ) {
                conjuntos.unir(cI, cJ);
                solucion.add(arista);
            }
        }
        return solucion.toArray(new Arista[0]);
    }

    public static Integer[] dijkstra(double[][] matriz, int nVertices, int origen, int destino) {
        boolean[] pendientes = new boolean[nVertices];
        int[] A = new int[nVertices];
        double[] D = new double[nVertices];
        for (int i = 0; i < nVertices; i++) {
            pendientes[i] = true;
            A[i] = -1;
            D[i] = 0;
        }

        System.out.print("Actual  ");
        for (int i = 0; i < nVertices; i++) {
            System.out.print("D[" + (i + 1) + "]    ");
        }
        for (int i = 0; i < nVertices; i++) {
            System.out.print("A[" + (i + 1) + "]    ");
        }
        System.out.println("Pendientes");

        int actual = origen - 1;
        do {
            for (int i = 0; i < nVertices; i++) {
                if (matriz[actual][i] > 0 && (D[i] == 0 || D[i] > D[actual] + matriz[actual][i])) {
                    D[i] = D[actual] + matriz[actual][i];
                    A[i] = actual;
                }
            }

            System.out.print((actual + 1) + "\t  ");
            for (int i = 0; i < nVertices; i++) {
                System.out.print(D[i] + "\t");
            }
            for (int i = 0; i < nVertices; i++) {
                System.out.print((A[i] + 1) + "\t");
            }
            for (int i = 0; i < nVertices; i++) {
                if (pendientes[i]) {
                    System.out.print((i + 1) + " ");
                }
            }
            System.out.println();

            pendientes[actual] = false;
            actual = -1;
            for (int i = 0; i < nVertices; i++) {
                if (pendientes[i] && D[i] > 0 && (actual == -1 || D[i] < D[actual])) {
                    actual = i;
                }
            }
        } while (actual != -1);

        System.out.println();
        List<Integer> camino = new ArrayList<>();
        actual = destino - 1;
        do {
            camino.add((actual));
            actual = A[actual];
        } while (actual != -1);
        return (Integer[]) camino.toArray(new Integer[0]);
    }

    static class Arista implements Comparable {

        public int v1, v2;
        public double etiqueta;

        public Arista(int v1, int v2, double etiqueta) {
            this.v1 = v1;
            this.v2 = v2;
            this.etiqueta = etiqueta;
        }

        @Override
        public int compareTo(Object o) {
            if (o instanceof Arista) {
                return -((Double) ((Arista) o).etiqueta).compareTo(etiqueta);
            } else {
                return 0;
            }
        }

        @Override
        public String toString() {
            return v1 + " --> " + v2 + "  (" + etiqueta + ")";
        }
    }

    public static int fDyV(int n) {
        switch (n) {
            case 0:
                return 0;
            case 1:
                return 1;
            default:
                return fDyV(n - 1) + fDyV(n - 2);
        }
    }

    public static int fDinamica(int n) {
        int[] V = new int[n < 2 ? 2 : (n + 1)];
        V[0] = 0;
        V[1] = 1;
        for (int i = 2; i <= n; i++) {
            V[i] = V[i - 1] + V[i - 2];
        }
        return V[n];
    }

    public static int fDirecta(int n) {
        return (int) Math.round((Math.pow((1.0 + Math.sqrt(5)) / 2.0, n) / Math.sqrt(5)) - (Math.pow((1.0 - Math.sqrt(5)) / 2.0, n) / Math.sqrt(5)));
    }

    public static boolean busquedaPuntoFijo(int[] v) {
        return busquedaPuntoFijo(v, 0, v.length - 1);
    }

    public static boolean busquedaPuntoFijo(int[] v, int p, int q) {
        if (q < p) {
            return false;
        }
        int m = (p + q) / 2;
        if (m == v[m]) {
            return true;
        }
        if (m < v[m]) {
            return busquedaPuntoFijo(v, p, m - 1);
        }
        return busquedaPuntoFijo(v, m + 1, q);
    }

}
