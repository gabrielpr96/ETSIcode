/*
 * Clase AlgoritmosBusqueda que define los Algoritmos de Busqueda para encontrar una clave en un vector de enteros.
 * Define las implementaciones de los siguientes métodos de Busqueda:
 *	- Lineal
 *	- Binaria.
 */
#ifndef _BUSQUEDA
#define _BUSQUEDA

 /*
  * declaración de la clase AlgoritmosBusqueda
  */
class AlgoritmosBusqueda
{
public:
	AlgoritmosBusqueda();
	~AlgoritmosBusqueda();

	/*
	 * Función busquedaLineal, implementa el método de busqueda lineal
	 * param v: el array de enteros
	 * param size: tamaño del array de enteros
	 * param key: la clave de busqueda
	 * return: la posicion de la clave en el vector, si no se encuentra devuelve -1
	 */
	int buscaLineal(int v[], int size, int key);

	/*
	 * Función buscaBinaria, implementa el método de busqueda binaria
	 * param v: el array de enteros
	 * param size: tamaño del array de enteros
	 * param key: la clave de busqueda
	 * return: la posicion de la clave en el vector, si no se encuentra devuelve -1
	 */
	int buscaBinaria(int v[], int size, int key);
private:
	int buscaBinaria(int v[], int p, int u, int key);
};

#endif