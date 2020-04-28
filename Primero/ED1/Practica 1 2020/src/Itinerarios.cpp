#include "Itinerarios.h"
#include <string.h>

Itinerarios::Itinerarios(){
    itinerario iti;
    fichero.clear();
    fichero.open("Itinerarios.dat", ios::binary|ios::in|ios::out);
    cargado=false;
    tam=0;
    fichero.seekg(0, ios::end);
    n=fichero.tellg()/sizeof(itinerario);

    elementos=new itinerario[tam];
}

Itinerarios::~Itinerarios(){
    fichero.close();
    delete [] elementos;
}

void Itinerarios::Insertar(itinerario i){
    if(cargado){
        if(Buscar(i.nombre, i.matricula)==-1){
            if(n==tam)
                tam=tam+SALTO;
            elementos[n]=i;
            n++;
        }else cout<<"El itinerario ya existe"<<endl;
    }
}

void Itinerarios::Borrar(cadena nombrei, cadena mat, int pos){
    if(cargado){
        if(pos==-1){
            pos=Buscar(nombrei, mat);
            if(pos<0 || pos>n)
                cout<<"La posicion del itinerario no es valida"<<endl;
        }
        else{
            for(int i=pos-1;i<n;i++)
                elementos[i]=elementos[i+1];
            n--;
        }
    }
}

void Itinerarios::Modificar(itinerario i, cadena nombrei, cadena mat, int pos){
    if(cargado){
        if(pos==-1){
            pos=Buscar(nombrei, mat);
            if(pos<0 || pos>n)
                cout<<"No se encuentra ningun itinerario en esa posicion"<<endl;
            //else elementos[pos]=i;
        }else elementos[pos]=i;
    }
}

int Itinerarios::Buscar(cadena nombrei, cadena mat){
    itinerario aux;
    int pos=-1;
    bool encontrado=false;
    int i=0;

    if(cargado){
        while(!encontrado && i<n){
            if(strcmp(elementos[i].matricula, mat)==0 && strcmp(elementos[i].nombre, nombrei)==0){
                encontrado=true;
                pos=i;
            }else i++;
        }
    }
    return pos;
}

void Itinerarios::Mostrar(cadena nombrei, cadena mat, int pos){
    itinerario iti;

    if(cargado){
        if(pos==-1){
            pos=Buscar(nombrei, mat);
            if(pos<0 || pos>n)
                cout<<"No existe esa posicion"<<endl;
        }
        else{
            cout<<"ITINERARIO "<<pos+1<<"\n"<<"Nombre: "<<elementos[pos].nombre<<"\n"
            <<"Origen: "<<elementos[pos].origen<<"\n"<<"Destino: "<<elementos[pos].destino<<"\n"
            <<"Hora de inicio: "<<elementos[pos].inicio.h<<":"<<elementos[pos].inicio.m<<" ";
            if(elementos[pos].inicio.am==true) cout<<"AM"<<endl;
            else cout<<"PM"<<endl;
            cout<<"Hora de llegada: "<<elementos[pos].fin.h<<":"<<elementos[pos].fin.m<<" ";
            if(elementos[pos].fin.am==true) cout<<"AM"<<endl;
            else cout<<"PM"<<endl;
            cout<<"Matricula: "<<elementos[pos].matricula<<endl<<endl;
        }
    }
}

void Itinerarios::Listar(){
    itinerario it;

    if(cargado){
        cout<<"-------------------Listado de Itinerarios-------------------\n";
        for(int i=0;i<n;i++){
            cout<<"ITINERARIO "<<i+1<<"\n"<<"Nombre: "<<elementos[i].nombre<<"\n"
            <<"Origen: "<<elementos[i].origen<<"\n"<<"Destino: "<<elementos[i].destino<<"\n"
            <<"Hora de inicio: "<<elementos[i].inicio.h<<":"<<elementos[i].inicio.m<<" ";
            if(elementos[i].inicio.am==true) cout<<"AM"<<endl;
            else cout<<"PM"<<endl;
            cout<<"Hora de llegada: "<<elementos[i].fin.h<<":"<<elementos[i].fin.m<<" ";
            if(elementos[i].fin.am==true) cout<<"AM"<<endl;
            else cout<<"PM"<<endl;
            cout<<"Matricula: "<<elementos[i].matricula<<endl<<endl;
        }
    }
}

bool Itinerarios::Cargar(){
    bool retorno=false;
    itinerario aux;

    if(elementos!=NULL)
        delete []elementos;
    if(tam<n)
        tam=tam+SALTO;
    fichero.seekg(0, ios::beg);

    for(int i=0;i<n;i++){
        fichero.read((char*)&aux,sizeof(itinerario));
        elementos[i]=aux;
    }
    if(!fichero.fail()){
            retorno=true;
            cargado=true;
    }

    return retorno;
}

bool Itinerarios::Guardar(){
    bool retorno=false;
    itinerario aux;
    //MODIFICAR
    for(int i=0; i<n;i++){
        for(int j=n; j>i;j--){
            if(strcmp(elementos[i].matricula, elementos[j].matricula)<0){
                aux=elementos[i];
                elementos[i]=elementos[j];
                elementos[j]=aux;
            }
        }
    }
    if(cargado==true){
        fichero.seekp(sizeof(int), ios::beg);
        for(int i=1; i<=n; i++)
            fichero.write((char*)&elementos[i], sizeof(itinerario));
        retorno=true;
    }
    return retorno;
}

void Itinerarios::Itinerariosenintervalo(Hora horaini, Hora horafin){
    int hmin, hmax;
    int minp, maxp;

    if(horaini.am==true)
        hmin=(horaini.h*60)+horaini.m;
    else hmin=(horaini.h*60)+horaini.m+(12*60);
    if(horafin.am==true)
        hmax=(horafin.h*60)+horafin.m;
    else hmax=(horafin.h*60)+horafin.m+(12*60);

    for(int i=0; i<n; i++){
        if(elementos[i].inicio.am=true)
            minp=(elementos[i].inicio.h*60)+elementos[i].inicio.m;
        else minp=(elementos[i].inicio.h*60)+elementos[i].inicio.m+(12*60);
        if(elementos[i].fin.am=true)
            maxp=(elementos[i].fin.h*60)+elementos[i].fin.m;
        else maxp=(elementos[i].fin.h*60)+elementos[i].fin.m+(12*60);
        if(minp>hmin && maxp<hmax)
            Mostrar(elementos[i].nombre, elementos[i].matricula, -1);
    }
}
