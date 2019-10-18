#include <iostream>
#include <cstdlib>

using namespace std;

//El objetivo de este codigo es asignar 0 a la variable de mayor valor

int& maximo(int& a, int& b){
    return a>b?a:b;
}

int main(){
    int a = 1, b = 2;
    cout << a << " " << b << endl;

    //Puede que te entre la tentacion de hacerlo asi de simple y explicito
    /*
    if(a > b)
    {
        a = 0;
    }
    else
    {
        b = 0;
    }
    */

    //O puede que seas de los mios y prefieras simplificarlo un poco
    /*
    (a>b?a:b) = 0;
    */

    //Esta es la solucion usando variables de tipo referencia, es mas legible
    /*
    maximo(a, b) = 0;
    */

    cout << a << " " << b << endl;
    system("PAUSE");
    return 0;
}
