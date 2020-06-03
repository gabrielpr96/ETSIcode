#define _GLIBCXX_USE_CXX11_ABI 0
#include <iostream>
#include <cstdlib>
#include "grafo.h"
#include "conjunto.h"
#include <queue>
#include <sstream>
#include <map>


using namespace std;

//Ejercicio 1
template <typename T>
T verticeMaxCoste(const Grafo<T, float>& G){
    map<T, float> costes;
    float maxCoste = -1;
    T maxVertice;

    Conjunto<Vertice<T> > vertices = G.vertices();
    while(!vertices.esVacio())
        costes[vertices.quitar().getObj()] = 0;

    Conjunto<Arista<T, float> > aristas = G.aristas();
    while(!aristas.esVacio()){
        Arista<T, float> arista = aristas.quitar();
        costes[arista.getOrigen()] += arista.getEtiqueta();
    }

    for(typename map<T, float>::iterator it = costes.begin(); it != costes.end(); it++){
        if(it->second > maxCoste){
            maxVertice = it->first;
            maxCoste = it->second;
        }
    }

    return maxVertice;
}


//Ejercicio 2
template <typename T, typename U>
void inaccesibles(const Grafo<T, U>& G){
    Conjunto<Vertice<T> > vertices = G.vertices();
    while(!vertices.esVacio()){
        Vertice<T> tmp = vertices.quitar();
        if(G.antecesores(tmp.getObj()).esVacio())
            cout << tmp.getObj() << " ";
    }
}


// Ejercicio 3
template <typename T, typename U>
bool caminoEntreDos(const Grafo<T, U>& G, const T& vo, const T& vd){
    queue<T> cola;
    cola.push(vo);
    Conjunto<T> cerrados;
    T actual = vo;

    do{
        Conjunto<Vertice<T> > adyacentes = G.adyacentes(actual);
        while(!adyacentes.esVacio()){
            T tmp = adyacentes.quitar().getObj();
            if(!cerrados.pertenece(tmp))
                cola.push(tmp);
        }
        cerrados.anadir(actual);
        actual = cola.front();
        cola.pop();
    }while(actual != vd && !cola.empty());

    return actual == vd;
}


//Ejercicio 4
template <typename T>
void caminosAcotados(const Grafo<T, float>& G, const T& u, float maxCoste){
    Conjunto<T> recorrido;
    recorrido.anadir(u);

    Conjunto<Arista<T, float> > adyacentes = G.aristas();
    while(!adyacentes.esVacio()){
        Arista<T, float> tmp = adyacentes.quitar();
        if(tmp.getOrigen() == u && maxCoste-tmp.getEtiqueta() > 0)
            caminosAcotados(G, tmp.getDestino(), maxCoste-tmp.getEtiqueta(), recorrido);
    }
}
template <typename T>
void caminosAcotados(const Grafo<T, float>& G, const T& u, float maxCoste, Conjunto<T> recorrido){
    recorrido.anadir(u);

    Conjunto<Arista<T, float> > adyacentes = G.aristas();
    while(!adyacentes.esVacio()){
        Arista<T, float> tmp = adyacentes.quitar();
        if(tmp.getOrigen() == u && maxCoste-tmp.getEtiqueta() > 0)
            caminosAcotados(G, tmp.getDestino(), maxCoste-tmp.getEtiqueta(), recorrido);
    }

    while(!recorrido.esVacio())
        cout << recorrido.quitar() << " <- ";
    cout << endl;
}



//Ejercicio 5
template <typename T, typename U>
T outConectado(const Grafo<T, U>& G){
    map<T, int> valor;
    T outConectada;

    Conjunto<Vertice<T> > vertices = G.vertices();
    while(!vertices.esVacio())
        valor[vertices.quitar().getObj()] = 0;

    Conjunto<Arista<T, float> > aristas = G.aristas();
    while(!aristas.esVacio()){
        Arista<T, float> tmp = aristas.quitar();
        valor[tmp.getOrigen()] += 1;
        valor[tmp.getDestino()] -= 1;
    }

    bool encontrada = false;
    typename map<T, int>::iterator it = valor.begin();
    while(it != valor.end() && !encontrada){
        if(it->second > 0){
            outConectada = it->first;
            encontrada = true;
        }
        it++;
    }

    return outConectada;
}


//Ejercicio 6
template <typename T, typename U>
void recorrido_profundidad(const Grafo<T, U>& G, const T& v){
    Conjunto<T> visitados;
    visitados.anadir(v);
    cout << v << " ";

    Conjunto<Vertice<T> > adyacentes = G.adyacentes(v);
    while(!adyacentes.esVacio())
        recorrido_profundidad(G, adyacentes.quitar().getObj(), visitados);
}
template <typename T, typename U>
void recorrido_profundidad(const Grafo<T, U>& G, const T& v, Conjunto<T> visitados){
    visitados.anadir(v);
    cout << v << " ";

    Conjunto<Vertice<T> > adyacentes = G.adyacentes(v);
    while(!adyacentes.esVacio()){
        T tmp = adyacentes.quitar().getObj();
        if(!visitados.pertenece(tmp))
        recorrido_profundidad(G, tmp, visitados);
    }
}


//Ejercicio extra de examen
template <typename T, typename U>
void caminoaVertice(const Grafo<T, U>& G, const T destino){
    Conjunto<T> visitados;
    Conjunto<Vertice<T> > vertices = G.vertices();

    while(!vertices.esVacio())
        caminoaVertice(G, vertices.quitar().getObj(), destino, visitados);
}

template <typename T, typename U>
void caminoaVertice(const Grafo<T, U>& G, const T origen, const T destino, Conjunto<T> visitados){
    visitados.anadir(origen);
    if(origen == destino){
        while(!visitados.esVacio())
            cout << visitados.quitar() << " ";
        cout << endl;
    }else{
        Conjunto<Vertice<T> > adyacentes = G.adyacentes(origen);
        while(!adyacentes.esVacio()){
            T v = adyacentes.quitar().getObj();
            if(!visitados.pertenece(v))
                caminoaVertice(G, v, destino, visitados);
        }

    }
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
    G.insertarArista(4, 7, 3);
    G.insertarArista(5, 1, 6);

    Grafo<string, float> H(7);
    H.insertarVertice("Huelva"); H.insertarVertice("Lepe"); H.insertarVertice("Niebla");
    H.insertarVertice("Mazagon"); H.insertarVertice("Almonte"); H.insertarVertice("Aljaraque");
    H.insertarVertice("Matalascañas");
    H.insertarArista("Lepe", "Huelva", 4);
    H.insertarArista("Huelva", "Niebla", 3);
    H.insertarArista("Huelva", "Mazagon", 2);
    H.insertarArista("Huelva", "Aljaraque", 1);
    H.insertarArista("Mazagon", "Almonte", 3);
    H.insertarArista("Mazagon", "Matalascañas", 4);
    H.insertarArista("Aljaraque", "Mazagon", 5);
    H.insertarArista("Almonte", "Huelva", 6);


    cout << " Vertice de maximo coste en G: " << verticeMaxCoste(G) << endl;
    cout << " Vertice de maximo coste en H: " << verticeMaxCoste(H) << endl;

    cout << endl << " Vertices inaccesibles en G: ";
    inaccesibles(G);
    cout << endl << " Vertices inaccesibles en H: ";
    inaccesibles(H);

    cout << endl << endl << " Camino entre Dos en H de Lepe a Almonte: ";
    cout << (caminoEntreDos(H, string("Lepe"), string("Almonte")) ? " SI " : " NO ") << endl;
    cout << " Camino entre Dos en H de Aljaraque a Lepe: ";
    cout << (caminoEntreDos(H, string("Aljaraque"), string("Lepe")) ? " SI " : " NO ") << endl;

    cout << endl << " Caminos acotados en G a coste 9 desde el vertice 2:" << endl;
    caminosAcotados(G, 2, 9);

    cout << endl << endl << " Vertice outConectado en G: " << outConectado(G);
    cout << endl << " Vertice outConectado en H: " << outConectado(H);

    cout << endl << endl << " Recorrido en profundidad de H desde el vertice Huelva:  ";
    recorrido_profundidad(H, string("Huelva"));
    cout << endl << endl;


    cout << "Caminos que llegan Mazagon:" << endl;
    caminoaVertice(H, string("Mazagon"));
    cout << endl;


    system("PAUSE");
    return EXIT_SUCCESS;
}
