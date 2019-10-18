/*
 * Clase AlgoritmosBusqueda que define los Algoritmos de Busqueda para encontrar una clave en un vector de enteros.
 * Define las implementaciones de los siguientes métodos de Busqueda:
 *	- Lineal
 *	- Binaria.
 */

#include "AlgoritmosBusqueda.h"

AlgoritmosBusqueda::AlgoritmosBusqueda() {}
AlgoritmosBusqueda :: ~AlgoritmosBusqueda() {}

/*
	 * Función busquedaLineal, implementa el método de busqueda lineal
	 * param v: el array de enteros
	 * param size: tamaño del array de enteros
	 * param key: la clave de busqueda
	 * return: la posicion de la clave en el vector, si no se encuentra devuelve -1
	 */

int AlgoritmosBusqueda::buscaLineal(int v[], int size, int key)
{
	int i = 0;
	while (i < size && v[i] != key)
		i++;
	if (i < size && v[i] == key)
		return i;
	return -1;
}


/*
	 * Función buscaBinaria, implementa el método de busqueda binaria
	 * param v: el array de enteros
	 * param size: tamaño del array de enteros
	 * param key: la clave de busqueda
	 * return: la posicion de la clave en el vector, si no se encuentra devuelve -1
	 */

int AlgoritmosBusqueda::buscaBinaria(int v[], int size, int key)
{
	return buscaBinaria(v, 0, size-1, key);
}

int AlgoritmosBusqueda::buscaBinaria(int v[], int p, int u, int key) {
	if (p > u)
		return -1;
	int m = (p + u) / 2;
	if (key == v[m])
		return m;
	else {
		if (key < v[m])
			return buscaBinaria(v, p, m-1, key);
		else
			return buscaBinaria(v, m+1, u, key);
	}
}