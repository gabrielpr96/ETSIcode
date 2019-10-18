#include <iostream>
#include <stdlib.h>
#include <string.h>
#include <stdio.h>

using namespace std;

class Punto{
    int x, y;
    int min() const {return x<y?x:y;}
public:
    Punto(int x, int y){this->x = x; this->y = y;}
    //Metodos que modifican las cordendas
    void setX(int x){this->x = x;}
    void setY(int y){this->y = y;}
    //Metodos que no modifican las cordenadas
    int getX() const {return x;}
    int getY() const {return y;}

    //Metodos sobrecargados con const
    void clona(Punto p){x = p.x; y = p.y;}
    void clona(Punto &p) const {p.x = x; p.y = y;}
    int proce() const {return 0;}
    int proce() {return 1;}
    void proce(int n) const {cout << n << endl;}

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

    cout << p1 << " : " << p2 << " : " << p3 << endl;
    p1.setX(5);
    //p2.setX(5); //Error setX no es const
    p1.clona(p2);   //Ejecuta el que no es const
    p2.clona(p3);   //Ejecuta el que es const
    cout << p1 << " : " << p2 << " : " << p3 << endl;
    cout << p1.proce() << ", " << p2.proce() << endl;   //p1 ejecuta el no const y p2 el const
    p1.proce(10);   //Hambos ejecutan
    p2.proce(15);   //el mismo metodo
    //p1.min();   //Error min es privado

    system("Pause");
    return 0;
}

//Puedes divertirte poniendo inline.
