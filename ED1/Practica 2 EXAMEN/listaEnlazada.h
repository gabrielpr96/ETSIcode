#ifndef LISTADINAMICA_H_INCLUDED
#define LISTADINAMICA_H_INCLUDED

#include <cstdlib>
#include <cstring>
#include "colaEnlazada.h"

struct peluquero{
    int Codigo;
    cadena Apellidos;
    cadena Nombre;
    int TipoServicio;
    cola Col;
};
struct peluqueroNodo{
    peluquero dato;
    peluqueroNodo *siguiente;
};

class lista{
    peluqueroNodo *elementos; // elementos de la lista
    int n;// nº de elementos que tiene la lista
    peluqueroNodo* buscarNodo(int i); //Busca el nodo anteior a uno dado. (indice 0)
public:
    lista(); // constructor de la clase
    ~lista();// destructor de la clase
    lista(peluquero &e);
    void vaciar();
    bool esvacia();
    int longitud();
    //void anadirIzq(peluquero e); No necesario implementar
    //void anadirDch(peluquero e); No necesario implementar
    //void eliminarIzq(); No necesario implementar
    //void eliminarDch();No necesario implementar
    //tecnico observarIzq(); No necesario implementar
    //tecnico observarDch(); No necesario implementar
    //void concatenar(lista l); No necesario implementar
    bool pertenece(peluquero &e);
    void insertar(int i, peluquero &e);
    void eliminar(int i);
    void modificar(int i, peluquero &e);
    peluquero &observar(int i);
    int posicion(peluquero &e);
};

void copiarpeluquero(peluquero &destino,peluquero &origen);
bool compararpeluquero(peluquero &p1,peluquero &p2);

#endif // LISTADINAMICA_H_INCLUDED
