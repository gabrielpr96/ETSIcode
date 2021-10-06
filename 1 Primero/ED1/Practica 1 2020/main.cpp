#include <iostream>
#include <conio.h>
#include "Autobuses.h"
#include "Itinerarios.h"
#include <stdlib.h>
#include <string.h>

#define SALTO 4
typedef char cadena[50];

using namespace std;

int menuMain();
char menuBus();
char menuIti();

int menuMain() {
    int opc;
    system("CLS");

    do {
        cout<< "----- Menu principal -----" << endl
            << "1- Gestion de Autobuses" << endl
            << "2- Gestion de Itinerarios" << endl
            << "3- Listar itinerarios segun franja horaria" << endl
            <<  "4- Salir" << endl
            << "Elija una opcion: ";
        cin >> opc;
    } while(opc < 1 || opc > 4);

    system("CLS");
    return opc;
}
char menuBus() {
    char opc1;
    system("CLS");

    cout<<"----- Menu Gestion de Autobuses -----\n"
        << "a) Insertar" << endl
        << "b) Borrar" << endl
        << "c) Modificar" << endl
        << "d) Buscar" << endl
        << "e) Listar" << endl
        << "f) Cargar autobuses desde fichero" << endl
        << "g) Guardar autobuses en fichero" << endl
        << "v) Volver" << endl
        << "Elija una opcion: ";
    cin>>opc1;

    system("CLS");
    return opc1;
}
char menuIti() {
    char opc2;
    system("CLS");

    cout<<"----- Menu Gestion de Itinerarios -----\n"
        << "a) Insertar" << endl
        << "b) Borrar" << endl
        << "c) Modificar" << endl
        << "d) Listar" << endl
        << "e) Buscar" << endl
        << "f) Cargar itinerarios desde fichero" << endl
        << "g) Guardar itinerarios en fichero" << endl
        << "v) Volver" << endl
        << "Elija una opcion: ";
    cin>>opc2;

    system("CLS");
    return opc2;
}

void pedirDatosAutobus(autobus &bus){
    char c;

    cout << "Introduzca la matricula del autobus: ";
    cin >> bus.matricula;

    cout << "Introduzca el id del conductor: " ;
    cin >> bus.conductor;

    do {
        cout << "¿Esta activo el autobus? (s/n): ";
        cin >> c;
    } while(c != 's' && c != 'n');
    bus.e.activo = c == 's';

    cout << "Introduzca la capacidad del deposito (en L): ";
    cin >> bus.e.deposito;

    cout << "Introduzca el numero de plazas: ";
    cin >> bus.e.plazas;

    do {
        cout<<"¿Ha pasado la ITV el autobus? (s/n): ";
        cin>>c;
    } while(c != 's' && c != 'n');
    bus.e.activo = c == 's';
}
void pedirDatosItinerario(itinerario &iti){
    char c;

    cout<<"Introduzca el nombre del itinerario: ";
    cin>>iti.nombre;

    cout<<"Introduzca el origen: ";
    cin>>iti.origen;

    cout<<"Introduzca el destino: ";
    cin>>iti.destino;

    cout<<"Introduzca la hora de inicio: ";
    cin>>iti.inicio.h;

    cout<<"Introduzca los minutos de inicio: ";
    cin>>iti.inicio.m;

    do {
        cout << "Es anterior o posterior al medio dia (a/p): ";
        cin >> c;
    } while(c != 'a' && c != 'p');
    iti.inicio.am = c == 'a';

    cout<<"Introduzca la hora de llegada: ";
    cin>>iti.fin.h;

    cout<<"Introduzca los minutos de llegada: ";
    cin>>iti.fin.m;

    do {
        cout<<"Es anterior o posterior al medio dia (a/p): ";
        cin>>c;
    } while(c != 'a' && c != 'p');
    iti.inicio.am= c == 'a';

    cout<<"Introduzca la matricula: ";
    cin>>iti.matricula;
}
void pedirMatOpos(cadena &mat, int &pos, const char *accion, const char *elemento){
    char c;

    do {
        cout << "Desea " << accion << " por matricula o por posicion (m/p): ";
        cin >> c;
    } while(c != 'm' && c != 'p');

    if(c == 'm') {
        cout << "Introduzca la matricula del " << elemento << " que desea " << accion << ": ";
        cin >> mat;
        pos = -1;
    } else if(c == 'p') {
        cout << "Introduzca la posicion del " << elemento << " que desea " << accion << ": ";
        cin >> pos;
        pos--;
    }
}
void pedirMatNomOpos(cadena &nom, cadena &mat, int &pos, const char *accion, const char *elemento){
    char c;

    do {
        cout << "Desea " << accion << " por matricula, por nombre o por posicion (m/p): ";
        cin >> c;
    } while(c != 'm' && c != 'p');

    if(c == 'm') {
        cout << "Introduzca el nombre del " << elemento << " que desea " << accion << ": ";
        cin >> nom;
        cout << "Introduzca la matricula del " << elemento << " que desea " << accion << ": ";
        cin >> mat;
        pos = -1;
    } else if(c == 'p') {
        cout << "Introduzca la posicion del " << elemento << " que desea " << accion << ": ";
        cin >> pos;
        pos--;
    }
}


int main() {
    Autobuses a;
    Itinerarios i;

    cadena mat, nom;
    int pos, menu = 1;
    char opt;
    Hora hIni, hFin;
    autobus bus;
    itinerario iti;

    do{
        switch(menu){
        case 1:
            opt = menuMain();
            switch(opt) {
            case 1:
                menu = 2;
            break;
            case 2:
                menu = 3;
                break;
            case 3:
                char c;

                cout << "Introduzca la hora de inicio: ";
                cin >> hIni.h;
                cout<<"Introduzca los minutos de inicio: ";
                cin >> hIni.m;
                do {
                    cout << "La hora es previa o posterior al medio dia (a/p): ";
                    cin >> c;
                } while(c != 'a' && c != 'p');
                hIni.am = c=='a';
                cout << "Introduzca la hora de fin: ";
                cin >> hFin.h;
                cout << "Introduzca los minutos de fin: ";
                cin >> hFin.m;
                do {
                    cout << "La hora es previa o posterior al medio dia (a/p): ";
                    cin >> c;
                } while(c != 'a' && c != 'p');
                hFin.am = c=='a';
                i.Itinerariosenintervalo(hIni, hFin);
            break;
            case 4:
                cout << "Guardado preventivo" << endl;
                a.Guardar();
                i.Guardar();
            break;
            default:
                cout<<"Inserte una opcion valida";
            }
            if(opt == 3)
                system("PAUSE");
            break;
        case 2:
            opt = menuBus();
            switch(opt) { //MENU DE AUTOBUSES
                case 'a':   //Insertar un autobus
                    pedirDatosAutobus(bus);
                    a.Insertar(bus);
                break;
                case 'b':   //Eliminar un autobus
                    pedirMatOpos(mat, pos, "borrar", "autobus");
                    a.Borrar(mat, pos);
                break;
                case 'c':   //Modificar un autobus
                    pedirMatOpos(mat, pos, "modificar", "autobus");
                    pedirDatosAutobus(bus);
                    a.Modificar(bus, mat, pos);
                break;
                case 'd':   //Mostrar un solo autobus
                    pedirMatOpos(mat, pos, "buscar", "autobus");
                    a.Mostrar(mat, pos);
                break;
                case 'e':   //Listar todos los autobuses
                    a.Listar();
                break;
                case 'f':   //Cargar de fichero
                    cout << (a.Cargar()?"Carga realizada con exito.":"Fallo al cargar.") << endl;
                break;
                case 'g':   //Guardar en fichero
                    cout << (a.Guardar()?"Guardado con exito.":"Fallo al guardar.") << endl;
                break;
                case 'v':   //Regresar al menu principal
                    menu = 1;
                break;
                default:
                    cout << "Inserte una opcion valida" << endl;
                break;
            }
            if(opt != 'v')
                system("PAUSE");
            break;
        case 3:
            opt = menuIti();
            switch(opt) { //MENU DE ITINERARIOS
                case 'a':   //Crear itinerario
                    pedirDatosItinerario(iti);
                    i.Insertar(iti);
                break;
                case 'b':   //Borrar itinerari
                    pedirMatNomOpos(nom, mat, pos, "borrar", "itinerario");
                    i.Borrar(nom, mat, pos);
                break;
                case 'c':   //Modificar itinerario
                    pedirMatNomOpos(nom, mat, pos, "modificar", "itinerario");
                    pedirDatosItinerario(iti);
                    i.Modificar(iti, nom, mat, pos);
                break;
                case 'd':   //Listar todos los itinerarios
                    i.Listar();
                break;
                case 'e':   //Mostrar un unico itinerario
                    pedirMatNomOpos(nom, mat, pos, "buscar", "itinerario");
                    i.Mostrar(nom, mat, pos);
                break;
                case 'f':   //Cargar itinerarios
                    cout << (i.Cargar()?"Datos cargados correctamente.":"Error al cargar los datos") << endl;
                break;
                case 'g':   //Guardar itinerarios
                    cout << (i.Guardar()?"Datos guardados correctamente.":"Error al guardar los datos") << endl;
                break;
                case 'v':   //Regresar al menu principal
                    menu = 1;
                break;
                default:
                    cout<<"Inserte una opcion valida";
            }
            if(opt != 'v')
                system("PAUSE");
            break;
        }
    }while(!(menu == 1 && opt == 4));

    return 0;
}
