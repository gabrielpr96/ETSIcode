#include "Itinerarios.h"
#include <string.h>

Itinerarios::Itinerarios(){
    cargado=false;
    elementos = NULL;
}

Itinerarios::~Itinerarios(){
    fichero.close();
    if(elementos != NULL)
        delete [] elementos;
}

void Itinerarios::Insertar(itinerario i){
    if(cargado){
        int pos = Buscar(i.nombre, i.matricula);
        if(pos == -1){
            elementos[n] = i;
            n++;
            controlarArray();
            cout << "Itinerario insertado con exito." << endl;
        }else cout << "El itinerario ya existe." << endl;
    }else cout << "No se han cargado datos." << endl;
}

//Si pos == -1 -> se borra por mat y nom
//Si pos <> -1 -> se borra por pos
void Itinerarios::Borrar(cadena nombrei, cadena mat, int pos){
    if(cargado){
        if(pos == -1)
            pos = Buscar(nombrei, mat);
        if(pos > 0 && pos < n){
            n--;
            for(int i = pos; i < n; i++)
                elementos[i] = elementos[i+1];
            controlarArray();
            cout << "Itinerario borrado correctamente." << endl;
        }else cout << "No se ha encontrado el itinerario." << endl;
    }else cout << "No se han cargado datos." << endl;
}

void Itinerarios::Modificar(itinerario i, cadena nombrei, cadena mat, int pos){
    if(cargado){
        if(pos == -1)
            pos = Buscar(nombrei, mat);
        if(pos > 0 && pos < n){
            elementos[pos] = elementos[pos+1];
            cout << "Itinerario modificado correctamente." << endl;
        }else cout << "No se ha encontrado el itinerario." << endl;
    }else cout << "No se han cargado datos." << endl;
}

int Itinerarios::Buscar(cadena nombrei, cadena mat){
    if(cargado){
        int i = 0;
        while(i < n && !(strcmp(elementos[i].nombre, nombrei) == 0 && strcmp(elementos[i].matricula, mat) == 0))
            i++;
        if(i < n)
            return i;
        else
            return -1;
    }else return -1;
}

void Itinerarios::Mostrar(cadena nombrei, cadena mat, int pos){
    if(cargado){
        if(pos == -1)
            pos = Buscar(nombrei, mat);
        if(pos >= 0 && pos < n){
            mostrarIti(pos);
        }else cout<<"No ha encontrado el autobus a mostrar."<<endl;
    }else cout << "No se han cargado datos." << endl;
}

void Itinerarios::Listar(){
    if(cargado){
        cout << "-------------------Listado de Itinerarios-------------------" << endl;
        for(int i = 0; i < n; i++)
            mostrarIti(i);
    }else cout << "No se han cargado datos." << endl;
}

bool Itinerarios::Cargar(){
    fichero.open("Itinerarios.dat",ios::binary | ios::in | ios::out);
    if(fichero.fail()){
        n = 0;
    }else{
        fichero.seekg(0, ios::end);
        n = fichero.tellg() / sizeof(itinerario);
        tam = n+1;
        elementos = new itinerario[tam];

        fichero.seekg(0, ios::beg);
        for(int i = 0; i < n; i++){
            fichero.read((char*)&elementos[i], sizeof(itinerario));
        }
    }

    bool res = !fichero.fail();
    cargado = res;
    fichero.close();
    return res;
}

bool Itinerarios::Guardar(){
    if(cargado){
        fichero.open("Itinerarios.dat", ios::binary | ios::out);
        fichero.seekp(0, ios::beg);
        for(int i = 0; i < n; i++)
            fichero.write((char*)&elementos[i], sizeof(itinerario));
        bool res = !fichero.fail();
        fichero.close();
        return res;
    }else{
        cout << "No se puede guardar sin nada cargado." << endl;
        return false;
    }
}

int h2m(Hora hora){
    int m = hora.h*60 + hora.m;
    if(!hora.am)
        m += 12*60;
    return m;
}
void Itinerarios::Itinerariosenintervalo(Hora hIni, Hora hFin){
    if(cargado){
        cout << endl << "-------------------Listado de Itinerarios-------------------" << endl;
        int ini = h2m(hIni), fin = h2m(hFin);
        for(int i = 0; i < n; i++)
            if(h2m(elementos[i].inicio) >= ini && h2m(elementos[i].fin) <= fin)
                mostrarIti(i);
    }else cout << "No se han cargado datos." << endl;
}



void Itinerarios::mostrarIti(int pos){
    itinerario iti = elementos[pos];
    cout<< "Itinerario " << (pos+1) << endl
        << "Nombre: " << iti.nombre << endl
        << "Origen: " << iti.origen << endl
        << "Destino: " << iti.destino << endl
        << "Hora de inicio: " << iti.inicio.h << ":" << iti.inicio.m << " " << (iti.inicio.am?"AM":"PM") << endl
        << "Hora de llegada: " << iti.fin.h << ":" << iti.fin.m << " " << (iti.fin.am?"AM":"PM") << endl
        <<"Matricula: " << iti.matricula << endl;
}
void Itinerarios::controlarArray(){
    int nTam = tam;
    if(n == tam)
        nTam += SALTO;
    else if(n == tam-SALTO-1)
        nTam -= SALTO;

    if(nTam != tam){
        tam = nTam;
        itinerario *tmp = new itinerario[tam];
        for(int i = 0; i < n; i++)
            tmp[i] = elementos[i];
        delete [] elementos;
        elementos = tmp;
    }
}
