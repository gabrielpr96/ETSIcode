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

    do {
        cout<< "----- Menu principal -----" << endl
            << "1- Gestion de Autobuses" << endl
            << "2- Gestion de Itinerarios" << endl
            << "3- Listar itinerarios segun franja horaria" << endl
            <<  "4- Salir" << endl
            << "Elija una opcion: ";
        cin >> opc;
    } while(opc < 1 || opc > 4);

    return opc;
}
char menuBus() {
    char opc1;

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

    return opc1;
}
char menuIti() {
    char opc2;

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
        cout << "Desea " << accion << " por matricula o por posicion (m/p): " << endl;
        cin >> c;
    } while(c != 'm' && c != 'p');

    if(c == 'm') {
        cout << "Introduzca la matricula del " << elemento << " que desea eliminar: " << endl;
        cin >> mat;
        pos = -1;
    } else if(c == 'p') {
        cout << "Introduzca la posicion del " << elemento << " que desea eliminar: " << endl;
        cin >> pos;
    }
}
void pedirMatOposOnom(cadena &mat, cadena &nom, int &pos, const char *accion, const char *elemento){
    char c;

    do {
        cout << "Desea " << accion << " por matricula, por nombre o por posicion (m/n/p): " << endl;
        cin >> c;
    } while(c != 'm' && c != 'n' && c != 'p');

    if(c == 'm') {
        cout << "Introduzca la matricula del " << elemento << " que desea eliminar: " << endl;
        cin >> mat;
        pos = -1;
        nom[0] = 0;
    } else if(c == 'n') {
        cout << "Introduzca el nombre del " << elemento << " que desea eliminar: " << endl;
        cin >> nom;
        pos = -2;
        mat[0] = 0;
    } else if(c == 'p') {
        cout << "Introduzca la posicion del " << elemento << " que desea eliminar: " << endl;
        cin >> pos;
    }
}


int main() {
    Autobuses a;
    Itinerarios i;

    cadena mat, nom;
    int pos, menu = 1;
    char opt;
    Hora hi, hf;
    autobus bus;
    itinerario iti;

    do{
        switch(menu){
        case 1:
            opt = menuMain();


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
                case 'd':   //Mostrar autobus por matricula
                    pedirMatOpos(mat, pos, "buscar", "autobus");

                    if(pos < 0)
                        pos = a.Buscar(mat);
                    if(pos){
                        cout << "El autobus se encuentra en la posicion " << pos << endl;
                        a.Mostrar(mat, pos);
                    }else
                        cout << "Esa matricula no corresponde a ningún autobus." << endl;
                break;
                case 'e':
                    a.Listar();
                break;
                case 'f':
                    a.Cargar();
                break;
                case 'g':
                    a.Guardar();
                break;
                case 'v':
                    menu = 1;
                break;
                default:
                    cout<<"Inserte una opcion valida";
            }
            break;
        case 2:
            opt = menuBus();
            switch(opt) { //MENU DE ITINERARIOS
                case 'a':   //Crear itinerario
                    pedirDatosItinerario(iti);
                    i.Insertar(iti);
                break;
                case 'b':   //Borrar itinerari
                    pedirMatOposOnom(mat, nom, pos, "borrar", "itinerario");
                    i.Borrar(nom, mat, pos);
                break;
                case 'c':   //Modificar itinerario
                    pedirMatOposOnom(mat, nom, pos, "modificar", "itinerario");
                    pedirDatosItinerario(iti);
                    i.Modificar(iti, nom, mat, pos);
                break;
                case 'd':
                    i.Listar();
                break;
                case 'e':
                    pedirMatOposOnom(nom, mat, pos, "buscar", "itinerario");

                    if(pos < 0)
                        pos = i.Buscar(nom, mat);
                    if(pos){
                        cout << "El itinerario se encuentra en la posicion " << pos << endl;
                        i.Mostrar(nom, mat, pos);
                    }else
                        cout << "Esa matricula no corresponde a ningun itinerario." << endl;
                break;
                case 'f':
                    i.Cargar();
                break;
                case 'g':
                    i.Guardar();
                break;
                case 'v':
                    menu = 1;
                break;
                default:
                    cout<<"Inserte una opcion valida";
            }
            break;
        case 3:
            opt = menuIti();
            break;
        }
        system("CLS");
        system("PAUSE");
    }while(opt != 4);
return 0;
/*
    do {
        opt = menuMain();

            system("pause");
        break;
        case 2:

            system("pause");
            break;
        case 3:
            char s1,s2;

            cout<<"Introduzca la hora de inicio: "<<endl;
            cin>>hi.h;
            cout<<"Introduzca los minutos de inicio: "<<endl;
            cin>>hi.m;
            do {
                cout<<"La hora es previa o posterior al medio dia (a/p): "<<endl;
                cin>>s1;
            } while(s1 != 'a' && s1 != 'p');
            if(s1=='a')
                hi.am=true;
            else
                hi.am=false;
            cout<<"Introduzca la hora de fin: "<<endl;
            cin>>hf.h;
            cout<<"Introduzca los minutos de fin: "<<endl;
            cin>>hf.m;
            do {
                cout<<"La hora es previa o posterior al medio dia (a/p): "<<endl;
                cin>>s2;
            } while(s2 != 'a' && s2 != 'p');
            if(s2=='a')
                hf.am=true;
            else
                hf.am=false;
            i.Itinerariosenintervalo(hi,hf);
            system("pause");
        break;
        case 4:
            //a.Guardar();
            //i.Guardar();
        break;
        default:
            cout<<"Inserte una opcion valida";
        }
    } while(opt != 4);
*/
}
