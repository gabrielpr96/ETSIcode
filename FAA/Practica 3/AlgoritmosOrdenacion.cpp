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


using namespace std;

AlgoritmosOrdenacion :: AlgoritmosOrdenacion() {}
AlgoritmosOrdenacion :: ~AlgoritmosOrdenacion(){}

void AlgoritmosOrdenacion::swap(int &a, int &b) {
	int tmp = a;
	a = b;
	b = tmp;
}


// Algoritmos de ordenacion tal cual
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

void AlgoritmosOrdenacion::mergesort(int v[], int e, int d)
{
	if (e < d) {
		int m = (e + d) / 2;
		mergesort(v, e, m);
		mergesort(v, m + 1, d);
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

void AlgoritmosOrdenacion::quicksort(int v[], int e, int d) {
	if (e < d) {
		int q = partition(v, e, d);
		quicksort(v, e, q);
		quicksort(v, q+1, d);
	}
}

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