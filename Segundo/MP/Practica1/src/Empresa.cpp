#include <typeinfo>
#include <cstdio>
#include "Empresa.h"
#include "Fecha.h"

using namespace std;

Empresa::Empresa(){
    ncli = 0;
    nmaxcli = 100;

    ncon = 0;
    nmaxcon = 10;

    contratos = new Contrato*[10];
}

Empresa::~Empresa(){
    for(int i = 0; i < ncli; i++)
        delete clientes[i];
    for(int i = 0; i < ncon; i++)
        delete contratos[i];
    delete [] contratos;
}


void Empresa::crearContrato(){
    long int dni;
    cout << "Introduzca el DNI: ";
    cin >> dni;

    int iCliente = -1, i = 0;
    while(i < ncli && iCliente == -1){
        if(clientes[i]->getDni() == dni)
            iCliente = i;
        else i++;
    }

    if(iCliente == -1){
        cout << "El DNI introducido no se corresponde con ningun cliente registrado.\n";
        if(ncli == nmaxcli){
            cout << "No se puede ingresar un nuevo cliente, cantidad maxima alcanzada.\n";
        }else{
            cout << "Se procede a registrar un nuevo cliente.\n";
            iCliente = ncli++;
            char nombre[50];
            int dia, mes, annio;
            cout << "Introduzca el nombre del cliente: ";
            cin.ignore();
            gets(nombre);
            cout << "Introduzca la fecha en que se da de alta (dia, mes y annio): ";
            cin >> dia >> mes >> annio;
            clientes[iCliente] = new Cliente(dni, nombre, Fecha(dia, mes, annio));
            cout << "Cliente creado correctamente.\n\n";
        }
    }else{
        cout << "Cliente seleccionado.\n\n";
    }

    if(iCliente != -1){
        int tipoContrato;
        cout << "Introduzca el tipo de contrato que desea (1. Movil 2. Tarifa Plana): ";
        cin >> tipoContrato;

        int minutosHablados;
        cout << "Introduzca los minutos hablados: ";
        cin >> minutosHablados;
        int dia, mes, annio;
        cout << "Introduzca la fecha del contrato (dia, mes y annio): ";
        cin >> dia >> mes >> annio;
        if(tipoContrato == 1){
            char nac[20];
            cout << "Introduzca la nacionalidad: ";
            cin >> nac;
            float precioMinuto;
            cout << "Introduzca el precio por minuto: ";
            cin >> precioMinuto;
            contratos[ncon++] = new ContratoMovil(dni, Fecha(dia, mes, annio), precioMinuto, minutosHablados, nac);
        }else if(tipoContrato == 2){
            //El contrato de tarifa plana no requiere mas datos
            contratos[ncon++] = new ContratoTP(dni, Fecha(dia, mes, annio), minutosHablados);
        }else{
            cout << "Tipo de contrato invalido.\n Operación cancelada.\n\n";
            delete clientes[iCliente];
            ncli--;
            iCliente = -1;
        }

        //Si la tabla esta llena, la amplio al doble
        if(ncon == nmaxcon){
            nmaxcon *= 2;
            Contrato **tmp = new Contrato*[nmaxcon];
            for(int i = 0; i < ncon; i++)
                tmp[i] = contratos[i];

            delete [] contratos;
            contratos = tmp;
        }
    }

    if(iCliente != -1){
        cout << "\nContrato registrado correctamente.\n\n";
    }
}

bool Empresa::cancelarContrato(int idContrato){
    bool eliminado = false;
    int i = 0;

    while(i < ncon && !eliminado){
        if(contratos[i]->getIdContrato() == idContrato){
            delete contratos[i];
            while(i < ncon-1)
                contratos[i] = contratos[++i];
            ncon--;
            eliminado = true;
        }else i++;
    }

    return eliminado;
}

bool Empresa::bajaCliente(long int dni){
    bool eliminado = false;
    int i = 0;

    while(i < ncon){
        if(contratos[i]->getDniContrato() == dni){
            delete contratos[i];
            int j = i;
            while(j < ncon-1)
                contratos[j] = contratos[++j];
            ncon--;
        }else i++;
    }

    i = 0;
    while(i < ncli && !eliminado){
        if(clientes[i]->getDni() == dni){
            delete clientes[i];
            while(i < ncli-1){
                clientes[i] = clientes[i+1];    //Aqui no le daba la gana de dejarme hacerlo todo una linea
                i++;
            }
            ncli--;
            eliminado = true;
        }else i++;
    }


    return eliminado;
}

int Empresa::descuento(float porcentaje)const{
    int afectados = 0;
    porcentaje = 1 - porcentaje/100;

    for(int i = 0; i < ncon; i++){
        if(typeid(*contratos[i]) == typeid(ContratoMovil)){
            ContratoMovil *c = dynamic_cast<ContratoMovil*>(contratos[i]);
            c->setPrecioMinuto(c->getPrecioMinuto()*porcentaje);
            afectados++;
        }
    }

    return afectados;
}

int Empresa::nContratosTP() const{
    int tarifasPlanas = 0;

    for(int i = 0; i < ncon; i++)
        if(typeid(*contratos[i]) == typeid(ContratoTP))
            tarifasPlanas++;

    return tarifasPlanas;
}

void Empresa::ver() const {
    cout << "La Empresa tiene "<<ncli<<" clientes y "<<ncon<<" contratos" << endl;

    cout << "Clientes:" << endl;
    for(int i = 0; i < ncli; i++)
        cout << *clientes[i] << endl;

    cout << endl << "Contratos:" << endl;
    for(int i = 0; i < ncon; i++){
        contratos[i]->ver();
        cout << " - " << contratos[i]->factura() << "e" << endl;
    }
}



void Empresa::cargarDatos(){
    clientes[ncli++] = new Cliente(75547001, "Peter Lee", Fecha(28, 2, 2001));
    clientes[ncli++] = new Cliente(45999000, "Juan Perez", Fecha(29, 2, 2000));
    clientes[ncli++] = new Cliente(37000017, "Luis Bono", Fecha(31, 1, 2002));

    contratos[ncon++] = new ContratoMovil(75547001, Fecha(28, 2, 2001), 0.12, 110, "DANES");
    contratos[ncon++] = new ContratoMovil(75547001, Fecha(31, 1, 2002), 0.09, 170, "DANES");
    contratos[ncon++] = new ContratoTP(37000017, Fecha(1, 2, 2002), 250);
    contratos[ncon++] = new ContratoTP(75547001, Fecha(28, 2, 2001), 312);
    contratos[ncon++] = new ContratoMovil(45999000, Fecha(31, 1, 2002), 0.10, 202, "ESPANOL");
    contratos[ncon++] = new ContratoMovil(75547001, Fecha(31, 1, 2002), 0.15, 80, "DANES");
    contratos[ncon++] = new ContratoTP(45999000, Fecha(1, 2, 2002), 400);
}
