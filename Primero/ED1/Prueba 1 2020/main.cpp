#include <iostream>
#include <fstream>
#include "PilaArchivo.h"

int main(){
    float tmp;
    fstream file("Temperaturas.dat", ios::binary|ios::out);
    tmp = 2;
    file.write((char*) &tmp, sizeof(float));
    tmp = 11;
    file.write((char*) &tmp, sizeof(float));
    tmp = 15;
    file.write((char*) &tmp, sizeof(float));
    tmp = 12;
    file.write((char*) &tmp, sizeof(float));
    tmp = 23;
    file.write((char*) &tmp, sizeof(float));
    tmp = 2;
    file.write((char*) &tmp, sizeof(float));
    tmp = 14;
    file.write((char*) &tmp, sizeof(float));
    tmp = 11;
    file.write((char*) &tmp, sizeof(float));
    tmp = 6;
    file.write((char*) &tmp, sizeof(float));
    tmp = 9;
    file.write((char*) &tmp, sizeof(float));
    tmp = 13;
    file.write((char*) &tmp, sizeof(float));
    tmp = 32;
    file.write((char*) &tmp, sizeof(float));
    tmp = 21;
    file.write((char*) &tmp, sizeof(float));
    tmp = 10;
    file.write((char*) &tmp, sizeof(float));
    tmp = 12;
    file.write((char*) &tmp, sizeof(float));
    file.close();


    //Comienzo de la solución
    PilaArchivo pila;
    float tMin, tMax, aux;
    cout << "tMin: ";
    cin >> tMin;
    cout << "tMax: ";
    cin >> tMax;

    ifstream entrada("Temperaturas.dat", ios::binary);
    entrada.seekg(0, ios::end);
    int n = entrada.tellg()/sizeof(float);

    entrada.seekg(0, ios::beg);
    for(int i = 0; i < n; i++){
        entrada.read((char*)&aux, sizeof(float));
        pila.apilar(aux);
    }
    entrada.close();

    ofstream salida("FiltroTemperaturas.dat", ios::binary);
    while(!pila.esVacia()){
        aux = pila.cima();
        pila.desapilar();
        if(aux >= tMin && aux <= tMax)
            salida.write((char*) &aux, sizeof(float));

    }
    salida.close();
    //Fin de la solución




    ifstream test("FiltroTemperaturas.dat", ios::binary);
    test.seekg(0, ios::end);
    n = test.tellg()/sizeof(float);
    test.seekg(0, ios::beg);
    for(int i = 0; i < n; i++){
        test.read((char*)&aux, sizeof(float));
        cout << aux << "  ";
    }
    test.close();

    return 0;
}
