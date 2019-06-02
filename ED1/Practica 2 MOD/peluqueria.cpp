#include <fstream>
#include <iostream>
#include <iomanip>
#include "peluqueria.h"

using namespace std;

peluqueria::peluqueria(){
    L.vaciar();
    clientes_atendidos = NULL;
    max_codigo_peluquero = 0;
}
peluqueria::~peluqueria(){
    if(clientes_atendidos != NULL)
        delete clientes_atendidos;
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
        }


        if(clientes_atendidos != NULL){
            delete clientes_atendidos;
            clientes_atendidos = NULL;
        }
        max_codigo_peluquero = 0;
        int nPeluReg, nRegCant;
        file.read((char *) &nPeluReg, sizeof(int));
        if(!file.eof()){
            for(int i = 0; i < nPeluReg; i++){
                file.read((char *) &nRegCant, sizeof(int));
                registrarTrabajo(i+1, nRegCant);
            }
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


        file.seekp(0, ios::end);
        file.write((char *) &max_codigo_peluquero, sizeof(int));
        for(int i = 0; i < max_codigo_peluquero; i++)
            file.write((char *) &clientes_atendidos[i], sizeof(int));


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
        while(!(L.observar(i).Codigo != codigo && L.observar(i).TipoServicio == pRetirar->TipoServicio) && i <= nPeluqueros) i++;

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

    cli.Peluquero = pSeleccionado;

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

            registrarTrabajo(CodigoPeluquero, 1);

            return true;
        }

        return false;
    }

    return false;
}

void peluqueria::ClienteMasMayor(){
    cliente cMayor, cTMP;
    peluquero *pMayor = NULL,*pTMP;
    cMayor.Edad = -1;

    int nPeluqueros = L.longitud(), nMayor;
    for(int i = 1; i <= nPeluqueros; i++){
        pTMP = &L.observar(i);
        int j = 0, nClientes = pTMP->Col.longitud();
        while(j < nClientes){
            cTMP = pTMP->Col.primero();
            pTMP->Col.encolar(cTMP);
            pTMP->Col.desencolar();
            if(cTMP.Edad > cMayor.Edad){
                cMayor = cTMP;
                pMayor = pTMP;
                nMayor = j+1;
            }
            j++;
        }
    }

    if(cMayor.Edad == -1)
        cout << "\tNo hay clientes.\n";
    else
        cout << "\tEl cliente mas mayor se llama: " << cMayor.Nombre << " " << cMayor.Apellidos << " y tiene " << cMayor.Edad << " anos.\n"
            << "\tFue registrado a las " << (cTMP.HoraLlegada/60) << ":" << setw(2) << setfill('0') << (cTMP.HoraLlegada%60) << setfill(' ') << ", solicito servicio tipo " << cTMP.TipoServicio << ".\n\n"
            << "\tEl peluquero que le atendera es " << pMayor->Nombre << " " << pMayor->Apellidos << " con codigo " << pMayor->Codigo << ".\n"
            << "\tEste peluquero tiene a " << pMayor->Col.longitud() << " clientes en cola, " << cMayor.Nombre << " es el numero " << nMayor << ".\n\n";
}
void peluqueria::ResumenSituacion(){
    peluquero *pTMP;
    cliente cTMP;

    int nPeluqueros = L.longitud(), nClientes, nTIPO, nSOLICITUDES, nTOTAL = 0;
    peluquero *pTIPO[nPeluqueros];

    for(int tServicio = 1; tServicio <= 3; tServicio++){
        cout << "\t\tTipo de servicio " << tServicio << ".\n\n";

        nTIPO = 0;
        nSOLICITUDES = 0;
        for(int i = 1; i <= nPeluqueros; i++){
            pTMP = &L.observar(i);
            if(pTMP->TipoServicio == tServicio){
                pTIPO[nTIPO] = pTMP;
                nTIPO++;
                nSOLICITUDES += pTMP->Col.longitud();
            }
        }
        nTOTAL += nSOLICITUDES;

        cout << "\tHay " << nTIPO << " peluqueros que ofrecen este tipo y " << nSOLICITUDES << " clientes que lo requieren.\n\n";

        for(int i = 0; i < nTIPO; i++){
            pTMP = pTIPO[i];
            cout << "\tEl peluquero " << pTMP->Codigo << " se llama " << pTMP->Nombre << " " << pTMP->Apellidos << ", tiene " << pTMP->Col.longitud() << " cliente/s.\n"
                << "\tSe llama/n:\n";
            int j = 0, nClientes = pTMP->Col.longitud();
            while(j < nClientes){
                cTMP = pTMP->Col.primero();
                pTMP->Col.encolar(cTMP);
                pTMP->Col.desencolar();
                j++;
                cout << "\t\t" << cTMP.Nombre << " " << cTMP.Apellidos << ".\n";
            }
            cout << "\n";
        }

        cout << "\t-----------------------------------------------\n\n";
    }

    cout << "\n\tEn total hay " << L.longitud() << " peluqueros, para " << nTOTAL << " clientes.\n\n";
}

void peluqueria::VerEstadistica(){
    if(clientes_atendidos == NULL)
        cout << "\tNo hay datos registrados.\n";
    else{
        peluquero *pTMP;
        int nPeluqueros = L.longitud();
        for(int i = 1; i <= nPeluqueros; i++){
            pTMP = &L.observar(i);
            cout << "\t" << pTMP->Nombre << " " << pTMP->Apellidos << " ha atendido a " << (pTMP->Codigo<=max_codigo_peluquero?clientes_atendidos[pTMP->Codigo-1]:0) << " cliente/s.\n\n";
        }
    }
}
void peluqueria::registrarTrabajo(int codigo, int cantidad){
    if(max_codigo_peluquero <= codigo){
        int *clientes_atendidosTMP = new int[codigo];

        if(clientes_atendidos != NULL){
            for(int i = 0; i < max_codigo_peluquero; i++)
                clientes_atendidosTMP[i] = clientes_atendidos[i];
            delete clientes_atendidos;
        }

        clientes_atendidos = clientes_atendidosTMP;
        for(int j = max_codigo_peluquero; j < codigo; j++)
            clientes_atendidos[j] = 0;
        max_codigo_peluquero = codigo;
    }

    clientes_atendidos[codigo-1] += cantidad;
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
