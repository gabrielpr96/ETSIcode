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
    friend Punto suma(Punto p1, const Punto &p2);
    friend void General::inc(Punto p, int x);
private:
    int x, y;
public:
    Punto(int nx = 0, int ny = 0){x=nx;y=ny;}
    void pinta(){cout << "(" << x << "," << y << ")" << endl;}
};

void General::inc(Punto p, int x){
    n = p.x+x;
}

Punto suma(Punto p1, const Punto &p2){
    Punto res;
    res.x = p1.x+p2.x;
    res.y = p1.y+p2.y;
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
