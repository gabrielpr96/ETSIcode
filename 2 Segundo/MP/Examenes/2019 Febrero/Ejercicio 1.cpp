#include <iostream>
#include <sstream>
#include <cstring>
#include <cstdlib>

using namespace std;

class Fecha{
    int dia;
    char* mes;
    int anio;
public:
    Fecha(int d, char* m, int a){
        dia = d; mes = m; anio = a;
    }
    int getDia(){return this->dia;}
    char* getMes(){return this->mes;}
    int getAnio(){return this->anio;}
};

class Cliente{
    char *nombre, *dni;
    Fecha fecha;
public:
    Cliente(const char *d, const char *nom, Fecha f);
    Cliente(const Cliente& c);
    Fecha getFecha(){return this->fecha;}
};

class ClienteVIP : public Cliente{
public:
    ClienteVIP(const ClienteVIP& c);
};

class ClienteNOVIP : public Cliente{
public:
    ClienteNOVIP(const ClienteNOVIP& c);
};

class Empresa{
    Cliente** clientes;
    int ncli, nmaxcli;
public:
    Empresa();
    static void bajaClientesFecha(Empresa e, Fecha f);
};

void Empresa::bajaClientesFecha(Empresa e, Fecha f){
    for(int i = 0; i < ncli; i++){      //Esto es un metodo estatico, no podemos acceder a ncli, pero si a e.ncli //Si vamos a eliminar clientes no podemos recorrer esa estructura porque ira cambiando. Esto fallaría. Podriamos usar un bucle while e incrementar la i solo si no eliminamos clientes.
        if(e.clientes[i].fecha == f){    //Ese .fecha esta mal, porque al ser un puntero hay que desreferenciar o usar la fecha -> //El operador igualdad para fechas no esta sobracargado y su comportamiento por defecto no hace lo que queremos que haga.
            e.bajaCliente(e.clientes[i]->getDni());
        }
    }
}

int main(){
    Fecha f1(2, "ene", 2017);
    Empresa BBVA;
    Empresa::bajaCLientesFecha(BBVA, f1);
}
