#include <fstream>
#include <iostream>
#include "peluqueria.h"

using namespace std;

peluqueria::peluqueria(){
    L.vaciar();
}

void peluqueria::AbrirPeluqueria(char *nombrefichero){
    ifstream file(nombrefichero, ios::binary);

    if(file){
        L.vaciar();

        int nPeluqueros, nClientes;
        peluquerof pfTMP;
        peluquero pTMP;
        cliente cTMP;

        file.read((char *) &nPeluqueros, sizeof(int));
        //cout << "nPeluqueros: " << nPeluqueros << "\n";
        for(int i = 0; i < nPeluqueros; i++){
            file.read((char *) &pfTMP, sizeof(pfTMP));
            strcpy(pTMP.Nombre,pfTMP.Nombre);
            strcpy(pTMP.Apellidos,pfTMP.Apellidos);
            pTMP.Codigo = pfTMP.Codigo;
            pTMP.TipoServicio = pfTMP.TipoServicio;
            L.insertar(i+1, pTMP);
        }

        file.read((char *) &nClientes, sizeof(int));
        for(int i = 0; i < nClientes; i++){
            file.read((char *) &cTMP, sizeof(cTMP));
            IncorporarCliente(cTMP);
            //cout << "Incorporado\n";
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
    peluquero *pTMP;
    cola cTMP;
    cliente cliTMP;
    for(int i = 1; i <= nPeluqueros; i++){
        pTMP = &L.observar(i);
        cout << "Nombre: " << pTMP->Nombre << "\n"
            << "Apellidos: " << pTMP->Apellidos << "\n"
            << "Tipo de servicio: " << pTMP->TipoServicio << "\n"
            << "Codigo: " << pTMP->Codigo << "\n"
            << "Lista de clientes: \n";

        cTMP.clonar(pTMP->Col);
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
    peluquero pTMP;
    pTMP.Codigo = t.Codigo;
    pTMP.TipoServicio = t.TipoServicio;
    strcpy(pTMP.Nombre, t.Nombre);
    strcpy(pTMP.Apellidos, t.Apellidos);

    int nPeluqueros = L.longitud();
    int i = 1, iNuevo = -1;
    while(i <= nPeluqueros && nPeluqueros == L.longitud()){//Esa es una forma de saber que aun no se ha insertado
        if(L.observar(i).Codigo > t.Codigo){
            iNuevo = i;
        }else if(i == nPeluqueros){
            iNuevo = i+1;
        }else{
            i++;
        }
        if(iNuevo != -1)
            L.insertar(iNuevo, pTMP);
    }

    //Ahora hay que calcular cuantos clientes hay del tipo que este nuevo peluquero puede antender
    int nClientes = 0;
    int nPeluquerosCompatibles = 0;
    for(int i = 1; i <= nPeluqueros; i++){
        if(L.observar(i).TipoServicio == t.TipoServicio){
            nClientes += L.observar(i).Col.longitud();
            nPeluquerosCompatibles++;
        }
    }
    nClientes /= nPeluquerosCompatibles; // Cuanto le toca a cada uno

    i = 1;
    while(L.observar(iNuevo).Col.longitud() < nClientes){
        if(L.observar(i).TipoServicio == t.TipoServicio && i != iNuevo){
            L.observar(iNuevo).Col.encolar(L.observar(i).Col.primero());
            L.observar(i).Col.desencolar();
        }
        i++;
        if(i == nPeluqueros) //Ojo con no meterle sus propios clientes
            i = 1;
    }
}
bool peluqueria::RetirarPeluquero(int codigo){
    //Primero encontrarlo en la lista
    int nClientes = L.longitud(), iEliminar = -1;
    int i = 1;
    while(i <= nClientes && iEliminar == -1){
        if(L.observar(i).Codigo == codigo)
            iEliminar = i;
        i++;
    }

    if(iEliminar != -1){//Comprobar que no sea el unico de su especialidad
            bool tieneSuplente = false;
        if(iEliminar != -1){//Reasignar sus clientes
            i = 1;
            while(!L.observar(iEliminar).Col.esvacia()){
                i++;
            }
            return true;
        }
    }
    return false;
}
bool peluqueria::IncorporarCliente(cliente cli){
    //determinar el peluquero con la cola mas corta de su especialidad
    int pSeleccionado = -1;

    int nPeluqueros = L.longitud();
    for(int i = 1; i <= nPeluqueros; i++){
        if(L.observar(i).TipoServicio == cli.TipoServicio && (pSeleccionado == -1 || L.observar(i).Col.longitud() < L.observar(pSeleccionado).Col.longitud()))
            pSeleccionado = i;
    }

    cout << "pSeleccionado: " << pSeleccionado << "\n";
    if(pSeleccionado != -1){
        //Asignarselo
        cout << "Voy a asignarselo a " << L.observar(pSeleccionado).Nombre << "\n";
        L.observar(pSeleccionado).Col.encolar(cli);
    }
}
bool peluqueria::EliminarCliente(cadena Nombre, cadena Apelllidos){
}
bool peluqueria::AtenderCliente(int CodigoPeluquero){
}
