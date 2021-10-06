#include <iostream>
#include <iomanip>
#include <cstdlib>
#include <fstream>
#include "complejo.h"


using namespace std;

int main(){
    complejo a(1,2), b(3),c(a), e(6,2);//b debe ser  3+0i, c es 1+2i
    const complejo d(-1,-2);
    //cout << fixed << setprecision(2); //mostrar 2 (setprecision) decimales (fixed)

    a.set(a.getr()+1,-1*a.geti());  a.ver();    cout << endl; //a = 2-2i
    b=5+c+a;                        b.ver();    cout << endl; //b = 8+0i
    c=5+c+a+2;                      c.ver();    cout << endl; //c = 10+0i
    c=-c;                           c.ver();    cout << endl; //c =-10+0i
    c=d+1;                          c.ver();    cout << endl; //c = 0-2i
    c=d+c;                          c.ver();    cout << endl; //c =-1-4i

    ++a;       cout << a << endl;    //a =3-2i
    a++;       cout << a << endl;   //a= 4-2i

    int i = (int)a;

    int r = (int)a;             //r= (int) a devuelve la parte realde a (4)
    e.set(8,0);//e = 8+0i
    if (e==b)
        cout << "e y b son iguales \n"; //Deberian serlo
    else
        cout << "e y b son distintos \n";
    if (e==8)
        cout << e<< " es igual a " << 8 << endl;

    b=++a;
    c=b++;
    cout << a << "," << b << "," << c << endl; //a=5-2i, b=6-2i, c=5-2i

    system("PAUSE");  return EXIT_SUCCESS;
    return 0;
}
