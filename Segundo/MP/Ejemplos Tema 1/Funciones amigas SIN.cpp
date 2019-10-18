#include <iostream>
#include <cstdlib>

using namespace std;

class Punto;

class General{
    int n;
public:
    void inc(Punto p, int x);
    void pinta(){ cout << n << endl;}
};

class Punto{
private:
    int x, y;
public:
    Punto(int nx = 0, int ny = 0){x=nx;y=ny;}
    void pinta(){cout << "(" << x << "," << y << ")" << endl;}

    int getX() const {return x;}
    int getY() const {return y;}
};

void General::inc(Punto p, int x){
    n = p.getX()+x;
}

Punto suma(Punto p1, const Punto &p2){
    Punto res(p1.getX()+p2.getX(), p1.getY()+p2.getY());
    return res;
}

int main(){
    Punto p1(10, 5), p2(20, 10);
    Punto p3 = suma(p1,p2);

    General g;
    p3.pinta();
    g.inc(p3, 2);
    g.pinta();

    system("PAUSE");
    return 0;
}
