#include <iostream>
#include <cstdlib>
#include <queue>
#include <cmath>
#include <cstring>
#include "arbin.h"
#include "abb.h"
#include "excepcion.h"

// Recorridos

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


/***************************************************************************/
/****************************** EJERCICIOS *********************************/
/***************************************************************************/
//Ejercicio 1
template <typename T>
int numHojas(const Arbin<T>& a) {
    return numHojas(a, a.getRaiz());
}
template <typename T>
int numHojas(const Arbin<T>& a, const typename Arbin<T>::Iterador& r) {
    if(r.arbolVacio())
        return 0;
    else if(a.subIzq(r).arbolVacio() && a.subDer(r).arbolVacio())
        return 1;
    else
        return numHojas(a, a.subIzq(r)) + numHojas(a, a.subDer(r));
}

/****************************************************************************/
//Ejercicio 2
template <typename T>
const Arbin<T>& simetrico(const Arbin<T>& a) {
    return *simetrico<T>(a, a.getRaiz());
}
template <typename T>
const Arbin<T>* simetrico(const Arbin<T>& a, const typename Arbin<T>::Iterador& r) {
    if(r.arbolVacio())
        return new Arbin<T>();
    else
        return new Arbin<T>(r.observar(), *simetrico(a, a.subDer(r)), *simetrico(a, a.subIzq(r)));
}

/****************************************************************************/
//Ejercicio 3
template <typename T>
void recorridoZigzag(const Arbin<T>& a, char sentido){
    recorridoZigzag(a, a.getRaiz(), sentido);
}
template <typename T>
void recorridoZigzag(const Arbin<T>& a, const typename Arbin<T>::Iterador& r, char sentido){
    if(!r.arbolVacio()){
        cout << r.observar() <<" ";
        recorridoZigzag(a, sentido=='I'?a.subIzq(r):a.subDer(r), sentido=='I'?'D':'I');
    }
}

/******************************************************************************/
//Ejercicio 4
template <typename T>
bool compensado(const Arbin<T>& a){
    return compensado(a, a.getRaiz());
}
template <typename T>
bool compensado(const Arbin<T>& a, const typename Arbin<T>::Iterador& r){
    if(r.arbolVacio() || (a.subIzq(r).arbolVacio() && a.subDer(r).arbolVacio()))
        return true;
    else
        if(!(compensado(a, a.subIzq(r)) && compensado(a,  a.subDer(r))))
            return false;
        else
            return abs(hijos(a, a.subIzq(r))-hijos(a,  a.subDer(r))) <= 1;
}
template <typename T>
int hijos(const Arbin<T>& a, const typename Arbin<T>::Iterador& r){
    if(r.arbolVacio())
        return 0;
    else
        return hijos(a, a.subIzq(r)) + hijos(a, a.subDer(r)) + 1;
}


/*****************************************************************************/
//Ejercicio 5
template <typename T>
void palabras(const Arbin<T>& a){
    palabras(a, a.getRaiz(), "");
}
template <typename T>
void palabras(const Arbin<T>& a, const typename Arbin<T>::Iterador& r, string palabra){
    if(!r.arbolVacio()){
        if(a.subIzq(r).arbolVacio() && a.subDer(r).arbolVacio())
            cout << palabra << r.observar() << "\n";
        else{
            palabras(a, a.subIzq(r), palabra+r.observar());
            palabras(a, a.subDer(r), palabra+r.observar());
        }
    }
}

/******************************************************************************/
//Ejercicio 6
int siguienteMayor(const ABB<int>& a, const typename Arbin<int>::Iterador& r, int x) throw (NoHaySiguienteMayor){
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
}//Por algun motivo que no llego a comprender, el compilador se hace un lio si pongo esta funcion detras.
int siguienteMayor(const ABB<int>& a, int x) throw (NoHaySiguienteMayor){
    return siguienteMayor(a, a.getRaiz(), x);
}


/******************************************************************************/
//Ejercicio 7
int posicionInorden(const ABB<int>& a, const typename Arbin<int>::Iterador& r, int x, int& n){
    int pos = 0;
    if(!r.arbolVacio()){
        pos = posicionInorden(a, a.subIzq(r), x, n);
        if(pos == 0){
            n++;
            if(r.observar() == x)
                pos = n;
            else
                pos = posicionInorden(a, a.subDer(r), x, n);
        }
    }
    return pos;
}
int posicionInorden(const ABB<int>& a, int x){
    int n = 0;
    return posicionInorden(a, a.getRaiz(), x, n);
}

/******************************************************************************/
//Ejercicio 8
bool haySumaCamino(const Arbin<int>& a, const typename Arbin<int>::Iterador& r, int suma){
    if(r.arbolVacio())
        return false;
    else
        if(a.subIzq(r).arbolVacio() && a.subDer(r).arbolVacio())
            return suma-r.observar() == 0;
        else
            return haySumaCamino(a, a.subIzq(r), suma-r.observar()) || haySumaCamino(a, a.subDer(r), suma-r.observar());
}
bool haySumaCamino(const Arbin<int>& a, int suma){
    return haySumaCamino(a, a.getRaiz(), suma);
}


/****************************************************************************/
/****************************************************************************/
int main(int argc, char *argv[]){
    Arbin<char> A('t', Arbin<char>('m', Arbin<char>(),
                                        Arbin<char>('f', Arbin<char>(), Arbin<char>())),
                       Arbin<char>('k', Arbin<char>('d', Arbin<char>(), Arbin<char>()),
                                        Arbin<char>()));

    Arbin<char> B('t', Arbin<char>('n', Arbin<char>(),
                                        Arbin<char>('d', Arbin<char>('e', Arbin<char>(), Arbin<char>()),
                                                         Arbin<char>())),
                       Arbin<char>('m', Arbin<char>('f', Arbin<char>(), Arbin<char>()),
                                        Arbin<char>('n', Arbin<char>(), Arbin<char>())));

    Arbin<char> D('t', Arbin<char>('k', Arbin<char>('d', Arbin<char>(), Arbin<char>()),
                                        Arbin<char>()),
                       Arbin<char>('m', Arbin<char>(),
                                        Arbin<char>('f', Arbin<char>(), Arbin<char>())));

    Arbin<char> E('o', Arbin<char>('r', Arbin<char>(),
                                        Arbin<char>('o', Arbin<char>(), Arbin<char>())),
                       Arbin<char>('l', Arbin<char>('a', Arbin<char>(), Arbin<char>()),
                                        Arbin<char>('e', Arbin<char>(), Arbin<char>())));

    Arbin<int> F(2, Arbin<int>(7, Arbin<int>(2, Arbin<int>(), Arbin<int>()),
                                  Arbin<int>(6, Arbin<int>(5, Arbin<int>(), Arbin<int>()),
                                                Arbin<int>(11, Arbin<int>(), Arbin<int>()))),
                    Arbin<int>(5, Arbin<int>(),
                                  Arbin<int>(9, Arbin<int>(),
                                                  Arbin<int>(4, Arbin<int>(), Arbin<int>()))));

    ABB<int> BB6, BB7;


    // NUMERO HOJAS //
    cout << "Num. hojas del arbol B: " << numHojas(B) << endl;
    cout << "Num. hojas del arbol E: " << numHojas(E) << endl << endl;


    // COPIA SIMETRICA //
    Arbin<char> C = simetrico(B);
    cout << "Recorrido en inorden del arbol B: " << endl;
    inorden(B, B.getRaiz());
    cout << endl << "Recorrido en inorden del arbol C: " << endl;
    inorden(C, C.getRaiz());
    cout << endl << endl;


    // RECORRIDO EN ZIG-ZAG //
    cout << "Recorrido en zigzag I de B:\n";
    recorridoZigzag(B, 'I');
    cout << endl;
    cout << "Recorrido en zigzag D de C:\n";
    recorridoZigzag(C, 'D');
    cout << endl << endl;


    // COMPENSADO //
    cout << "Esta A compensado?:";
    cout << (compensado(A) ? " SI" : " NO") << endl;
    cout << "Esta B compensado?:";
    cout << (compensado(B) ? " SI" : " NO") << endl << endl;


    // PALABRAS DE UN ARBOL //
    cout << "PALABRAS DE A:\n";
    palabras(A);
    cout << "PALABRAS DE B:\n";
    palabras(B);
    cout << endl;


    // SIGUIENTE MAYOR
    BB6.insertar(8); BB6.insertar(3); BB6.insertar(10); BB6.insertar(1); BB6.insertar(6);
    BB6.insertar(14); BB6.insertar(4); BB6.insertar(7); BB6.insertar(13);
    try {
        cout << "Siguiente mayor en BB6 de 5: " << siguienteMayor(BB6, 5) << endl;
        cout << "Siguiente mayor en BB6 de 8: " << siguienteMayor(BB6, 8) << endl;
        cout << "Siguiente mayor en BB6 de 13: " << siguienteMayor(BB6, 13) << endl;
        cout << "Siguiente mayor en BB6 de 14: " << siguienteMayor(BB6, 14) << endl;
    }catch(const NoHaySiguienteMayor& e) {
        cout << e.Mensaje() << endl << endl;
    }


    // POSICION INORDEN //
    BB7.insertar(5); BB7.insertar(1); BB7.insertar(3); BB7.insertar(8); BB7.insertar(6);
    cout << "Posicion Inorden en BB7 de 3: ";
    cout << posicionInorden(BB7, 3);
    cout << endl << "Posicion Inorden en BB7 de 8: ";
    cout << posicionInorden(BB7, 8);
    cout << endl << "Posicion Inorden en BB7 de 7: ";
    cout << posicionInorden(BB7, 7);
    cout << endl << endl;


    // SUMA CAMINO
    cout << "Hay un camino de suma 26 en F?:";
    cout << (haySumaCamino(F, 26) ? " SI" : " NO") << endl;
    cout << "Hay un camino de suma 9 en F?:";
    cout << (haySumaCamino(F, 9) ? " SI" : " NO") << endl;


    system("PAUSE");
    return 0;
}
