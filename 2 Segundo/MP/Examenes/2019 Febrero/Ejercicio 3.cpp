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
    Fecha(int d, const char* m, int a){
        dia = d; anio = a;
        mes = new char[strlen(m)+1];
        strcpy(mes, m);
    }
    Fecha(const Fecha& f) : Fecha(f.dia, f.mes, f.anio){}
    ~Fecha(){
        delete [] mes;
    }
    int getDia() const {return this->dia;}
    const char* getMes() const {return this->mes;}
    int getAnio() const {return this->anio;}
    Fecha& operator=(const Fecha& f){
        dia = f.dia;
        anio = f.anio;
        delete [] mes;
        mes = new char[strlen(f.mes)+1];
        strcpy(mes, f.mes);
    }

    //Solo para probar
    void setMes(const char* m){
        delete [] mes;
        mes = new char[strlen(m)*1];
        strcpy(mes, m);
    }
};


int main(){
    char str[4];
    strcpy(str, "ene");
    Fecha f1(2, str, 2017);
    cout << f1.getMes() << endl; //ene
    strcpy(str, "feb"); //Al tener una copia en otro lugar de la memoria, da igual que modifique esto
    cout << f1.getMes() << endl; //ene

    char* nom = new char[4];
    strcpy(nom, "ene");
    Fecha f2(2, nom, 2017);
    cout << f2.getMes() << endl; //ene
    //strcpy(f2.getMes(), "feb"); //Esto no se puede hacer, porque devuelve un const char*
    strcpy(nom, "feb"); //Igualmente si mofico el que le pase, no ocurre nada porque tiene una copia
    cout << f2.getMes() << endl; //ene

    f1 = f2;    //Ahora el operador de asignacion hace bien su trabajo
    cout << f1.getMes() << f2.getMes() << endl; //eneene
    f1.setMes("feb"); //Aunque cambie el de f1, f2 sigue con su propia memoria
    cout << f1.getMes() << f2.getMes() << endl; //febene

    Fecha f3(f1); //El constructor de copia ahora hace su trabajo correctamente.
    cout << f1.getMes() << f2.getMes() << f3.getMes() << endl; //febenefeb
     f3.setMes("mar"); //Cambio solo el de f3
    cout << f1.getMes() << f2.getMes() << f3.getMes() << endl; //febenemar

    delete [] nom;
    //Da igual que libere esta memoria, los objetos tienen copias.

    const Fecha f4(f1);
    f4.getDia();    //Ahora si que puedo, porque es constante
    f4.getMes();    //Ahora si que puedo, porque es constante
    f4.getAnio();   //Ahora si que puedo, porque es constante

    //No liveran la memoria que tienen, falta el destructor.
    return 0;
}
