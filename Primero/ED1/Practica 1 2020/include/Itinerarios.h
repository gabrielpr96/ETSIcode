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
    int tam; //tamaño de la tabla dinámica
    int n; //número de itinerarios
    bool cargado; //indica si el fichero está cargado en memoria

    public:
        Itinerarios(); //constructor de la clase
        ~Itinerarios(); //Destructor
        void Insertar(itinerario i);
        void Borrar(cadena nombrei,cadena mat,int pos); //borra el itinerario(se le
             //pasa nombre itinerario y matricula o bien posición)
        void Modificar(itinerario i,cadena nombrei,cadena mat,int pos); //modifica el
            //itinerario cuyo nombre de itinerario sea nombrei y matrícula sea mat o
            //bien esté en la posición pos, con los datos del itinerario i)
        int Buscar(cadena nombrei,cadena mat); //devuelve la posición del itinerario de
            //nombre nombrei y matricula mat. –1 si no lo encuentra
        void Mostrar(cadena nombrei, cadena mat,int pos); //muestra por pantalla el
            //itinerario de nombre nombrei y matricula mat o bien de posición pos)
        void Listar();
        bool Cargar(); //permite cargar en memoria el fichero de itinerarios denominado
            //“itinerarios.dat”. Devuelve true si correcto, false si error.
        bool Guardar(); //Vuelca el contenido de la tabla dinámica(si ésta existe) al
            //fichero“itinerarios.dat”
        void Itinerariosenintervalo(Hora horaini, Hora horafin);
};

#endif // ITINERARIOS_H
