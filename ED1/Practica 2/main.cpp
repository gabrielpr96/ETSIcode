#include <iostream>
#include <cstdlib>
#include <cstdio>
#include <conio.h> //!Remplazar
#include <cstring>
#include "listaDinamica.h"
#include "colaDinamica.h"
#include "peluqueria.h"

using namespace std;

char menu(){
    system("cls");
    cout << "Peluquería Corte 2.0\n"
         << "----------------------------------\n"
         << "1. Leer fichero (rescatar copia)\n"
         << "2. Insertar peluquero\n"
         << "3. Insertar cliente\n"
         << "4. Retirar peluqiero\n"
         << "5. Atender cliente\n"
         << "6. Mostrar peluqueria\n"
         << "7. Eliminar un cliente\n"
         << "8. Volcar a fichero (crear copia)\n"
         << "0. Salir\n"
         << "----------------------------------\n"
         << "Introduzca una operacion";
    char opt;
    cin >> opt;
    return opt;
}

int main(){
    peluqueria p;

    char opt;
    do{
        opt = menu();
        switch(opt){
            //!Ponerle cabeceras a todas las opciones
            case '1':
                //!Listar todos los .dat
                cout << "Nombre del fichero a cargar: ";
                cadena archivo;
                cin >> archivo;
                strcat(archivo, ".dat");

                p.AbrirPeluqueria(archivo);
            break;
            case '2':
                peluquerof Ptmp;
                cout << "Nombre del peluquero: ";
                gets(Ptmp.Nombre);
                cout << "Apellidos del peluquero: ";
                gets(Ptmp.Apellidos);
                cout << "Codigo del peluquero: ";
                cin >> Ptmp.Codigo;
                cout << "Tipo de servicio del peluquero: ";
                cin >> Ptmp.TipoServicio;

                p.IncorporarPeluquero(Ptmp);
            break;
            case '3':
                cliente Ctmp;
                int h, m;

                cout << "Nombre del cliente: ";
                gets(Ctmp.Nombre);
                cout << "Apellidos del cliente: ";
                gets(Ctmp.Apellidos);
                cout << "Edad del cliente: ";
                cin >> Ctmp.Edad;
                cout << "Tipo de servicio solicitado: ";
                cin >> Ctmp.TipoServicio;
                cout << "Hora de llegada (horas minutos): ";
                cin >> h >> m;
                Ctmp.HoraLlegada = h*60 + m;

                p.IncorporarCliente(Ctmp);
            break;
            case '4':
                int idP;

                cout << "Codigo del peluquero a retirar: ";
                cin >> idP;

                cout << "\n" << (p.RetirarPeluquero(idP)?"Peluquero retirado correctamente.":"No se pudo retirar al peluquero.") << "\n";
            break;
            case '5':
                int idC;

                cout << "Codigo del peluquero que atiende: ";
                cin >> idC;

                cout << "\n" << (p.AtenderCliente(idC)?"El peluquero atendio a un cliente nuevo.":"El peluquero no pudo atendar a un nuevo cliente.") << "\n";
            break;
            case '6':
                p.Mostrar();
            break;
            case '7':
                cadena nombre, apellidos;
                cout << "Nombre del peluquero: ";
                gets(nombre);
                cout << "Apellidos del peluquero: ";
                gets(apellidos);

                cout << "\n" << (p.EliminarCliente(nombre, apellidos)?"Cliente eliminado correctamente.":"No se pudo eliminar al cliente.") << "\n";
            break;
            case '8':
                cout << "Nombre del fichero a guardar: ";
                cadena fichero;
                cin >> fichero;
                strcat(fichero, ".dat");
                p.VolcarPeluqueria(fichero);
            break;
        }
        cout << "\n\nPulse una tecla para regresar al menu.";
        cin.ignore();
    }while(opt != '0');
    return 0;
}
