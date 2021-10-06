//Ejercicio 1 (Grupo L1 Jueves 8:30)
template <typename T,typename U>
T verticeMaxConexion(const Grafo<T,U>& G)
{
    map<T, int> conexiones;
    Conjunto<Vertice<T> > vertices = G.vertices();
    while(!vertices.esVacio())
        conexiones[vertices.quitar().getObj()] = 0;

    Conjunto<Arista<T, U> > aristas = G.aristas();
    while(!aristas.esVacio()){
        Arista<T, U> a = aristas.quitar();
        conexiones[a.getOrigen()]++;
        conexiones[a.getDestino()]++;
    }

    vertices = G.vertices();
    T maxV = vertices.quitar().getObj(); //Se asume que hay almenos un vertice en el grafo
    while(!vertices.esVacio()){
        T v = vertices.quitar().getObj();
        if(conexiones[v] > conexiones[maxV])
            maxV = v;
    }

    return maxV;
}