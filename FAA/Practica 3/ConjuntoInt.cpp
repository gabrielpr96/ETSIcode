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
	//return datos[rand() % tamano];
	return datos[tamano / 2];
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
	int posMin, aux;
	for (int i = 0; i < tamano - 2; i++) {
		posMin = i;
		for (int j = i + 1; j < tamano; j++) {
			if (datos[j] < datos[posMin])
				posMin = j;
		}
		aux = datos[posMin];
		datos[posMin] = datos[i];
		datos[i] = aux;
	}
}
void ConjuntoInt::ordenarDeCreciente() {
	int posMax, aux;
	for (int i = 0; i < tamano - 2; i++) {
		posMax = i;
		for (int j = i + 1; j < tamano; j++) {
			if (datos[j] > datos[posMax])
				posMax = j;
		}
		aux = datos[posMax];
		datos[posMax] = datos[i];
		datos[i] = aux;
	}
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

