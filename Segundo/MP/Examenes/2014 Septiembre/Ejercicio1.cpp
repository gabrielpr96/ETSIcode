#include <iostream>
#include "numero.cpp"

using namespace std;

bool mayor(Numeros a, Numeros b) {
    if (a.getN() > b.getN())
    return true;
    return false;
}

int main() {
    Numeros a(6, 0, 2), b(3, 1, 4), c=a;
    a.ver();
    b.ver();
    c.ver();
    a.eliminarPrimero();
    if (mayor(a,b))
    cout << "a es mayor que b \n";
    b=a;
    a=a;
    a.eliminarPrimero();
    a.ver();
    b.ver();
    c.ver();
}
