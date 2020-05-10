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
        <<"Matricula: " << iti.matricula << endl << endl;
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


///Modificacion:
void pintarHora(Hora h){
    cout << (h.h<10?"0":"") << h.h << ":" << (h.m<10?"0":"") << h.m << " " << (h.am?"AM":"PM");
}

void Itinerarios::listaitixmatricula(cadena mat){
    if(cargado){
        cout<< "Itinerarios ----------------------------------------------------" << endl
            << "Nombre\tOrigen\tDestino\tSalida\tLlegada" << endl;
        for(int i = 0; i < n; i++)
            if(strcmp(mat, elementos[i].matricula) == 0){
                cout << elementos[i].nombre << "\t" << elementos[i].origen << "\t" << elementos[i].destino << "\t";
                pintarHora(elementos[i].inicio);
                cout << "\t";
                pintarHora(elementos[i].fin);
                cout << endl;
            }
    }else cout << "No se han cargado datos." << endl;
}

void ordenarPorSalida(itinerario *&v, int n){
    int pos,ele;
    for(pos = 0; pos < n; pos++)
        for(ele = n-1; ele > pos; ele--)
            if(h2m(v[ele-1].inicio) > h2m(v[ele].inicio)){
                itinerario tmp = v[ele];
                v[ele] = v[ele-1];
                v[ele-1] = tmp;
            }
}

void Itinerarios::viajeslocalidad(cadena localidad){
    if(cargado){
        itinerario *tmp = new itinerario[n];
        int nTmp = 0;

        for(int i = 0; i < n; i++){
            if(strcmp(elementos[i].origen, localidad) == 0){
                tmp[nTmp] = elementos[i];
                nTmp++;
            }
        }
        ordenarPorSalida(tmp, nTmp);

        cout<< "Origen: " << localidad << endl
            << "------------------------------------------" << endl
            << "Matricula\tDestino\tSalida\tLlegada" << endl
            << "---------\t-------\t------\t-------" << endl;
        for(int i = 0; i < nTmp; i++){
            cout << tmp[i].matricula << "\t\t" << tmp[i].destino << "\t" << "\t";
            pintarHora(tmp[i].inicio);
            cout << "\t";
            pintarHora(tmp[i].fin);
            cout << endl;
        }
        cout << endl;

        nTmp = 0;
        for(int i = 0; i < n; i++){
            if(strcmp(elementos[i].destino, localidad) == 0){
                tmp[nTmp] = elementos[i];
                nTmp++;
            }
        }
        ordenarPorSalida(tmp, nTmp);

        cout<< "Destino: " << localidad << endl
            << "------------------------------------------" << endl
            << "Matricula\tOrigen\tSalida\tLlegada" << endl
            << "---------\t------\t------\t-------" << endl;
        for(int i = 0; i < nTmp; i++){
            cout << tmp[i].matricula << "\t\t" << tmp[i].origen << "\t" << "\t";
            pintarHora(tmp[i].inicio);
            cout << "\t";
            pintarHora(tmp[i].fin);
            cout << endl;
        }

        delete [] tmp;
    }else cout << "No se han cargado datos." << endl;
}
