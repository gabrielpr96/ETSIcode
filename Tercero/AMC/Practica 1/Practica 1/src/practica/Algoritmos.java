package practica;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Algoritmos {

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

    public static void ordenaQuick(Punto[] puntos, boolean sortX) {
        ordenaQuick(puntos, 0, puntos.length - 1, sortX);
    }

    private static int ordenaQuickPart(Punto puntos[], int izq, int der, boolean sortX) {
        Punto med = puntos[der];
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

    private static void ordenaQuick(Punto[] puntos, int izq, int der, boolean sortX) {
        if (izq < der) {
            int med = ordenaQuickPart(puntos, izq, der, sortX);
            ordenaQuick(puntos, izq, med - 1, sortX);
            ordenaQuick(puntos, med + 1, der, sortX);
        }
    }

    private static void ordenaSwap(Punto[] puntos, int a, int b) {
        Punto temp = puntos[a];
        puntos[a] = puntos[b];
        puntos[b] = temp;
    }

    public static void ordenaHeap(Punto[] puntos, boolean sortX) {
        int n = puntos.length;

        for (int i = n / 2 - 1; i >= 0; i--) {
            ordenaHeapHundir(puntos, i, n, sortX);
        }

        for (int i = n - 1; i > 0; i--) {
            ordenaSwap(puntos, 0, i);
            ordenaHeapHundir(puntos, 0, i, sortX);
        }
    }

    private static void ordenaHeapHundir(Punto[] puntos, int raiz, int n, boolean sortX) {
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

    private static Random r = new Random();

    static {
        r.setSeed(System.nanoTime());
        r.setSeed(0);
    }

    public static Punto[] randomMap(int n, double resMinX, double resMaxX, double resMinY, double resMaxY) {
        Punto[] puntos = new Punto[n];
        for (int i = 0; i < n; i++) {
            puntos[i] = new Punto(resMinX + (resMaxX - resMinX) * r.nextDouble(), resMinY + (resMaxY - resMinY) * r.nextDouble());
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
        Linea min = minIzq.comparar(minDer)? minIzq : minDer;
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
        return min;
    }

}
