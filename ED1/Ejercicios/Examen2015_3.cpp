#include <iostream>
#include <fstream>
#include <cstring>
#include <cstdio>

using namespace std;

struct TCliente{
    char Nombre[100];
    char Direccion[100];
    char Telefono[15];
    int Edad;
    float Saldo;
};

class cola {
        TCliente clientes[10];
        int n;
    public:
        cola();
        void encolar(TCliente c);
        void desencolar();
        TCliente primero();
        int longitud();
};

cola::cola(){
    n = 0;
}
void cola::encolar(TCliente c){
    if(n == 10) return;
    clientes[n] = c;
    n++;
}
void cola::desencolar(){
    n--;
    for(int i = 0; i < n; i++)
        clientes[i] = clientes[i+1];
}
TCliente cola::primero(){
    return clientes[0];
}
int cola::longitud(){
    return n;
}

class Banco{
        cola *ColaClientes; //Tabla dinámica de cola de clientes
        int Tama;           //Tamaño de la tabla dinámica de cola de clientes
    public:
        Banco();
        ~Banco();
        void Volcar(char Fichero[]);
        void Cargar(char Fichero[]);
        void AgregarCliente(int lista, TCliente c);
        void EliminarCliente(int lista);
        void Vaciar();
        void Mostrar();
};

Banco::Banco(){
    ColaClientes = NULL;
    Tama = 0;
}
Banco::~Banco(){
    Vaciar();
}
void Banco::AgregarCliente(int lista, TCliente c){
    if(lista >= Tama){
        cola *ColaTMP = new cola[lista];

        for(int i = 0 ; i < Tama; i++)
            ColaTMP[i] = ColaClientes[i];

        Tama = lista;

        delete ColaClientes;
        ColaClientes = ColaTMP;
    }

    ColaClientes[lista-1].encolar(c);
}
void Banco::EliminarCliente(int lista){
    if(lista > Tama) return;
    ColaClientes[lista-1].desencolar();
}
void Banco::Vaciar(){
    delete ColaClientes;
    ColaClientes = NULL;
    Tama = 0;
}
void Banco::Mostrar(){
    TCliente cTMP;

    for(int i = 0; i < Tama; i++){
        cout << "\nCola " << (i+1) << ":  ";
        for(int j = 0; j < ColaClientes[i].longitud(); j++){
            cTMP = ColaClientes[i].primero();
            cout << cTMP.Nombre << " | ";
            ColaClientes[i].desencolar();
            ColaClientes[i].encolar(cTMP);
        }
    }
}
void Banco::Cargar(char Fichero[]){
    Vaciar();

    ifstream file(Fichero, ios::binary);
    int nClientes, lista = 1;
    TCliente cTMP;

    if(file){
        while(file.read((char *) &nClientes, sizeof(int)) && !file.eof()){
            for(int i = 0; i < nClientes; i++){
                file.read((char *) &cTMP, sizeof(TCliente));
                AgregarCliente(lista, cTMP);
            }
            lista++;
        }
    }

    file.close();
}

void Banco::Volcar(char Fichero[]){
    ///Poner aqui el codigo

    ofstream file(Fichero, ios::binary);
    TCliente cTMP;
    int nClientes;

    if(file){
        for(int i = 0; i < Tama; i++){
            nClientes = ColaClientes[i].longitud();
            file.write((char *) &nClientes, sizeof(int));
            for(int j = 0; j < nClientes; j++){
                cTMP = ColaClientes[i].primero();
                file.write((char *) &cTMP, sizeof(TCliente));
                ColaClientes[i].desencolar();
                ColaClientes[i].encolar(cTMP);
            }
        }
    }

    file.close();

    ///Poner aqui el codigo
}


char menu(){
    system("CLS");
    cout << "\t\tBANCO BIC.\n\n BIC, BIC; BIC, BIC, BIC.\n BIC naranja escribe fino, BIC cristal escribe normal.\n BIC, BIC; BIC, BIC, BIC.\n Dos escrituras a elegir.\n\n"
        << "\t1. Agregar un cliente.\n"
        << "\t2. Eliminar un cliente.\n"
        << "\t3. Mostrar el banco.\n"
        << "\t4. VOLCAR\n"
        << "\t5. CARGAR\n"
        << "\t0. Salir\n\n"
        << "\tOpcion: ";
    return cin.get();
}

int main(){
    Banco B;

    char opt;
    do{
        opt = menu();
        system("CLS");
        switch(opt){
            case '1':
                cout << "\tAgregar un cliente.\n\n";
                TCliente cTMP;
                cin.ignore();
                cout << "Nombre: ";
                gets(cTMP.Nombre);
                B.AgregarCliente(toupper(cTMP.Nombre[0])-64,cTMP);
            break;
            case '2':
                cout << "\tEliminar un cliente.\n\n";
                int lista;
                cout << "Numero de lista>: ";
                cin >> lista;
                B.EliminarCliente(lista);
            break;
            case '3':
                B.Mostrar();
                cin.ignore();
                cin.ignore();
            break;
            case '4':
                char archivoVolcar[100];
                cout << "Nombre del archivo: ";
                cin >> archivoVolcar;
                B.Volcar(archivoVolcar);
            break;
            case '5':
                char archivoCargar[100];
                cout << "Nombre del archivo: ";
                cin >> archivoCargar;
                B.Cargar(archivoCargar);
            break;
        }
    }while(opt != '0');

    return 0;
}
