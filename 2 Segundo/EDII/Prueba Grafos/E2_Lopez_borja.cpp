//Ejercicio 2 (Grupo L1 Jueves 8:30)
template <typename T,typename U>
int numCaminos(const Grafo<T,U>& G, const T& v1, const T& v2)
{
    map<T, bool> visitados;
    Conjunto<Vertice<T> > vertices = G.vertices();
    while(!vertices.esVacio())
        visitados[vertices.quitar().getObj()] = false;

    return numCaminos(G, v1, v2, visitados);
}
template <typename T,typename U>
int numCaminos(const Grafo<T,U>& G, const T& v1, const T& v2, map<T, bool>& visitados)
{
    int nCaminos;
    visitados[v1] = true;

    if(v1 == v2){
        nCaminos = 1;
    }else{
        nCaminos = 0;
        Conjunto<Vertice<T> > adyacentes = G.adyacentes(v1);
        while(!adyacentes.esVacio()){
            T v = adyacentes.quitar().getObj();
            if(!visitados[v])
                nCaminos += numCaminos(G, v, v2, visitados);
        }
    }

    visitados[v1] = false;
    return nCaminos;
}