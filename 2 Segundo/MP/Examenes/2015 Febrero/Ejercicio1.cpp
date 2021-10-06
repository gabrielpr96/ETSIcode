#include <iostream>
#include <sstream>
#include "examen.h"

using namespace std;

int main() {
    const Clase1 x(1);
    Clase1 xx(11);
    const Clase2 y(2);
    Clase2 yy(2);
    const Clase3 z(3);
    Clase3 zz(33), zzz(zz);
    cout << "x: " << x << endl;
    cout << "y: " << y << endl;
    cout << "z: " << z << endl;
    xx=xx;
    xx=x;
    yy=y;
    zz=zz;
    zz=z;
    cout << "xx: " << xx << endl;
    cout << "yy: " << yy << endl;
    cout << "zz: " << zz << endl;
    cout << "zzz: " << zzz << endl;
    cout <<"\n--------------\n";
    Clase1 *p=new Clase3(333);
    delete p;
    cout <<"\n--------------\n";
    p=new Clase2(222);
    delete p;
    cout <<"\n--------------\n";
}
