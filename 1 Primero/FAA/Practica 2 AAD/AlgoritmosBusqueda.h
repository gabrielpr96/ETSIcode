/*
 * Clase AlgoritmosBusqueda que define los Algoritmos de Busqueda para encontrar una clave en un vector de enteros.
 * Define las implementaciones de los siguientes m�todos de Busqueda:
 *	- Lineal
 *	- Binaria.
 */
#ifndef _BUSQUEDA
#define _BUSQUEDA

 /*
  * declaraci�n de la clase AlgoritmosBusqueda
  */
class AlgoritmosBusqueda
{
public:
	AlgoritmosBusqueda();
	~AlgoritmosBusqueda();

	/*
	 * Funci�n busquedaLineal, implementa el m�todo de busqueda lineal
	 * param v: el array de enteros
	 * param size: tama�o del array de enteros
	 * param key: la clave de busqueda
	 * return: la posicion de la clave en el vector, si no se encuentra devuelve -1
	 */
	int buscaLineal(int v[], int size, int key);

	/*
	 * Funci�n buscaBinaria, implementa el m�todo de busqueda binaria
	 * param v: el array de enteros
	 * param size: tama�o del array de enteros
	 * param key: la clave de busqueda
	 * return: la posicion de la clave en el vector, si no se encuentra devuelve -1
	 */
	int buscaBinaria(int v[], int size, int key);
private:
	int buscaBinaria(int v[], int p, int u, int key);
};

#endif