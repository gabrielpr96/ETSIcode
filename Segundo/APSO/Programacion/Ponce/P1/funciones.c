#include <stdio.h>
#include "funciones.h"

int minimo(const int arr[], int n){
	int min = arr[0], i;
	for(i = 1; i < n; i++)
		if(arr[i] < min)
			min = arr[i];
	return min;
}
int maximo(const int arr[], int n){
	int max = arr[0], i;
	for(i = 1; i < n; i++)
		if(arr[i] > max)
			max = arr[i];
	return max;
}
float media(const int arr[], int n){
	int sum = 0, i;
	for(i = 0; i < n; i++)
		sum += arr[i];
	return sum/(float)n;
}
