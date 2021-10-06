#ifndef ITINERARIOS_H
#define ITINERARIOS_H
#include <iostream>
#include <fstream>

typedef char cadena[50];
using namespace std;
#define SALTO 4

struct Hora{
    int h;
    int m;
    bool am;
};

struct itinerario{
    cadena nombre;
    cadena origen;
    cadena destino;
    Hora inicio;
    Hora fin;
    cadena matricula;
};

class Itinerarios{
    itinerario *elementos;
    fstream fichero;
    int tam; //tama�o de la tabla din�mica
    int n; //n�mero de itinerarios
    bool cargado; //indica si el fichero est� cargado en memoria

    void mostrarIti(int pos);
    void controlarArray();

    public:
        Itinerarios(); //constructor de la clase
        ~Itinerarios(); //Destructor
        void Insertar(itinerario i);
        void Borrar(cadena nombrei,cadena mat,int pos); //borra el itinerario(se le
             //pasa nombre itinerario y matricula o bien posici�n)
        void Modificar(itinerario i,cadena nombrei,cadena mat,int pos); //modifica el
            //itinerario cuyo nombre de itinerario sea nombrei y matr�cula sea mat o
            //bien est� en la posici�n pos, con los datos del itinerario i)
        int Buscar(cadena nombrei,cadena mat); //devuelve la posici�n del itinerario de
            //nombre nombrei y matricula mat. �1 si no lo encuentra
        void Mostrar(cadena nombrei, cadena mat,int pos); //muestra por pantalla el
            //itinerario de nombre nombrei y matricula mat o bien de posici�n pos)
        void Listar();
        bool Cargar(); //permite cargar en memoria el fichero de itinerarios denominado
            //�itinerarios.dat�. Devuelve true si correcto, false si error.
        bool Guardar(); //Vuelca el contenido de la tabla din�mica(si �sta existe) al
            //fichero�itinerarios.dat�
        void Itinerariosenintervalo(Hora hIni, Hora hFin);

        ///Modificacion:
        void listaitixmatricula(cadena mat);

        void viajeslocalidad(cadena localidad);
};

#endif // ITINERARIOS_H
