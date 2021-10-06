#include <iostream>
#include <cstdlib>
#include "grafo.h"
#include "conjunto.h"
#include <map>


using namespace std;

//Ejercicio 1 (Grupo L1 Jueves 8:30)
template <typename T,typename U>
T verticeMaxConexion(const Grafo<T,U>& G){
    map<T, int> nConexiones;

    Conjunto<Vertice<T> > vertices = G.vertices();
    while(!vertices.esVacio())
        nConexiones[vertices.quitar().getObj()] = 0;

    Conjunto<Arista<T, float> > aristas = G.aristas();
    while(!aristas.esVacio()){
        Arista<T, float> tmp = aristas.quitar();
        nConexiones[tmp.getOrigen()] += 1;
        nConexiones[tmp.getDestino()] += 1;
    }

    T maxConectada;
    int maxConectadaValor = -1;
    typename map<T, int>::iterator it = nConexiones.begin();
    while(it != nConexiones.end()){
        if(it->second > maxConectadaValor){
            maxConectada = it->first;
            maxConectadaValor = it->second;
        }
        it++;
    }
    return maxConectada;
}


/*)
//Ejercicio 2 (Grupo L1 Jueves 8:30)
template <typename T,typename U>
int numCaminos(const Grafo<T,U>& G, const T& v1, const T& v2){
    Conjunto<T> recorrido;

    return numCaminos(G, v1, v2, recorrido);
}

template <typename T,typename U>
int numCaminos(const Grafo<T,U>& G, const T& v1, const T& v2, Conjunto<T> recorrido){
    recorrido.anadir(v1);

    int caminos = 0;
    Conjunto<Vertice<T> > adyacentes = G.adyacentes(v1);
    while(!adyacentes.esVacio()){
        T aux = adyacentes.quitar().getObj();
        if(aux == v2)
            caminos ++;
        else if(!recorrido.pertenece(aux))
            caminos += numCaminos(G, aux, v2, recorrido);
    }
    return caminos;
}
*/

//Ejercicio 2 (Grupo L1 Jueves 8:30)
template <typename T,typename U>
int numCaminos(const Grafo<T,U>& G, const T& v1, const T& v2)
{
    Conjunto<T> recorrido;
    recorrido.anadir(v1);
    int nCaminos = 0;
    Conjunto<Arista<T, float> > adyacentes = G.aristas();
    while(!adyacentes.esVacio())
    {
        Arista<T, float> arista = adyacentes.quitar();
        if(arista.getOrigen() == v1 && arista.getDestino() != v2)
            nCaminos= nCaminos + numCaminos(G, arista.getDestino(),v2, recorrido);
        else if (arista.getOrigen() == v1 && arista.getDestino() == v2)
            return 1;
    }
    return nCaminos;
}
template <typename T, typename U>
int numCaminos(const Grafo<T,U>& G, const T& v1, const T& v2, Conjunto<T> recorrido){
    recorrido.anadir(v1);
    int nCaminos = 0;
    Conjunto<Arista<T, float> > adyacentes = G.aristas();
    while(!adyacentes.esVacio())
    {
        Arista<T, float> arista = adyacentes.quitar();
        Vertice<T> orig = arista.getOrigen();
        Vertice<T> dest = arista.getDestino();
        if(orig == v1 && dest != v2 && !recorrido.pertenece(dest.getObj()))
            nCaminos = nCaminos + numCaminos(G, arista.getDestino(), v2, recorrido);
        else if(orig == v1 && dest == v2)
            nCaminos = nCaminos + 1;
    }
    return nCaminos;
}


//********************************************************************//
int main(){
    Grafo<int, float> G(7);
    for (int i = 1; i <= 7; i++) G.insertarVertice(i);
    G.insertarArista(2, 1, 4);
    G.insertarArista(1, 3, 3);
    G.insertarArista(1, 4, 2);
    G.insertarArista(1, 6, 1);
    G.insertarArista(6, 4, 5);
    G.insertarArista(4, 7, 4);
    G.insertarArista(4, 5, 3);
    G.insertarArista(5, 1, 6);



    cout<<" PRUEBA II ** GRUPO L1: Jueves 8:30 ** "<< endl << endl;

    cout << " Vertice de maxima conexion en G: " << verticeMaxConexion(G) << endl;

    cout << endl << " Numero de Caminos simples en G - desde vertice 1 al 5: " <<  numCaminos(G, 1, 5) <<endl;

    system("PAUSE");
    return 0;
}
