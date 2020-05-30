#include "listaC.h"
#include <iostream>
listaC::listaC(){
    elementos = NULL;
    n = 0;
}
listaC::~listaC(){
    vaciar();
}
listaC::listaC(Paciente &e){
    elementos = new TNodo_ListaC;
    copiarPaciente(elementos->Datos, e);
    elementos->Sig = NULL;
    n = 1;
}
TNodo_ListaC* listaC::buscarNodo(int i){
    if(n == 0) return NULL;
    TNodo_ListaC *tmp = elementos;

    while(tmp != NULL && --i >= 0)
        tmp = tmp->Sig;

    return tmp;
}
void listaC::vaciar(){
    TNodo_ListaC *tmp;

    while(elementos != NULL){
        tmp = elementos;
        elementos = elementos->Sig;
        delete tmp;
    }

    n = 0;
}
bool listaC::esvacia(){
    return n == 0;
}
int listaC::longitud(){
    return n;
}
bool listaC::pertenece(Paciente &e){
    return posicion(e) != -1;
}
void listaC::insertar(int i, Paciente &e){
    TNodo_ListaC *nuevoNodo = new TNodo_ListaC;
    copiarPaciente(nuevoNodo->Datos, e);

    if(i == 1){
        nuevoNodo->Sig = elementos;
        elementos = nuevoNodo;
    }else{
        TNodo_ListaC *anterior = buscarNodo(i-2);
        nuevoNodo->Sig = anterior->Sig;
        anterior->Sig = nuevoNodo;
    }

    n++;
}
void listaC::eliminar(int i){
    TNodo_ListaC *tmp;

    if(i == 1){
        tmp = elementos;
        elementos = elementos->Sig;
    }else{
        TNodo_ListaC *anterior = buscarNodo(i-2);
        tmp = anterior->Sig;
        anterior->Sig = tmp->Sig;
    }
    delete tmp;

    n--;
}
void listaC::modificar(int i, Paciente &e){
    copiarPaciente(buscarNodo(i-1)->Datos, e);
}
Paciente& listaC::observar(int i){
    return buscarNodo(i-1)->Datos;
}
int listaC::posicion(Paciente &e){
    TNodo_ListaC *tmp = elementos;
    int i = 0;

    while(!compararPaciente(e, tmp->Datos))
        tmp = tmp->Sig;

    return (i==n?-1:i+1);
}
