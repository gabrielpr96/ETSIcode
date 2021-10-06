#include "listaEstatica.h"
#include <iostream>
lista::lista(){
    n = 0;
}
lista::~lista(){
}
lista::lista(peluquero &e){
    elementos[0] = e;
    n = 1;
}
void lista::vaciar(){
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
    if(n+1 < MAX){//Agregar el elemento, desplazando los demas
        for(int pos = n; pos >= i; pos++)
            copiarpeluquero(elementos[pos], elementos[pos-1]);
        elementos[i-1] = e;
        n++;
    }
}
void lista::eliminar(int i){
    for(int pos = i; pos < n; pos++)
            copiarpeluquero(elementos[pos-1], elementos[pos]);
    n--;
}
void lista::modificar(int i, peluquero &e){
    copiarpeluquero(elementos[i-1], e);
}
peluquero& lista::observar(int i){
    return elementos[i-1];
}
int lista::posicion(peluquero &e){
    int i = 0;

    while(!compararpeluquero(e, elementos[i++]));

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
            //p1.Col.comparar(p2.Col); //Las colas no se comparan
}
