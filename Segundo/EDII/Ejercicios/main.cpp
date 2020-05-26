#include <iostream>
#include <queue>
#include <sstream>
#include <map>
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
    }
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
template <typename T>
bool esABB(const Arbin<T>& a) {
    return esABB(a, a.getRaiz());
}
template <typename T>
bool esABB(const Arbin<T>& a, const typename Arbin<T>::Iterador& ra) {
    if(ra.arbolVacio() && rb.arbolVacio())
        return true;
    else if((ra.arbolVacio() && !rb.arbolVacio()) || (!ra.arbolVacio() && rb.arbolVacio()))
        return false;
    else
        return esABB(a, a.subIzq(ra), b, b.subIzq(rb)) && esABB(a, a.subDer(ra), b, b.subDer(rb));

}

//Tema 3 - 2. Recorrer en orden descendente (inorden izq->der)
/*int siguienteMayor(const ABB<int>& a, const typename Arbin<int>::Iterador& r, int x){
    if(r.arbolVacio())
        throw NoHaySiguienteMayor("No hay ningun valor mayor en este arbol");
    else{
        if(r.observar() <= x){
            return siguienteMayor(a, a.subDer(r), x);
        }else{
            try{
                return siguienteMayor(a, a.subIzq(r), x);
            }catch(NoHaySiguienteMayor e){
                return r.observar();
            }
        }
    }
}
int siguienteMayor(const ABB<int>& a, int x) {
    return siguienteMayor(a, a.getRaiz(), x);
}*/
///Final de los arboles binarios de busqueda



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
    cout << "A tiene la misma forma que E: " << (mismaForma(A, E)?"Si":"No") << endl;




    return 0;
}
