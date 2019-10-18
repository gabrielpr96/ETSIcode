#include <cstdlib>
#include <cstring>
#include "colaEstatica.h"

#include <iostream>

cola::cola(){
    ne = 0;
}
cola::~cola(){

}
void cola::encolar(cliente e){
    if(ne+1 < MAX){
        elementos[ne] = e;
        ne++;
    }
}
void cola::desencolar(){
    for(int i = 1; i < ne; i++)
        elementos[i-1] = elementos[i];
    ne--;
}
bool cola::esvacia(){
    return ne==0;
}
cliente cola::primero(){
    return elementos[0];
}
int cola::longitud(){
    return ne;
}
void cola::vaciar(){
    ne = 0;
}
void cola::clonar(cola &c){
    vaciar();

    cola cTMP; //Para no cargarme la original
    while(!c.esvacia()){
        encolar(c.primero());
        cTMP.encolar(c.primero());
        c.desencolar();
    }

    while(!cTMP.esvacia()){
        c.encolar(cTMP.primero());
        cTMP.desencolar();
    }
}
