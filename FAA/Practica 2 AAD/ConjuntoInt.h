#ifndef _LIB_CONJUNTOINT
#define _LIB_CONJUNTOINT

#include <iostream>
#include "Constantes.h"
using namespace std;



/////////// Declaración de la clase conjuntoInt /////////////

class ConjuntoInt
{
private:
	int tamano;
	int *datos;
	static int casoInstancia;
	void ordenarCreciente();
	void ordenarDeCreciente();
public:
	ConjuntoInt (int max= 0); 
	~ConjuntoInt (); 
	void vaciar ();
	int GeneraKey();
	void GeneraVector (int tam);
	int* getDatos();
	void escribe ();
	static int getCaso();
	static void setCaso(int c);
};

#endif

