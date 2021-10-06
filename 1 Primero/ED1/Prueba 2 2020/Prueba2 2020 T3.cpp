#include <iostream>

using namespace std;

struct TNodoD{
    float Temperatura;
    float Humedad;
    TNodoD *Sig;
};

struct TNodoT{
    float Temp;
    TNodoT *Sig;
};

struct TNodoH{
    float Hume;
    TNodoH *Sig;
};

void mostrar(TNodoD *nO){
    if(nO == NULL) return;
    TNodoD *n = nO;
    do{
        cout << n->Temperatura << "/" << n->Humedad << "  ";
        n = n->Sig;
    }while(n != nO);
    cout << "\n";
}
void mostrar(TNodoT *n){
    while(n != NULL){
        cout << n->Temp << "  ";
        n = n->Sig;
    }
    cout << "\n";
}
void mostrar(TNodoH *n){
    while(n != NULL){
        cout << n->Hume << "  ";
        n = n->Sig;
    }
    cout << "\n";
}

class GestionDatos{
    TNodoD *ListaDatos;
public:
    GestionDatos(){
        ListaDatos = NULL;
    }
    void Cargar(TNodoT **LTemp, TNodoH **LHume){
        TNodoT *nT = *LTemp, *nTbor;
        TNodoH *nH = *LHume, *nHbor;
        TNodoD *nD = ListaDatos, *nDnew;

        while(nT != NULL && nH != NULL){
            //Creo el nuevo nodo
            nDnew = new TNodoD;
            nDnew->Temperatura = nT->Temp;
            nDnew->Humedad = nH->Hume;

            //Lo enlazo
            if(nD == NULL){
                nDnew->Sig = nDnew;
                ListaDatos = nDnew;
            }else{
                nDnew->Sig = nD->Sig;
                nD->Sig = nDnew;
            }
            nD = nDnew;

            //Avanzo los punteros, borrando de las listas originales
            nTbor = nT;
            nHbor = nH;
            nT = nT->Sig;
            nH = nH->Sig;
            (*LTemp) = nT;
            (*LHume) = nH;
            delete nTbor;
            delete nHbor;
        }
    }
    void DescartarDatos(float TMin, float TMax, float HMin, float HMax){
        //Solo para ver los resultados
        mostrar(ListaDatos);

        float mediaT = 0, mediaH = 0;
        int nElementos = 0;
        TNodoD *nD = ListaDatos, *nDbor;

        do{
            mediaT += nD->Temperatura;
            mediaH += nD->Humedad;
            nElementos++;
            nD = nD->Sig;
        }while(nD != ListaDatos);
        mediaT /= (float)nElementos;
        mediaH /= (float)nElementos;

        //Solo para ver cuales son las medias
        cout << "MediaT " << mediaT << " MediaH " << mediaH << "\n";

        if(!(mediaT > TMin && mediaT < TMax) || !(mediaH > HMin && mediaH < HMax)){
            //Borrar la lista entera
            nD = ListaDatos;
            do{
                nDbor = nD;
                nD = nD->Sig;
                delete nDbor;
            }while(nD != ListaDatos);
            ListaDatos = NULL;
        }

        //Solo para ver los resultados
        mostrar(ListaDatos);
    }
};

int main(){
    TNodoT *Temperaturas = new TNodoT;
    Temperaturas->Temp = 10.5;
    Temperaturas->Sig = new TNodoT;
    Temperaturas->Sig->Temp = 11.2;
    Temperaturas->Sig->Sig = new TNodoT;
    Temperaturas->Sig->Sig->Temp = 9.87;
    Temperaturas->Sig->Sig->Sig = new TNodoT;
    Temperaturas->Sig->Sig->Sig->Temp = -9.1;
    Temperaturas->Sig->Sig->Sig->Sig = -NULL;

    TNodoH *Humedades = new TNodoH;
    Humedades->Hume = 35.1;
    Humedades->Sig = new TNodoH;
    Humedades->Sig->Hume = 66.8;
    Humedades->Sig->Sig = new TNodoH;
    Humedades->Sig->Sig->Hume = 72.1;
    Humedades->Sig->Sig->Sig = NULL;

    mostrar(Temperaturas);
    mostrar(Humedades);

    GestionDatos G;
    G.Cargar(&Temperaturas, &Humedades);

    //Solo para ver los resultados
    mostrar(Temperaturas);

    //Solo para ver los resultados
    mostrar(Humedades);

    G.DescartarDatos(9, 11, 40, 50);
}
