#include <cstdlib>
#include <cstring>
#include "colaDinamica.h"

#include <iostream>

cola::cola(){
    elementos = new cliente[INCREMENTO];
    if(elementos == NULL){
        ne = fin = inicio = -1;
        Tama = -1;
    }else{
        ne = fin = inicio = 0;
        Tama = INCREMENTO;
    }
    //std::cout << "\n" << this << " al nacer " << elementos << "\n";
}
cola::~cola(){
    //std::cout << "\n" << this << " He sido destruido " << elementos << "\n";
    if(elementos != NULL)
        delete [] elementos;
    elementos = NULL;
    ne = fin = inicio = -1;
    Tama = 0;
}
void cola::encolar(cliente e){
    //std::cout << "\nSoy: " << this << " Encolo\n";
    if(ne == Tama){
            //std::cout << "Redimensiono: " << (Tama+INCREMENTO) << "\n";
        cliente *elementosTMP = new cliente[Tama+INCREMENTO];
        if(elementosTMP != NULL){
            for(int i = 0; i < ne; i++){
                elementosTMP[i] = elementos[inicio];
                inicio = (inicio+1)%Tama;
            }
            inicio = 0;
            fin = ne;
            Tama += INCREMENTO;
            if(elementos != NULL)
                delete [] elementos;
            elementos = elementosTMP;
        }
    }
    if(ne < Tama){
        //std::cout << "He escrito en fin = " << fin << "\n";
        fin -= fin/Tama; //!Absolutamente necesario, aunque parezca lo contrario
        elementos[fin] = e;
        fin = (fin+1)%Tama;
        ne++;
    }
//std::cout << "Ahora fin: " << fin << " ne: " << ne << " Tama: " << Tama <<  "  inicio: " << inicio << "\n";
}
void cola::desencolar(){
    if(ne == 0) return;

    //std::cout << "\nSoy: " << this << " DesEncolo\n";
    inicio = (inicio+1)%Tama;
    ne--;

    if(Tama-ne == INCREMENTO && Tama > INCREMENTO){
        //std::cout << "Redimensiono: " << (Tama-INCREMENTO) << "\n";
        cliente *elementosTMP = new cliente[Tama-INCREMENTO];
        if(elementosTMP != NULL){
            for(int i = 0; i < ne; i++){
                elementosTMP[i] = elementos[inicio];
                inicio = (inicio+1)%Tama;
            }
            Tama -= INCREMENTO;
            inicio = 0;
            fin = ne;
            if(elementos != NULL)
                delete [] elementos;
            elementos = elementosTMP;
        }
    }
//std::cout << "Ahora fin: " << fin << " ne: " << ne << " Tama: " << Tama <<  "  inicio: " << inicio << "\n";
}
bool cola::esvacia(){
    return ne==0;
}
cliente cola::primero(){
    return elementos[inicio];
}
int cola::longitud(){
    return ne;
}
void cola::vaciar(){
    if(elementos != NULL)
        delete [] elementos;
    elementos = new cliente[INCREMENTO];
    ne = fin = inicio = 0;
    Tama = INCREMENTO;
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
bool cola::comparar(cola &c){   //! DEPRECATED
    if(c.longitud() != longitud()) return false;

    bool igual = true;
    cola tmp;

    //Copiar a TMP y comprobar
    while(!c.esvacia()){
        tmp.encolar(primero());
        if(igual)
            igual = compararClientes(primero(), c.primero());

        c.desencolar();
        desencolar();
    }

    //Restaurar la cola
    while(!tmp.esvacia()){
        encolar(tmp.primero());
        tmp.desencolar();
    }

    return igual;
}

bool cola::compararClientes(cliente c1, cliente c2){
    return strcmp(c1.Nombre, c2.Nombre) == 0 &&
            strcmp(c1.Apellidos, c2.Apellidos) == 0 &&
            c1.Edad == c2.Edad &&
            c1.HoraLlegada  == c2.HoraLlegada &&
            c1.TipoServicio == c2.TipoServicio;
}
