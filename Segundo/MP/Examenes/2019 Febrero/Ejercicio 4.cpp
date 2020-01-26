#include <iostream>
#include <sstream>
#include <cstring>
#include <cstdlib>
#include <typeinfo>

using namespace std;

class Fecha{
    int dia;
    char* mes;
    int anio;
public:
    Fecha(int d, const char* m, int a){
        dia = d; anio = a;
        mes = new char[strlen(m)+1];
        strcpy(mes, m);
    }
    Fecha(const Fecha& f) : Fecha(f.dia, f.mes, f.anio){}
    ~Fecha(){
        delete [] mes;
    }
    int getDia()const{return this->dia;}
    const char* getMes()const{return this->mes;}
    int getAnio()const{return this->anio;}
    Fecha& operator=(const Fecha& f){
        dia = f.dia;
        anio = f.anio;
        delete [] mes;
        mes = new char[strlen(f.mes)+1];
        strcpy(mes, f.mes);
    }
};

class Cliente{
    char *nombre, *dni;
    Fecha fecha;
public:
    Cliente(const char *d, const char *nom, Fecha f) : fecha(f){
        nombre = new char[strlen(nom)+1];
        strcpy(nombre, nom);
        dni = new char[strlen(d)+1];
        strcpy(dni, d);
    }
    Cliente(const Cliente& c) : Cliente(c.dni, c.nombre, c.fecha){}
    ~Cliente(){
        delete [] nombre;
        delete [] dni;
    }
    Fecha getFecha(){return this->fecha;}

    //Solo para probar
    virtual void ver() const{
        cout << dni << " " << nombre << " " << fecha.getDia() << "/" << fecha.getMes() << "/" << fecha.getAnio();
    }
};

class ClienteVIP : public Cliente{
public:
    ClienteVIP(const ClienteVIP& c) : Cliente(c){}

    //Solo de prueba
    ClienteVIP(const char *d, const char *nom, Fecha f) : Cliente(d, nom, f){}
    void ver() const{
        Cliente::ver();
        cout << " Cliente VIP";
    }
};

class ClienteNOVIP : public Cliente{
public:
    ClienteNOVIP(const ClienteNOVIP& c) : Cliente(c){}

    //Solo de prueba
    ClienteNOVIP(const char *d, const char *nom, Fecha f) : Cliente(d, nom, f){}
    void ver() const{
        Cliente::ver();
        cout << " Cliente NO VIP";
    }
};

class Empresa{
    Cliente** clientes;
    int ncli, nmaxcli;
public:
    Empresa(){
        clientes = new Cliente*[20];
        ncli = 0;
        nmaxcli = 20;
    }
    Empresa(const Empresa& e){
        nmaxcli = e.nmaxcli;
        ncli = e.ncli;
        clientes = new Cliente*[nmaxcli];
        for(int i = 0; i < ncli; i++)
            if(typeid(*e.clientes[i]) == typeid(ClienteVIP))
                clientes[i] = new ClienteVIP(*((ClienteVIP*)e.clientes[i]));
            else if(typeid(*e.clientes[i]) == typeid(ClienteNOVIP))
                clientes[i] = new ClienteNOVIP(*(ClienteNOVIP*)e.clientes[i]);
    }
    ~Empresa(){
        for(int i = 0; i < ncli; i++)
            delete clientes[i];
        delete [] clientes;
    }
    Empresa& veteranos(){
        Empresa *v = new Empresa;

        int menorAnio = -1;
        for(int i = 0; i < ncli; i++)
            if(menorAnio == -1 || clientes[i]->getFecha().getAnio() < menorAnio)
                menorAnio = clientes[i]->getFecha().getAnio();

        for(int i = 0; i < ncli; i++)
            if(clientes[i]->getFecha().getAnio() == menorAnio)
                v->addCliente(*clientes[i]);

        return *v;
    }

    void addCliente(const Cliente& c){
        if(typeid(c) == typeid(ClienteVIP))
            clientes[ncli] = new ClienteVIP((ClienteVIP&)c);
        else if(typeid(c) == typeid(ClienteNOVIP))
            clientes[ncli] = new ClienteNOVIP((ClienteNOVIP&)c);
        else return;

        ncli++;
        if(ncli == nmaxcli){
            nmaxcli *= 2;
            Cliente** tmp = new Cliente*[nmaxcli];
            for(int i = 0; i < ncli; i++)
                tmp[i] = clientes[i];
            delete [] clientes;
            clientes = tmp;
        }
    }

    //Metodo para probar
    void ver(){
        for(int i = 0; i < ncli; i++){
            clientes[i]->ver();
            cout << "\n";
        }
    }
    void pruebaEliminar(){
        delete clientes[0];
        for(int i = 0; i < ncli-1; i++)
            clientes[i] = clientes[i+1];
        ncli--;
    }
};


int main(){
    Empresa BBVA, aux;

    //Prueba
    BBVA.addCliente(ClienteVIP("001", "Rosi de palma", Fecha(1, "ene", 2000)));
    BBVA.addCliente(ClienteNOVIP("002", "Whitney Houston", Fecha(1, "ene", 2010)));
    BBVA.addCliente(ClienteVIP("003", "Mickey Rooney", Fecha(1, "ene", 2000)));
    cout << "BBVA:\n"; BBVA.ver();
    //Fin de prueba

    Empresa OHL = BBVA;

    //Prueba
    OHL.pruebaEliminar();
    cout << "\nOHL tras eliminar uno cualquiera:\n"; OHL.ver();
    cout << "BBVA:\n"; BBVA.ver();
    //Fin de prueba

    aux = BBVA.veteranos();

    //Prueba
    cout << "\naux:\n"; aux.ver();
    aux.pruebaEliminar();
    cout << "aux tras borar uno cualquiera:\n"; aux.ver();
    cout << "BBVA:\n"; BBVA.ver();
    //Fin de prueba
}
