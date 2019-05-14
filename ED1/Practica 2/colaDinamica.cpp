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
    std::cout << "Soy la cola " << this << " y me han dado " << elementos << "\n";
}
cola::~cola(){
    if(elementos != NULL)
        delete [] elementos;
    elementos = NULL;
    ne = fin = inicio = -1;
    Tama = 0;
}
void cola::encolar(cliente e){
    if(ne == Tama){
        cliente *elementosTMP = new cliente[Tama+INCREMENTO];
        if(elementosTMP != NULL){
            for(int i = 0; i < ne; i++){
                //Emoueza a copiar ya desfragmentado
                elementosTMP[i] = elementos[inicio];
                inicio = (inicio+1)%Tama;
            }
            inicio = 0;
            fin = ne;
            Tama += INCREMENTO;
            delete [] elementos;
            elementos = elementosTMP;

        std::cout << "Soy la cola " << this << " y mi nuevo elementos es " << elementos << "\n";
        }
    }
    if(ne < Tama){
        elementos[fin] = e;
        fin = (fin+1)%Tama;
        ne++;
    }
}
void cola::desencolar(){
    inicio = (inicio+1)%Tama;
    ne--;

    if(Tama-ne == INCREMENTO && Tama > INCREMENTO){
        std::cout << "\nTama: " << Tama << "  ne: " << ne << "\n";
        std::cout << "Soy la cola " << this << " y mi elementos es " << elementos << "\n";
        cliente *elementosTMP = new cliente[Tama-INCREMENTO];
        if(elementosTMP != NULL){
            for(int i = 0; i < ne; i++){
                std::cout << "Copio el " << inicio << " en el nuevo " << i << "\n";
                elementosTMP[i] = elementos[inicio];
                inicio = (inicio+1)%Tama;
            }
            Tama -= INCREMENTO;
            inicio = 0;
            fin = Tama;
            std::cout << "Voy a liberar " << elementos << " me quedo con " << elementosTMP << "\n";
            delete [] elementos;
            std::cout << "He liberado\n";
            elementos = elementosTMP;
        }
    }
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
    cola cTMP; //Para no cargarme la original
    while(!c.esvacia()){
        encolar(c.primero());
        cTMP.encolar(c.primero());
        c.desencolar();
    }
    while(!cTMP.esvacia()){
        c.encolar(cTMP.primero());
        std::cout << "Encolar.\n";
        cTMP.desencolar();
        std::cout << "DesEncolar.\n";
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
