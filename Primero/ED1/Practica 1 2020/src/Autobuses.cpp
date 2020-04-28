#include "Autobuses.h"
#include <string.h>

Autobuses::Autobuses(){
    fichero.clear();
    fichero.open("Autobuses.dat",ios::binary | ios::in | ios::out);
    cargado=false;
    tam=0;
    fichero.read((char*)&n,sizeof(int));
    elementos=new autobus[tam];

    //f.seekg(sizeof(int), ios::in);
}

Autobuses::~Autobuses(){
    fichero.close();
    delete [] elementos;
}

void Autobuses::Insertar(autobus a){
    if(!cargado){
        if(Buscar(a.matricula)==-1){
            fichero.seekp(sizeof(int)+(n*sizeof(autobus)),ios::beg);
            fichero.write((char*)&a, sizeof(autobus));
            n++;
            fichero.seekp(0, ios::beg);
            fichero.write((char*)&n, sizeof(int));
        }else cout<<"La matricula ya pertenece a un autobus"<<endl;
    }else{
        if(Buscar(a.matricula)==-1){
            if(n>tam)
                tam=tam+SALTO;
            elementos[n]=a;
            n++;
        }else cout<<"La matrícula ya pertenece a un autobus"<<endl;
    }
}

void Autobuses::Borrar(cadena mat, int pos){
    autobus ab;
    pos=-1;

    if(!cargado){
        if(pos==-1)
            pos=Buscar(mat);
        if(pos<1 || pos>n)
            cout<<"La posicion del autobus no es valida"<<endl;
        else{
            fichero.seekg(sizeof(int), ios::beg);
            fichero.seekp(sizeof(int), ios::beg);
            for(int i=pos;i<n;i++){
                fichero.seekg((i*sizeof(autobus)), ios::beg);
                fichero.read((char*)&ab, sizeof(autobus));
                fichero.seekp(((i-1)*sizeof(autobus)), ios::beg);
                fichero.write((char*)&ab, sizeof(autobus));
            }
            n--;
            fichero.seekp(0, ios::beg);
            fichero.write((char*)&n, sizeof(int));
        }

    }else{
        if(pos==-1)
            pos=Buscar(mat);
        if(pos<1 || pos>n)
            cout<<"La posicion del autobus no es valida"<<endl;
        else{
            for(int i=pos-1;i<n;i++)
                elementos[i]=elementos[i+1];
            n--;
        }
    }
}

void Autobuses::Modificar(autobus a,cadena mat,int pos){
    if(!cargado){
        if(pos==-1)
            pos=Buscar(mat);
        if(pos<1 || pos>n)
            cout<<"No se encuentra ningun autobus en esa posicion"<<endl;
        else{
            fichero.seekp(sizeof(int)+(sizeof(autobus)*(pos-1)), ios::beg);
            fichero.write((char*)&a, sizeof(autobus));
        }
    }else{
        if(pos==-1)
            pos=Buscar(mat);
        if(pos<1 || pos>n)
            cout<<"No se encuentra ningun autobus en esa posicion"<<endl;
        else elementos[pos]=a;
    }
}

int Autobuses::Buscar(cadena mat){
    autobus aux;
    int pos=-1;
    bool encontrado=false;
    int i=1;

    if(!cargado){
        while(!encontrado && i<=n){
            fichero.seekg(sizeof(int)+((i-1)*sizeof(autobus)), ios::beg);
            fichero.read((char *)&aux, sizeof(autobus));
            if(strcmp(aux.matricula,mat)==0){
                encontrado=true;
                pos=i;
            }else i++;
        }
    }else{
        i--;
        while(!encontrado && i<n){
            if(strcmp(elementos[i].matricula,mat)==0){
                encontrado=true;
                pos=i;
            }else i++;
        }
    }
    return pos;
}

void Autobuses::Mostrar(cadena mat, int pos){
    autobus a;

    if(!cargado){
        if(pos==-1){
            pos=Buscar(mat);
            if(pos<1 || pos>n)
                cout<<"No existe esa posicion";
        }
        else{
            fichero.seekg(sizeof(int)+(sizeof(autobus)*(pos-1)), ios::beg);
            fichero.read((char*)&a, sizeof(autobus));
            cout<<"Autobus "<<pos<<endl<<"Matricula: "<<a.matricula<<"\n"
            <<"Conductor: "<<a.conductor<<"\n"<<"Plazas: "<<a.e.plazas<<"\n"
            <<"Deposito(en L): "<<a.e.deposito<<"\n";
            if(a.e.activo==true)
                cout<<"Autobus activo\n";
            else cout<<"Autobus inactivo\n";
            if(a.e.itv==true)
                cout<<"ITV pasada\n";
            else cout<<"ITV no pasada\n";
        }
    }else{
        if(pos==-1)
            pos=Buscar(mat);
            if(pos<0 || pos>n)
                cout<<"No existe esa posicion";
        else{
            cout<<"Autobus "<<pos+1<<endl<<"Matricula: "<<elementos[pos].matricula
            <<"\n"<<"Conductor: "<<elementos[pos].conductor<<"\n"<<"Plazas: "
            <<elementos[pos].e.plazas<<"\n"<<"Deposito(en L): "<<elementos[pos].e.deposito<<"\n";
            if(elementos[pos].e.activo==true)
                cout<<"Autobus activo\n";
            else cout<<"Autobus inactivo\n";
            if(elementos[pos].e.itv==true)
                cout<<"ITV pasada\n";
            else cout<<"ITV no pasada\n";
        }
    }
}

void Autobuses::Listar(){
    autobus a;

    if(!cargado){
        cout<<"-------------------Listado de Autobuses-------------------"<<endl;
        for(int i=0;i<n;i++){
            fichero.seekg(sizeof(int), ios::beg);
            fichero.seekg((i*sizeof(autobus)), ios::cur);
            fichero.read((char*)&a,sizeof(autobus));

            cout<<"AUTOBUS "<<i+1<<"\nMatricula: "<<a.matricula<<"\n"
            <<"Conductor: "<<a.conductor<<"\n"<<"Plazas: "<<a.e.plazas<<"\n"
            <<"Deposito(en L): "<<a.e.deposito<<"\n";
            if(a.e.activo==true)
                cout<<"Autobus activo\n";
            else cout<<"Autobus inactivo\n";
            if(a.e.itv==true)
                cout<<"ITV pasada\n\n";
            else cout<<"ITV no pasada\n\n";
        }
    }else{
        cout<<"-------------------Listado de Autobuses-------------------";
        for(int i=0;i<n;i++){
            cout<<"Autobus "<<i+1<<endl<<"Matricula: "<<elementos[i].matricula
            <<"\n"<<"Conductor: "<<elementos[i].conductor<<"\n"<<"Plazas: "
            <<elementos[i].e.plazas<<"\n"<<"Deposito(en L): "<<elementos[i].e.deposito<<"\n";
            if(elementos[i].e.activo==true)
                cout<<"Autobus activo\n";
            else cout<<"Autobus inactivo\n";
            if(elementos[i].e.itv==true)
                cout<<"ITV pasada\n\n";
            else cout<<"ITV no pasada\n\n";
        }
    }
}

bool Autobuses::Cargar(){
    bool done=false;
    autobus ab;

    if(elementos != NULL) //por si se vuelve a llamar a cargar
        delete [] elementos;
    if(tam<n)
        tam=tam+SALTO;
    fichero.seekg(sizeof(int), ios::beg);
    for(int i=0; i<n; i++){
        fichero.read((char*)&ab, sizeof(autobus));
        elementos[i]=ab;
    }

    if(!fichero.fail()){
        done=true;
        cargado=true;
    }
    return done;
}

bool Autobuses::Guardar(){
    bool retorno=false;
    autobus aux;

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
            fichero.write((char*)&elementos[i], sizeof(autobus));
        retorno=true;
    }
    return retorno;
}

//METODO DE ORDENACION BURBUJA de una clase vector con los elementos enteros
//en el atributo celda. Deberá adaptarlo a su práctica
/*void vector::Burbuja(int inicio, int fin){
    int pos,ele;

    for(pos=inicio; pos<fin; pos++)
        for(ele=fin; ele>pos; ele--)
            if(celda[ele-1]>celda[ele])
                intercambiar(celda[ele-1],celda[ele]);
					//Función genérica que tendrá que ser implementada
        }

}*/

//f.seekp(sizeof(int)+sizeof(autobuses)&n, ios::beg); //se situa al final
