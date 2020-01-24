#include <iostream>
#include <sstream>
#include <cstring>
#include <cstdlib>

class clase1 {
    int n;
public:
    clase1(int x) { n = x; }
};

class clase2 {
    int n;
public:
    clase2(int x=3) { n = x; }
};

class clase3 {
    static int n;
public:
    clase3() { n++; }
};

class clase4 {
    int n;
};

int clase3::n=0;

int main() {
    //Lo importante es comprobar si tienen constructor por defecto (sin parametros), ya que esta forma de declarar arrays obliga a crear el objeto invocando al constructor.
    //clase1 a[3];  //Esta es la que da fallo. Por que el constructor de oficio (que es por defecto) desaparece si declaramos otro constructor.
    clase2 b[3];    //Gracias a los parametros por defecto, esta clase tiene un constructor con un parametro y otro sin parametros.
    clase3 c[3];    //Tiene un constructor por defecto.
    clase4 d[3];    //El compilador genera un constructor de oficio, que es por defecto.
}
