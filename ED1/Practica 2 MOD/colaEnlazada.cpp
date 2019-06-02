#include <cstdlib>
#include <cstring>
#include "colaEnlazada.h"

#include <iostream>

cola::cola(){
    inicio = NULL;
    fin = NULL;
    ne = 0;
}
cola::~cola(){
    clienteNodo *borrar;

    while(inicio != NULL){
        borrar = inicio;
        inicio = inicio->siguiente;
        delete borrar;
    }
}
void cola::encolar(cliente e){
    clienteNodo *nuevoNodo = new clienteNodo;
    nuevoNodo->dato = e;
    nuevoNodo->siguiente = NULL;

    if(fin != NULL){
        fin->siguiente = nuevoNodo;
    }
    fin = nuevoNodo;
    if(inicio == NULL)
        inicio = nuevoNodo;

    ne++;
}
void cola::desencolar(){
    if(inicio != NULL){
        clienteNodo *borrar = inicio;
        inicio = inicio->siguiente;
        if(inicio == NULL)
            fin = NULL;

        delete borrar;
        ne--;
    }
}
bool cola::esvacia(){
    return ne==0;
}
cliente cola::primero(){
    return inicio->dato;
}
int cola::longitud(){
    return ne;
}
void cola::vaciar(){
    clienteNodo *borrar;

    while(inicio != NULL){
        borrar = inicio;
        inicio = inicio->siguiente;
        delete borrar;
    }

    fin = NULL;
    ne = 0;
}
void cola::clonar(cola &c){
    vaciar();
    //std::cout << "Me han pasado " << (&c) << "\nEl siguiente es mi hijo: \n";
    cola cTMP; //Para no cargarme la original
    while(!c.esvacia()){
        encolar(c.primero());
        cTMP.encolar(c.primero());
        c.desencolar();
    }
    //std::cout << "Tamano TMP: " << cTMP.longitud() << "\n";
    while(!cTMP.esvacia()){
        //std::cout << "He mandodado a encolar:\n";
        c.encolar(cTMP.primero());
        cTMP.desencolar();
    }
}
