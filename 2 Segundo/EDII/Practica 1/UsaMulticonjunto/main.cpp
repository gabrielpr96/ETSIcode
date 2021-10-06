#include <iostream>
#include <Multiconjunto.h>
#include <Persona.h>

using namespace std;

int main(){
    cout << "Prueba con enteros--------------------------\n";
    Multiconjunto<int> v1;
    cout << "Vacio: " << v1.esVacio() << "\n";
    cout << "Cardinalidad: " << v1.cardinalidad() << "\n";
    v1.anade(2);
    v1.anade(3);
    v1.anade(2);
    cout << "Vacio: " << v1.esVacio() << "\n";
    cout << "Cardinalidad: " << v1.cardinalidad() << "\n";
    cout << "Pertenece 1: " << v1.pertenece(1) << "\n";
    cout << "Pertenece 2: " << v1.pertenece(2) << "\n";
    cout << "Pertenece 3: " << v1.pertenece(3) << "\n";
    cout << "Pertenece 4: " << v1.pertenece(4) << "\n";
    v1.elimina(2);
    cout << "Vacio: " << v1.esVacio() << "\n";
    cout << "Cardinalidad: " << v1.cardinalidad() << "\n";
    cout << "Pertenece 1: " << v1.pertenece(1) << "\n";
    cout << "Pertenece 2: " << v1.pertenece(2) << "\n";
    cout << "Pertenece 3: " << v1.pertenece(3) << "\n";
    cout << "Pertenece 4: " << v1.pertenece(4) << "\n";

    cout << "\n\nPrueba con caracteres--------------------------\n";
    Multiconjunto<char> v2;
    cout << "Vacio: " << v2.esVacio() << "\n";
    cout << "Cardinalidad: " << v2.cardinalidad() << "\n";
    v2.anade('B');
    v2.anade('C');
    v2.anade('B');
    cout << "Vacio: " << v2.esVacio() << "\n";
    cout << "Cardinalidad: " << v2.cardinalidad() << "\n";
    cout << "Pertenece A: " << v2.pertenece('A') << "\n";
    cout << "Pertenece B: " << v2.pertenece('B') << "\n";
    cout << "Pertenece C: " << v2.pertenece('C') << "\n";
    cout << "Pertenece D: " << v2.pertenece('D') << "\n";
    v2.elimina('B');
    cout << "Vacio: " << v2.esVacio() << "\n";
    cout << "Cardinalidad: " << v2.cardinalidad() << "\n";
    cout << "Pertenece A: " << v2.pertenece('A') << "\n";
    cout << "Pertenece B: " << v2.pertenece('B') << "\n";
    cout << "Pertenece C: " << v2.pertenece('C') << "\n";
    cout << "Pertenece D: " << v2.pertenece('D') << "\n";

    cout << "\n\nPrueba con personas--------------------------\n";
    Persona p1("Pepe", 16);
    Persona p2("Juan", 17);
    Persona p3("Alfredo", 18);
    Persona p4("Arbin", 19);
    Multiconjunto<Persona> v3;
    cout << "Vacio: " << v3.esVacio() << "\n";
    cout << "Cardinalidad: " << v3.cardinalidad() << "\n";
    v3.anade(p2);
    v3.anade(p3);
    v3.anade(p2);
    cout << "Vacio: " << v3.esVacio() << "\n";
    cout << "Cardinalidad: " << v3.cardinalidad() << "\n";
    cout << "Pertenece Pepe: " << v3.pertenece(p1) << "\n";
    cout << "Pertenece Juan: " << v3.pertenece(p2) << "\n";
    cout << "Pertenece Alfredo: " << v3.pertenece(p3) << "\n";
    cout << "Pertenece Arbin: " << v3.pertenece(p4) << "\n";
    v3.elimina(p2);
    cout << "Vacio: " << v3.esVacio() << "\n";
    cout << "Cardinalidad: " << v3.cardinalidad() << "\n";
    cout << "Pertenece Pepe: " << v3.pertenece(p1) << "\n";
    cout << "Pertenece Juan: " << v3.pertenece(p2) << "\n";
    cout << "Pertenece Alfredo: " << v3.pertenece(p3) << "\n";
    cout << "Pertenece Arbin: " << v3.pertenece(p4) << "\n";


    return 0;
}
