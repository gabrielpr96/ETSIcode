#include <iostream>
#include <sstream>
#include <cstring>
#include <cstdlib>

using namespace std;

class claseB;

class claseA {
    int *tabla, n;
public:
    claseA(int n = 0){
        this->n = n;
        if(n > 0){
            tabla = new int[n];
            for(int i = 0; i < n; i++)
                tabla[i] = i*2 + 1;
        }else{
            tabla = NULL;
        }
    }
    claseA(const claseA& c){
        this->n = c.n;
        if(n > 0){
            tabla = new int[n];
            for(int i = 0; i < n; i++)
                tabla[i] = c.tabla[i];
        }else{
            tabla = NULL;
        }
    }
    virtual ~claseA(){
        delete [] tabla;
    }
    operator int() const {
        return n;
    }
    int& operator[](int i) const{
        return tabla[i];
    }
    claseA& operator+=(claseB *c){
        //Por suerte este operador no hay que implementarlo, porque es bastante chungo.
        return *this;
    }
    virtual void setCadena(char* cad){
    }
};

ostream& operator<<(ostream& s, const claseA& c){
    for(int i = 0; i < (int)c; i++)
        s << c[i] << " ";
    return s;
}

class claseB: public claseA {
    char *cadena;
public:
    claseB(char* cad, int n = 0) : claseA(n){
        cadena = new char[strlen(cad)+1];
        strcpy(cadena, cad);
    }
    claseB(const claseB& c) : claseA(c){
        cadena = new char[strlen(c.cadena)+1];
        strcpy(cadena, c.cadena);
    }
    ~claseB(){
        delete [] cadena;
    }
    void setCadena(char* cad){
        delete [] cadena;
        cadena = new char[strlen(cad)+1];
        strcpy(cadena, cad);
    }
    char* getCadena(){
        return cadena;
    }
};

int main() {
    claseA a(5), b, c; //crea la claseA con los 5 primeros numeros impares, b y c vacios
    claseA *x=new claseB("BBVA");
    claseB *y=new claseB("AVE", 2), *z, i("ACS",3), j(i);
    c=a;
    z=y;
    x->setCadena("OHL");
    if (a > b)
        cout << "a:" << a << endl;
    for(int i=0; i<(int)a; i++)
        cout << a[i] << endl;
    c+=y;
    cout << "a:" << a << "b:" << b << "c:" << c << endl;
    i[0]=2;cout << i[0] << "," << j[0] << endl;
    cout << "x contiene la cadena:" << ((claseB *)x)->getCadena() << endl;
    delete x; system("pause"); return EXIT_SUCCESS;
}
