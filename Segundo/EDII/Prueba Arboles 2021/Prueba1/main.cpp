#include <iostream>
#include <cstdlib>
#include "arbin.h"

using namespace std;


//Ejercicio 1

template <typename T>
bool cargaPar(const Arbin<T>& a) {
    return cargaPar(a, a.getRaiz());
};

template <typename T>
bool cargaPar(const Arbin<T>& a, const typename Arbin<T>::Iterador& r){
    if(r.arbolVacio() || (a.subIzq(r).arbolVacio() && a.subDer(r).arbolVacio())){
        return true;
    }else{
        if(!(cargaPar(a, a.subIzq(r)) && cargaPar(a,  a.subDer(r)))){
            return false;
        }else{
            int diff = nDescendientes(a, a.subIzq(r)) - nDescendientes(a,  a.subDer(r));
            if(diff == 0 || diff == -2 || diff == 2){
                return true;
            }else{
                return false;
            }
        }
    }
}


template <typename T>
int nDescendientes(const Arbin<T>& a, const typename Arbin<T>::Iterador& r){
    if(r.arbolVacio()){
        return 0;
    }else{
        return nDescendientes(a, a.subIzq(r))+1+nDescendientes(a, a.subDer(r));
    }
}


//Ejercicio 2

void hojasCota(const Arbin<int>& a, const typename Arbin<int>::Iterador& r, int cota, int peso){
    if(!r.arbolVacio()){
        peso = peso + r.observar();
        if(a.subIzq(r).arbolVacio() && a.subDer(r).arbolVacio()){
            if(cota > peso){
                cout << "hoja (" << r.observar() << ") -> pesoHoja: " <<  peso << endl;
            }
        }else{
            hojasCota(a, a.subIzq(r), cota, peso);
            hojasCota(a,  a.subDer(r), cota, peso);
        }
    }
}

void hojasCota (const Arbin<int>& a, int cota) {
    hojasCota(a, a.getRaiz(), cota, 0);
};



/* ******************************************************************* */
/* ******************************************************************* */
int main() {
    Arbin<char> a('m', Arbin<char>('q', Arbin<char>('s', Arbin<char>(), Arbin<char>()),
                                        Arbin<char>('t', Arbin<char>(), Arbin<char>())),
                       Arbin<char>('d', Arbin<char>('k', Arbin<char>(), Arbin<char>()),
                                        Arbin<char>()));

    Arbin<char> b('m', Arbin<char>('q', Arbin<char>('s', Arbin<char>(), Arbin<char>()),
                                        Arbin<char>('t', Arbin<char>(), Arbin<char>())),
                       Arbin<char>('d', Arbin<char>('k', Arbin<char>(), Arbin<char>()),
                                        Arbin<char>('x', Arbin<char>(), Arbin<char>())));


    Arbin<int> d (3, Arbin<int>(8, Arbin<int>(9, Arbin<int>(), Arbin<int>()),
                                 Arbin<int>(7, Arbin<int>(), Arbin<int>())),
                     Arbin<int>(5, Arbin<int>(4, Arbin<int>(), Arbin<int>()),
                                 Arbin<int>()));

   cout << "************************************"; cout << endl;
   cout << "PRUEBA-I (GRUPO L1)"; cout << endl;
   cout << "************************************"; cout << endl;

// Ejercicio 1
/*
   cout << "cargaPar el arbol A?:";
    if(cargaPar(a)) cout<<"SI";
      else cout << "NO";
   cout << endl;

   cout << "cargaPar el arbol B?:";
    if(cargaPar(b)) cout<<"SI";
      else cout << "NO";

   cout << endl<<endl;
*/

// Ejercicio 2
   cout << "Hojas Cota 19 en Arbol D: "<<endl;
   hojasCota(d,19);
   cout << endl<<endl;


    system("PAUSE");
    return 1;
}

