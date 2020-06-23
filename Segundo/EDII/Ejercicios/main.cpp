#include <iostream>
#include <queue>
#include <sstream>
#include <map>
#include <list>
#include <queue>
#include <cmath>
#include "grafo.h"
#include "conjunto.h"
#include "arbin.h"
#include "abb.h"

using namespace std;


///Funciones del profesor
template <typename T>
void inorden(const Arbin<T>& a, const typename Arbin<T>::Iterador& r) {
    if (!r.arbolVacio()) {
        inorden(a, a.subIzq(r));
        cout << r.observar() << " ";
        inorden(a, a.subDer(r));
    }
}

template <typename T>
void preorden(const Arbin<T>& a, const typename Arbin<T>::Iterador& r) {
    if (!r.arbolVacio()) {
        cout << r.observar() << " ";
        preorden(a, a.subIzq(r));
        preorden(a, a.subDer(r));
    }
}

template <typename T>
void postorden(const Arbin<T>& a, const typename Arbin<T>::Iterador& r) {
    if (!r.arbolVacio()) {
        postorden(a, a.subIzq(r));
        postorden(a, a.subDer(r));
        cout << r.observar() << " ";
    }
}

template <typename T>
void anchura(const Arbin<T>& a) {
    if (!a.esVacio()) {
        queue<typename Arbin<T>::Iterador> c;
        typename Arbin<T>::Iterador ic = a.getRaiz();
        c.push(ic);
        while (!c.empty()) {
             ic = c.front();
             c.pop();
             cout << ic.observar() << " ";
             if (!a.subIzq(ic).arbolVacio())
                c.push(a.subIzq(ic));
             if (!a.subDer(ic).arbolVacio())
                c.push(a.subDer(ic));
        }
    }
}
///Fin de las funciones del profesor

///Arboles binarios

//Tema 2 - 1. Determinar si A y B son arboles idénticos
template <typename T>
bool identicos(const Arbin<T>& a, const Arbin<T>& b) {
    return identicos(a, a.getRaiz(), b, b.getRaiz());
}
template <typename T>
bool identicos(const Arbin<T>& a, const typename Arbin<T>::Iterador& ra, const Arbin<T>& b, const typename Arbin<T>::Iterador& rb) {
    if(ra.arbolVacio() && rb.arbolVacio())
        return true;
    else if((ra.arbolVacio() && !rb.arbolVacio()) || (!ra.arbolVacio() && rb.arbolVacio()) || ra.observar() != rb.observar())
        return false;
    else
        return ra.observar() == rb.observar() && identicos(a, a.subIzq(ra), b, b.subIzq(rb)) && identicos(a, a.subDer(ra), b, b.subDer(rb));

}

//Tema 2 - 2. Suma de arbol de enteros
int suma(const Arbin<int>& a, const typename Arbin<int>::Iterador& r) {
    if(r.arbolVacio())
        return 0;
    else
        return r.observar() + suma(a, a.subIzq(r)) + suma(a, a.subDer(r));

}
int suma(const Arbin<int>& a) {
    return suma(a, a.getRaiz());
}

//Tema 2 - 3. Copia con simetría vertical
template <typename T>
const Arbin<T> simetrico(const Arbin<T>& a) {
    return simetrico(a, a.getRaiz());
}
template <typename T>
const Arbin<T> simetrico(const Arbin<T>& a, const typename Arbin<T>::Iterador& r) {
    if(r.arbolVacio() )
        return Arbin<T>();
    else
        return Arbin<T>(r.observar(), simetrico(a, a.subDer(r)), simetrico(a, a.subIzq(r)));

}

//Tema 2 - 4. Visualizar nodos a cierto nivel
template <typename T>
void nivel(const Arbin<T>& a, int n) {
    return nivel(a, a.getRaiz(), n);
}
template <typename T>
void nivel(const Arbin<T>& a, const typename Arbin<T>::Iterador& r, int n) {
    if(!r.arbolVacio()){
        if(n == 0){
            cout << r.observar() << " ";
        }else{
            n--;
            nivel(a, a.subIzq(r), n);
            nivel(a, a.subDer(r), n);
        }
    }
}

//Tema 2 - 5. Comprobar si un arbol es completo
template <typename T>
bool completo(const Arbin<T>& a) {
    if(a.esVacio()){
        return true;
    }else{
        int ultimoNivel = altura(a)-1;
        return nodosNivel(a, ultimoNivel) == pow(2, ultimoNivel);
    }
}
template <typename T>
int nodosNivel(const Arbin<T>& a, int n) {
    return nodosNivel(a, a.getRaiz(), n);
}
template <typename T>
int nodosNivel(const Arbin<T>& a, const typename Arbin<T>::Iterador& r, int n) {
    if(r.arbolVacio()){
        return 0;
    }else{
        if(n == 0){
            return 1;
        }else{
            n--;
            return nodosNivel(a, a.subIzq(r), n) + nodosNivel(a, a.subDer(r), n);
        }
    }
}
template <typename T>
int altura(const Arbin<T>& a) {
    return altura(a, a.getRaiz(), 0);
}
template <typename T>
int altura(const Arbin<T>& a, const typename Arbin<T>::Iterador& r, int profundidad) {
    if(r.arbolVacio()){
        return profundidad;
    }else{
        profundidad++;
        int pIzq = altura(a, a.subIzq(r), profundidad), pDer = altura(a, a.subDer(r), profundidad);
        return pIzq>pDer?pIzq:pDer;
    }
}

//Tema 2 - 6. Frontera en una lista
template <typename T>
void frontera(const Arbin<T>& a, vector<T>& lista) {
    frontera(a, a.getRaiz(), lista);
}
template <typename T>
void frontera(const Arbin<T>& a, const typename Arbin<T>::Iterador& r, vector<T>& lista) {
    if(!r.arbolVacio()){
        if(a.subIzq(r).arbolVacio() && a.subDer(r).arbolVacio()){
            lista.push_back(r.observar());
        }else{
            frontera(a, a.subIzq(r), lista);
            frontera(a, a.subDer(r), lista);
        }
    }
}

//Tema 2 - 7. Existe un recorrido en un arbol
template <typename T>
bool existeCamino(const Arbin<T>& a, const vector<T>& camino) {
    if(camino.size() == 0) return true;
    return existeCamino(a, a.getRaiz(), camino, 0);
}
template <typename T>
bool existeCamino(const Arbin<T>& a, const typename Arbin<T>::Iterador& r, const vector<T>& camino, int pos) {
    if(!r.arbolVacio()){
        if(camino[pos] == r.observar()){
            pos++;
            return camino.size() == pos || existeCamino(a, a.subIzq(r), camino, pos) || existeCamino(a, a.subDer(r), camino, pos);
        }else return false;
    }else return false;
}

//Tema 2 - 8. Determinar si tienen la misma forma
template <typename T>
bool mismaForma(const Arbin<T>& a, const Arbin<T>& b) {
    return mismaForma(a, a.getRaiz(), b, b.getRaiz());
}
template <typename T>
bool mismaForma(const Arbin<T>& a, const typename Arbin<T>::Iterador& ra, const Arbin<T>& b, const typename Arbin<T>::Iterador& rb) {
    if(ra.arbolVacio() && rb.arbolVacio())
        return true;
    else if((ra.arbolVacio() && !rb.arbolVacio()) || (!ra.arbolVacio() && rb.arbolVacio()))
        return false;
    else
        return mismaForma(a, a.subIzq(ra), b, b.subIzq(rb)) && mismaForma(a, a.subDer(ra), b, b.subDer(rb));

}

///Final de los arboles binarios


///Arboles binarios de busqueda ABB
//Tema 3 - 1. Comprobar si es un arbol binario de busqueda
int buscarMayor(const Arbin<int>& a, const typename Arbin<int>::Iterador& r){
    if(a.subDer(r).arbolVacio())
        return r.observar();
    else
        return buscarMayor(a, a.subDer(r));
}
int buscarMenor(const Arbin<int>& a, const typename Arbin<int>::Iterador& r){
    if(a.subIzq(r).arbolVacio())
        return r.observar();
    else
        return buscarMenor(a, a.subIzq(r));
}
bool esABB(const Arbin<int>& a, const typename Arbin<int>::Iterador& r) {
    if(r.arbolVacio())
        return true;
    else if((a.subIzq(r).arbolVacio() || buscarMenor(a, a.subIzq(r)) < r.observar()) && (a.subDer(r).arbolVacio() || buscarMayor(a, a.subDer(r)) > r.observar()))
        return esABB(a, a.subIzq(r)) && esABB(a, a.subDer(r));
    else
        return false;

}
bool esABB(const Arbin<int>& a) {
    return esABB(a, a.getRaiz());
}

//Tema 3 - 1. Comprobar si es un arbol binario de busqueda ALTERNATIVA
void aplanarABB(const Arbin<int>& a, const typename Arbin<int>::Iterador& r, vector<int>& lista) {
    if(!r.arbolVacio()){
        aplanarABB(a, a.subDer(r), lista);
        lista.push_back(r.observar());
        aplanarABB(a, a.subIzq(r), lista);
    }
}
bool esABBalt(const Arbin<int>& a) {
    vector<int> lista;
    aplanarABB(a, a.getRaiz(), lista);

    bool bueno = true;
    int i = 0;

    while(i < (int)(lista.size())-1 && bueno)
        bueno = lista[i] > lista[++i];

    return bueno;
}

//Tema 3 - 2. Recorrer en orden descendente (inorden izq<-der)
void mayorMenorABB(const ABB<int>& a, const typename Arbin<int>::Iterador& r){
    if(!r.arbolVacio()){
        mayorMenorABB(a, a.subDer(r));
        cout << r.observar() << " ";
        mayorMenorABB(a, a.subIzq(r));
    }
}
void mayorMenorABB(const ABB<int>& a) {
    mayorMenorABB(a, a.getRaiz());
}

//Tema 3 - 3. Antecesores de un elemento
bool antecesoresDe(const ABB<int>& a, const typename Arbin<int>::Iterador& r, int x){
    if(r.arbolVacio())
        return false;
    else if(r.observar() == x)
        return true;
    else if(antecesoresDe(a, a.subDer(r), x) || antecesoresDe(a, a.subIzq(r), x)){
        cout << r.observar() << " ";
        return true;
    }else
        return false;
}
void antecesoresDe(const ABB<int>& a, int x) {
    antecesoresDe(a, a.getRaiz(), x);
}

//Tema 3 - 3. Antecesores de un elemento ALTERNATIVA
void antecesoresDeAlt(const ABB<int>& a, const typename Arbin<int>::Iterador& r, int x){
    if(!r.arbolVacio()){
        if(r.observar() < x){
            antecesoresDeAlt(a, a.subDer(r), x);
            cout << r.observar() << " ";
        }else if(r.observar() > x){
            antecesoresDeAlt(a, a.subIzq(r), x);
            cout << r.observar() << " ";
        }
    }
}
void antecesoresDeAlt(const ABB<int>& a, int x) {
    antecesoresDeAlt(a, a.getRaiz(), x);
}

//Tema 3 - 4. Devolver el elemento anterior
void anteriorInorden(const ABB<int>& a, const typename ABB<int>::Iterador& r, int x, int& actual, int& anterior) {
    if (!r.arbolVacio()) {
        anteriorInorden(a, a.subIzq(r), x, actual, anterior);
        if(r.observar() == x)
            anterior = actual;
        actual = r.observar();
        anteriorInorden(a, a.subDer(r), x, actual, anterior);
    }
}
int anteriorInorden(const ABB<int>& a, int x) {
    int actual = -1, anterior = -1;
    anteriorInorden(a, a.getRaiz(), x, actual, anterior);
    return anterior;
}
///Final de los arboles binarios de busqueda


///Arboles binarios de busqueda equilibrados AVL
//Tema 3 - 3. Antecesores de un elemento ALTERNATIVA
template <typename T>
int contarNodos(const Arbin<T>& a, const typename Arbin<T>::Iterador& r) {
    if(r.arbolVacio())
        return 0;
    else
        return 1 + contarNodos(a, a.subIzq(r)) + contarNodos(a, a.subDer(r));

}
bool esAVL(const ABB<int>& a, const typename Arbin<int>::Iterador& r){
    if(r.arbolVacio())
        return true;
    else
        if(abs(contarNodos(a, a.subIzq(r))-contarNodos(a, a.subDer(r))) > 1)
            return false;
        else
            return esAVL(a, a.subIzq(r)) && esAVL(a, a.subDer(r));
}
bool esAVL(const ABB<int>& a) {
    return esAVL(a, a.getRaiz());
}

///Final de Arboles binarios de busqueda equilibrados

///Grafos

//Tema 5 - 1. Todos sus vertices tienen el mismo numero de vertices adyacentes
template <typename T, typename U>
bool regular(const Grafo<T, U>& G){
    map<T, int> adyacentes;

    Conjunto<Vertice<T> > vertices = G.vertices();
    while(!vertices.esVacio())
        adyacentes[vertices.quitar().getObj()] = 0;

    Conjunto<Arista<T, U> > aristas = G.aristas();
    while(!aristas.esVacio())
        adyacentes[aristas.quitar().getDestino()] ++;

    bool regular = true;
    typename map<T, int>::iterator it = adyacentes.begin();
    typename map<T, int>::iterator ultimo = adyacentes.end();
    ultimo--;
    while(it != ultimo && regular){
        regular = it->second == (++it)->second;
    }

    return regular;
}

//Tema 5 - 3. Comprobar si existe un camino entre dos vertices
template <typename T, typename U>
bool existeCamino(const Grafo<T, U>& G, T origen, T destino){
    Conjunto<T> visitados;
    return existeCamino(G, origen, destino, visitados);
}
template <typename T, typename U>
bool existeCamino(const Grafo<T, U>& G, T actual, T destino, Conjunto<T> visitados){
    visitados.anadir(actual);
    if(actual == destino) return true;

    bool encontrado = false;
    Conjunto< Vertice<T> > adyacentes = G.adyacentes(actual);
    while(!adyacentes.esVacio() && !encontrado){
        T elemento = adyacentes.quitar().getObj();
        if(!visitados.pertenece(elemento))
            encontrado = existeCamino(G, elemento, destino, visitados);
    }
    return encontrado;
}

//Tema 5 - 4. Mostrar los ciclos simples de un grafo
template <typename T>
void mostrarCiclo(list<T> ciclo){

    typename list<T>::iterator it;
    for (it = ciclo.begin(); it != ciclo.end(); it++){
        cout << (*it) << " ";
    }
    cout << endl;

}
template <typename T, typename U>
void ciclosSimples(const Grafo<T, U>& G){
    map<T, bool> visitados;
    Conjunto<Vertice<T> > vertices = G.vertices();
    while(!vertices.esVacio())
        visitados[vertices.quitar().getObj()] = false;

    list<T> ciclo;
    vertices = G.vertices();
    while(!vertices.esVacio())
        ciclosSimples(G, vertices.quitar().getObj(), visitados, ciclo);
}
template <typename T, typename U>
void ciclosSimples(const Grafo<T, U>& G, T actual, map<T , bool> visitados, list<T> ciclo){
    visitados[actual] = true;
    ciclo.push_back(actual);

    Conjunto<Vertice<T> > adyacentes = G.adyacentes(actual);
    while(!adyacentes.esVacio()){
        T v = adyacentes.quitar().getObj();
        if(v == ciclo.front()){
            ciclo.push_back(v);
            mostrarCiclo(ciclo);
        }else{
            if(!visitados[v]){
                ciclosSimples(G, v, visitados, ciclo);
            }
        }
    }
}

//Tema 5 - 8. Un grafo es par si n/2 vertices tienen grado de salida par
template <typename T, typename U>
bool grafoPar(const Grafo<T, U>& G){
    map<T, int> salidas;
    Conjunto<Vertice<T> > vertices = G.vertices();
    while(!vertices.esVacio())
        salidas[vertices.quitar().getObj()] = 0;

    Conjunto<Arista<T, U> > aristas = G.aristas();
    while(!aristas.esVacio())
        salidas[aristas.quitar().getOrigen()]++;

    int par = 0;
    vertices = G.vertices();
    while(!vertices.esVacio()){
        T v = vertices.quitar().getObj();
        if(salidas[v] != 0 && salidas[v]%2 == 0)
            par++;
    }

    return par == G.vertices().cardinalidad()/2;
}

//Tema 5 - 9. Existe un camino entre vertices ITERATIVO
template <typename T, typename U>
bool existeCaminoITER(const Grafo<T, U>& G, T origen, T destino){
    bool encontrado = false;

    Conjunto<T> visitados, pendientes;
    pendientes.anadir(origen);
    while(!pendientes.esVacio() && !encontrado){
        T actual = pendientes.quitar();
        visitados.anadir(actual);
        if(actual == destino){
            encontrado = true;
        }else{
            Conjunto<Vertice<T> > adyacentes = G.adyacentes(actual);
            while(!adyacentes.esVacio()){
                T v = adyacentes.quitar().getObj();
                if(!visitados.pertenece(v) && ! pendientes.pertenece(v)){
                    pendientes.anadir(v);
                }
            }
        }
    }

    return encontrado;
}

//Tema 5 - 10. Existe un camino entre vertices RECURSIVO
template <typename T, typename U>
bool existeCaminoREC(const Grafo<T, U>& G, T origen, T destino){
    Conjunto<T> visitados;
    return existeCamino(G, origen, destino, visitados);
}
template <typename T, typename U>
bool existeCaminoREC(const Grafo<T, U>& G, T origen, T destino, Conjunto<T> visitados){
    if(origen == destino) return true;

    bool encontrado = false;

    visitados.anadir(origen);

    Conjunto<Vertice<T> > adyacentes = G.adyacentes(origen);
    while(!adyacentes.esVacio() && ! encontrado){
        encontrado = existeCamino(G, adyacentes.eliminar().getObj(), destino, visitados);
    }

    return encontrado;
}

//Tema 5 - 11. Algoritmo Dijkstrap con tablas de adyacencia
void dijkstraMatriz(int **matriz, int nVertices, int origen, int destino){
    bool pendientes[nVertices];
    int A[nVertices], D[nVertices];
    for(int i = 0; i < nVertices; i++){
        pendientes[i] = true;
        A[i] = -1;
        D[i] = 0;
    }

    cout << "Actual  ";
    for(int i = 0; i < nVertices; i++)
        cout << "D[" << (i+1) << "]    ";
    for(int i = 0; i < nVertices; i++)
        cout << "A[" << (i+1) << "]    ";
    cout << "Pendientes" << endl;

    int actual = origen-1;
    do{
        for(int i = 0; i < nVertices; i++){
            if(matriz[actual][i] > 0 && (D[i] == 0 || D[i] > D[actual]+matriz[actual][i])){
                D[i] = D[actual]+matriz[actual][i];
                A[i] = actual;
            }
        }

        cout << (actual+1) << "\t  ";
        for(int i = 0; i < nVertices; i++)
            cout << D[i] << "\t";
        for(int i = 0; i < nVertices; i++)
            cout << (A[i]+1) << "\t";
        for(int i = 0; i < nVertices; i++)
            if(pendientes[i])
                cout << (i+1) << " ";
        cout << endl;

        pendientes[actual] = false;
        actual = -1;
        for(int i = 0; i < nVertices; i++)
            if(pendientes[i] && D[i] > 0 && (actual == -1 || D[i] < D[actual]))
                actual = i;
    }while(actual != -1);

    cout << endl;
    actual = destino-1;
    do{
        cout << "V" << (actual+1) << " <- ";
        actual = A[actual];
    }while(actual != -1);
}

//Tema 5 - Vertices demasiado lejanos
template <typename T, typename U>
Conjunto<T> verticesDemasiadoLejanos(const Grafo<T, U>& G, T origen, int d){
    Conjunto<T> visitados, lejanos;

    verticesDemasiadoLejanos(G, origen, d, visitados);

    Conjunto<Vertice<T> > vertices = G.vertices();
    while(!vertices.esVacio()){
        T v = vertices.quitar().getObj();
        if(!visitados.pertenece(v))
            lejanos.anadir(v);
    }
    return lejanos;
}
template <typename T, typename U>
void verticesDemasiadoLejanos(const Grafo<T, U>& G, T origen, int d, Conjunto<T> &visitados){
    visitados.anadir(origen);

    if(d > 0){
        d--;
        Conjunto<Vertice<T> > adyacentes = G.adyacentes(origen);
        while(!adyacentes.esVacio())
            verticesDemasiadoLejanos(G, adyacentes.quitar().getObj(), d, visitados);
    }
}

///Fin de Grafos


int main(){
    Arbin<char> A('t', Arbin<char>('m', Arbin<char>(),
                                        Arbin<char>('f', Arbin<char>(), Arbin<char>())),
                       Arbin<char>('k', Arbin<char>('d', Arbin<char>(), Arbin<char>()),
                                        Arbin<char>()));
    Arbin<char> B('t', Arbin<char>('m', Arbin<char>(),
                                        Arbin<char>('f', Arbin<char>(), Arbin<char>())),
                       Arbin<char>('k', Arbin<char>('d', Arbin<char>(), Arbin<char>()),
                                        Arbin<char>()));
    cout << "A y B identicos: " << (identicos(A, B)?"Si":"No") << endl << endl;

    Arbin<int> C(2, Arbin<int>(7, Arbin<int>(2, Arbin<int>(), Arbin<int>()),
                                  Arbin<int>(6, Arbin<int>(5, Arbin<int>(), Arbin<int>()),
                                                Arbin<int>(11, Arbin<int>(), Arbin<int>()))),
                    Arbin<int>(5, Arbin<int>(),
                                  Arbin<int>(9, Arbin<int>(),
                                                  Arbin<int>(4, Arbin<int>(), Arbin<int>()))));
    cout << "Suma de los enteros de C: " << suma(C) << endl << endl;

    Arbin<char> D = simetrico(A);
    cout << "Anchura de A: ";
    anchura(A); cout << endl;
    cout << "Anchura de D: ";
    anchura(D); cout << endl << endl;

    cout << "Los nodos del nivel 1 de A: ";
    nivel(A, 1);
    cout << endl << endl;

    Arbin<char> E('t', Arbin<char>('m', Arbin<char>('e', Arbin<char>(), Arbin<char>()),
                                        Arbin<char>('f', Arbin<char>(), Arbin<char>())),
                       Arbin<char>('k', Arbin<char>('d', Arbin<char>(), Arbin<char>()),
                                        Arbin<char>('g', Arbin<char>(), Arbin<char>())));

    cout << "A es un arbol completo: " << (completo(A)?"Si":"No") << endl;
    cout << "E es un arbol completo: " << (completo(E)?"Si":"No") << endl << endl;

    vector<char> lista;
    frontera(A, lista);
    cout << "Frontera de A: ";
    for(int i = 0; i < lista.size(); i++)
        cout << lista[i] << " ";
    cout << endl << endl;

    Arbin<char> F('m', Arbin<char>('q', Arbin<char>('s', Arbin<char>(), Arbin<char>()),
                                        Arbin<char>('t', Arbin<char>(), Arbin<char>())),
                       Arbin<char>('d', Arbin<char>('k', Arbin<char>(), Arbin<char>()),
                                        Arbin<char>()));

    vector<char> camino;
    camino.push_back('m'); camino.push_back('q'); camino.push_back('t');
    cout << "Existe camino m-q-t en F: " << (existeCamino(F, camino)?"Si":"No") << endl;
    camino.clear(); camino.push_back('m'); camino.push_back('d');
    cout << "Existe camino m-d en F: " << (existeCamino(F, camino)?"Si":"No") << endl;
    camino.clear(); camino.push_back('r'); camino.push_back('q'); camino.push_back('t');
    cout << "Existe camino r-q-t en F: " << (existeCamino(F, camino)?"Si":"No") << endl;
    camino.clear(); camino.push_back('m'); camino.push_back('k');
    cout << "Existe camino m-k en F: " << (existeCamino(F, camino)?"Si":"No") << endl << endl;

    Arbin<char> G('f', Arbin<char>('g', Arbin<char>(),
                                        Arbin<char>('v', Arbin<char>(), Arbin<char>())),
                       Arbin<char>('r', Arbin<char>('i', Arbin<char>(), Arbin<char>()),
                                        Arbin<char>()));

    cout << "A tiene la misma forma que G: " << (mismaForma(A, G)?"Si":"No") << endl;
    cout << "A tiene la misma forma que E: " << (mismaForma(A, E)?"Si":"No") << endl << endl;


    Arbin<int> H(2, Arbin<int>(7, Arbin<int>(2, Arbin<int>(), Arbin<int>()),
                                  Arbin<int>(6, Arbin<int>(5, Arbin<int>(), Arbin<int>()),
                                                Arbin<int>(11, Arbin<int>(), Arbin<int>()))),
                    Arbin<int>(5, Arbin<int>(),
                                  Arbin<int>(9, Arbin<int>(),
                                                  Arbin<int>(4, Arbin<int>(), Arbin<int>()))));

    Arbin<int> I(5, Arbin<int>(3, Arbin<int>(2, Arbin<int>(1, Arbin<int>(), Arbin<int>()), Arbin<int>()),
                                  Arbin<int>(4, Arbin<int>(), Arbin<int>())),
                    Arbin<int>(7, Arbin<int>(6, Arbin<int>(), Arbin<int>()),
                                  Arbin<int>(8, Arbin<int>(),
                                                  Arbin<int>(9, Arbin<int>(), Arbin<int>()))));

    cout << "H es un ABB: " << (esABB(H)?"Si":"No") << "    y el metodo alternativo dice: " << (esABBalt(H)?"Si":"No") << endl;
    cout << "I es un ABB: " << (esABB(I)?"Si":"No") << "    y el metodo alternativo dice: " << (esABBalt(I)?"Si":"No") << endl << endl;

    ABB<int> J, K;
    J.insertar(8); J.insertar(3); J.insertar(10); J.insertar(1); J.insertar(6);
    J.insertar(14); J.insertar(4); J.insertar(7); J.insertar(13);
    K.insertar(5); K.insertar(1); K.insertar(3); K.insertar(8); K.insertar(6);

    cout << "ABB J en orden descendente: ";
    mayorMenorABB(J); cout << endl;
    cout << "ABB K en orden descendente: ";
    mayorMenorABB(K); cout << endl << endl;

    cout << "Antecesores de 13 en J: ";
    antecesoresDe(J, 13); cout << "\t Forma alternativa: ";
    antecesoresDeAlt(J, 13); cout << endl;
    cout << "Antecesores de 6 en J: ";
    antecesoresDe(J, 6); cout << "\t Forma alternativa: ";
    antecesoresDeAlt(J, 6); cout << endl << endl;

    cout << "Anterior en inorden de 6 en J " << anteriorInorden(J, 6) << endl;
    cout << "Anterior en inorden de 4 en J " << anteriorInorden(J, 4) << endl;
    cout << "Anterior en inorden de 13 en J " << anteriorInorden(J, 13) << endl << endl;

    ABB<int> L;
    L.insertar(10); J.insertar(9); J.insertar(11); J.insertar(8);

    cout << "J es un AVL: " << (esAVL(J)?"Si":"No") << endl;
    cout << "K es un AVL: " << (esAVL(K)?"Si":"No") << endl;
    cout << "L es un AVL: " << (esAVL(L)?"Si":"No") << endl << endl;

    Grafo<int, float> N(7);
    for (int i = 1; i <= 7; i++) N.insertarVertice(i);
    N.insertarArista(2, 1, 4);
    N.insertarArista(1, 3, 3);
    N.insertarArista(1, 4, 2);
    N.insertarArista(1, 6, 1);
    N.insertarArista(6, 4, 5);
    N.insertarArista(4, 7, 3);
    N.insertarArista(5, 1, 6);

    Grafo<int, float> O(7);
    for (int i = 1; i <= 7; i++) O.insertarVertice(i);
    O.insertarArista(2, 1, 4);
    O.insertarArista(1, 3, 3);
    O.insertarArista(1, 4, 2);
    O.insertarArista(6, 5, 5);
    O.insertarArista(4, 6, 7);
    O.insertarArista(4, 7, 3);
    O.insertarArista(3, 2, 1);

    cout << "El grafo N es regular: " << (regular(N)?"Si":"No") << endl;
    cout << "El grafo O es regular: " << (regular(O)?"Si":"No") << endl << endl;

    cout << "En N existe un camino entre 1 y 7: " << (existeCamino(N, 1, 7)?"Si":"No") << endl;
    cout << "En N existe un camino entre 5 y 2: " << (existeCamino(N, 5, 2)?"Si":"No") << endl << endl;

    Grafo<int, float> P(3);
    for (int i = 1; i <= 3; i++) P.insertarVertice(i);
    P.insertarArista(1, 2, 1);
    P.insertarArista(2, 1, 1);
    P.insertarArista(2, 3, 1);
    P.insertarArista(3, 1, 1);

    cout << "Ciclos simples en P:" << endl;
    ciclosSimples(P); cout << endl;

    Grafo<int, float> Q(4);
    for (int i = 1; i <= 4; i++) Q.insertarVertice(i);
    Q.insertarArista(1, 2, 1);
    Q.insertarArista(1, 3, 1);
    Q.insertarArista(2, 1, 1);
    Q.insertarArista(2, 4, 1);

    cout << "El grafo N es par: " << (grafoPar(N)?"Si":"No") << endl;
    cout << "El grafo O es par: " << (grafoPar(O)?"Si":"No") << endl;
    cout << "El grafo Q es par: " << (grafoPar(Q)?"Si":"No") << endl;
    cout << "El grafo P es par: " << (grafoPar(P)?"Si":"No") << endl << endl;

    cout << "El grafo Q 4 es accesible desde 1: " << (existeCaminoITER(Q, 1, 4)?"Si":"No") << " la version recursiva dice: " << (existeCaminoREC(Q, 1, 4)?"Si":"No") << endl;
    cout << "El grafo Q 4 es accesible desde 3: " << (existeCaminoITER(Q, 3, 4)?"Si":"No") << " la version recursiva dice: " << (existeCaminoREC(Q, 3, 4)?"Si":"No") << endl << endl;

    int **R = new int*[6];
    for(int i = 0; i < 6; i++)
        R[i] = new int[6];
    for(int i = 0; i < 6; i++)
        for(int j = 0; j < 6; j++)
            R[i][j] = 0;
    R[0][1] = 20;
    R[0][2] = 75;
    R[0][3] = 60;
    R[1][2] = 2;
    R[1][3] = 30;
    R[1][4] = 25;
    R[2][1] = 100;
    R[2][4] = 200;
    R[3][5] = 20;
    R[4][2] = 40;
    R[4][3] = 3;
    R[4][5] = 25;

    dijkstraMatriz(R, 6, 1, 6);
    cout << endl << endl;

    int **T = new int*[8];
    for(int i = 0; i < 8; i++)
        T[i] = new int[8];
    for(int i = 0; i < 8; i++)
        for(int j = 0; j < 6; j++)
            T[i][j] = 0;
    T[0][1] = 2;
    T[0][6] = 3;
    T[1][2] = 5;
    T[1][7] = 3;
    T[2][3] = 4;
    T[2][4] = 1;
    T[4][3] = 1;
    T[4][5] = 2;
    T[6][7] = 1;
    T[6][4] = 3;
    T[6][5] = 7;
    T[7][2] = 1;
    T[7][4] = 2;

    dijkstraMatriz(T, 8, 1, 4);
    cout << endl << endl;

    Grafo<int, float> S(7);
    for (int i = 1; i <= 7; i++) S.insertarVertice(i);
    S.insertarArista(3, 2, 1);
    S.insertarArista(3, 4, 1);
    S.insertarArista(2, 1, 1);
    S.insertarArista(4, 5, 1);
    S.insertarArista(2, 6, 1);
    S.insertarArista(3, 7, 1);

    cout << "Vertices lejanos a 3 en S con d = 1: ";
    Conjunto<int> lejanos = verticesDemasiadoLejanos(S, 3, 1);
    while(!lejanos.esVacio())
        cout << lejanos.quitar() << " ";
    cout << endl;

    return 0;
}
