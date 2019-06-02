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

    int *clientes_atendidos, max_codigo_peluquero;
    void registrarTrabajo(int codigo, int cantidad);
public:
    peluqueria();
    ~peluqueria();
    void AbrirPeluqueria(char *nombrefichero);
    void IncorporarPeluquero(peluquerof t);
    bool RetirarPeluquero(int codigo);
    bool EliminarCliente(cadena Nombre, cadena Apelllidos);
    bool IncorporarCliente(cliente cli);
    void Mostrar();
    bool AtenderCliente(int CodigoPeluquero);
    void VolcarPeluqueria(char *nombrefichero);

    void ClienteMasMayor();
    void ResumenSituacion();

    void VerEstadistica();
};

#endif // PELUQUERIA_H_INCLUDED
