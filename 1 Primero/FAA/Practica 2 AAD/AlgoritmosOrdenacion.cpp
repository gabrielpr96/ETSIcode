/*
 * Clase AlgoritmosOrdenacion que implementa los Algoritmos de Ordenación para ordenar un vector de enteros en orden descendente.
 * Define las implementaciones de los siguientes métodos de Ordenación en vectores: 
 *	- Burbuja
 *	- Inserción
 *	- Selección.
 */

#include "AlgoritmosOrdenacion.h"
#include <iostream>
#include <ctime>
#include <windows.h>

///Modificaciones
//#define MOD_PROBLEMA_1
//#define MOD_PROBLEMA_2
//#define MOD_PROBLEMA_5
//#define MOD_PROBLEMA_6
//#define MOD_PROBLEMA_7
//#define OPT_QUICK_RAND
//#define OPT_QUICK_TAIL
//#define OPT_SEL_STABLE
//#define OPT_SEL_SILLY
//#define MOD_TRAZA
///Fin de las modificaciones


using namespace std;

AlgoritmosOrdenacion :: AlgoritmosOrdenacion() {}
AlgoritmosOrdenacion :: ~AlgoritmosOrdenacion(){}

void AlgoritmosOrdenacion::swap(int &a, int &b) {
	int tmp = a;
	a = b;
	b = tmp;

}


// Algoritmos de ordenacion tal cual
#ifndef MOD_TRAZA
void AlgoritmosOrdenacion :: ordenaBurbuja(int v[], int size)
{
	int aux;
	for (int i = size - 2; i >= 0; i--) {
		for (int j = 0; j <= i; j++) {
			if (v[j] > v[j + 1]) {
				aux = v[j];
				v[j] = v[j+1];
				v[j + 1] = aux;
			}
		}
	}
}

#ifndef MOD_PROBLEMA_1
void AlgoritmosOrdenacion :: ordenaInsercion(int v[], int size)
{
	int x, j;
	for (int i = 1; i < size; i++) {
		x = v[i];
		j = i - 1;
		while (j >= 0 && x < v[j]) {
			v[j + 1] = v[j];
			j--;
		}
		v[j + 1] = x;
	}
}
#endif

#if !( defined(MOD_PROBLEMA_2) || defined(OPT_SEL_SILLY) || defined(OPT_SEL_STABLE))
void AlgoritmosOrdenacion :: ordenaSeleccion(int v[], int size)
{
	int posMin, aux;
	for (int i = 0; i < size - 2; i++) {
		posMin = i;
		for (int j = i + 1; j < size; j++) {
			if (v[j] < v[posMin])
				posMin = j;
		}
		aux = v[posMin];
		v[posMin] = v[i];
		v[i] = aux;
	}
}
#endif

void AlgoritmosOrdenacion::ordenaShell(int v[], int size)
{
	for (int h = size / 2; h > 0; h /= 2) {
		for (int i = h; i < size; i++) {
			int j = i;
			int t = v[i];
			while (j >= h && t < v[j - h]) {
				v[j] = v[j - h];
				j -= h;
			}
			v[j] = t;
		}
	}
}

void AlgoritmosOrdenacion::ordenaMergesort(int v[], int size)
{
	mergesort(v, 0, size - 1);
}
#ifndef MOD_PROBLEMA_5
void AlgoritmosOrdenacion::mergesort(int v[], int e, int d)
{
	if (e < d) {
		int m = (e + d) / 2;
		mergesort(v, e, m);
		mergesort(v, m + 1, d);
		merge(v, e, m, d);
	}
}
#endif
void AlgoritmosOrdenacion::merge(int v[], int e, int m, int d)
{
	int *B = new int[d - e + 1];

	int i = e;
	int j = m + 1;
	int k = 0;
	while (i <= m && j <= d) {
		if (v[i] < v[j])
			B[k++] = v[i++];
		else
			B[k++] = v[j++];
	}
	while(i <= m)
		B[k++] = v[i++];
	while (j <= d)
		B[k++] = v[j++];

	for (k = 0; k <= d - e; ++k)
		v[e + k] = B[k];

	delete[] B;
}


void AlgoritmosOrdenacion::ordenaQuicksort(int v[], int size)
{
	quicksort(v, 0, size - 1);
}
#if !(defined(MOD_PROBLEMA_6) || defined(MOD_PROBLEMA_7) || defined(OPT_QUICK_RAND) || defined(OPT_QUICK_TAIL))
void AlgoritmosOrdenacion::quicksort(int v[], int e, int d) {
	if (e < d) {
		int q = partition(v, e, d);
		quicksort(v, e, q);
		quicksort(v, q+1, d);
	}
}
#endif
#ifndef OPT_QUICK_RAND
int AlgoritmosOrdenacion::partition(int v[], int e, int d) {
	int x = v[e];
	int i = e - 1;
	int j = d + 1;
	while (true) {
		while (x < v[--j]);
		while (v[++i] < x);
		if (i >= j) return j;
		swap(v[i], v[j]);
	}
}
#endif

void AlgoritmosOrdenacion::ordenaShakesort(int v[], int size)
{
	for (int i = 0; i < size / 2; i++) {
		bool desordenado = false;
		for (int j = i; j < size - i - 1; j++) {
			if (v[j] > v[j + 1]) {
				swap(v[j], v[j + 1]);
				desordenado = true;
			}
		}
		for (int j = size - 2 - i; j > i; j--) {
			if (v[j] < v[j - 1]) {
				swap(v[j], v[j - 1]);
				desordenado = true;
			}
		}
		if (!desordenado) break;
	}
}

void AlgoritmosOrdenacion::ordenaBricksort(int v[], int size)
{
	bool ordenado = false;

	while (!ordenado) {
		ordenado = true;

		for (int i = 1; i <= size - 2; i = i + 2){
			if (v[i] > v[i + 1]){
				swap(v[i], v[i + 1]);
				ordenado = false;
			}
		}
		for (int i = 0; i <= size - 2; i = i + 2){
			if (v[i] > v[i + 1]){
				swap(v[i], v[i + 1]);
				ordenado = false;
			}
		}
	}
}
#endif

///Modificaciones concretas
//Insercion con busqueda binaria, en general reduce el teimpo de ejecucion de N a logN.
#ifdef MOD_PROBLEMA_1
int busquedaBinaria(int v[], int e, int d, int key) {
	if (d <= e)
		return (key > v[e]) ? (e + 1) : e;

	int m = (e + d) / 2;

	if (key == v[m])
		return m + 1;

	if (key > v[m])
		return busquedaBinaria(v, m + 1, d, key);
	return busquedaBinaria(v, e, m - 1, key);
}
void AlgoritmosOrdenacion::ordenaInsercion(int v[], int size)
{
	int i, j, x, pos;

	for (i = 1; i < size; i++){
		j = i - 1;
		x = v[i];

		pos = busquedaBinaria(v, 0, j, x);

		while (j >= pos){
			v[j + 1] = v[j];
			j--;
		}
		v[j + 1] = x;
	}
}
#endif

//Es util si muchos elementos estan repetidos, pero son mas calculos generalmente innecesarios.
//Tambien evita que se realice un movimiento tonto si posmin no ha sido modificada.
#ifdef MOD_PROBLEMA_2
void AlgoritmosOrdenacion::ordenaSeleccion(int v[], int size)
{
	int posMin;
	for (int i = 0; i < size - 2; i++) {
		posMin = i;
		for (int j = i + 1; j < size; j++) {
			if (v[j] < v[posMin])
				posMin = j;
		}
		if (posMin != i && v[posMin] != v[i]) {
			swap(v[posMin], v[i]);
		}
	}
}
#endif

//Merge sin recursion a partir de una constante.
//Dependiendo del hardware y del kernel, puede que cierto valor de la constante resulte mejor.
#ifdef MOD_PROBLEMA_5
#define M 100
void seleccion(int v[], int size)
{
	int posMin, aux;
	for (int i = 0; i < size - 1; i++) {
		posMin = i;
		for (int j = i + 1; j < size; j++) {
			if (v[j] < v[posMin])
				posMin = j;
		}
		aux = v[posMin];
		v[posMin] = v[i];
		v[i] = aux;
	}
}
void AlgoritmosOrdenacion::mergesort(int v[], int e, int d)
{
	if (e < d) {
		if (d - e + 1 <= M)
			seleccion(v, d - e + 1);
		else {
			int m = (e + d) / 2;
			mergesort(v, e, m);
			mergesort(v, m + 1, d);
			merge(v, e, m, d);
		}
	}
}
#endif

//Quick sin recursion a partir de una constante, igual que el otro.
#ifdef MOD_PROBLEMA_6
#define M 100
void seleccion(int v[], int size)
{
	int posMin, aux;
	for (int i = 0; i < size - 1; i++) {
		posMin = i;
		for (int j = i + 1; j < size; j++) {
			if (v[j] < v[posMin])
				posMin = j;
		}
		swap(v[posMin], v[i]);
	}
}
void AlgoritmosOrdenacion::quicksort(int v[], int e, int d) {
	if (e < d) {
		if (d - e + 1 <= M)
			seleccion(v, d - e + 1);
		else {
			int q = partition(v, e, d);
			quicksort(v, e, q);
			quicksort(v, q + 1, d);
		}
	}
}
#endif

//Quick comprobando antes de meterse en el embrollo deque todos sean iguales.
#ifdef MOD_PROBLEMA_7
void AlgoritmosOrdenacion::quicksort(int v[], int e, int d) {
	if (e < d) {
		bool iguales = true;
		int i = e+1;
		while (i <= d && iguales) {
			iguales = v[i - 1] == v[i];
			i++;
		}

		if (!iguales) {
			int q = partition(v, e, d);
			quicksort(v, e, q);
			quicksort(v, q + 1, d);
		}
	}
}
#endif

#ifdef OPT_QUICK_RAND
int AlgoritmosOrdenacion::partition(int v[], int e, int p) {
	//srand(time(NULL));
	swap(v[e + rand() % (p - e)], v[p]);

	int x = v[p];
	int i = (e - 1);

	for (int j = e; j <= p - 1; j++) {
		if (v[j] <= x) {
			i++;
			swap(v[i], v[j]);
		}
	}
	swap(v[i + 1], v[p]);
	return (i + 1);
}

void AlgoritmosOrdenacion::quicksort(int v[], int e, int p) {
	if (e < p) {
		int q = partition(v, e, p);

		quicksort(v, e, q - 1);
		quicksort(v, q + 1, p);
	}
}
#endif
#ifdef OPT_QUICK_TAIL
void AlgoritmosOrdenacion::quicksort(int v[], int e, int p) {
	while (e < p) {
		/* pi is partitioning index, arr[p] is now
		   at right place */
		int q = partition(v, e, p);

		// If left part is smaller, then recur for left 
		// part and handle right part iteratively 
		if (q - e < p - q) {
			quicksort(v, e, q - 1);
			e = q + 1;
		}

		// Else recur for right part 
		else {
			quicksort(v, q + 1, p);
			p = q - 1;
		}
	}
}
#endif
#ifdef OPT_SEL_SILLY
void AlgoritmosOrdenacion::ordenaSeleccion(int v[], int size) {
	int posMin, aux;
	for (int i = 0; i < size - 2; i++) {
		posMin = i;
		for (int j = i + 1; j < size; j++) {
			if (v[j] < v[posMin])
				posMin = j;
		}
		if (posMin != i && v[posMin] != v[i]) {
			aux = v[posMin];
			v[posMin] = v[i];
			v[i] = aux;
		}
	}
}
#endif
#ifdef OPT_SEL_STABLE
void AlgoritmosOrdenacion::ordenaSeleccion(int v[], int size) {
	// Iterate through array elements 
	for (int i = 0; i < size - 1; i++) {

		// Loop invariant : Elements till a[i - 1] 
		// are already sorted. 

		// Find minimum element from  
		// arr[i] to arr[n - 1]. 
		int min = i;
		for (int j = i + 1; j < size; j++)
			if (v[min] > v[j])
				min = j;

		// Move minimum element at current i. 
		int key = v[min];
		while (min > i) {
			v[min] = v[min - 1];
			min--;
		}
		v[i] = key;
	}
}
#endif

///Versiones con traza
#ifdef MOD_TRAZA
void imprimir(int v[], int size) {
	for (int i = 0; i < size; i++)
		cout << v[i] << (i < size - 1 ? ", " : "\n");
}
void imprimir(int v[], int size, int s1, int s2) {
	for (int i = 0; i < size; i++) {
		if (i == s1)
			SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE), 249);
		else if (i == s2)
			SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE), 252);
		cout << v[i];
		SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE), 7);
		cout << (i < size - 1 ? ", " : "\n");
	}
}
void imprimir(int v[], int size, int s1, int s2, bool back, int f, bool desde) {
	for (int i = 0; i < size; i++) {
		if (i == s1)
			SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE), back?9:240);
		else if (i == s2)
			SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE), back?12:240);
		else if (f != -1 && ((i >= f && desde) || (i <= f && !desde)))
			SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE), 10);
		cout << v[i];
		SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE), 7);
		cout << (i < size - 1 ? ", " : "\n");
	}
}
void imprimir(int v[], int size, int s1, int s2, int ini, int fin) {
	for (int i = 0; i < size; i++) {
		if (i == s1)
			SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE), 9);
		else if (i == s2)
			SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE), 12);
		else if ((ini != -1 && i <= ini) || (fin != -1 && i >= fin))
			SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE), 10);
		cout << v[i];
		SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE), 7);
		cout << (i < size - 1 ? ", " : "\n");
	}
}
void imprimir(int v[], int size, int s1, int s2, int m) {
	for (int i = 0; i < size; i++) {
		if (i == s1)
			SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE), i >= m ? 249 : 9);
		else if (i == s2)
			SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE), i >= m ? 252 : 12);
		else if (i >= m)
			SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE), 240);
		cout << v[i];
		SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE), 7);
		cout << (i < size - 1 ? ", " : "\n");
	}
}
void imprimir(int v[], int size, int s1, int s2, int str, int end, int super) {
	for (int i = 0; i < size; i++) {
		bool dentro = (i >= str && i <= end);
		if (i == s1)
			SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE), dentro?249:9);
		else if (i == s2)
			SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE), dentro?252:12);
		else if(i == super)
			SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE), dentro?250:10);
		else if (dentro)
			SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE), 240);
		cout << v[i];
		SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE), 7);
		cout << (i < size - 1 ? ", " : "\n");
	}
}

void AlgoritmosOrdenacion::ordenaBurbuja(int v[], int size)
{
	int aux;
	for (int i = size - 2; i >= 0; i--) {
		cout << "\nInicio una pasada desde 0 hasta " << (i+1) << "\n";
		for (int j = 0; j <= i; j++) {
			if (v[j] > v[j + 1]) {
				imprimir(v, size, j, j + 1, true, i+2, true);
				swap(v[j], v[j + 1]);
			}else {
				imprimir(v, size, j, j + 1, false, i + 2, true);
			}
		}
	}
}

void AlgoritmosOrdenacion::ordenaInsercion(int v[], int size)
{
	int x, j;
	for (int i = 1; i < size; i++) {
		cout << "\nPasada desde 1 hasta " << (size-1) << "\n";
		x = v[i];
		j = i - 1;
		while (j >= 0 && x < v[j]) {
			v[j + 1] = v[j];
			j--;
		}
		v[j + 1] = x;
		imprimir(v, size, -1, j + 1, true, i-1, false);
	}
}

void AlgoritmosOrdenacion::ordenaSeleccion(int v[], int size)
{
	int posMin, aux;
	for (int i = 0; i < size - 2; i++) {
		posMin = i;
		cout << "\nPasada desde " << (i + 1) << " hasta " << (size - 1) << "\n";
		for (int j = i + 1; j < size; j++) {
			if (v[j] < v[posMin])
				posMin = j;
		}
		imprimir(v, size, posMin, i, true, i - 1, false);
		swap(v[posMin], v[i]);
	}
}

void AlgoritmosOrdenacion::ordenaShell(int v[], int size)
{
	for (int h = size / 2; h > 0; h /= 2) {
		for (int i = h; i < size; i++) {
			cout << "\nPasada desde " << h << " hasta " << (size - 1) << " con distancia " << h << "\n";
			int j = i;
			int t = v[i];
			while (j >= h && t < v[j - h]) {
				v[j] = v[j - h];
				j -= h;
			}
			v[j] = t;
			imprimir(v, size, i, j==i?i-h:j, j!=i, -1, false);
		}
	}
}

void AlgoritmosOrdenacion::ordenaMergesort(int v[], int size)
{
	mergesort(v, 0, size - 1);
}
void AlgoritmosOrdenacion::mergesort(int v[], int e, int d)
{
	if (e < d) {
		int m = (e + d) / 2;
		if (m != 0) {
			cout << "\nMando ordenar desde " << e << " hasta " << m << "\n";
			mergesort(v, e, m);
			cout << "Mando ordenar desde "<< (m + 1) << " hasta " << d << "\n";
			mergesort(v, m + 1, d);
		}
		cout << "Mando unir los subvectores " << e << ", " << m << " y " << (m + 1) << ", " << d << "\n";
		merge(v, e, m, d);
	}
}
void AlgoritmosOrdenacion::merge(int v[], int e, int m, int d)
{
	int *B = new int[d - e + 1];

	int i = e;
	int j = m + 1;
	int k = 0;
	while (i <= m && j <= d) {
		if (v[i] < v[j]) {
			B[k++] = v[i++];
			imprimir(v, d - e + 1, i, j, m + 1);
		}
		else {
			B[k++] = v[j++];
			imprimir(v, d - e + 1, j, i, m + 1);
		}
		cout << "Tabla temporal: ";
		imprimir(B, k);
	}
	while (i <= m) {
		B[k++] = v[i++];
		imprimir(v, d - e + 1, i, j, m + 1);
		cout << "Tabla temporal: ";
		imprimir(B, k);
	}
	while (j <= d) {
		B[k++] = v[j++];
		imprimir(v, d - e + 1, j, i, m + 1);
		cout << "Tabla temporal: ";
		imprimir(B, k);
	}

	cout << "Volcando la tabla temporal.\n\n";
	for (k = 0; k <= d - e; ++k)
		v[e + k] = B[k];

	delete[] B;
}

int gSize;
void AlgoritmosOrdenacion::ordenaQuicksort(int v[], int size)
{
	gSize = size;
	quicksort(v, 0, size - 1);
}
void AlgoritmosOrdenacion::quicksort(int v[], int e, int d) {
	if (e < d) {
		cout << "\nMando particionar de " << e << " hasta " << d << "\n";
		int q = partition(v, e, d);
		cout << "Llamo a quicksort desde " << e << " hasta " << q << "\n";
		quicksort(v, e, q);
		cout << "Llamo a quicksort desde " << (q+1) << " hasta " << d << "\n";
		quicksort(v, q + 1, d);
	}
}
int AlgoritmosOrdenacion::partition(int v[], int e, int d) {
	int x = v[e];
	int i = e - 1;
	int j = d + 1;
	while (true) {
		while (x < v[--j]);
		while (v[++i] < x);
		if (i >= j) {
			cout << "Division: ";
			cout << "i " << i << "  j " << j << "\n";
			imprimir(v, gSize, -1, -1, e, d, j);
			return j;
		}
		cout << "Intercambio: " << i << " a " << j << ": ";
		imprimir(v, gSize, i, j, e, d, -1);
		swap(v[i], v[j]);
	}
}

void AlgoritmosOrdenacion::ordenaShakesort(int v[], int size)
{
	for (int i = 0; i < size / 2; i++) {
		bool desordenado = false;
		cout << "\nPasada desde " << i << " hasta " << (size - i-1) << " con incremento\n";
		for (int j = i; j < size - i - 1; j++) {
			if (v[j] > v[j + 1]) {
				imprimir(v, size, j, j+1, i - 1, size - i);
				swap(v[j], v[j + 1]);
				desordenado = true;
			}
		}
		cout << "\nPasada desde " << (size - 2) << " hasta " << (i + 1) << " con decremento\n";
		for (int j = size - 2 - i; j > i; j--) {
			if (v[j] < v[j - 1]) {
				imprimir(v, size, j, j + 1, i, size - i);
				swap(v[j], v[j - 1]);
				desordenado = true;
			}
		}
		if (!desordenado) break;
	}
}

void AlgoritmosOrdenacion::ordenaBricksort(int v[], int size)
{
	bool ordenado = false;

	while (!ordenado) {
		ordenado = true;

		cout << "\nPasada desde " << 1 << " hasta " << (size - 2) << " con los impares\n";
		for (int i = 1; i <= size - 2; i = i + 2) {
			if (v[i] > v[i + 1]) {
				imprimir(v, size, i, i + 1, true, -1, false);
				swap(v[i], v[i + 1]);
				ordenado = false;
			}else
				imprimir(v, size, i, i + 1, false, -1, false);
		}
		cout << "\nPasada desde " << 0 << " hasta " << (size - 2) << " con los pares\n";
		for (int i = 0; i <= size - 2; i = i + 2) {
			if (v[i] > v[i + 1]) {
				imprimir(v, size, i, i + 1, true, -1, false);
				swap(v[i], v[i + 1]);
				ordenado = false;
			}else
				imprimir(v, size, i, i + 1, false, -1, false);
		}
	}
}
#endif



