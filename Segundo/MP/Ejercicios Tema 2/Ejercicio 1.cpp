#include <iostream>
#include <sstream>
#include <cstdlib>

using namespace std;

class Clase {
    int n;
public:
    Clase(int x) { n=x; }
    //No se necesita constructor de copia porque no tiene memoria dinamica
};

class Clase2 {
    const int tamanio;
    int *tabladinamica; //el constructor crea la tabla con un tamaño fijo indicado por parametro
    Clase y;
public:
    Clase2(int i, Clase c = 0) : tamanio(i), y(c){
        this->tabladinamica = new int[tamanio];
    }
    ~Clase2(){
        delete [] tabladinamica;
    }
protected:
    int getTamanio(){ return tamanio; };
    Clase getY(){ return y; };
};

class Clase3: public Clase2 {
    static int n; //para saber cuantos objetos de tipo Clase3 se crean
    Clase z;
public:
    Clase3(int i, Clase c1, Clase c2) : Clase2(i, c1), z(c2){
        n++;
    }
    Clase3(Clase3 &c) : Clase2(c.getTamanio(), c.getY()), z(c.z){    //Necesario, porque aunque no tiene memoria dinamica, hay que incrementar n
        n++;
    }
    ~Clase3(){
        n--;
    }
};
int Clase3::n = 0;

int main() {
    Clase a(2), b(a);
    Clase2 x(2), y(3, a); //x con un atributo Clase de valor 0 y una tabla de 2 elementos
    Clase3 h(2, a, b), hc(h); //2 es el tamaño de la tabla dinámica, a es y, b es z

    system("PAUSE");
    return EXIT_SUCCESS;
}
