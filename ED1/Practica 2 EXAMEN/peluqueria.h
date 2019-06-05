#ifndef PELUQUERIA_H_INCLUDED
#define PELUQUERIA_H_INCLUDED

#include "listaEnlazada.h" //Cambiar para otra implementacion
#include "colaEnlazada.h"

struct peluquerof{
    int Codigo;
    cadena Apellidos;
    cadena Nombre;
    int TipoServicio;
};

class peluqueria{
    lista L; //lista de los técnicos que están atendiendo
    int buscarPorCodigo(int Codigo);
    int posicionCorrecta(int Codigo);
    int peluqueroMasOcupado(int Servicio, int codigoIgnorar);
    int peluqueroMenosOcupado(int Servicio, int codigoIgnorar);
public:
    peluqueria();
    void AbrirPeluqueria(char *nombrefichero);
    void IncorporarPeluquero(peluquerof t);
    bool RetirarPeluquero(int codigo);
    bool EliminarCliente(cadena Nombre, cadena Apelllidos);
    bool IncorporarCliente(cliente cli);
    void Mostrar();
    bool AtenderCliente(int CodigoPeluquero);
    void VolcarPeluqueria(char *nombrefichero);

    bool ExisteCliente(cadena Nombre, cadena Apellidos);
    void ModificarCliente(cadena Nombre, cadena Apellidos, cliente cNEW);
    void MostrarUltimoCliente();
};

#endif // PELUQUERIA_H_INCLUDED
