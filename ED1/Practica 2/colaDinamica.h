#ifndef COLADINAMICA_H_INCLUDED
#define COLADINAMICA_H_INCLUDED

typedef char cadena[50];

struct cliente{
    cadena Apellidos;
    cadena Nombre;
    int Edad;
    int TipoServicio;
    int HoraLlegada; //almacenada en minutos
};

class cola{
    cliente *elementos; //elementos de la cola
    int inicio, fin; //principio y fin de la cola
    int Tama; //Capacidad de la tabla
    int ne; //Nº de elementos
public:
    cola(); // constructor de la clase
    ~cola();
    void encolar(cliente e);
    void desencolar();
    bool esvacia();
    cliente primero();
    int longitud();
    void clonar(cola c);
    bool comparar(cola c);
};

#endif // COLADINAMICA_H_INCLUDED
