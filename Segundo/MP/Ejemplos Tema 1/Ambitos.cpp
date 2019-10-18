#include <iostream>
#include <cstdlib>

using namespace std;

void ver();

class clase{
    int a;
public:
    int aGet(){return a;}
    void aSet(int i){a = i;}
    void ver();
};
int a;
clase x;

void clase::ver(){
    a = 2;      //Clase
    int a = 0;  //Local
    a++;        //Local
    clase::a++; //Clase
    ::a = 3;    //Global
    cout << "A " << ::a;
    cout << "  x.a " << clase::a;
    cout << "  a " << a << endl;
}

void ver(){
    a = 0;      //Global
    x.aSet(4);  //Clase global
    int a = 5;  //Local
    clase x;    //Clase local
    a++;        //Local
    x.aSet(2);  //Clase local
    ::a++;      //Global
    ::x.aSet(::x.aGet()+1);  //Global
    cout << "A " << ::a;
    cout << "  X.a" << ::x.aGet();
    cout << "  a " << a;
    cout << "  x.a " << x.aGet() << endl;
}

int main(){
    a = 2;      //Global
    x.aSet(0);  //Clase global
    cout << "A " << a << "  X.a " << x.aGet() << endl;
    ver();  //Funcion
    cout << "A " << a << "  X.a " << x.aGet() << endl;
    x.ver();    //Metodo de la clase global

    system("Pause");
    return 0;
}
