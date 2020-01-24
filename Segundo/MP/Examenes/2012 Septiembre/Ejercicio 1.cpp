#include <iostream>
#include <sstream>
#include <cstring>
#include <cstdlib>

using namespace std;

class complejo {
    int real;
    int imag;
public:
    complejo(int r, int i = 0){
        real = r;
        imag = i;
    }
    int getR() const {return real;}
    int getI() const {return imag;}
};

ostream& operator<<(ostream& s, const complejo& c){
    s << c.getR() << (c.getI()<0?"":"+") << c.getI() << "i";
    return s;
}

class punto {
    const int x, y;
public:
    punto(const complejo& c) : x(c.getR()), y(c.getI()){
    }
    punto() : x(0), y(0){ //Esta es mi solucion, implemento el mismo numero de funciones asi queno debe estar muy mal. Aunque no sea exactamente como viene resuelto.
    }
    //punto(int a=0, int b=0): x(a),y(b) { } //Luego hago un truco para no necesitar este constructor
    virtual ~punto(){}
    operator int() const{
        return x;
    }
    virtual string cad() const{
        stringstream s;
        s << "(" << x << "," << y << ")";
        return s.str();
    }
};

class punto2: public punto {
    char *nombre;
public:
    punto2(char* nom, int x = 0, int y = 0) : punto(complejo(x, y)) { //Este es el truco que utlizo
        nombre = new char[strlen(nom)+1];
        strcpy(nombre, nom);
    }
    ~punto2(){
        delete [] nombre;
        cout << "Destructor punto 2\n";
    }
    char* getNombre() const {
        return nombre;
    }
    string cad() const {
        stringstream s;
        s << nombre << " ";
        s << punto::cad();
        return s.str();
    }
};

int main() {
    complejo a(2,7), b=5, c=a;
    const punto x=a, y;
    int n=x;
    cout << "a:" << a << " b:" << b << " c:" << c << endl;
    cout << "x:" << x.cad() << " y:" << y.cad() << endl;
    cout << "n:" << n << endl;
    a=4; // 1 punto
    b=c;
    cout << "a:" << a << " b:" << b << " c:" << c << endl;
    punto2 *pb=new punto2("x",0,0);
    punto *pa=pb;
    cout << pb->getNombre() << " " << pa->cad() << endl;
    delete pa;
    system("PAUSE");
    return EXIT_SUCCESS;
}
