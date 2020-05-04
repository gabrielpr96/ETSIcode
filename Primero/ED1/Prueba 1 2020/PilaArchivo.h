#ifndef _Pila_Archivo
#define _Pila_Archivo

#include <iostream>
#include <fstream>
#include <string.h>
#include <stdlib.h>
using namespace std;

typedef char cadena[30];

class PilaArchivo {
    fstream logico;
    cadena fichero;
    int tope;
public:
    PilaArchivo();

    void apilar(float e);
    void desapilar();
    float cima();
    bool esVacia();
    int longitud();
    ~PilaArchivo();
};

#endif // _Pila_Archivo

