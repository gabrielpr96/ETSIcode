package practica;

public class Conjunto {

    int padre, rango;

    public Conjunto(int padre) {
        this.padre = padre;
        this.rango = 0;
    }

    public static int buscar(Conjunto[] conjuntos, int i) {
        if (conjuntos[i].padre == i) {
            return i;
        } else {
            int c = buscar(conjuntos, conjuntos[i].padre);
            conjuntos[i].padre = c;
            return c;
        }
    }

    public static void unir(Conjunto[] conjuntos, int i, int j) {
        if (conjuntos[i].rango > conjuntos[j].rango) {
            conjuntos[j].padre = i;
        } else {
            conjuntos[i].padre = j;
        }
        if (conjuntos[i].rango == conjuntos[j].rango) {
            conjuntos[j].rango++;
        }
    }

}
