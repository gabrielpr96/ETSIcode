#include "listaDinamica.h"

lista::lista(){
    peluquero *elementos = new peluquero[INCREMENTO];
    if(elementos == NULL){
        Tama = n = -1;
    }else{
        Tama = INCREMENTO;
        n = 0;
    }
}
lista::~lista(){
    if(elementos != NULL)
        delete [] elementos;
    elementos = NULL;
    Tama = n = 0;
}
lista::lista(peluquero &e){}
bool lista::esvacia(){
    return n == 0;
}
int lista::longitud(){
    return n;
}
bool lista::pertenece(peluquero &e){
    return posicion(e)!=1;
}
void lista::insertar(int i, peluquero &e){
    if(n == Tama){//Agrandar la tabla
       peluquero *elementosTMP = new peluquero[Tama+INCREMENTO];
        if(elementosTMP != NULL){
            for(int i = 0; i < n; i++)
                copiarpeluquero(elementosTMP[i], elementos[i]);
            Tama += INCREMENTO;
            delete [] elementos;
            elementos = elementosTMP;
        }
    }

    if(n < Tama){//Agregar el elemento, desplazando los demas
        for(int pos = n-1; pos >= i-1; pos--)
            copiarpeluquero(elementos[pos+1], elementos[pos]);
        copiarpeluquero(elementos[i-1], e);
        n++;
    }
}
void lista::eliminar(int i){
    while(i < n){
        copiarpeluquero(elementos[i-1], elementos[i]);
        i++;
    }
    n--;
    if(Tama-n >= INCREMENTO && Tama > INCREMENTO){
        peluquero *elementosTMP = new peluquero[Tama-INCREMENTO];
        if(elementosTMP != NULL){
            for(int pos = 0; pos < Tama; pos++)
                copiarpeluquero(elementosTMP[pos], elementos[pos]);
            Tama -= INCREMENTO;
            delete [] elementos;
            elementos = elementosTMP;
        }
    }
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
            strcmp(p1.Apellidos, p2.Apellidos) == 0 &&
            p1.Col.comparar(p2.Col);
}
