#include <iostream>
using namespace std;
#include <ctime>  //para time
#include <cstdlib> // para srand, rand
#include "ConjuntoInt.h"

ConjuntoInt::ConjuntoInt (int max)
{
 tamano= max;
 datos= new int[max];
}
ConjuntoInt::~ConjuntoInt ()
{
 delete[] datos;
}


int ConjuntoInt::casoInstancia = MEDIO;

void ConjuntoInt::vaciar ()
{
 tamano= 0;
}
int* ConjuntoInt::getDatos()
{
	int* v=datos;
	for (int i= 0; i <tamano;i++){
		v[i]=datos[i];}
	return v;
}

int ConjuntoInt::GeneraKey()
{
	return datos[rand() % tamano];
	//return datos[tamano / 2];
	//return rand()%10000; //genera un número aleatorio entre 1 y 999
}

void ConjuntoInt::GeneraVector (int tam)
{
 tamano=tam;
 for (int i = 0; i < tamano; i++)
	 datos[i] = rand() % 10000;
 switch (casoInstancia) {
	case MEJOR: ordenarCreciente(); break;
	case MEDIO: break;
	case PEOR: ordenarDeCreciente(); break;
 }

 
}

void ConjuntoInt::ordenarCreciente() {
	int x, j;
	for (int i = 1; i < tamano; i++) {
		x = datos[i];
		j = i - 1;
		while (j >= 0 && x < datos[j]) {
			datos[j + 1] = datos[j];
			j--;
		}
		datos[j + 1] = x;
	}
}
void ConjuntoInt::ordenarDeCreciente() {
}

void ConjuntoInt::escribe()
{
 for (int i= 0; i<tamano; i++)
     cout << datos[i] << (i<tamano-1? ", ": "\n");
}


int ConjuntoInt::getCaso() {
	return casoInstancia;
}
void ConjuntoInt::setCaso(int c) {
	casoInstancia = c;
}

