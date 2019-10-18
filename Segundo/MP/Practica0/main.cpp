#include <iostream>
#include <iomanip>
#include <cstdlib>
#include <fstream>
#include "complejo.h"


using namespace std;

int main(){
    complejo a(1,2), b(3,4) ,c(1,-3), d(-1,-2), e(6,2);

    a.set(a.getr()+1,-1*a.geti());
    a.ver(); cout << endl; //a = 2-2i
    cout << "a.real vale " << a.getr()<< "\n"; //a.real vale 2
    b=c+d;      b.ver(); cout << endl; //b = 0-5i
    b=5+c+a+2;  b.ver(); cout << endl; //b =10-5i
    b=3+c+a;    b.ver(); cout << endl; //b = 6-5i
    b=-3*2+b;   b.ver(); cout << endl; //b = 0-5i
    c=5+c+a+2;  c.ver(); cout << endl; //c = 10-5i
    a=-d;       a.ver(); cout << endl; //a = 1+2i
                d.ver(); cout << endl; //d =-1-2i
    c=-c;       c.ver(); cout << endl; //c =-10+5i
    a.set();   //insertar por teclado el complejo 7-2i
    a.ver(); cout << endl;//a = 7-2i
    cout << "a vale "<< a << "\n"; //a vale 7-2i

    ofstream salida("prueba.txt");
    if (!salida.fail()) {
        salida << "a vale " << a << "\n";
        salida << "b vale " << b << "\n";
        salida << "c vale " << c << "\n";
    }
    salida.close();

    complejo z(1,3), s(1,1);
    cout << s << ", " << z << endl; //1+1i, 1+3i
    z=-z; cout << "z = " << z << endl; //z = -1-3i
    z=2+z;cout << "z = " << z << endl; //z = 1-3i
    z=-z; cout << "z = " << z << endl; //z = -1+3i
    z.set(2,0); cout << "z = " << z << endl; //z = 2+0i
    z=-z; cout << "z = " << z << endl; //z = -2+0i
    z.set(0,2); cout << "z = " << z << endl; //z = 0+2i
    z=-z; cout << "z = " << z << endl; //z = 0-2i

    system("PAUSE");  return EXIT_SUCCESS;

    return 0;
}
