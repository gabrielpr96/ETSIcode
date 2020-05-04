#include "PilaArchivo.h"

PilaArchivo::PilaArchivo() {
    cadena n1, n2, n3;

    do {
        strcpy(n1, "file_");
        strcpy(n2, itoa(rand()%1000, n2, 10));
        strcpy(n3, ".dat");
        strcat(n1, strcat(n2, n3));
        strcpy(fichero, n1);

        logico.open(fichero, ios::binary|ios::in);

        if (!logico.fail())
            logico.close();

    } while (!logico.fail());

    logico.clear();
    logico.open(fichero, ios::binary|ios::out);
    logico.close();
    logico.open(fichero, ios::binary|ios::out|ios::in);
    tope=-1;
}


bool PilaArchivo::esVacia() {
    return (tope==-1);
}

int PilaArchivo::longitud() {
    return tope+1;
}

void PilaArchivo::desapilar() {
    tope--;
}
PilaArchivo::~PilaArchivo()
{
 logico.close();
}

void PilaArchivo::apilar(float e) {
    logico.seekp((tope+1)*sizeof(float), ios::beg);
    logico.write((char*) &e, sizeof(float));
    tope++;
}

float PilaArchivo::cima() {
    float result;

    logico.seekg(tope*sizeof(float), ios::beg);
    logico.read((char*)&result, sizeof(float));

    return result;
}
