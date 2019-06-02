#include <iostream>
#include <cstdlib>
#include <cstdio>
#include <conio.h> //!Remplazar
#include <cstring>
#include "peluqueria.h"

using namespace std;


void printn(char c, int n){
    for(int i = 0; i < n; i++)
        cout << c;
}
void printTitulo(const char *titulo){
    cout << "\n";

    printn(32,5); printn(201,1); printn(205,68); printn(187,1); cout << "\n";
    printn(32,5); printn(186,1);printn(32,24); cout << "Peluquer"<<char(161)<<"a Corte 2.0"; printn(32,24);printn(186,1); cout << "\n";
    printn(32,5); printn(204,1);printn(205,68);printn(185,1); cout << "\n";
    printn(32,5); printn(186,1); printn(32,68); printn(186,1); cout << "\n";

    int div = (68-strlen(titulo))/2, extra = (68-strlen(titulo))-(2*div);
    printn(32,5); printn(186,1);printn(32, div); cout << titulo; printn(32, div+extra);printn(186,1); cout << "\n";

    printn(32,5); printn(186,1); printn(32,68); printn(186,1); cout << "\n";
    printn(32,5); printn(200,1); printn(205,68); printn(188,1); cout << "\n";

    cout << "\n\n";
}
char menu(){
    system("cls");

    cout << "\n";
    printn(32,5); printn(201,1); printn(205,68); printn(187,1); cout << "\n";
    printn(32,5); printn(186,1);printn(32,24); cout << "Peluquer"<<char(161)<<"a Corte 2.0"; printn(32,24);printn(186,1); cout << "\n";
    printn(32,5); printn(204,1);printn(205,68);printn(185,1); cout << "\n";

    printn(32,5); printn(186,1); printn(32,68); printn(186,1); cout << "\n";
    printn(32,5); printn(186,1);printn(32,24); cout << "1. Leer fichero"; printn(32,29);printn(186,1); cout << "\n";
    printn(32,5); printn(186,1); printn(32,68); printn(186,1); cout << "\n";
    printn(32,5); printn(186,1);printn(32,24); cout << "2. Insertar peluquero"; printn(32,23);printn(186,1); cout << "\n";
    printn(32,5); printn(186,1); printn(32,68); printn(186,1); cout << "\n";
    printn(32,5); printn(186,1);printn(32,24); cout << "3. Insertar cliente"; printn(32,25);printn(186,1); cout << "\n";
    printn(32,5); printn(186,1); printn(32,68); printn(186,1); cout << "\n";
    printn(32,5); printn(186,1);printn(32,24); cout << "4. Retirar peluquero"; printn(32,24);printn(186,1); cout << "\n";
    printn(32,5); printn(186,1); printn(32,68); printn(186,1); cout << "\n";
    printn(32,5); printn(186,1);printn(32,24); cout << "5. Atender cliente"; printn(32,26);printn(186,1); cout << "\n";
    printn(32,5); printn(186,1); printn(32,68); printn(186,1); cout << "\n";
    printn(32,5); printn(186,1);printn(32,24); cout << "6. Mostrar peluqueria"; printn(32,23);printn(186,1); cout << "\n";
    printn(32,5); printn(186,1); printn(32,68); printn(186,1); cout << "\n";
    printn(32,5); printn(186,1);printn(32,24); cout << "7. Eliminar un cliente"; printn(32,22);printn(186,1); cout << "\n";
    printn(32,5); printn(186,1); printn(32,68); printn(186,1); cout << "\n";
    printn(32,5); printn(186,1);printn(32,24); cout << "8. Volcar a fichero"; printn(32,25);printn(186,1); cout << "\n";
    printn(32,5); printn(186,1); printn(32,68); printn(186,1); cout << "\n";
    printn(32,5); printn(186,1);printn(32,24); cout << "9. Cliende de mayor edad"; printn(32,20);printn(186,1); cout << "\n";
    printn(32,5); printn(186,1); printn(32,68); printn(186,1); cout << "\n";
    printn(32,5); printn(186,1);printn(32,24); cout << "A. Kowalski analysis"; printn(32,24);printn(186,1); cout << "\n";
    printn(32,5); printn(186,1); printn(32,68); printn(186,1); cout << "\n";
    printn(32,5); printn(186,1);printn(32,24); cout << "B. Mostrar estadistica"; printn(32,22);printn(186,1); cout << "\n";
    printn(32,5); printn(186,1); printn(32,68); printn(186,1); cout << "\n";

    printn(32,5); printn(186,1); printn(32,68); printn(186,1); cout << "\n";
    printn(32,5); printn(186,1);printn(32,24); cout << "0. Salir"; printn(32,36);printn(186,1); cout << "\n";

    printn(32,5); printn(186,1); printn(32,68); printn(186,1); cout << "\n";
    printn(32,5); printn(200,1); printn(205,68); printn(188,1); cout << "\n";

    cout << "\t\tIntroduzca una operacion: ";

    char opt;
    cin >> opt;
    return opt;
}

int main(){
    peluqueria p;
    //p.AbrirPeluqueria("inicial.dat"); //Eliminar

    char opt;
    do{
        opt = menu();
        system("cls");
        switch(opt){
            //!Ponerle cabeceras a todas las opciones
            case '1':
                printTitulo("Abrir un archivo guardado");

                cout << "\tNombre del fichero a cargar: ";
                cadena archivo;
                cin >> archivo;
                strcat(archivo, ".dat");
                cin.ignore();

                p.AbrirPeluqueria(archivo);
            break;
            case '2':
                printTitulo("Insertar un peluquero");
                peluquerof Ptmp;

                cin.ignore();
                cout << "\tNombre del peluquero: ";
                gets(Ptmp.Nombre);
                cout << "\tApellidos del peluquero: ";
                gets(Ptmp.Apellidos);
                cout << "\tCodigo del peluquero: ";
                cin >> Ptmp.Codigo;
                cout << "\tTipo de servicio del peluquero: ";
                cin >> Ptmp.TipoServicio;
                cin.ignore();

                p.IncorporarPeluquero(Ptmp);
                cout << "\n\tPeluquero agregado correctamente.";
            break;
            case '3':
                printTitulo("Insertar un cliente");
                cliente Ctmp;
                int h, m;

                cin.ignore();
                cout << "\tNombre del cliente: ";
                gets(Ctmp.Nombre);
                cout << "\tApellidos del cliente: ";
                gets(Ctmp.Apellidos);
                cout << "\tEdad del cliente: ";
                cin >> Ctmp.Edad;
                cout << "\tTipo de servicio solicitado: ";
                cin >> Ctmp.TipoServicio;
                cout << "\tHora de llegada (horas minutos): ";
                cin >> h >> m;
                Ctmp.HoraLlegada = h*60 + m;
                cin.ignore();

                cout << "\n\t" << (p.IncorporarCliente(Ctmp)?"Cliente agregado correctamente.":"No se pudo agregar el cliente.") << "\n";
            break;
            case '4':
                printTitulo("Retirar un peluquero");
                int idP;

                cout << "\tCodigo del peluquero a retirar: ";
                cin >> idP;
                cin.ignore();

                cout << "\n\t" << (p.RetirarPeluquero(idP)?"Peluquero retirado correctamente.":"No se pudo retirar al peluquero.") << "\n";
            break;
            case '5':
                printTitulo("Atender a un cliente");
                int idC;

                cout << "\tCodigo del peluquero que atiende: ";
                cin >> idC;
                cin.ignore();

                cout << "\n\t" << (p.AtenderCliente(idC)?"El peluquero atendio a un cliente nuevo.":"El peluquero no pudo atendar a un nuevo cliente.") << "\n";
            break;
            case '6':
                printTitulo("Mostrar los trabajos asignados");

                p.Mostrar();
                cin.ignore();
            break;
            case '7':
                printTitulo("Eliminar un cliente");
                cadena nombre, apellidos;

                cin.ignore();
                cout << "\tNombre del cliente: ";
                cin.getline(nombre, sizeof(cadena));
                cout << "\tApellidos del cliente: ";
                cin.getline(apellidos, sizeof(cadena));

                cout << "\n\t" << (p.EliminarCliente(nombre, apellidos)?"Cliente eliminado correctamente.":"No se pudo eliminar al cliente.") << "\n";
            break;
            case '8':
                printTitulo("Guardar a un archivo");

                cout << "\tNombre del fichero a guardar: ";
                cadena fichero;
                cin >> fichero;
                cin.ignore();

                strcat(fichero, ".dat");
                p.VolcarPeluqueria(fichero);
            break;
            case '9':
                printTitulo("Cliente mas mayor");
                p.ClienteMasMayor();
                cin.ignore();
            break;
            case 'a':
            case 'A':
                printTitulo("Resumen de situacion");
                p.ResumenSituacion();
                cin.ignore();
            break;
            case 'b':
            case 'B':
                printTitulo("Estadisticas");
                p.VerEstadistica();
                cin.ignore();
            break;
        }
        if(opt != '0'){
            cout << "\n\n\n\tPulse una tecla para regresar al menu.";
            cin.ignore();
        }
    }while(opt != '0');
    return 0;
}
