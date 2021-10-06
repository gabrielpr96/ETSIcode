#include "Autobuses.h"
#include <string.h>
#include <stdlib.h>

Autobuses::Autobuses(){
    fichero.open("Autobuses.dat", ios::binary | ios::in | ios::out);
    if(fichero.fail()){
        fichero.open("Autobuses.dat", ios::binary | ios::out);
        fichero.close();
        fichero.open("Autobuses.dat", ios::binary | ios::in | ios::out);
        n = 0;
        fichero.write((char*)&n, sizeof(int));
    }else
        fichero.read((char*)&n,sizeof(int));

    cargado=false;
    tam=SALTO;
    elementos=new autobus[tam];
}

Autobuses::~Autobuses(){
    fichero.close();
    delete [] elementos;
}

void Autobuses::Insertar(autobus a){
    if(Buscar(a.matricula) == -1){
        if(cargado){
            elementos[n]=a;
            n++;
            controlarArray();
        }else{
            fichero.seekp(sizeof(int)+(n*sizeof(autobus)), ios::beg);
            fichero.write((char*)&a, sizeof(autobus));
            n++;
            fichero.seekp(0, ios::beg);
            fichero.write((char*)&n, sizeof(int));
        }
        cout << "Insertado correctamente." << endl;
    }else cout << "La matrícula ya pertenece a un autobus" << endl;
}

//Si pos == -1 -> se borra por mat
//Si pos <> -1 -> se borra por pos
void Autobuses::Borrar(cadena mat, int pos){
    if(pos == -1)
        pos = Buscar(mat);

    if(pos < 0 || pos > n)
        cout << "No ha encontrado el autobus a eliminar." << endl;
    else{
        n--;
        if(cargado){
            for(int i = pos; i < n; i++)
                elementos[i] = elementos[i+1];
            controlarArray();
        }else{
            autobus bus;
            for(int i = pos; i < n; i++){
                fichero.seekg(sizeof(int)+((i+1)*sizeof(autobus)), ios::beg);
                fichero.read((char*)&bus, sizeof(autobus));
                fichero.seekp(sizeof(int)+(i*sizeof(autobus)), ios::beg);
                fichero.write((char*)&bus, sizeof(autobus));
            }
            fichero.seekp(0, ios::beg);
            fichero.write((char*)&n, sizeof(int));
        }
        cout << "Autobus eliminado correctamente." << endl;
    }
}

void Autobuses::Modificar(autobus a, cadena mat, int pos){
    if(pos == -1)
        pos = Buscar(mat);

    if(pos < 0 || pos > n)
        cout << "No ha encontrado el autobus a modificar." << endl;
    else{
        if(cargado){
            elementos[pos] = a;
        }else{
            fichero.seekp(sizeof(int)+(sizeof(autobus)*pos), ios::beg);
            fichero.write((char*)&a, sizeof(autobus));
        }
        cout << "Autobus modificado correctamente." << endl;
    }
}

int Autobuses::Buscar(cadena mat){
    int i = 0;

    if(cargado){
        while(i < n && strcmp(elementos[i].matricula, mat) != 0)
            i++;
    }else{
        autobus tmp;
        fichero.seekg(sizeof(int), ios::beg);
        while(i < n && fichero.read((char *)&tmp, sizeof(autobus)) && strcmp(tmp.matricula, mat) != 0)
            i++;
        fichero.clear();
    }
    if(i < n)
        return i;
    else
        return -1;
}

void Autobuses::Mostrar(cadena mat, int pos){
    autobus tmp;

    if(pos == -1)
        pos = Buscar(mat);

    if(pos < 0 || pos >= n)
        cout<<"No ha encontrado el autobus a mostrar."<<endl;
    else{
        if(cargado){
            tmp = elementos[pos];
        }else{
            fichero.seekg(sizeof(int)+(sizeof(autobus)*pos), ios::beg);
            fichero.read((char*)&tmp, sizeof(autobus));
        }
        mostrarBus(tmp, pos);
    }
}

void Autobuses::Listar(){
    cout << "-------------------Listado de Autobuses-------------------" << endl;

    if(cargado){
        for(int i=0; i < n; i++){
            mostrarBus(elementos[i], i);
            cout << endl;
        }
    }else{
        autobus tmp;
        fichero.seekg(sizeof(int), ios::beg);
        cout << "N " << n << " int " << sizeof(int) << " bus " << sizeof(autobus) <<  endl;
        for(int i=0; i < n; i++){
            fichero.read((char*)&tmp, sizeof(autobus));
            mostrarBus(tmp, i);
        }
        fichero.clear();
    }
}

bool Autobuses::Cargar(){
    fichero.seekg(0, ios::beg);
    fichero.read((char*)&n,sizeof(int));
    for(int i = 0; i < n; i++){
        fichero.read((char*)&elementos[i], sizeof(autobus));
        controlarArray();
    }

    if(fichero.fail()){
        fichero.clear();
        cargado = false;
        return false;
    }else{
        cargado = true;
        return true;
    }
}

bool Autobuses::Guardar(){
    if(cargado){
        int pos,ele;
        for(pos = 0; pos < n; pos++)
            for(ele = n-1; ele > pos; ele--)
                if(strcmp(elementos[ele-1].matricula, elementos[ele].matricula) > 0){
                    autobus tmp = elementos[ele];
                    elementos[ele] = elementos[ele-1];
                    elementos[ele-1] = tmp;
                }

        fichero.seekp(0, ios::beg);
        fichero.write((char*)&n, sizeof(int));
        for(int i = 0; i < n; i++)
            fichero.write((char*)&elementos[i], sizeof(autobus));
        fichero.clear();
        return true;
    }else{
        cout << "No se puede guardar sin nada cargado. Estas operando con el fichero directamente." << endl;
        return false;
    }
}


void Autobuses::mostrarBus(autobus bus, int pos){
    cout<< "Autobus " << (pos+1) << endl
        << "Matricula: " << bus.matricula << endl
        << "Conductor: " << bus.conductor << endl
        << "Plazas: " << bus.e.plazas << endl
        << "Deposito(en L): " << bus.e.deposito << endl
        << "Autobus " << (bus.e.activo?"activo":"inactivo") << endl
        << "ITV " << (bus.e.itv?"pasada":"no padasa") << endl << endl;
}
void Autobuses::controlarArray(){
    int nTam = tam;
    if(n == tam)
        nTam += SALTO;
    else if(n == tam-SALTO-1)
        nTam -= SALTO;

    if(nTam != tam){
        tam = nTam;
        autobus *tmp = new autobus[tam];
        for(int i = 0; i < n; i++)
            tmp[i] = elementos[i];
        delete [] elementos;
        elementos = tmp;
    }
}
