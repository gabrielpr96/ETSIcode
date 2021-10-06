#ifndef EXAMEN_H_INCLUDED
#define EXAMEN_H_INCLUDED

#include <iostream>
#include <sstream>

using namespace std;
class Clase1 {
 static int n; //para saber cuantos objetos de tipo Clase3 se crean
 int i;
public:
    Clase1(int x){ //el parámetro x es obligatorio
        n++;
        i = x;
    }
    Clase1(const Clase1& c) : Clase1(c.i){
    }
    virtual ~Clase1(){
        n--;
    }
    int getI() const {return i;;}
    void setI(int i){this->i=i;}


    friend ostream& operator<<(ostream &s, const Clase1 &c) {
        s << "n: " << c.n << " i: " << c.i;
        return s;
    }
};

int Clase1::n = 0;

class Clase2: public Clase1 {
 int tamanioMax;
 int tamanio;
 int *tabla; //el constructor crea la tabla con un tamaño indicado por parámetro o 10 si no se indica
public:
    Clase2(int x, int n = 10) : Clase1(x){
        tamanio = 0;
        tamanioMax = n;
        tabla = new int[n];
    }
    Clase2(const Clase2& c) :Clase1(c.getI()) {
        tamanioMax = c.tamanioMax;
        tabla = new int[c.tamanioMax];
        tamanio = c.tamanio;
        for(int i = 0; i < tamanio; i++)
            tabla[i] = c.tabla[i];
    }
    ~Clase2(){
        delete [] tabla;
    }
    Clase2& operator=(const Clase2& c){
        if (this != &c) {
            Clase1::operator=(c);
            delete [] tabla;
            tamanioMax = c.tamanioMax;
            tabla = new int[c.tamanioMax];
            tamanio = c.tamanio;
            for(int i = 0; i < tamanio; i++)
                tabla[i] = c.tabla[i];
        }
        return *this;
    }


    friend ostream& operator<<(ostream &s, const Clase2 &c) {
        s << (Clase1 &)c << " tamanioMax: " << c.tamanioMax;
        s << " tamanio: " << c.tamanio << " { ";
        for(int i=0; i<c.tamanio; i++)
        s << c.tabla[i] << " ";
        s << " }";
        return s;
    }
};

class Clase3: public Clase2 {
 const int z;
 Clase1 y;
public:
    Clase3(int zz) : Clase2(0), z(zz), y(0){
        int i;
        cin >> i;
        y.setI(i);
        cin >> i;
        setI(i);
    }
    Clase3& operator=(const Clase3& c){
        if (this != &c) {
            Clase2::operator=(c);
            y = c.y;
        }
        return *this;
    }


    friend ostream& operator<<(ostream &s, const Clase3 &c) {
        s << (Clase2 &)c << " z: " << c.z << " y(" << c.y << ")";
        return s;
    }
};

#endif // EXAMEN_H_INCLUDED
