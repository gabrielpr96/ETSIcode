#include <iostream>

using namespace std;
struct TNodo_Lista{
    char Datos;
    TNodo_Lista *siguiente;
};

class lista{
    TNodo_Lista *elementos; // elementos de la lista
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

    void intercalar(lista &l2);
};

void mostrar(const char * nombre, lista &l);

lista::lista(){
    elementos = NULL;
    n = 0;
}
lista::~lista(){
    TNodo_Lista *borrar;

    while(elementos != NULL){
        borrar = elementos;
        elementos = elementos->siguiente;
        delete borrar;
    }
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
        nNew->siguiente = elementos;
        elementos = nNew;
    }else{
        TNodo_Lista *nANT = Anterior(i);
        nNew->siguiente = nANT->siguiente;
        nANT->siguiente = nNew;
    }

    n++;
}
void lista::eliminar(int i){
    TNodo_Lista *nBOR;

    if(i == 1){
        nBOR = elementos;
        elementos = elementos->siguiente;
    }else{
        TNodo_Lista *nANT = Anterior(i);
        nBOR = nANT->siguiente;
        nANT->siguiente = nBOR->siguiente;
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
    TNodo_Lista *nTMP = elementos;
    int i = 0;

    while(nTMP->Datos == e)
        nTMP = nTMP->siguiente;

    return (i==n?-1:i+1);
}
TNodo_Lista* lista::Anterior(int i){
    i-=2;
    TNodo_Lista *nANT = elementos;

    while(nANT != NULL && --i >= 0)
        nANT = nANT->siguiente;

    return nANT;
}


void lista::intercalar(lista &l2){
    ///Meter aquí el código

    TNodo_Lista *nTMP = elementos, *nINT;
    if(!(esvacia() || l2.esvacia())){
        while(!l2.esvacia()){
            nINT = l2.elementos;
            l2.elementos = nINT->siguiente;
            nINT->siguiente = nTMP->siguiente;
            nTMP->siguiente = nINT;
            nTMP = nINT;

            n++;
            l2.n--;

            if(nTMP->siguiente != NULL)
                nTMP = nTMP->siguiente;
        }
    }

    ///Meter aquçi el código
}


void mostrar(const char * nombre, lista &l){
    cout << "\n" << nombre << ": ";
    int n = l.longitud();
    for(int i = 0; i < n; i++)
        cout << l.observar(i+1) << " -> ";
    cout << "/\n";
}

int main(){
    lista l1, l2;

    ///Descomentar un bloque para probar ese ejemplo.

    /* // Ejemplo 1
    l1.insertar(1,'A');
    l1.insertar(2,'B');

    l2.insertar(1,'a');
    l2.insertar(2,'b');
    l2.insertar(3,'c');
    l2.insertar(4,'d');
    l2.insertar(5,'e');
    l2.insertar(6,'f');
    */

    /* // Ejemplo 2
    l1.insertar(1,'A');
    l1.insertar(2,'B');
    l1.insertar(3,'C');
    l1.insertar(4,'D');
    l1.insertar(5,'E');
    l1.insertar(6,'F');

    l2.insertar(1,'a');
    l2.insertar(2,'b');
    l2.insertar(3,'c');
    */


    mostrar("Lista 1", l1);
    mostrar("Lista 2", l2);

    cout << "\nIntercalar.\n";
    l1.intercalar(l2);


    mostrar("Lista 1", l1);
    mostrar("Lista 2", l2);

    return 0;
}
