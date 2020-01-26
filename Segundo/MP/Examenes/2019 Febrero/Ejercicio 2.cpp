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


int main(){
    const char* cstr = "Hey";
    //Fecha f0(2, cstr, 2017); //Esto no se puede hacer, porque no acepta const char*, pero eso es auxiliar.

    char str[4];
    strcpy(str, "ene");
    Fecha f1(2, str, 2017);
    cout << f1.getMes() << endl; //ene
    strcpy(str, "feb"); //Al no hacer copia de la cadena que se le pasa y al no ser const, la puedo cambiar aqui mismo
    cout << f1.getMes() << endl; //feb

    char* nom = new char[4];
    strcpy(nom, "ene");
    Fecha f2(2, nom, 2017);
    cout << f2.getMes() << endl; //ene
    strcpy(f2.getMes(), "feb"); //Al devolver un char*, puedo cambiar su contenido.
    cout << f2.getMes() << endl; //feb

    f1 = f2;    //El operador asignacion no esta sobrescirto, así que esto deja el puntero de mes de f1 huerfano.
    cout << f1.getMes() << f2.getMes() << endl; //febfeb
    strcpy(f1.getMes(), "ene"); //Como son una copia binaria, ambos apuntan al mismo sitio.
    cout << f1.getMes() << f2.getMes() << endl; //eneene

    Fecha f3(f1); //El constructor de copia tampoco esta sobrescrito y el de oficio no lo hacec bien con una copia binaria.
    cout << f1.getMes() << f2.getMes() << f3.getMes() << endl; //eneeneene
    strcpy(f1.getMes(), "feb"); //Como son una copia binaria, todos apuntan al mismo sitio.
    cout << f1.getMes() << f2.getMes() << f3.getMes() << endl; //febfebfeb

    delete [] nom;  //Como todos tienen este puntero, si libero la memoria, tendrian un puntero a una posición no reservada.

    const Fecha f4(f1);
    //f4.getDia(); //No puedo ejecutar este metodo porque no es constante
    //f4.getMes(); //No puedo ejecutar este metodo porque no es constante
    //f4.getAnio(); //No puedo ejecutar este metodo porque no es constante

    //No liveran la memoria que tienen, falta el destructor.
    return 0;
}
