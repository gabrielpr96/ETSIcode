#include <iostream>
#include <sstream>
#include <cstring>
#include <cstdlib>

using namespace std;

class A {
    const int x;
    float f;
    static int n;
public:
    A(int e, float f) : x(e) {this->f=f; n++; }// constructor
    virtual ~A() { n--; }
    int getx() const;
    static int getn() { return n; }
};
int A::n = 0;
int A::getx() const { return x; }

class B : public A {
    string s;
public:
    B(string cad, int i, float f) : A(i, f) {s = cad; } // constructor
};

int main() {
    cout << "Hay " << A::getn() << " objetos\n";
    A x(2,3.3);
    B y("y", 1, 2.5);
    A *p=new B("juan", 7, 7);
    A *q=new A(5,5);
    cout << "Hay " << A::getn() << " objetos\n";
    delete q; cout << "Hay " << A::getn() << " objetos\n";
    delete p; cout << "Hay " << A::getn() << " objetos\n";

    system("PAUSE");
    return EXIT_SUCCESS;
}
