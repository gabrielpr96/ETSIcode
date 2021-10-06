#include <iostream>

#define METODO_ORIGINAL
//#define METODO_A
//#define METODO_B
//#define METODO_C
//#define METODO_D

using namespace std;

struct TNodo_Lista{
    char Datos;
    TNodo_Lista *Siguiente;
    TNodo_Lista *Anterior;
};

class lista{
    TNodo_Lista *Lista; // elementos de la lista
    int n;// nº de elementos que tiene la lista
    TNodo_Lista* Anterior(int i); //Busca el nodo anteior a uno dado. (indice 1)
public:
    lista(); // constructor de la clase
    ~lista();// destructor de la clase
    bool esvacia();
    int longitud();
    bool pertenece(char e);
    void insertar(int i, char e);
    void eliminar(int i);
    void modificar(int i, char e);
    char observar(int i);
    int posicion(char e);

    void mostrar();
};


lista::lista(){
    Lista = NULL;
    n = 0;
}
lista::~lista(){
    ///Estos son los codigos del examen

    #ifdef METODO_ORIGINAL
    TNodo_Lista *N_Aux;  //Esta es mi implementacion, funciona.
    while(Lista != NULL){
        N_Aux = Lista;
        Lista = Lista->Siguiente;
        delete N_Aux;
    }
    #endif
    #ifdef METODO_A
    TNodo_Lista *N_Aux=Lista;
    while (Lista!=NULL){
        Lista=Lista->Siguiente; //Cuando lista avanza por ultima vez, se queda en null.
        N_Aux=Lista->Anterior;  //Esta linea intenta desreferenciar null ERROR
        delete N_Aux;
    }
    #endif
    #ifdef METODO_B
    TNodo_Lista *N_Aux=Lista;
    while (N_Aux->Siguiente!=NULL){ //No llega hasta el ultimo porque comprueba que el siguiente no sea null, vasta con eliminar ese ->Siguiente
        N_Aux=Lista->Siguiente;
        delete Lista;
        Lista=N_Aux;
    }   //El resto esta bien
    Lista=NULL;
    #endif
    #ifdef METODO_C
    TNodo_Lista *N_Aux=Lista;
    while (N_Aux!=NULL){
        N_Aux=N_Aux->Siguiente; //En la ultima iteracion, N_Aux sera null.
        delete N_Aux->Anterior; //No hay ningun nodo cuyo anterior sea el nodo final
    }
    Lista=NULL;
    #endif
    #ifdef METODO_D
    TNodo_Lista *N_Aux=NULL;
    if (Lista!=NULL){
        while(Lista!=NULL){
            N_Aux=Lista;
            Lista=Lista->Siguiente; //Hace que N_Aux apunte al ultimo nodo
        }
        while(N_Aux!=NULL){
            Lista=N_Aux->Anterior;  //Lista se queda un posicion atras de N_Aux
            delete N_Aux;
            N_Aux=Lista;    //N_Aux retrocede
        }
    }   //Es inutilmente rebuscado, pero funciona
    #endif

    ///Estos son los codigos del examen
}
bool lista::esvacia(){
    return n == 0;
}
int lista::longitud(){
    return n;
}
bool lista::pertenece(char e){
    return posicion(e)!=-1;
}
void lista::insertar(int i, char e){
    TNodo_Lista *nNew = new TNodo_Lista;
    nNew->Datos = e;

    if(i == 1){
        nNew->Siguiente = Lista;
        nNew->Anterior = NULL;
        Lista = nNew;
    }else{
        TNodo_Lista *nANT = Anterior(i);
        nNew->Siguiente = nANT->Siguiente;
        nNew->Anterior = nANT;
        nANT->Siguiente = nNew;
        if(nNew->Siguiente != NULL)
            nNew->Siguiente->Anterior = nNew;
    }

    n++;
}
void lista::eliminar(int i){
    TNodo_Lista *nBOR;

    if(i == 1){
        nBOR = Lista;
        Lista = Lista->Siguiente;
    }else{
        TNodo_Lista *nANT = Anterior(i);
        nBOR = nANT->Siguiente;
        nANT->Siguiente = nBOR->Siguiente;
        nBOR->Siguiente->Anterior = nANT;
    }

    delete nBOR;

    n--;
}
void lista::modificar(int i, char e){
    Anterior(i+1)->Datos = e;
}
char lista::observar(int i){
    return Anterior(i+1)->Datos;
}
int lista::posicion(char e){
    TNodo_Lista *nTMP = Lista;
    int i = 0;

    while(nTMP->Datos == e)
        nTMP = nTMP->Siguiente;

    return (i==n?-1:i+1);
}
TNodo_Lista* lista::Anterior(int i){
    i-=2;
    TNodo_Lista *nANT = Lista;

    while(nANT != NULL && --i >= 0)
        nANT = nANT->Siguiente;

    return nANT;
}

void lista::mostrar(){
    TNodo_Lista *nTMP = Lista;
    while(nTMP != NULL){
        cout << nTMP->Datos << " -> ";
        nTMP = nTMP->Siguiente;
    }
    cout << "/\n";
}



int main(){
    lista *l = new lista;

    l->insertar(1,'A');
    l->insertar(2,'B');
    l->insertar(3,'C');


    l->mostrar();

    delete l;

    return 0;
}
