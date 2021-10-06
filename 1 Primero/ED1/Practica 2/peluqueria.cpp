#include <fstream>
#include <iostream>
#include <iomanip>
#include "peluqueria.h"

using namespace std;

peluqueria::peluqueria(){
    //L.vaciar();
}

void printn(char c, int n);
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

        cout << "\tFichero cargado correctamente.\n";
    }else{
        cout << "\tNo se pudo abrir el fichero.\n";
    }

    file.close();
}
void peluqueria::VolcarPeluqueria(char *nombrefichero){
    ofstream file(nombrefichero, ios::binary);

    if(file){
        int nPeluqueros = L.longitud(), nClientes = 0;
        peluquerof pfTMP;
        peluquero *pTMP;
        cliente cTMP;
        cola colaTMP;

        file.write((char *) &nPeluqueros, sizeof(int));
        for(int i = 0; i < nPeluqueros; i++){
            file.seekp(sizeof(int) + sizeof(peluquerof)*i, ios::beg);
            //cout << "SEEK: " << (sizeof(int) + sizeof(peluquerof)*i) << "\n";
            pTMP = &L.observar(i+1);
            strcpy(pfTMP.Nombre,pTMP->Nombre);
            strcpy(pfTMP.Apellidos,pTMP->Apellidos);
            pfTMP.Codigo = pTMP->Codigo;
            pfTMP.TipoServicio = pTMP->TipoServicio;
            file.write((char *) &pfTMP, sizeof(peluquerof));

            colaTMP.clonar(pTMP->Col);
            while(!colaTMP.esvacia()){
                cTMP = colaTMP.primero();
                file.seekp(sizeof(int) + sizeof(peluquerof)*nPeluqueros + sizeof(int) + sizeof(cliente)*nClientes, ios::beg);
                //cout << " SEEK:  " << (sizeof(int) + sizeof(peluquerof)*nPeluqueros + sizeof(int) + sizeof(cliente)*nClientes) << "\n";
                file.write((char *) &cTMP, sizeof(cliente));
                colaTMP.desencolar();
                nClientes+=1;
            }
        }

        file.seekp(sizeof(int) + sizeof(peluquerof)*nPeluqueros, ios::beg);
        file.write((char *) &nClientes, sizeof(int));


        cout << "\tFichero guardado correctamente.\n";
    }else{
        cout << "\tNo se pudo guardar el fichero.\n";
    }

    file.close();
}
void peluqueria::Mostrar(){
    int nPeluqueros = L.longitud();
    peluquero *pTMP;
    cola cTMP;
    cliente cliTMP;
    cadena nTMP;


   // printn(32,5); printn(204,1);printn(205,68);printn(185,1); cout << "\n";
    //printn(32,5); printn(186,1); printn(32,68); printn(186,1); cout << "\n";


    //printn(32,5); printn(186,1); printn(32,68); printn(186,1); cout << "\n";
    //printn(32,5); printn(200,1); printn(205,68); printn(188,1); cout << "\n";

    for(int i = 1; i <= nPeluqueros; i++){
        pTMP = &L.observar(i);

        printn(32,5); printn(201,1); printn(205,68); printn(187,1); cout << "\n";
        strcpy(nTMP, pTMP->Apellidos);
        strcat(nTMP, ", ");
        strcat(nTMP, pTMP->Nombre);
        printn(32,5); printn(186,1); cout << " Peluquero " << setw(2) << pTMP->Codigo << ": " << setw(31) << left << nTMP << "Tipo de servicio: " << pTMP->TipoServicio << "   "; printn(186,1); cout << "\n";
        printn(32,5); printn(204,1);printn(205,68);printn(185,1); cout << "\n";
        printn(32,5); printn(186,1); cout << setw(30) << "  Apellidos y nombre" <<"  "<< setw(10) << "Edad" <<"   "<< setw(10) << "Servicio" <<"   "<< setw(10) << "Llegada   "; printn(186,1); cout << "\n";
        printn(32,5); printn(186,1); printn(32,68); printn(186,1); cout << "\n";

        //cout << "Tamano: " << pTMP->Col.longitud() << "\n";
        cTMP.clonar(pTMP->Col);
        //cout << "Tamano: " << cTMP.longitud() << "\n";
        while(!cTMP.esvacia()){
            cliTMP = cTMP.primero();
            cTMP.desencolar();


            strcpy(nTMP, cliTMP.Apellidos);
            strcat(nTMP, ", ");
            strcat(nTMP, cliTMP.Nombre);
            printn(32,5); printn(186,1); cout << "  " << setw(30) << nTMP <<" "<< setw(2) << cliTMP.Edad <<"             "<< setw(1) << cliTMP.TipoServicio <<"          "<< setw(2) << (cliTMP.HoraLlegada/60) << ":" << setw(2) << setfill('0') << (cliTMP.HoraLlegada%60) << "    " << setfill(' '); printn(186,1); cout << "\n";

        }

        printn(32,5); printn(200,1); printn(205,68); printn(188,1); cout << "\n\n";
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

        int i = 1, nPeluqueros = L.longitud();
        while(i <= nPeluqueros && !(L.observar(i).Codigo != codigo && L.observar(i).TipoServicio == pRetirar->TipoServicio))
            i++;

        if(i <= nPeluqueros || pRetirar->Col.esvacia()){
            while(!pRetirar->Col.esvacia()){
                L.observar(peluqueroMenosOcupado(pRetirar->TipoServicio, codigo)).Col.encolar(pRetirar->Col.primero());
                pRetirar->Col.desencolar();
            }
            L.eliminar(pos);
            return true;
        }
    }

    return false;
}
bool peluqueria::IncorporarCliente(cliente cli){
    //determinar el peluquero con la cola mas corta de su especialidad
    int pSeleccionado = peluqueroMenosOcupado(cli.TipoServicio, -1);

    if(pSeleccionado != -1){
        //Asignarselo
        //cout << "Voy a asignarselo a " << L.observar(pSeleccionado).Nombre << "\n";
        L.observar(pSeleccionado).Col.encolar(cli);

        return true;
    }

    return false;
}
bool peluqueria::EliminarCliente(cadena Nombre, cadena Apelllidos){
    int nPeluqueros = L.longitud();
    peluquero *pTMP;
    cola cTMP;
    bool eliminado = false;
    int i = 1;
    while(i <= nPeluqueros && !eliminado){
        cTMP.vaciar();
        pTMP = &L.observar(i);
        while(!pTMP->Col.esvacia()){
            if(!eliminado && strcmp(pTMP->Col.primero().Nombre, Nombre) == 0 && strcmp(pTMP->Col.primero().Apellidos, Apelllidos) == 0)
                eliminado = true; //Si coincide, no lo copio
            else
                cTMP.encolar(pTMP->Col.primero());
            pTMP->Col.desencolar();
        }
        //Restaurar la cola, sin el eliminado
        pTMP->Col.clonar(cTMP);
        i++;
    }

    return eliminado;
}
bool peluqueria::AtenderCliente(int CodigoPeluquero){
    int pos = buscarPorCodigo(CodigoPeluquero);
    if(pos != -1){
        peluquero *pTMP = &L.observar(pos);
        if(!pTMP->Col.esvacia()){
            pTMP->Col.desencolar();

            return true;
        }

        return false;
    }

    return false;
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
    if(L.esvacia()) return 1;

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
