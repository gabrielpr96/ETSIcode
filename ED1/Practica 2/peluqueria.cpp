#include <fstream>
#include <iostream>
#include "peluqueria.h"

using namespace std;

void peluqueria::AbrirPeluqueria(char *nombrefichero){
    ifstream file(nombrefichero, ios::binary);

    if(file){
        int nPeluqueros, nClientes;
        peluquerof pfTMP;
        peluquero pTMP;
        cliente cTMP;

        file.read((char *) &nPeluqueros, sizeof(int));
        cout << "nPeluqueros: " << nPeluqueros << "\n";
        for(int i = 0; i < nPeluqueros; i++){
            file.read((char *) &pfTMP, sizeof(pfTMP));
            strcpy(pTMP.Nombre,pfTMP.Nombre);
            strcpy(pTMP.Apellidos,pfTMP.Apellidos);
            pTMP.Codigo = pfTMP.Codigo;
            pTMP.TipoServicio = pfTMP.TipoServicio;
            L.insertar(i+1, pTMP);
            cout << "\t\tInsertado\n";
        }

        file.read((char *) &nClientes, sizeof(int));
        for(int i = 0; i < nClientes; i++){
            file.read((char *) &cTMP, sizeof(cTMP));
            IncorporarCliente(cTMP);
        }

        cout << "Fichero cargado correctamente.\n";
    }else{
        cout << "No se pudo abrir el fichero.\n";
    }

    file.close();
}
void peluqueria::VolcarPeluqueria(char *nombrefichero){
    ofstream file(nombrefichero, ios::binary);

    if(file){
        int nPeluqueros = L.longitud(), nClientes = 0;
        peluquerof pfTMP;
        peluquero pTMP;
        cliente cTMP;
        cola colaTMP;

        file.write((char *) &nPeluqueros, sizeof(int));
        for(int i = 0; i < nPeluqueros; i++){
            file.seekp(sizeof(int) + sizeof(peluquerof)*i, ios::beg);
            cout << "SEEK: " << (sizeof(int) + sizeof(peluquerof)*i) << "\n";
            pTMP = L.observar(i+1);
            strcpy(pfTMP.Nombre,pTMP.Nombre);
            strcpy(pfTMP.Apellidos,pTMP.Apellidos);
            pfTMP.Codigo = pTMP.Codigo;
            pfTMP.TipoServicio = pTMP.TipoServicio;
            file.write((char *) &pfTMP, sizeof(peluquerof));

            colaTMP.clonar(pTMP.Col);
            while(!colaTMP.esvacia()){
                cTMP = colaTMP.primero();
                file.seekp(sizeof(int) + sizeof(peluquerof)*nPeluqueros + sizeof(int) + sizeof(cliente)*nClientes, ios::beg);
                cout << " SEEK:  " << (sizeof(int) + sizeof(peluquerof)*nPeluqueros + sizeof(int) + sizeof(cliente)*nClientes) << "\n";
                file.write((char *) &cTMP, sizeof(cliente));
                colaTMP.desencolar();
                nClientes+=1;
            }
        }

        cout << "Fichero guardado correctamente.\n";
    }else{
        cout << "No se pudo guardar el fichero.\n";
    }

    file.close();
}
void peluqueria::Mostrar(){
    cout << "Peluqueros activos:\n\n";

    int nPeluqueros = L.longitud();
    peluquero pTMP;
    cola cTMP;
    cliente cliTMP;
    for(int i = 1; i <= nPeluqueros; i++){
        pTMP = L.observar(i);
        cout << "Nombre: " << pTMP.Nombre << "\n"
            << "Apellidos: " << pTMP.Apellidos << "\n"
            << "Tipo de servicio: " << pTMP.TipoServicio << "\n"
            << "Codigo: " << pTMP.Codigo << "\n"
            << "Lista de clientes: \n";

        cTMP.clonar(pTMP.Col);
        while(!cTMP.esvacia()){
            cliTMP = cTMP.primero();
            cTMP.desencolar();

            cout << "\tNombre: " << cliTMP.Nombre << "\n"
                << "\tApellidos: " << cliTMP.Apellidos << "\n"
                << "\tEdad: " << cliTMP.Edad << "\n"
                << "\tHora de llegada: " << (cliTMP.HoraLlegada/60) << ":" << (cliTMP.HoraLlegada%60) << "\n"
                << "\tTipo de servicio: " << cliTMP.TipoServicio << "\n\n"; //Esta linea es redundante
        }

        cout << "-------------------------------\n\n";
    }
}
void peluqueria::IncorporarPeluquero(peluquerof t){
}
bool peluqueria::RetirarPeluquero(int codigo){
}
bool peluqueria::IncorporarCliente(cliente cli){
}
bool peluqueria::EliminarCliente(cadena Nombre, cadena Apelllidos){
}
bool peluqueria::AtenderCliente(int CodigoPeluquero){
}
