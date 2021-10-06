
/*
 * Clase AlgoritmosBusqueda que implementa los Algoritmos de Búsqueda para buscar un elemento en un vector de enteros.
 * Define las implementaciones de los siguientes métodos de búsqueda
 * de búsqueda en vectores:
 *	- Secuencial
 *	- Binaria o dicotómica
 *  - Ternaria
 */

#include "AlgoritmosBusqueda.h"


/*
 * Implementación de los métodos de la clase AlgoritmosBusqueda
 */
AlgoritmosBusqueda::AlgoritmosBusqueda() { }
AlgoritmosBusqueda:: ~AlgoritmosBusqueda() { }

/*
	 * Función busquedaSecuencialIt, implementa el método de búsqueda secuencial Iterativo
	 * param v: el array de enteros donde buscar
	 * param size: tamaño del array
	 * param key: clave o elemento a buscar
	 * return posición de la clave en el array
	 */

int AlgoritmosBusqueda::busquedaSecuencialIt(int v[], int size,int key)
{
	int i = 0;
	while (v[i] != key && i < size) i++;
	if (i == size)
		return -1;
	else
		return i;
}


/*
	 * Función busquedaBinariaRc, implementa el método de búsqueda binaria Recursivo
	 * param v: el array de enteros donde buscar
	 * param size: tamaño del array
	 * param key: clave o elemento a buscar
	 * return posición de la clave en el array
	 */
   
int AlgoritmosBusqueda::busquedaBinariaRc(int v[], int size,int key)
{
	return BinariaRc(v, 0, size - 1, key);
}

#include <iostream>


int AlgoritmosBusqueda::BinariaRc(int v[], int left, int right, int key)
{
	if (left > right)
		return - 1;

	int middle = left + (right-left)/2;

	if (key == v[middle])
		return middle;
	else
		if (key < v[middle])
			return BinariaRc(v, left, middle - 1, key);
		else
			return BinariaRc(v, middle + 1, right, key);
}
	

/*
	 * Función busquedaTernariaRc, implementa el método de búsqueda ternaria recursiva
	 * param v: el array de enteros donde buscar
	 * param size: tamaño del array
	 * param key: clave o elemento a buscar
	 * return posición de la clave en el array
	 */
	 
int AlgoritmosBusqueda::busquedaTernariaRc(int v[], int size,int key)
{
	return TernariaRc(v, 0, size - 1, key);
}


int AlgoritmosBusqueda::TernariaRc(int v[], int left, int right, int key)
{
	if (left > right)
		return -1;

	int div = (right - left) / 3;

	if (key == v[left + div])
		return left + div;
	else
		if (key < v[left + div])
			return TernariaRc(v, left, left + div - 1, key);
		else
			if (key == v[right - div])
				return  right - div;
			else
				if (key < v[right - div])
					return TernariaRc(v, left + div + 1, right - div - 1, key);
				else
					return TernariaRc(v, right - div + 1, right, key);
}