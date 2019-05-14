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

        cout << "Tamano: " << pTMP->Col.longitud() << "\n";
        cTMP.clonar(pTMP->Col);
        cout << "Tamano: " << cTMP.longitud() << "\n";
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

    int pos = posicionCorrecta(t.Codigo);
    L.insertar(pos, pTMP);
    //cout << "Insertado en " << pos << "\n";

    //Ahora hay que calcular cuantos clientes hay del tipo que este nuevo peluquero puede antender
    int nClientes = 0;
    int nPeluquerosCompatibles = 0;
    int nPeluqueros = L.longitud();
    for(int i = 1; i <= nPeluqueros; i++){
        if(L.observar(i).TipoServicio == t.TipoServicio){
            nClientes += L.observar(i).Col.longitud();
            nPeluquerosCompatibles++;
        }
    }
    nClientes /= nPeluquerosCompatibles; // Cuanto le toca a cada uno
    //cout << "Cada uno debe tener " << nClientes << "\n";

    peluquero *pNuevo = &L.observar(pos);
    peluquero *pMayor;
    while(pNuevo->Col.longitud() < nClientes){
        pMayor = &L.observar(peluqueroMasOcupado(t.TipoServicio, t.Codigo));   //Encontrar el mas ocupado
        //cout << "El mas ocupado es " << pMayor->Nombre << "\n";
        pNuevo->Col.encolar(pMayor->Col.primero());   //Mover el cliente
        pMayor->Col.desencolar();
    }
}
bool peluqueria::RetirarPeluquero(int codigo){
    //Primero encontrarlo en la lista
    int pos = buscarPorCodigo(codigo);

    if(pos != -1){
        peluquero *pRetirar = &L.observar(pos);
        int i = 1;
        bool tieneSuplente = false;
        while(!tieneSuplente){
            tieneSuplente = L.observar(i++).TipoServicio == pRetirar->TipoServicio;
        }
        if(tieneSuplente){
            while(!pRetirar->Col.esvacia()){
                L.observar(peluqueroMenosOcupado(pRetirar->TipoServicio, codigo)).Col.encolar(pRetirar->Col.primero());
                pRetirar->Col.desencolar();
            }
            L.eliminar(posicionCorrecta(pRetirar->Codigo));
            return true;
        }
    }

    return false;
}
bool peluqueria::IncorporarCliente(cliente cli){
    //determinar el peluquero con la cola mas corta de su especialidad
    int pSeleccionado = peluqueroMenosOcupado(cli.TipoServicio, -1);
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


int peluqueria::buscarPorCodigo(int Codigo){
    int nClientes = L.longitud(), iPeluquero = -1;
    int i = 1;
    while(i <= nClientes && iPeluquero == -1){
        if(L.observar(i).Codigo == Codigo)
            iPeluquero = i;
        i++;
    }
    return iPeluquero;
}
int peluqueria::posicionCorrecta(int Codigo){
    int nPeluqueros = L.longitud();
    int i = 1, iNuevo = -1;
    while(iNuevo == -1){//Esa es una forma de saber que aun no se ha insertado
        if(L.observar(i).Codigo > Codigo){
            iNuevo = i;
        }else if(i == nPeluqueros){
            iNuevo = i+1;
        }else{
            i++;
        }
    }
    return iNuevo;
}
int peluqueria::peluqueroMasOcupado(int Servicio, int codigoIgnorar){
    int i = 1, nPeluqueros = L.longitud(), iMayor = -1;
    while(i <= nPeluqueros){
        if(L.observar(i).Codigo != codigoIgnorar && L.observar(i).TipoServicio == Servicio &&
           (iMayor == -1 || L.observar(i).Col.longitud() > L.observar(iMayor).Col.longitud()))
            iMayor = i;
        i++;
    }
    return iMayor;
}
int peluqueria::peluqueroMenosOcupado(int Servicio, int codigoIgnorar){
    int i = 1, nPeluqueros = L.longitud(), iMenor = -1;
    while(i <= nPeluqueros){
        if(L.observar(i).Codigo != codigoIgnorar && L.observar(i).TipoServicio == Servicio &&
           (iMenor == -1 || L.observar(i).Col.longitud() < L.observar(iMenor).Col.longitud()))
            iMenor = i;
        i++;
    }
    return iMenor;
}
