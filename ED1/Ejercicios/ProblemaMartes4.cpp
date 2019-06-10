#include <iostream>

using namespace std;

struct Tnodo{
    int dato;
    Tnodo *siguiente;
};

class examen{
    Tnodo *L;
    Tnodo *Pares;
    Tnodo *Impares;
public:
    examen();
    ~examen();
    void concatenar(int n);
    void mostrar();
    void dividir();
};

examen::examen(){
    L = Pares = Impares =  NULL;
}
examen::~examen(){
    Tnodo *nTMP;

    if(L != NULL){
        while(L != NULL){
            nTMP = L;
            L = L->siguiente;
            delete nTMP;
        }
    }
    if(Pares != NULL){
        while(Pares != NULL){
            nTMP = Pares;
            Pares = Pares->siguiente;
            delete nTMP;
        }
    }
    if(Impares != NULL){
        while(Impares != NULL){
            nTMP = Impares;
            Impares = Impares->siguiente;
            delete nTMP;
        }
    }
}

void examen::mostrar(){
    Tnodo *nTMP = L;

    if(nTMP == NULL)
        cout << "La lista esta vacia.\n";
    else{
        cout << "Lista principal:  ";
        while(nTMP != NULL){
            cout << nTMP->dato << " -> ";
            nTMP = nTMP->siguiente;
        }
        cout << "/\n";
    }

    nTMP = Pares;
    if(nTMP == NULL)
        cout << "La lista de pares esta vacia.\n";
    else{
        cout << "Lista de pares:  ";
        while(nTMP != NULL){
            cout << nTMP->dato << " -> ";
            nTMP = nTMP->siguiente;
        }
        cout << "/\n";
    }

    nTMP = Impares;
    if(nTMP == NULL)
        cout << "La lista de impares esta vacia.\n";
    else{
        cout << "Lista de impares:  ";
        while(nTMP != NULL){
            cout << nTMP->dato << " -> ";
            nTMP = nTMP->siguiente;
        }
        cout << "/\n";
    }
}
void examen::concatenar(int n){
    Tnodo *nNEW = new Tnodo;
    nNEW->dato = n;
    nNEW->siguiente = NULL;

    if(L == NULL)
        L = nNEW;
    else{
        Tnodo *nTMP = L;
        while(nTMP->siguiente != NULL) nTMP = nTMP->siguiente;
        nTMP->siguiente = nNEW;
    }
}

void examen::dividir(){
    //Se suponen las listas Impares y Pares vacias.

    Tnodo *nANT = NULL;
    Tnodo *nTMP = L;
    Tnodo *nPar = NULL;
    Tnodo *nImp = NULL;


    while(nTMP != NULL){
        if(nTMP->dato%2 == 0){
            if(nPar == NULL)
                Pares = nTMP;
            else
                nPar->siguiente = nTMP;

            nPar = nTMP;
            if(nTMP == L)
                L = L->siguiente;
            else if(nANT != NULL)
                nANT->siguiente = nTMP->siguiente;
        }else
            nANT = nTMP;

        nTMP = nTMP->siguiente;
        if(nPar != NULL)
            nPar->siguiente = NULL;
    }


    while(L != NULL){
        nTMP = L;
        nANT = NULL;
        while(nTMP->siguiente != NULL){
            nANT = nTMP;
            nTMP = nTMP->siguiente;
        }
        if(nImp == NULL)
            Impares = nTMP;
        else
            nImp->siguiente = nTMP;
        nImp = nTMP;
        nTMP->siguiente = NULL;
        if(nANT == NULL)
            L = NULL;
        else
            nANT->siguiente = NULL;
    }
}

int main(){
    examen e;
    int n, dani;
    cout << "Numero de datos a introducir: ";
    cin >> n;
    for(int i = 0; i < n; i++){
        cout << "Dato [" << (i+1) << "]: ";
        cin >> dani;
        e.concatenar(dani);
    }
    cout << "\n";

    e.mostrar();

    cout << "\nVoy a dividir.\n\n";
    e.dividir();

    e.mostrar();

    return 0;
}
