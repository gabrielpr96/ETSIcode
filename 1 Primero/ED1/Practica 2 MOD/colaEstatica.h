#ifndef COLAESTATICA_H_INCLUDED
#define COLAESTATICA_H_INCLUDED

#define MAX 10

typedef char cadena[50];

struct cliente{
    cadena Apellidos;
    cadena Nombre;
    int Edad;
    int TipoServicio;
    int HoraLlegada; //almacenada en minutos
};

class cola{
    cliente elementos[MAX]; //elementos de la cola
    int ne; //Nº de elementos
public:
    cola(); // constructor de la clase
    ~cola();
    void encolar(cliente e);
    void desencolar();
    bool esvacia();
    cliente primero();
    int longitud();
    void vaciar();
    void clonar(cola &c);
};

#endif // COLAESTATICA_H_INCLUDED
