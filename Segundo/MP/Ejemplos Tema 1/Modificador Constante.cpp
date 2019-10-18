#include <iostream>
#include <stdlib.h>
#include <string.h>
#include <stdio.h>

using namespace std;

class Otra{
    int x;
public:
    void ini(int x){this->x = x;}
    int getX() const {return x;}
};

class Punto{
    int x, y;
    int min() const {return x<y?x:y;}
public:
    Punto(int x, int y){this->x = x; this->y = y;}
    //Metodos que modifican las cordendas
    void setX(int x){this->x = x;}
    void setY(int y){this->y = y;}
    void clona(Punto p){x = p.x; y = p.y;}
    //Metodos que no modifican las cordenadas
    void en(Punto &p) const {p.x = x; p.y = y;}
    void cambia(Otra &o) const {int n = min(); o.ini(n);}
    int getX() const {return x;}
    int getY() const {return y;}

    //Las conversiones de tipo tambien son constantes
    operator char*() const{
        char salida[30];
        sprintf(salida, "(%i, %i)", x, y);
        return strdup(salida);
    }
};

int main(){
    Punto p1(1,2), p3(2,3);
    const Punto p2(5,4);
    Otra ot;

    cout << p1 << " : " << p2 << " : " << p3 << endl;
    p3.setX(4);
    //p2.setX(100);   //Error setX no es un metodo constante
    p1.clona(p2);
    //p2.clona(p3)    //Error clona no es un metodo constate
    cout << p1 << " : " << p2 << " : " << p3 << endl;
    p2.en(p3);      //en es un metodo constante
    p2.cambia(ot);  //cambia es un metodo constate
    p1.setY(p3.getY()-1);
    p1.setX(ot.getX());
    cout << p1 << " : " << p2 << " : " << p3 << endl;
    //p1.min();       //Error min es privado

    system("Pause");
    return 0;
}
