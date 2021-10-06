/* 
 *Definiciones de las Constantes para la pr�ctica 2 
 */
#ifndef _CONSTANTES
#define _CONSTANTES

/* Constantes simb�licas para indicar el metodo de ordenacion*/
enum algoritmosOrdenacion {BURBUJA, INSERCION, SELECCION, SHELL, MERGESORT, QUICKSORT, SHAKESORT, BRICKSORT};
/* Constantes simb�licas para indicar el metodo de busqueda*/
enum algoritmosBusqueda { LINEAL, BINARIA};
/* Constantes simb�licas para indicar el caso de la instancia*/
enum casosInstancia { MEJOR, MEDIO, PEOR };
/* Constantes para indicar el Orden del metodo de ordenacion*/
enum ordenes {CUADRADO, NlogN, logN, AXpB, AX3t2pB};
/* Constantes para indicar el Numero de repeticiones para el caso medio de cada m�todo de b�squeda */
static const int REPETICIONES = 100;
/* Constantes para indicar la variacion de las tallas del vector */
enum valoresTallas { TALLA_INI = 100, TALLA_FIN = 1000, TALLA_DELTA = 100}; 

#endif