/* 
 *Definiciones de las Constantes utilizadas en la pr�ctica 3. 
 */
#ifndef _CONSTANTES
#define _CONSTANTES

/* Constantes simb�licas para indicar el metodo de ordenacion*/
enum algoritmosOrdenacion { BURBUJA, INSERCION, SELECCION, SHELL, MERGESORT, QUICKSORT, SHAKESORT, BRICKSORT };

/* Constantes simb�licas para indicar el metodo de Busqueda*/
enum algoritmosBusquedaClave{SECUENCIALIt, BINARIARc, TERNARIARc};

/* Constantes para indicar el Orden de complejidad*/
enum ordenes {N, NlogN,CUADRADO, AXpB, AX3t2pB, log2N, log3N};

enum casosInstancia { MEJOR, PEOR, MEDIO };

/* Constantes para indicar el Numero de repeticiones para el caso medio de cada m�todo de ordenaci�n-b�squeda */
static const int REPETICIONES = 100;

/* Constantes para indicar la variacion de las tallas del vector */
enum valoresTallas { TALLA_INI = 100, TALLA_FIN = 1000, TALLA_DELTA = 100 };

#endif