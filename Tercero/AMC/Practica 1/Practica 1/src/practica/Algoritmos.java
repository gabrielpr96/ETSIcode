package practica;

import java.awt.FileDialog;
import java.awt.Frame;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import javax.swing.JTextArea;
import static practica.Conjunto.*;

public class Algoritmos {

    //Algoritmos de busqueda de triangulos
    public static Triangulo exaustivo(Punto[] puntos) {
        return exaustivo(puntos, 0, puntos.length - 1);
    }

    private static Triangulo exaustivo(Punto[] puntos, int inicio, int fin) {
        Triangulo mejor = new Triangulo(puntos[0], puntos[1], puntos[2]), tmp;
        for (int i = inicio; i <= fin; i++) {
            for (int j = i + 1; j <= fin; j++) {
                for (int k = j + 1; k <= fin; k++) {
                    tmp = new Triangulo(puntos[i], puntos[j], puntos[k]);
                    if (tmp.comparar(mejor)) {
                        mejor = tmp;
                    }
                }
            }
        }

        return mejor;
    }

    public static Triangulo DyV(Punto[] puntos) {
        ordenaQuick(puntos, true);
        Punto[] puntosY = new Punto[puntos.length];
        System.arraycopy(puntos, 0, puntosY, 0, puntos.length);
        ordenaQuick(puntosY, false);
        return DyV(puntos, puntosY, 0, puntos.length - 1);
    }

    private static Triangulo DyV(Punto[] puntosX, Punto[] puntosY, int izq, int der) {
        //Controlar que haya suficiente para la division, en caso contrario se hace por metodo exaustivo
        int n = der - izq + 1;
        if (n <= 3) {
            return exaustivo(puntosX, izq, der);
        }
        //Dividir la nube en dos mitades
        int mid = (der + izq) / 2;
        //Agreguar los puntos ya ordenados en Y en las mitades
        Punto[] puntosYizq = new Punto[mid - izq + 1];
        Punto[] puntosYder = new Punto[n - (mid - izq + 1)];
        int ii = 0, di = 0;
        for (int i = 0; i < puntosY.length; i++) { //puntosY[i].getX() < puntosX[mid].getX() || (puntosY[i].getX() == puntosX[mid].getX() && puntosY[i].getY() <= puntosX[mid].getY())
            if (puntosY[i].comparar(puntosX[mid], true) && ii < puntosYizq.length) {
                puntosYizq[ii++] = puntosY[i];
            } else {
                puntosYder[di++] = puntosY[i];
            }
        }
        //Obtener el triangulo menor de cada mitad
        Triangulo minIzq = DyV(puntosX, puntosYizq, izq, mid), minDer = DyV(puntosX, puntosYder, mid + 1, der);
        //Sacar la linea menor de las dos mitades
        Triangulo min = minIzq.comparar(minDer) ? minIzq : minDer;
        //Extraer la zona crossover ordenada por Y
        Punto[] crossover = new Punto[n];
        int nCrossover = 0;
        for (int i = 0; i < puntosY.length; i++) {
            if (Math.abs(puntosY[i].getX() - puntosX[mid].getX()) < min.perimetro() / 2) {
                crossover[nCrossover++] = puntosY[i];
            }
        }
        //Busqueda optimizada en la zona crossover
        n = nCrossover;
        puntosY = crossover;
        Triangulo tmp;
        for (int i = 0; i < n; ++i) {
            for (int j = i + 1; j < n && (puntosY[j].getY() - puntosY[i].getY()) < min.perimetro() / 2; ++j) {
                for (int k = j + 1; k < n && (puntosY[k].getY() - puntosY[i].getY()) < min.perimetro() / 2; ++k) {
                    tmp = new Triangulo(puntosY[i], puntosY[j], puntosY[k]);
                    if (tmp.comparar(min)) {
                        min = tmp;
                    }
                }
            }
        }
        return min;
    }

    //Algoritmos de busqueda de lineas
    public static Linea exaustivoLinea(Punto[] puntos) {
        return exaustivoLinea(puntos, 0, puntos.length - 1);
    }

    private static Linea exaustivoLinea(Punto[] puntos, int inicio, int fin) {
        Linea mejor = new Linea(puntos[0], puntos[1]), tmp;
        for (int i = inicio; i <= fin; i++) {
            for (int j = i + 1; j <= fin; j++) {
                tmp = new Linea(puntos[i], puntos[j]);
                if (tmp.comparar(mejor)) {
                    mejor = tmp;
                }
            }
        }

        return mejor;
    }

    public static Linea DyVlinea(Punto[] puntos) {
        ordenaQuick(puntos, true);
        Punto[] puntosY = new Punto[puntos.length];
        System.arraycopy(puntos, 0, puntosY, 0, puntos.length);
        ordenaQuick(puntosY, false);
        return DyVlinea(puntos, puntosY, 0, puntos.length - 1);
    }

    private static Linea DyVlinea(Punto[] puntosX, Punto[] puntosY, int izq, int der) {
        //Controlar que haya suficiente para la division, en caso contrario se hace por metodo exaustivo
        int n = der - izq + 1;
        if (n <= 3) {
            return exaustivoLinea(puntosX, izq, der);
        }
        //Dividir la nube en dos mitades
        int mid = (der + izq) / 2;
        //Agreguar los puntos ya ordenados en Y en las mitades
        Punto[] puntosYizq = new Punto[mid - izq + 1];
        Punto[] puntosYder = new Punto[n - (mid - izq + 1)];
        int ii = 0, di = 0;
        for (int i = 0; i < puntosY.length; i++) {
            if (puntosY[i].comparar(puntosX[mid], true) && ii < puntosYizq.length) {
                puntosYizq[ii++] = puntosY[i];
            } else {
                puntosYder[di++] = puntosY[i];
            }
        }
        //Obtener la linea menor de cada mitad
        Linea minIzq = DyVlinea(puntosX, puntosYizq, izq, mid), minDer = DyVlinea(puntosX, puntosYder, mid + 1, der);
        //Sacar la linea menor de las dos mitades
        Linea min = minIzq.comparar(minDer) ? minIzq : minDer;
        //Extraer la zona crossover ordenada por Y
        Punto[] crossover = new Punto[n];
        int nCrossover = 0;
        for (int i = 0; i < puntosY.length; i++) {
            if (Math.abs(puntosY[i].getX() - puntosX[mid].getX()) < min.longitud()) {
                crossover[nCrossover++] = puntosY[i];
            }
        }
        //Busqueda optimizada en la zona crossover
        n = nCrossover;
        puntosY = crossover;
        Linea tmp;
        for (int i = 0; i < n; ++i) {
            for (int j = i + 1; j < n && (puntosY[j].getY() - puntosY[i].getY()) < min.longitud(); ++j) {
                tmp = new Linea(puntosY[i], puntosY[j]);
                if (tmp.comparar(min)) {
                    min = tmp;
                }
            }
        }
        return min;
    }

    //Algoritmos de busqueda del camino mínimo
    public static Arista[] generarAristas(Punto[] puntos) {
        Arista[] aristas = new Arista[puntos.length * (puntos.length - 1) / 2];
        int k = 0;
        for (int i = 0; i < puntos.length; i++) {
            for (int j = i + 1; j < puntos.length; j++) {
                aristas[k++] = new Arista(puntos[i], puntos[j]);
            }
        }
        return aristas;
    }

    public static AristaSimple[] generarAristasSimples(Punto[] puntos) {
        AristaSimple[] aristas = new AristaSimple[puntos.length * (puntos.length - 1) / 2];
        int k = 0;
        for (int i = 0; i < puntos.length; i++) {
            for (int j = i + 1; j < puntos.length; j++) {
                aristas[k++] = new AristaSimple(i, j, puntos[i].distancia(puntos[j]));
            }
        }
        return aristas;
    }

    public static double[][] generarMatrizAdyacencia(Punto[] puntos) {
        double[][] matriz = new double[puntos.length][puntos.length];
        for (int i = 0; i < puntos.length; i++) {
            matriz[i][i] = Double.POSITIVE_INFINITY;
            for (int j = i + 1; j < puntos.length; j++) {
                matriz[i][j] = matriz[j][i] = puntos[i].distancia(puntos[j]);
            }
        }
        return matriz;
    }

    public static double costeCamino(Arista[] aristas) {
        double coste = 0;
        for (Arista arista : aristas) {
            coste += arista.coste();
        }
        return coste;
    }

    public static Arista[] kruskal(Arista[] aristas, Punto[] vertices) throws Exception {
        Arista[] solucion = new Arista[vertices.length - 1];
        ordenaQuick(aristas, true);
        Map<Punto, Integer> conjuntos = new HashMap<>();
        for (int i = 0; i < vertices.length; i++) {
            conjuntos.put(vertices[i], i);
        }
        int iAristas = 0, iSolucion = 0;
        while (iSolucion < solucion.length) {
            if (iAristas >= aristas.length) {
                throw new Exception("No se puede conectar el grafo");
            }
            Arista arista = aristas[iAristas++];
            int c1 = conjuntos.get(arista.getVertice1()), c2 = conjuntos.get(arista.getVertice2());
            if (c1 != c2) {
                solucion[iSolucion++] = arista;
                //conjuntos.replaceAll((v, c) -> c==c2?c1:c);
                for (Map.Entry<Punto, Integer> vertice : conjuntos.entrySet()) {
                    if (vertice.getValue() == c2) {
                        vertice.setValue(c1);
                    }
                }
            }
        }
        return solucion;
    }

    public static Arista[] prim(Arista[] aristas, Punto[] vertices) throws Exception {
        Arista[] solucion = new Arista[vertices.length - 1];
        Map<Punto, Map<Punto, Arista>> costes = new HashMap<>();
        for (Punto vertice : vertices) {
            costes.put(vertice, null);
        }
        Punto vActual = vertices[0];
        int iSolucion = 0;
        while (iSolucion < solucion.length) {
            Map<Punto, Arista> costesActual = costes.get(vActual);
            if (costesActual == null) {
                costesActual = new HashMap<>();
                for (Punto vertice : vertices) {
                    costesActual.put(vertice, null);
                }
                for (Arista arista : aristas) {
                    if (arista.getVertice1() == vActual) {
                        if (costesActual.get(arista.getVertice2()) == null || arista.comparar(costesActual.get(arista.getVertice2()), true)) {
                            costesActual.put(arista.getVertice2(), arista);
                        }
                    } else if (arista.getVertice2() == vActual) {
                        if (costesActual.get(arista.getVertice1()) == null || arista.comparar(costesActual.get(arista.getVertice1()), true)) {
                            costesActual.put(arista.getVertice1(), arista);
                        }
                    }
                }
                costes.put(vActual, costesActual);
            }

            Arista mejor = null;
            Punto siguenteVactual = null;
            for (Map.Entry<Punto, Map<Punto, Arista>> costesVertice : costes.entrySet()) {
                if (costesVertice.getValue() != null) {
                    for (Map.Entry<Punto, Arista> arista : costesVertice.getValue().entrySet()) {
                        if (arista.getValue() != null && costes.get(arista.getKey()) == null && (mejor == null || arista.getValue().comparar(mejor, true))) {
                            mejor = arista.getValue();
                            siguenteVactual = arista.getKey();
                        }
                    }
                }
            }
            if (mejor == null) {
                throw new Exception("No se puede conectar el grafo");
            }

            costes.get(vActual).put(siguenteVactual, null);
            vActual = siguenteVactual;

            solucion[iSolucion++] = mejor;
        }
        return solucion;
    }

    public static Arista[] kruskal(AristaSimple[] aristas, Punto[] vertices) throws Exception {
        ordenaQuick(aristas, true);
        //Arrays.sort(aristas);
        Conjunto[] conjuntos = new Conjunto[vertices.length];
        for (int i = 0; i < vertices.length; i++) {
            conjuntos[i] = new Conjunto(i);
        }
        int iSolucion = 0, iArista = 0;
        Arista[] solucion = new Arista[vertices.length - 1];
        while (iSolucion < vertices.length - 1) {
            if (iArista >= aristas.length) {
                throw new Exception("No se puede conectar el grafo");
            }
            AristaSimple arista = aristas[iArista];
            int cI = buscar(conjuntos, arista.v1), cJ = buscar(conjuntos, arista.v2);
            if (cI != cJ) {
                unir(conjuntos, cI, cJ);
                solucion[iSolucion++] = new Arista(vertices[arista.v1], vertices[arista.v2]);
            }
            iArista++;
        }
        return solucion;
    }

    public static Arista[] prim(double[][] matriz, Punto[] vertices) throws Exception {
        Arista[] solucion = new Arista[vertices.length - 1];

        int[] padre = new int[vertices.length];
        double distancia[] = new double[vertices.length];
        Boolean conexo[] = new Boolean[vertices.length];

        for (int i = 0; i < vertices.length; i++) {
            distancia[i] = Double.POSITIVE_INFINITY;
            conexo[i] = false;
        }

        distancia[0] = 0;
        padre[0] = -1;

        for (int n = 0; n < vertices.length - 1; n++) {
            int u = -1;
            double uValor = Double.POSITIVE_INFINITY;
            for (int i = 0; i < vertices.length; i++) {
                if (conexo[i] == false && distancia[i] < uValor) {
                    uValor = distancia[i];
                    u = i;
                }
            }

            conexo[u] = true;

            for (int v = 0; v < vertices.length; v++) {
                if (matriz[u][v] != 0 && conexo[v] == false && matriz[u][v] < distancia[v]) {
                    padre[v] = u;
                    distancia[v] = matriz[u][v];
                }
            }
        }

        for (int i = 1; i < vertices.length; i++) {
            solucion[i - 1] = new Arista(vertices[i], vertices[padre[i]]);
        }

        return solucion;
    }

    //Algoritmos de ordenación
    public static void ordenaQuick(Comparable2[] puntos, boolean sortX) {
        ordenaQuick(puntos, 0, puntos.length - 1, sortX);
    }

    private static int ordenaQuickPart(Comparable2 puntos[], int izq, int der, boolean sortX) {
        Comparable2 med = puntos[der];
        int i = (izq - 1);
        for (int j = izq; j < der; j++) {
            if (puntos[j].comparar(med, sortX)) {
                i++;
                ordenaSwap(puntos, i, j);
            }
        }
        ordenaSwap(puntos, i + 1, der);

        return i + 1;
    }

    private static void ordenaQuick(Comparable2[] puntos, int izq, int der, boolean sortX) {
        if (izq < der) {
            int med = ordenaQuickPart(puntos, izq, der, sortX);
            ordenaQuick(puntos, izq, med - 1, sortX);
            ordenaQuick(puntos, med + 1, der, sortX);
        }
    }

    private static void ordenaSwap(Object[] elementos, int a, int b) {
        Object tmp = elementos[a];
        elementos[a] = elementos[b];
        elementos[b] = tmp;
    }

    public static void ordenaHeap(Comparable2[] puntos, boolean sortX) {
        int n = puntos.length;

        for (int i = n / 2 - 1; i >= 0; i--) {
            ordenaHeapHundir(puntos, i, n, sortX);
        }

        for (int i = n - 1; i > 0; i--) {
            ordenaSwap(puntos, 0, i);
            ordenaHeapHundir(puntos, 0, i, sortX);
        }
    }

    private static void ordenaHeapHundir(Comparable2[] puntos, int raiz, int n, boolean sortX) {
        int mayor = raiz;
        int izq = 2 * raiz + 1;
        int der = 2 * raiz + 2;

        if (izq < n && puntos[mayor].comparar(puntos[izq], sortX)) {
            mayor = izq;
        }

        if (der < n && puntos[mayor].comparar(puntos[der], sortX)) {
            mayor = der;
        }

        if (mayor != raiz) {
            ordenaSwap(puntos, raiz, mayor);
            ordenaHeapHundir(puntos, mayor, n, sortX);
        }
    }

    //Algoritmos adaptados al dibujado
    private static final Random R = new Random();

    static {
        R.setSeed(System.nanoTime());
        R.setSeed(0);
    }

    public static Punto[] randomMap(int n, double resMinX, double resMaxX, double resMinY, double resMaxY) {
        Punto[] puntos = new Punto[n];
        for (int i = 0; i < n; i++) {
            puntos[i] = new Punto(resMinX + (resMaxX - resMinX) * R.nextDouble(), resMinY + (resMaxY - resMinY) * R.nextDouble());
        }
        return puntos;
    }

    private static int esperaDraw = 1000;

    public static void setEsperaDraw(int espera) {
        esperaDraw = espera;
    }

    public static void DrawExaustivo(Punto[] puntos, Lienzo lienzo, JTextArea mensaje) {
        lienzo.drawMap(puntos);
        Triangulo mejor = DrawExaustivo(puntos, 0, puntos.length - 1, lienzo);
        if (mejor != null) {
            lienzo.setTipo(false, true);
            lienzo.drawTriangulo(mejor);
            mensaje.setText(mejor.toString());
        }
    }

    private static Triangulo DrawExaustivo(Punto[] puntos, int inicio, int fin, Lienzo lienzo) {
        Triangulo mejor = new Triangulo(puntos[0], puntos[1], puntos[2]), tmp;
        for (int i = inicio; i <= fin; i++) {
            for (int j = i + 1; j <= fin; j++) {
                for (int k = j + 1; k <= fin; k++) {
                    tmp = new Triangulo(puntos[i], puntos[j], puntos[k]);
                    if (tmp.comparar(mejor)) {
                        mejor = tmp;
                        lienzo.drawMejorTriangulo(tmp);
                    }
                    lienzo.setTipo(false, false);
                    lienzo.drawTriangulo(tmp);
                    if (esperaDraw != 0) {
                        try {
                            Thread.sleep(esperaDraw);
                        } catch (InterruptedException ex) {
                            return null;
                        }
                    }
                }
            }
        }

        return mejor;
    }

    public static void DrawDyV(Punto[] puntos, Lienzo lienzo, JTextArea mensaje) {
        ordenaQuick(puntos, true);
        Punto[] puntosY = new Punto[puntos.length];
        System.arraycopy(puntos, 0, puntosY, 0, puntos.length);
        ordenaQuick(puntosY, false);
        mensaje.setText("Puntos ordenados, buscando");
        lienzo.drawMap(puntos);
        Triangulo mejor = DrawDyV(puntos, puntosY, 0, puntos.length - 1, lienzo);
        if (mejor != null) {
            lienzo.setTipo(false, true);
            lienzo.drawTriangulo(mejor);
            mensaje.setText(mejor.toString());
        }
    }

    private static Triangulo DrawDyV(Punto[] puntosX, Punto[] puntosY, int izq, int der, Lienzo lienzo) {
        //Controlar que haya suficiente para la division, en caso contrario se hace por metodo exaustivo
        int n = der - izq + 1;
        if (n <= 3) {
            return DrawExaustivo(puntosX, izq, der, lienzo);
        }
        //Dividir la nube en dos mitades
        int mid = (der + izq) / 2;
        //Agreguar los puntos ya ordenados en Y en las mitades
        Punto[] puntosYizq = new Punto[mid - izq + 1];
        Punto[] puntosYder = new Punto[n - (mid - izq + 1)];
        int ii = 0, di = 0;
        for (int i = 0; i < puntosY.length; i++) {
            if (puntosY[i].comparar(puntosX[mid], true) && ii < puntosYizq.length) {
                puntosYizq[ii++] = puntosY[i];
            } else {
                puntosYder[di++] = puntosY[i];
            }
        }
        //Obtener el triangulo menor de cada mitad
        Triangulo minIzq = DrawDyV(puntosX, puntosYizq, izq, mid, lienzo), minDer = DrawDyV(puntosX, puntosYder, mid + 1, der, lienzo);
        //Sacar la linea menor de las dos mitades
        Triangulo min = minIzq.comparar(minDer) ? minIzq : minDer;
        //Extraer la zona crossover ordenada por Y
        Punto[] crossover = new Punto[n];
        int nCrossover = 0;
        for (Punto punto : puntosY) {
            if (Math.abs(punto.getX() - puntosX[mid].getX()) < min.perimetro() / 2) {
                crossover[nCrossover++] = punto;
            }
        }
        //Busqueda optimizada en la zona crossover
        n = nCrossover;
        puntosY = crossover;
        Triangulo tmp;
        for (int i = 0; i < n; ++i) {
            for (int j = i + 1; j < n && (puntosY[j].getY() - puntosY[i].getY()) < min.perimetro() / 2; ++j) {
                for (int k = j + 1; k < n && (puntosY[k].getY() - puntosY[i].getY()) < min.perimetro() / 2; ++k) {
                    tmp = new Triangulo(puntosY[i], puntosY[j], puntosY[k]);
                    if (tmp.comparar(min)) {
                        min = tmp;
                    }
                    lienzo.setTipo(true, false);
                    lienzo.drawTriangulo(tmp);
                    if (esperaDraw != 0) {
                        try {
                            Thread.sleep(esperaDraw);
                        } catch (InterruptedException ex) {
                            return null;
                        }
                    }
                }
            }
        }
        lienzo.drawMejorTriangulo(min);
        return min;
    }

    public static void DrawExaustivoLinea(Punto[] puntos, Lienzo lienzo, JTextArea mensaje) {
        lienzo.drawMap(puntos);
        Linea mejor = DrawExaustivoLinea(puntos, 0, puntos.length - 1, lienzo);
        if (mejor != null) {
            lienzo.setTipo(false, true);
            lienzo.drawLinea(mejor);
            mensaje.setText(mejor.toString());
        }
    }

    private static Linea DrawExaustivoLinea(Punto[] puntos, int inicio, int fin, Lienzo lienzo) {
        Linea mejor = new Linea(puntos[0], puntos[1]), tmp;
        for (int i = inicio; i <= fin; i++) {
            for (int j = i + 1; j <= fin; j++) {
                tmp = new Linea(puntos[i], puntos[j]);
                if (tmp.comparar(mejor)) {
                    mejor = tmp;
                    lienzo.drawMejorLinea(tmp);
                }
                lienzo.setTipo(false, false);
                lienzo.drawLinea(tmp);
                if (esperaDraw != 0) {
                    try {
                        Thread.sleep(esperaDraw);
                    } catch (InterruptedException ex) {
                        return null;
                    }
                }
            }
        }

        return mejor;
    }

    public static void DrawDyVlinea(Punto[] puntos, Lienzo lienzo, JTextArea mensaje) {
        ordenaQuick(puntos, true);
        Punto[] puntosY = new Punto[puntos.length];
        System.arraycopy(puntos, 0, puntosY, 0, puntos.length);
        ordenaQuick(puntosY, false);
        mensaje.setText("Puntos ordenados, buscando");
        lienzo.drawMap(puntos);
        Linea mejor = DrawDyVlinea(puntos, puntosY, 0, puntos.length - 1, lienzo);
        if (mejor != null) {
            lienzo.setTipo(false, true);
            lienzo.drawLinea(mejor);
            mensaje.setText(mejor.toString());
        }
    }

    private static Linea DrawDyVlinea(Punto[] puntosX, Punto[] puntosY, int izq, int der, Lienzo lienzo) {
        //Controlar que haya suficiente para la division, en caso contrario se hace por metodo exaustivo
        int n = der - izq + 1;
        if (n <= 3) {
            return DrawExaustivoLinea(puntosX, izq, der, lienzo);
        }
        //Dividir la nube en dos mitades
        int mid = (der + izq) / 2;
        //Agreguar los puntos ya ordenados en Y en las mitades
        Punto[] puntosYizq = new Punto[mid - izq + 1];
        Punto[] puntosYder = new Punto[n - (mid - izq + 1)];
        int ii = 0, di = 0;
        for (int i = 0; i < puntosY.length; i++) {
            if (puntosY[i].comparar(puntosX[mid], true) && ii < puntosYizq.length) {
                puntosYizq[ii++] = puntosY[i];
            } else {
                puntosYder[di++] = puntosY[i];
            }
        }
        //Obtener la linea menor de cada mitad
        Linea minIzq = DrawDyVlinea(puntosX, puntosYizq, izq, mid, lienzo), minDer = DrawDyVlinea(puntosX, puntosYder, mid + 1, der, lienzo);
        //Sacar la linea menor de las dos mitades
        Linea min = minIzq.comparar(minDer) ? minIzq : minDer;
        //Extraer la zona crossover ordenada por Y
        Punto[] crossover = new Punto[n];
        int nCrossover = 0;
        for (int i = 0; i < puntosY.length; i++) {
            if (Math.abs(puntosY[i].getX() - puntosX[mid].getX()) < min.longitud()) {
                crossover[nCrossover++] = puntosY[i];
            }
        }
        //Busqueda optimizada en la zona crossover
        n = nCrossover;
        puntosY = crossover;
        Linea tmp;
        for (int i = 0; i < n; ++i) {
            for (int j = i + 1; j < n && (puntosY[j].getY() - puntosY[i].getY()) < min.longitud(); ++j) {
                tmp = new Linea(puntosY[i], puntosY[j]);
                if (tmp.comparar(min)) {
                    min = tmp;
                }
                lienzo.setTipo(true, false);
                lienzo.drawLinea(tmp);
                if (esperaDraw != 0) {
                    try {
                        Thread.sleep(esperaDraw);
                    } catch (InterruptedException ex) {
                        return null;
                    }
                }
            }
        }
        lienzo.drawMejorLinea(min);
        return min;
    }

    public static void DrawKruskal(Punto[] puntos, Lienzo lienzo, JTextArea mensaje) {
        lienzo.drawMap(puntos, puntos.length <= 20);
        mensaje.setText("Generando aristas");
        Arista[] aristas = generarAristas(puntos);
        mensaje.setText("Ejecutando algoritmo");
        try {
            TablaKruskal tabla = null;
            if (puntos.length <= 20) {
                tabla = new TablaKruskal(puntos);
            }
            Arista[] resultado = DrawKruskal(aristas, puntos, lienzo, tabla);
            mensaje.setText("Resultado obtenido: " + costeCamino(resultado));
            FileDialog dialog = new FileDialog((Frame) null, "Guardar el resultado de la ejecucion");
            dialog.setMode(FileDialog.SAVE);
            dialog.setVisible(true);
            String file = dialog.getFile();
            if (file != null) {
                if (!file.endsWith(".tour")) {
                    file += ".tour";
                }
                TSPlib.formar(dialog.getDirectory() + file, resultado, puntos);
            }
        } catch (Exception e) {
            mensaje.setText("Error al ejecutar el algoritmo");
            e.printStackTrace();
        }
    }

    public static Arista[] DrawKruskal(Arista[] aristas, Punto[] vertices, Lienzo lienzo, TablaKruskal tabla) throws Exception {
        Arista[] solucion = new Arista[vertices.length - 1];
        ordenaQuick(aristas, true);
        Map<Punto, Integer> conjuntos = new HashMap<>();
        for (int i = 0; i < vertices.length; i++) {
            conjuntos.put(vertices[i], i);
        }
        if (tabla != null) {
            tabla.addPaso(null, conjuntos, true);
        }
        int iAristas = 0, iSolucion = 0;
        while (iSolucion < solucion.length) {
            if (iAristas >= aristas.length) {
                throw new Exception("No se puede conectar el grafo");
            }
            Arista arista = aristas[iAristas++];
            lienzo.drawLinea(arista.getLinea());
            try {
                Thread.sleep(esperaDraw);
            } catch (InterruptedException ex) {
                return null;
            }
            int c1 = conjuntos.get(arista.getVertice1()), c2 = conjuntos.get(arista.getVertice2());
            if (c1 != c2) {
                solucion[iSolucion++] = arista;
                lienzo.addDrawLinea(arista.getLinea());
                try {
                    Thread.sleep(esperaDraw);
                } catch (InterruptedException ex) {
                    return null;
                }
                //conjuntos.replaceAll((v, c) -> c==c2?c1:c);
                for (Map.Entry<Punto, Integer> vertice : conjuntos.entrySet()) {
                    if (vertice.getValue() == c2) {
                        vertice.setValue(c1);
                    }
                }
                if (tabla != null) {
                    tabla.addPaso(arista, conjuntos, true);
                }
            } else {
                if (tabla != null) {
                    tabla.addPaso(arista, conjuntos, false);
                }
            }
        }
        return solucion;
    }

    public static void DrawPrim(Punto[] puntos, Lienzo lienzo, JTextArea mensaje) {
        lienzo.drawMap(puntos, puntos.length <= 20);
        mensaje.setText("Generando aristas");
        Arista[] aristas = generarAristas(puntos);
        mensaje.setText("Ejecutando algoritmo");
        try {
            TablaPrim tabla = null;
            if (puntos.length <= 20) {
                tabla = new TablaPrim(puntos);
            }
            Arista[] resultado = DrawPrim(aristas, puntos, lienzo, tabla);
            mensaje.setText("Resultado obtenido: " + costeCamino(resultado));
            FileDialog dialog = new FileDialog((Frame) null, "Guardar el resultado de la ejecucion");
            dialog.setMode(FileDialog.SAVE);
            dialog.setVisible(true);
            String file = dialog.getFile();
            if (file != null) {
                if (!file.endsWith(".tour")) {
                    file += ".tour";
                }
                TSPlib.formar(dialog.getDirectory() + file, resultado, puntos);
            }
        } catch (Exception e) {
            mensaje.setText("Error al ejecutar el algoritmo");
            e.printStackTrace();
        }
    }

    public static Arista[] DrawPrim(Arista[] aristas, Punto[] vertices, Lienzo lienzo, TablaPrim tabla) throws Exception {
        Arista[] solucion = new Arista[vertices.length - 1];
        Map<Punto, Map<Punto, Arista>> costes = new HashMap<>();
        for (Punto vertice : vertices) {
            costes.put(vertice, null);
        }
        Punto vActual = vertices[0];
        int iSolucion = 0;
        while (iSolucion < solucion.length) {
            Map<Punto, Arista> costesActual = costes.get(vActual);
            if (costesActual == null) {
                costesActual = new HashMap<>();
                for (Punto vertice : vertices) {
                    costesActual.put(vertice, null);
                }
                for (Arista arista : aristas) {
                    if (arista.getVertice1() == vActual) {
                        if (costesActual.get(arista.getVertice2()) == null || arista.comparar(costesActual.get(arista.getVertice2()), true)) {
                            costesActual.put(arista.getVertice2(), arista);
                        }
                    } else if (arista.getVertice2() == vActual) {
                        if (costesActual.get(arista.getVertice1()) == null || arista.comparar(costesActual.get(arista.getVertice1()), true)) {
                            costesActual.put(arista.getVertice1(), arista);
                        }
                    }
                }
                costes.put(vActual, costesActual);
                if (tabla != null) {
                    tabla.updateMatrix(vActual, costesActual);
                }
            }

            Arista mejor = null;
            Punto siguenteVactual = null;
            for (Map.Entry<Punto, Map<Punto, Arista>> costesVertice : costes.entrySet()) {
                if (costesVertice.getValue() != null) {
                    for (Map.Entry<Punto, Arista> arista : costesVertice.getValue().entrySet()) {
                        if (arista.getValue() != null && costes.get(arista.getKey()) == null && (mejor == null || arista.getValue().comparar(mejor, true))) {
                            mejor = arista.getValue();
                            siguenteVactual = arista.getKey();
                            lienzo.drawLinea(arista.getValue().getLinea());
                            try {
                                Thread.sleep(esperaDraw);
                            } catch (InterruptedException ex) {
                                return null;
                            }
                        }
                    }
                }
            }
            if (mejor == null) {
                throw new Exception("No se puede conectar el grafo");
            }

            costes.get(vActual).put(siguenteVactual, null);
            vActual = siguenteVactual;

            solucion[iSolucion++] = mejor;
            lienzo.addDrawLinea(mejor.getLinea());
            if (tabla != null) {
                tabla.addPaso(mejor, solucion);
            }

            try {
                Thread.sleep(esperaDraw);
            } catch (InterruptedException ex) {
                return null;
            }
        }
        return solucion;
    }

    public static void DrawKruskalSimp(Punto[] puntos, Lienzo lienzo, JTextArea mensaje) {
        lienzo.drawMap(puntos, puntos.length <= 20);
        mensaje.setText("Generando aristas");
        AristaSimple[] aristas = generarAristasSimples(puntos);
        mensaje.setText("Ejecutando algoritmo");
        try {
            TablaKruskal tabla = null;
            if (puntos.length <= 20) {
                tabla = new TablaKruskal(puntos);
            }
            Arista[] resultado = DrawKruskal(aristas, puntos, lienzo, tabla);
            mensaje.setText("Resultado obtenido: " + costeCamino(resultado));
            FileDialog dialog = new FileDialog((Frame) null, "Guardar el resultado de la ejecucion");
            dialog.setMode(FileDialog.SAVE);
            dialog.setVisible(true);
            String file = dialog.getFile();
            if (file != null) {
                if (!file.endsWith(".tour")) {
                    file += ".tour";
                }
                TSPlib.formar(dialog.getDirectory() + file, resultado, puntos);
            }
        } catch (Exception e) {
            mensaje.setText("Error al ejecutar el algoritmo");
            e.printStackTrace();
        }
    }

    public static Arista[] DrawKruskal(AristaSimple[] aristas, Punto[] vertices, Lienzo lienzo, TablaKruskal tabla) throws Exception {
        ordenaQuick(aristas, true);
        //Arrays.sort(aristas);
        Conjunto[] conjuntos = new Conjunto[vertices.length];
        for (int i = 0; i < vertices.length; i++) {
            conjuntos[i] = new Conjunto(i);
        }
        if (tabla != null) {
            tabla.addPaso(null, conjuntos, true);
        }
        int iSolucion = 0, iArista = 0;
        Arista[] solucion = new Arista[vertices.length - 1];
        while (iSolucion < vertices.length - 1) {
            if (iArista >= aristas.length) {
                throw new Exception("No se puede conectar el grafo");
            }
            AristaSimple arista = aristas[iArista];
            int cI = buscar(conjuntos, arista.v1), cJ = buscar(conjuntos, arista.v2);
            lienzo.drawLinea(new Linea(vertices[arista.v1], vertices[arista.v2]));
            try {
                Thread.sleep(esperaDraw);
            } catch (InterruptedException ex) {
                return null;
            }
            if (cI != cJ) {
                unir(conjuntos, cI, cJ);
                solucion[iSolucion] = new Arista(vertices[arista.v1], vertices[arista.v2]);
                lienzo.addDrawLinea(solucion[iSolucion].getLinea());
                iSolucion++;
                try {
                    Thread.sleep(esperaDraw);
                } catch (InterruptedException ex) {
                    return null;
                }

                if (tabla != null) {
                    tabla.addPaso(solucion[iSolucion - 1], conjuntos, true);
                }
            } else {
                if (tabla != null) {
                    tabla.addPaso(new Arista(vertices[arista.v1], vertices[arista.v2]), conjuntos, true);
                }
            }
            iArista++;
        }
        return solucion;
    }

    public static void DrawPrimSimp(Punto[] puntos, Lienzo lienzo, JTextArea mensaje) {
        lienzo.drawMap(puntos, puntos.length <= 20);
        mensaje.setText("Generando aristas");
        double[][] aristas = generarMatrizAdyacencia(puntos);
        mensaje.setText("Ejecutando algoritmo");
        try {
            TablaPrimSimp tabla = null;
            if (puntos.length <= 20) {
                tabla = new TablaPrimSimp(puntos, aristas);
            }
            Arista[] resultado = DrawPrim(aristas, puntos, lienzo, tabla);
            mensaje.setText("Resultado obtenido: " + costeCamino(resultado));
            FileDialog dialog = new FileDialog((Frame) null, "Guardar el resultado de la ejecucion");
            dialog.setMode(FileDialog.SAVE);
            dialog.setVisible(true);
            String file = dialog.getFile();
            if (file != null) {
                if (!file.endsWith(".tour")) {
                    file += ".tour";
                }
                TSPlib.formar(dialog.getDirectory() + file, resultado, puntos);
            }
        } catch (Exception e) {
            mensaje.setText("Error al ejecutar el algoritmo");
            e.printStackTrace();
        }
    }

    public static Arista[] DrawPrim(double[][] matriz, Punto[] vertices, Lienzo lienzo, TablaPrimSimp tabla) throws Exception {
        Arista[] solucion = new Arista[vertices.length - 1];

        int[] padre = new int[vertices.length];
        double distancia[] = new double[vertices.length];
        boolean conexo[] = new boolean[vertices.length];

        for (int i = 0; i < vertices.length; i++) {
            distancia[i] = Double.POSITIVE_INFINITY;
            conexo[i] = false;
        }

        distancia[0] = 0;
        padre[0] = -1;

        if (tabla != null) {
            tabla.addPaso(-1, -1, 0, conexo);
        }

        for (int n = 0; n < vertices.length - 1; n++) {
            int u = -1;
            double uValor = Double.POSITIVE_INFINITY;
            for (int i = 0; i < vertices.length; i++) {
                if (conexo[i] == false && distancia[i] < uValor) {
                    uValor = distancia[i];
                    u = i;
                }
            }

            conexo[u] = true;

            for (int v = 0; v < vertices.length; v++) {
                if (matriz[u][v] != 0 && conexo[v] == false && matriz[u][v] < distancia[v]) {
                    padre[v] = u;
                    distancia[v] = matriz[u][v];
                    lienzo.eliminarLineasPorPuntos(vertices[v]);
                    lienzo.addDrawLinea(new Linea(vertices[u], vertices[v]));
                    try {
                        Thread.sleep(esperaDraw);
                    } catch (InterruptedException ex) {
                        return null;
                    }
                    if (tabla != null) {
                        tabla.addPaso(u, v, distancia[v], conexo);
                    }
                }
            }
        }

        for (int i = 1; i < vertices.length; i++) {
            solucion[i - 1] = new Arista(vertices[i], vertices[padre[i]]);
        }
        tabla.addPaso(-1, -1, 0, conexo);

        return solucion;
    }

}
