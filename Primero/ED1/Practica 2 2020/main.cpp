#include <iostream>
#include <conio.h>
#include "listaCD.h"
#include "listaC.h"
#include "listaD.h"

using namespace std;

//Para probar las liestas
void mostrar(listaD &lista){
    for(int i = 0; i < lista.longitud(); i++)
        cout << lista.observar(i+1).n << "  ";
    cout << endl;
}

char menu(){
    system("CLS");
    cout<< "--------- Menu Principal ----------" << endl
        << "1- Cargar datos desde fichero" << endl
        << "2- Admision de un nuevo Paciente" << endl
        << "3- Atender un Paciente" << endl
        << "4- Listar Pacientes esperando para atender" << endl
        << "5- Listar Pacientes según prioridad" << endl
        << "6- Listar Pacientes Atendidos" << endl
        << "7- Salir" << endl;
    return getch();
}

int main(){
    char opt;
    do{
        opt = menu();
        system("CLS");
        switch(opt){
        case '1':
            break;
        case '2':
            break;
        case '3':
            break;
        case '4':
            break;
        case '5':
            break;
        case '6':
            break;
        }
        if(opt != '7'){
            cout << endl << endl;
            system("PAUSE");
        }
    }while(opt != '7');

    //Prueba de las liestas
    listaD lista;
    Paciente e;
    e.n = 1;lista.insertar(1, e);
    e.n = 2;lista.insertar(1, e);
    e.n = 3;lista.insertar(1, e);
    e.n = 4;lista.insertar(4, e);
    mostrar(lista);
    lista.eliminar(4);
    mostrar(lista);


    return 0;
}
