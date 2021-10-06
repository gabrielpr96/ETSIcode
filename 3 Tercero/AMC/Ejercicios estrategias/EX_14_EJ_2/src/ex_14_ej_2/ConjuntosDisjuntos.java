package ex_14_ej_2;

public final class ConjuntosDisjuntos {

    int[] rank, parent;
    int n;

    public ConjuntosDisjuntos(int n) {
        rank = new int[n];
        parent = new int[n];
        this.n = n;
        makeSet();
    }

    private void makeSet() {
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
    }

    public int buscar(int x) {
        if (parent[x] != x) {
            parent[x] = buscar(parent[x]);
        }
        return parent[x];
    }

    public void unir(int x, int y) {
        int xRoot = buscar(x), yRoot = buscar(y);

        if (xRoot == yRoot) {
            return;
        }

        if (rank[xRoot] < rank[yRoot]) {
            parent[xRoot] = yRoot;
        } else if (rank[yRoot] < rank[xRoot]) {
            parent[yRoot] = xRoot;
        } else {
            parent[yRoot] = xRoot;
            rank[xRoot] = rank[xRoot] + 1;
        }
    }
}
