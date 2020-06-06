#include <iostream>
#include <conio.h>
#include <stdlib.h>
#include <time.h>
#include "TADColaPri.h"
#include "listaCD.h"
#include "listaC.h"
#include "listaD.h"

using namespace std;

//Para probar las liestas
void mostrar(listaD &lista){
    for(int i = 0; i < lista.longitud(); i++)
        cout << lista.observar(i+1).nombre << "  ";
    cout << endl;
}

///Modificación 1
float mediaaritm(listaD& ld){
    float media = 0;
    for(int i = 1; i <= ld.longitud(); i++)
        media += ld.observar(i).edad;
    return media / ld.longitud();
}
float mediaaritm(listaC& lc){
    float media = 0;
    for(int i = 1; i <= lc.longitud(); i++)
        media += lc.observar(i).edad;
    return media / lc.longitud();
}

char menu(){
    system("CLS");
    cout<< "--------- Menu Principal ---------" << endl
        << "1- Cargar datos desde fichero" << endl
        << "2- Admision de un nuevo Paciente" << endl
        << "3- Atender un Paciente" << endl
        << "4- Listar Pacientes esperando para atender" << endl
        << "5- Listar Pacientes segun prioridad" << endl
        << "6- Listar Pacientes Atendidos" << endl
        << "7- Listar Pacientes Atendidos MOD" << endl
        << "8- Salir" << endl;
    return getch();
}

int main(){
    srand(time(0));

    cadena nombre;
    Paciente p;
    ColaPri c;
    listaD ld;
    listaC lc;
    char opt;
    int n;
goto test;
    do{
        opt = menu();
        system("CLS");
        switch(opt){
        case '1':   // Cargar datos desde fichero
            cout << "--------- Cargar desde fichero ---------" << endl << endl;
            cout << "Nombre del fichero para cargar: ";
            cin >> nombre;
            c.cargarfichero(nombre);
            cout << "Datos cargados correctamente." << endl;
            break;
        case '2':   // Agregar paciente
            cout << "--------- Admision de un paciente nuevo ---------" << endl << endl;
            cout << "ID del paciente: ";
            cin >> p.id;
            cout << "Nombre del paciente: ";
            cin >> p.nombre;
            cout << "Apellido del paciente: ";
            cin >> p.apellidos;
            cout << "Edad del paciente: ";
            cin >> p.edad;
            c.insertarColaPri(prioridadPaciente(p)-1, p);
            cout << "Paciente insertado correctamente." << endl;
            break;
        case '3':   // Atender a un pacienete
            cout << "--------- Atender a un paciente ---------" << endl << endl;
            if(c.esvaciaColaPri())
                cout << "No hay pacientes que atender." << endl;
            else{
                p = c.consultarColaPri();
                c.sacarColaPri();
                cout<< "ID: " << p.id << endl
                    << "Nombre: " << p.nombre << endl
                    << "Apellidos: " << p.apellidos << endl
                    << "Edad: " << p.edad << endl;

                if(rand()%2 == 0)
                    ld.insertar(1, p);  // Masculino
                else
                    lc.insertar(1, p);  // Femenino
            }
            break;
        case '4':   // Mostrar todos los pacientes
            cout << "--------- Mostrar todos los pacientes ---------" << endl << endl;
            for(int i = MAXcolas-1; i >= 0 ; i--){
                n = c.longitudCola(i);
                if(n > 0){
                    cout << "Pacientes en cola " << (i+1) << ":" << endl;
                    for(;n > 0; n--){
                        p = c.sacarCola(i);
                        c.insertarColaPri(i, p);
                        cout<< "\t" << "ID: " << p.id << endl
                            << "\t" << "Nombre: " << p.nombre << endl
                            << "\t" << "Apellidos: " << p.apellidos << endl
                            << "\t" << "Edad: " << p.edad << endl << endl;
                    }
                }
            }
            break;
        case '5':   // Mostrar pacientes de una prioridad especifica
            cout << "--------- Mostrar pacientes atendidos segun prioridad ---------" << endl << endl;
            int prioridad;
            cout << "Prioridad: ";
            cin >> prioridad;
            n = c.longitudCola(prioridad-1);
            if(n > 0){
                for(;n > 0; n--){
                    p = c.sacarCola(prioridad-1);
                    c.insertarColaPri(prioridad-1, p);
                    cout<< "ID: " << p.id << endl
                        << "Nombre: " << p.nombre << endl
                        << "Apellidos: " << p.apellidos << endl
                        << "Edad: " << p.edad << endl << endl;
                }
            }else
                cout << "No hay pacientes en la cola " << prioridad << endl;
            break;
        case '6':   // Listar pacientes antendidos
            cout << "--------- Mostrar pacientes atendidos por sexo ---------" << endl << endl;
            if(ld.esvacia())
                cout << "No hay pacientes masculinos." << endl;
            else
                cout << "Pacientes masculinos: " << endl;
            for(int i = 0; i < ld.longitud(); i++){
                p = ld.observar(i+1);
                cout<< "\t" << "ID: " << p.id << endl
                    << "\t" << "Nombre: " << p.nombre << endl
                    << "\t" << "Apellidos: " << p.apellidos << endl
                    << "\t" << "Edad: " << p.edad << endl << endl;
            }
            cout << endl;
            if(lc.esvacia())
                cout << "No hay pacientes femeninos." << endl;
            else
                cout << "Pacientes femeninos: " << endl;
            for(int i = 0; i < lc.longitud(); i++){
                p = lc.observar(i+1);
                cout<< "\t" << "ID: " << p.id << endl
                    << "\t" << "Nombre: " << p.nombre << endl
                    << "\t" << "Apellidos: " << p.apellidos << endl
                    << "\t" << "Edad: " << p.edad << endl << endl;
            }
            cout << endl;

            break;
        case '7':   /// Listar pacientes atendidos, mejor
            cout << "--------- Mostrar pacientes atendidos por sexo con media de edad ---------" << endl << endl << endl;
            cout<< "\t\t\t\tListado General" << endl
                << "-----------------------------------------------------" << endl
                << "- Sexo: Hombre                          Total: " << ld.longitud() << endl
                << "Pacientes -------------------------------------------" << endl;
            for(int i = 0; i < ld.longitud(); i++){
                p = ld.observar(i+1);
                cout<< "\t" << "ID: " << p.id << endl
                    << "\t" << "Nombre: " << p.nombre << endl
                    << "\t" << "Apellidos: " << p.apellidos << endl
                    << "\t" << "Edad: " << p.edad << endl << endl;
            }
            cout << "Edad Media: " << mediaaritm(ld) << endl;

            cout<< "- Sexo: Mujer                          Total: " << lc.longitud() << endl
                << "Pacientes -------------------------------------------" << endl;
            cout << endl;
            for(int i = 0; i < lc.longitud(); i++){
                p = lc.observar(i+1);
                cout<< "\t" << "ID: " << p.id << endl
                    << "\t" << "Nombre: " << p.nombre << endl
                    << "\t" << "Apellidos: " << p.apellidos << endl
                    << "\t" << "Edad: " << p.edad << endl << endl;
            }
            cout << endl;
            cout << "Edad Media: " << mediaaritm(lc) << endl;

            break;
        case '8':   // Salir guardando los datos en un fichero
            cout << "--------- Guardar a fichero antes de salir ---------" << endl << endl;
            cout << "Nombre del fichero para guardar: ";
            cin >> nombre;
            c.guardarfichero(nombre);
            cout << "Datos guardados correctamente." << endl;
            break;
        }
        cout << endl << endl;
        system("PAUSE");
    }while(opt != '8');
test:
    //Prueba de las liestas
    listaD lista;
    Paciente e;
    strcpy(e.nombre, "1"); strcpy(e.apellidos, ""); lista.insertar(1, e);
    strcpy(e.nombre, "2"); strcpy(e.apellidos, ""); lista.insertar(2, e);
    strcpy(e.nombre, "3"); strcpy(e.apellidos, ""); lista.insertar(3, e);
    strcpy(e.nombre, "4"); strcpy(e.apellidos, ""); lista.insertar(4, e);
    mostrar(lista);
    lista.eliminarpornombre("3", "");
    mostrar(lista);
    lista.eliminarpornombre("1", "");
    mostrar(lista);
    lista.eliminarpornombre("4", "");
    mostrar(lista);


    return 0;
}
