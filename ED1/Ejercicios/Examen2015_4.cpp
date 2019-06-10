#include <iostream>
#include <fstream>
#include <cstring>
#include <cstdio>

using namespace std;


struct NodoLetra {
    char letra;
    NodoLetra *sig;
};
struct NodoBase {
    NodoLetra *col;
    NodoBase *sig;
};

class Letrix {
        NodoBase *inicio;
    public:
        Letrix();
        ~Letrix();
        void insertarCompleto(int col, char let);
        void insertar(int col, char let);
        void mostrar();
};

Letrix::Letrix(){
    inicio = NULL;
}
Letrix::~Letrix(){
    ///Poner aqui el codigo

    NodoBase *bTMP = inicio, *bBOR;
    NodoLetra *lTMP, *lBOR;

    while(bTMP != NULL){
        lTMP = bTMP->col;
        while(lTMP != NULL){
            lBOR = lTMP;
            lTMP = lTMP->sig;
            delete lBOR;
        }
        bBOR = bTMP;
        bTMP = bTMP->sig;
        delete bBOR;
    }

    inicio = NULL;

    ///Poner aqui el codigo
}
void Letrix::insertarCompleto(int col, char let){
    //Esto es un ejemplo de codigo espagueti, no escribais espagueti.

    int i = 0;
    NodoBase *bTMP = NULL;
    do{
        if(bTMP == NULL)
            bTMP = inicio;
        else
            bTMP = bTMP->sig;
        if(bTMP != NULL)
            i++;
    }while(bTMP != NULL && i < col);
    if(inicio == NULL){
        bTMP = new NodoBase;
        bTMP->col = NULL;
        bTMP->sig = NULL;
        inicio = bTMP;
        i++;
    }else{
        bTMP = inicio;
        while(bTMP->sig != NULL)
            bTMP = bTMP->sig;
    }
    for(;i < col; i++){
        bTMP->sig = new NodoBase;
        bTMP = bTMP->sig;
        bTMP->col = NULL;
        bTMP->sig = NULL;
    }

    NodoLetra *lTMP = bTMP->col;
    NodoLetra *lANT = NULL;

    while(lTMP != NULL && lTMP->letra < let){
        lANT = lTMP;
        lTMP = lTMP->sig;
    }

    NodoLetra *lNEW = new NodoLetra;
    lNEW->letra = let;
    lNEW->sig = lTMP;
    if(lANT == NULL)
        bTMP->col = lNEW;
    else
        lANT->sig = lNEW;
}
void Letrix::mostrar(){
    NodoBase *bTMP = inicio;
    NodoLetra *lTMP;

    while(bTMP != NULL){
        lTMP = bTMP->col;
        while(lTMP != NULL){
            cout << lTMP->letra << "  ";
            lTMP = lTMP->sig;
        }
        bTMP = bTMP->sig;
        cout << "\n\n";
    }
}

void Letrix::insertar(int col, char let){
    ///Poner aqui el codigo

    NodoBase *bTMP = inicio;
    while(col > 1){
        bTMP = bTMP->sig;
        col--;
    }

    NodoLetra *lTMP = bTMP->col;
    NodoLetra *lANT = NULL;

    while(lTMP != NULL && lTMP->letra < let){
        lANT = lTMP;
        lTMP = lTMP->sig;
    }

    NodoLetra *lNEW = new NodoLetra;
    lNEW->letra = let;
    lNEW->sig = lTMP;
    if(lANT == NULL)
        bTMP->col = lNEW;
    else
        lANT->sig = lNEW;

    ///Poner aqui el codigo
}

char menu(){
    system("CLS");
    cout << "\t\tLeTriX\n\n"
        << "\t1. Agregar una letra.\n"
        << "\t2. Mostrar.\n"
        << "\t0. Salir\n\n"
        << "\tOpcion: ";
    return cin.get();
}

int main(){
    Letrix letrix;

    //insertarcompleto crea los NodoBase, cosa que no se pide en el insertar solicitado

    letrix.insertarCompleto(1, 'F');
    letrix.insertarCompleto(1, 'U');
    letrix.insertarCompleto(1, 'B');
    letrix.insertarCompleto(1, 'A');
    letrix.insertarCompleto(1, 'R');

    letrix.insertarCompleto(3, 'R');
    letrix.insertarCompleto(3, 'A');
    letrix.insertarCompleto(3, 'R');

    letrix.insertarCompleto(5, 'H');

    char opt;
    do{
        opt = menu();
        system("CLS");
        switch(opt){
            case '1':
                cout << "\tAgregar una letra.\n\n";
                char let;
                int col;
                cout << "Columna [1-5]: ";
                cin >> col;
                cout << "Letra: ";
                cin >> let;
                letrix.insertar(col, let);
            break;
            case '2':
                cout << "\tLetras almacenadas, las columnas realmente son filas.\n\n";
                letrix.mostrar();
                cin.ignore();
                cin.ignore();
            break;
        }
    }while(opt != '0');

    return 0;
}
