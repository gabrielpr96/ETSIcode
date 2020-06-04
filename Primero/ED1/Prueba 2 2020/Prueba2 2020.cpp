#include <iostream>
#include <cstdlib>

using namespace std;

struct TNodoy {
    char Datos;
    TNodoy *Sig;
};

struct TNodox {
    TNodoy *PNodoy;
    TNodox *Sig;
};


struct TNodoz {
    char *Datos;
    int n;
    TNodoz *Sig;
};

void mostrarLista(TNodox *nx){
    TNodoy *ny;

    while(nx != NULL){
        ny = nx->PNodoy;
        while(ny != NULL){
            cout << ny->Datos << " ";
            ny = ny->Sig;
        }
        cout << "- ";
        nx = nx->Sig;
    }
    cout << endl;
}
void mostrarLista(TNodoz *nz){
    while(nz != NULL){
        for(int i = 0; i < nz->n; i++)
            cout << nz->Datos[i] << " ";
        cout << "- ";
        nz = nz->Sig;
    }
    cout << endl;
}


TNodoz* crearLista2(TNodox *nx){
    if(nx == NULL) return NULL;
    TNodoz *nz = new TNodoz;
    TNodoz *lista = nz;
    TNodoy *ny;

    while(nx != NULL){
        nz->n = 0;

        ny = nx->PNodoy;
        while(ny != NULL){
            nz->n++;
            ny = ny->Sig;
        }

        int i = 0;
        nz->Datos = new char[nz->n];
        ny = nx->PNodoy;
        while(ny != NULL){
            nz->Datos[i++] = ny->Datos;
            ny = ny->Sig;
        }

        nx = nx->Sig;
        if(nx != NULL){
            nz->Sig = new TNodoz;
            nz = nz->Sig;
        }else nz->Sig = NULL;
    }

    return lista;
}

void eliminarLista(TNodox *nx){
    TNodox *bx;
    TNodoy *ny, *by;

    while(nx != NULL){
        ny = nx->PNodoy;
        while(ny != NULL){
            by = ny;
            ny = ny->Sig;
            delete by;
        }
        bx = nx;
        nx = nx->Sig;
        delete bx;
    }
}
void eliminarLista(TNodoz *nz){
    TNodoz *bz;

    while(nz != NULL){
        delete [] nz->Datos;
        bz = nz;
        nz = nz->Sig;
        delete bz;
    }
}

int main(){
    TNodox *lista1 = new TNodox;
    lista1->PNodoy = new TNodoy;
    lista1->PNodoy->Datos = 'B';
    lista1->PNodoy->Sig = new TNodoy;
    lista1->PNodoy->Sig->Datos = 'F';
    lista1->PNodoy->Sig->Sig = NULL;
    lista1->Sig = new TNodox;
    lista1->Sig->PNodoy = NULL;
    lista1->Sig->Sig = new TNodox;
    lista1->Sig->Sig->PNodoy = new TNodoy;
    lista1->Sig->Sig->PNodoy->Datos = 'H';
    lista1->Sig->Sig->PNodoy->Sig = NULL;
    lista1->Sig->Sig->Sig = new TNodox;
    lista1->Sig->Sig->Sig->PNodoy = NULL;
    lista1->Sig->Sig->Sig->Sig = NULL;

    mostrarLista(lista1);

    TNodoz *lista2 = crearLista2(lista1);
    mostrarLista(lista2);

    eliminarLista(lista1);
    eliminarLista(lista2);

    return 0;
}
