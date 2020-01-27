#include <iostream>
#include <sstream>
#include <cstring>
#include <cstdlib>

using namespace std;

class Comercial{
    static int contador;
    static float base;
    char* nombre;
    const int cod_comercial;
public:
    Comercial(char* nom) : cod_comercial(contador++){
        nombre = new char[strlen(nom)+1];
        strcpy(nombre, nom);
    }
    Comercial(const Comercial& c) : Comercial(c.nombre){}
    virtual ~Comercial(){
        delete [] nombre;
    }
    const char* getNombre() const {return nombre;}
    virtual float nomina() const {return base;}
    int codComercial() const {return cod_comercial;}
    virtual void ver() const {cout << cod_comercial << ": " << nombre << ", nomina: " << nomina();}

    static void setBase(float b){
        if(b >= 150)
            base = b;
    }
    static float getBase(){
        return base;
    }
    virtual void nada() = 0;
};

float Comercial::base = 200;
int Comercial::contador = 1;

class ComercialTP : public Comercial{
    int horas;
public:
    ComercialTP(char* nom, int h) : Comercial(nom){
        horas = h;
    }
    //No implemento el constructor por copia porque no tiene memoria dinamica, por que genera el compilador esta bien
    //No implemento destructor porque no tiene memoria dinamica
    float nomina() const {return Comercial::nomina()+horas*10;}
    virtual void ver() const {Comercial::ver(); cout << ", horas: " << horas;}
    void nada(){;}
};

class ComercialTC : public Comercial{
    int antiguedad;
public:
    ComercialTC(char* nom, int a) : Comercial(nom){
        antiguedad = a;
    }
    //No implemento el constructor por copia porque no tiene memoria dinamica, por que genera el compilador esta bien
    //No implemento destructor porque no tiene memoria dinamica
    float nomina() const {return Comercial::nomina()+100*10+20*(antiguedad/3);}
    virtual void ver() const {Comercial::ver(); cout << ", antiguedad: " << antiguedad;}
    void nada(){;}
};

int main() {
    ComercialTC t("Pepe", 5);
    t.ver();
    return 0;
}
