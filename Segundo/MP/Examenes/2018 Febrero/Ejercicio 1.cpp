#include <iostream>
#include <sstream>
#include <cstring>
#include <cstdlib>

using namespace std;

class Par {
    const int clave;
    float valor;
    static int contador;
public:
    Par(float n = 0) : clave(contador++){
        valor = n;
    }
    Par copia() const {
        return Par(valor);
    }
    int getClave() const{
        return clave;
    }

    operator float() const{
        return valor;
    }

    Par& operator=(const Par& P){
        valor = P.valor;
        return *this;
    }

    virtual string texto() const{
        stringstream s;
        s << clave<<"("<<valor<<")";
        return s.str();
    }
};

int Par::contador = 1;

ostream& operator<<(ostream& o, const Par& p){
    o << p.getClave() << "-" << (float)p;
    return o;
}

class ParEtiquetado: public Par {
    char *etiqueta;
public:
    ParEtiquetado(const char* t, float v) : Par(v){
        etiqueta=new char[strlen(t)+1];
        strcpy(etiqueta, t);
    }

    ~ParEtiquetado(){
        delete [] etiqueta;
    }

    const char* getEtiqueta() const{
        return etiqueta;
    }
    const char*  cambiarEtiqueta(const char * t){
        delete [] etiqueta;
        etiqueta=new char[strlen(t)+1];
        strcpy(etiqueta, t);
        return etiqueta;
    }

    string texto() const{
        stringstream s;
        s << etiqueta << "," << Par::texto();
        return s.str();
    }
};

ostream& operator<<(ostream& s, const ParEtiquetado& p) {
    s << p.getEtiqueta() << "," << (Par &)p;
    return s;
}


int main() {
    Par a(4.5), b(5.0), c(a);
    const Par x=a.copia();
    float n=x; //El compilador lo remplaza por float n = (float)x LLama al operator float() de Par que devuelve un float y se lo asigna a n
    cout << "a:" << a << " b:" << b << " c:" << c << endl;
    a=4; //El compilador lo remplaza por a = Par(4) se crea un nuevo objeto Par con el constructor de un parametro
    b=1+x+b; //El compilador lo remplaza b = Par(1+(float)x+(float)b) hace la suma con floats y crea una nuevo objeto Par con el constructor de un parametro
    Par y;  //Se crea un nuevo objeto con el constructor de un parametro usando el parametro por defecto.
    cout << "a:" << a << " b:" << b << " c:" << c << endl;
    cout << "x:" << x.texto() << " y:" << y.texto() << endl;
    cout << "n:" << n << " clave de x: " << x.getClave() << endl;
    const ParEtiquetado pe1("xxx",3.9);
    ParEtiquetado pe2=pe1;
    Par *ppar=new ParEtiquetado("yyy", 4.7);
    cout << "pe1:" << pe1 << " pe2:" << pe2 << " ppar:" << *((ParEtiquetado *)ppar) << endl;
    cout << pe1.getEtiqueta() << endl;
    cout << pe2.cambiarEtiqueta("zzz") << endl;
    cout << "pe1:" << pe1.texto() << " pe2:" << pe2.texto() << " ppar:" << ppar->texto() << endl;
    delete ppar;
    system("PAUSE");
    return EXIT_SUCCESS;
}
