#include "listaEnlazada.h"
#include <iostream>
lista::lista(){
    elementos = NULL;
    n = 0;
}
lista::~lista(){
    peluqueroNodo *borrar;

    while(elementos != NULL){
        borrar = elementos;
        elementos = elementos->siguiente;
        delete borrar;
    }
}
lista::lista(peluquero &e){}
peluqueroNodo* lista::buscarNodo(int i){
    peluqueroNodo *anterior = elementos;

    while(anterior != NULL && --i >= 0)
        anterior = anterior->siguiente;

    return anterior;
}
void lista::vaciar(){
    peluqueroNodo *borrar;

    while(elementos != NULL){
        borrar = elementos;
        elementos = elementos->siguiente;
        delete borrar;
    }

    n = 0;
}
bool lista::esvacia(){
    return n == 0;
}
int lista::longitud(){
    return n;
}
bool lista::pertenece(peluquero &e){
    return posicion(e)!=-1;
}
void lista::insertar(int i, peluquero &e){
    peluqueroNodo *nuevoNodo = new peluqueroNodo;
    copiarpeluquero(nuevoNodo->dato, e);

    if(i == 1){
        nuevoNodo->siguiente = elementos;
        elementos = nuevoNodo;
    }else{
        peluqueroNodo *anterior = buscarNodo(i-2);
        nuevoNodo->siguiente = anterior->siguiente;
        anterior->siguiente = nuevoNodo;
    }

    n++;
}
void lista::eliminar(int i){
    peluqueroNodo *borrar;

    if(i == 1){
        borrar = elementos;
        elementos = elementos->siguiente;
        delete borrar;
    }else{
        peluqueroNodo *anterior = buscarNodo(i-2);
        borrar = anterior->siguiente;
        anterior->siguiente = borrar->siguiente;
        delete borrar;
    }

    n--;
}
void lista::modificar(int i, peluquero &e){
    copiarpeluquero(buscarNodo(i-1)->dato, e);
}
peluquero& lista::observar(int i){
    return buscarNodo(i-1)->dato;
}
int lista::posicion(peluquero &e){
    peluqueroNodo *tmp = elementos;
    int i = 0;

    while(!compararpeluquero(e, tmp->dato))
        tmp = tmp->siguiente;

    return (i==n?-1:i+1);
}

void copiarpeluquero(peluquero &destino,peluquero &origen){
    destino.Codigo = origen.Codigo;
    destino.TipoServicio = origen.TipoServicio;
    strcpy(destino.Nombre, origen.Nombre);
    strcpy(destino.Apellidos, origen.Apellidos);
    destino.Col.clonar(origen.Col);
}

bool compararpeluquero(peluquero &p1,peluquero &p2){
    return  p1.TipoServicio == p2.TipoServicio &&
            p1.Codigo == p2.Codigo &&
            strcmp(p1.Nombre, p2.Nombre) == 0 &&
            strcmp(p1.Apellidos, p2.Apellidos) == 0;
}
