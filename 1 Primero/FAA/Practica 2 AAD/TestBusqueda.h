/*
 * La clase TestBusqueda contiene los metodos para:
 * 1. Comprobar que los m�todos de busqueda de la clase Busqueda funcionan adecuadamente.
 * 2. Calcular la eficiencia para el caso medio de un m�todo de busqueda,
 *    permitiendo guardar los datos e imprimir la gr�fica correspondiente
 * 3. Comparar el coste temporal de dos de los m�todos de busqueda
 *    lineal, y binaria, permitiendo guardar los datos e imprimir la
 *    gr�fica correspondiente.
 */
#ifndef _TEST_BUSQUEDA
#define _TEST_BUSQUEDA

#include <vector>
#include <string>
#include <iostream>
using namespace std;

class TestBusqueda {
	vector<string> nombreAlgoritmo;
	vector<int> complegidadAlgoritmo;
public:
	TestBusqueda();
	~TestBusqueda();
	/*
	 * Busca una clave en un array de enteros seg�n el m�todo indicado
	 * param v: el array de enteros a ordenar
	 * param size: tama�o del array de enteros a ordenar
	 * param key: clave a buscar en el array de enteros
	 * param metodo: Metodo de ordenacion a utilizar
	 * return: Tiempo empleado en la busqueda (en milisegundos)
	 */
	static double buscarClave(int v[], int size, int key, int metodo, int &pos);
	/*
	 * Comprueba que los metodos de busqueda funcionan correctamente
   */
	void comprobarMetodosBusqueda();

	/*
	 * Compara dos metodos de busqueda.
	 * Permite las opciones de crear el fichero de datos y la gr�fica correspondiente.
	 * param metodo1: Primer metodo de busqueda a comparar
	 * param metodo2: Segundo metodo de busqueda a comparar
	 */
	void comparar(int metodo1, int metodo2);
	void compararTodos();

	/*
	* Calcula el caso medio de un metodo de busqueda,
	* Permite las opciones de crear el fichero de datos y la gr�fica correspondiente.
    * param metodo: metodo de busqueda a estudiar.
    */
	void casoMedio(int metodo);

};
#endif