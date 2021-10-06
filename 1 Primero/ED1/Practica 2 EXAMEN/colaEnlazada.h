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
struct clienteNodo{
    cliente dato;
    clienteNodo *siguiente;
};

class cola{
    clienteNodo *inicio, *fin; //primer y ultimo elemento de la cola
    int ne; //Nº de elementos
    bool compararClientes(cliente c1, cliente c2);
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

#endif // COLADINAMICA_H_INCLUDED
